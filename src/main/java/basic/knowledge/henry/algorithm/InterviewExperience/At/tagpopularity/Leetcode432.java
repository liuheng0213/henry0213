package basic.knowledge.henry.algorithm.InterviewExperience.At.tagpopularity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class Leetcode432 {
    public static void main(String[] args) {
        Leetcode432 leetcode432 = new Leetcode432();
        leetcode432.inc("a");
        leetcode432.inc("b");
        leetcode432.inc("c");
        leetcode432.inc("d");
        leetcode432.inc("e");
        leetcode432.inc("f");
        leetcode432.inc("g");
        leetcode432.inc("h");
        leetcode432.inc("i");
        leetcode432.inc("j");
        leetcode432.inc("k");
        leetcode432.inc("l");
        leetcode432.dec("a");
        leetcode432.dec("b");
        leetcode432.dec("c");
        leetcode432.dec("d");
        leetcode432.dec("e");
        leetcode432.dec("f");
        leetcode432.inc("g");
        leetcode432.inc("h");
        leetcode432.inc("i");
        leetcode432.inc("j");

        String maxKey = leetcode432.getMaxKey();
        String minKey = leetcode432.getMinKey();
        System.out.println(maxKey);
        System.out.println(minKey);
        leetcode432.inc("k");
        leetcode432.inc("l");

        String maxKey1 = leetcode432.getMaxKey();
        String minKey1 = leetcode432.getMinKey();
        System.out.println(maxKey1);
        System.out.println(minKey1);
        leetcode432.inc("a");
        leetcode432.dec("j");

        String maxKey2 = leetcode432.getMaxKey();
        String minKey2 = leetcode432.getMinKey();
        System.out.println(maxKey2);
        System.out.println(minKey2);

    }


    HashMap<String,Integer> keyToFreq;
    TreeMap<Integer,HashSet<String>> freqToKeys;
    public Leetcode432() {
        keyToFreq = new HashMap<>();
        freqToKeys = new TreeMap<>();
    }

    public void inc(String key) {
        int freq = keyToFreq.getOrDefault(key,0);
        keyToFreq.put(key,freq + 1);

        HashSet<String> list = freqToKeys.getOrDefault(freq,new HashSet<>());
        list.remove(key);
        if(list.isEmpty()){
            freqToKeys.remove(freq);
        }

        freqToKeys.putIfAbsent(freq + 1, new HashSet<>());
        freqToKeys.get(freq + 1).add(key);


    }

    public void dec(String key) {
        int freq = keyToFreq.getOrDefault(key, 0);
        if(freq == 0){
            throw new RuntimeException("wrong operation");
        }

        if(freq == 1){
            keyToFreq.remove(key);
        }else{
            keyToFreq.put(key,freq - 1);
        }


        HashSet<String> list = freqToKeys.get(freq);
        list.remove(key);
        if(list.isEmpty()){
            freqToKeys.remove(freq);
        }
        if(freq - 1 > 0){
            freqToKeys.putIfAbsent(freq - 1, new HashSet<>());
            freqToKeys.get(freq - 1).add(key);
        }

    }

    public String getMaxKey() {
        if(freqToKeys.isEmpty()){
            return "";
        }
        return freqToKeys.lastEntry().getValue().iterator().next();
    }

    public String getMinKey() {
        if(freqToKeys.isEmpty()){
            return "";
        }
        return freqToKeys.firstEntry().getValue().iterator().next();
    }
}
