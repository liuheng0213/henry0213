package basic.knowledge.henry.algorithm.InterviewExperience.At.tagpopularity;


import java.util.*;

/**
 * Implement a system to track the popularity of content based on user interactions (thumbs up or thumbs down). Your task is to design an interface called ContentPopularity with the following methods:
 * 1. increasePopularity(contentId: int) -> None: Increases the popularity of the specified content ID by one (representing a thumbs up).
 * 2. decreasePopularity(contentId: int) -> None: Decreases the popularity of the specified content ID by one (representing a thumbs down).
 * 3. mostPopular() -> int: Returns the content ID with the highest popularity. If there are ties, return any one of them. -1 if no content.
 *
 *
 * 复制代码
 * 大致的想法是要維護一個doubly linkedlist + HashMap (如果要接近O(1) time complexity)
 * 我選擇用TreeMap + HashMap (O(logN) time complexity)
 * 面試官說只要比O(N)好就可以
 * 寫完之後會問time complexity
 * 然後問說如果call mostPopular()時, 有好幾個contentId平手, 要return最早被increasePopularity的那個要怎麼做, 提到可以用linkedHashMap來maintain orders
 * -----------------------------------------------
 */
public class ContentPopularity {
    HashMap<String,Integer> keyToFreq;
    TreeMap<Integer, LinkedList<String>> freqToKeys;
    public ContentPopularity() {
        keyToFreq = new HashMap<>();
        freqToKeys = new TreeMap<>();
    }

    public void increasePopularity(String key) {
        int freq = keyToFreq.getOrDefault(key, 0);
        keyToFreq.put(key,freq + 1);

        LinkedList<String> existingkeys = freqToKeys.getOrDefault(freq, new LinkedList<>());
        existingkeys.remove(key);
        if(existingkeys.isEmpty()){
            freqToKeys.remove(freq);
        }

        freqToKeys.putIfAbsent(freq+1, new LinkedList<>());
        freqToKeys.get(freq + 1).add(key);
    }

    public void decreasePopularity(String key) {
        int freq = keyToFreq.getOrDefault(key, 0);
        if(freq == 0){
            throw new RuntimeException("wrong operation");
        }

        if(freq == 1){
            keyToFreq.remove(key);
        }else{
            keyToFreq.put(key,freq - 1);
        }

        LinkedList<String> existingkeys = freqToKeys.getOrDefault(freq, new LinkedList<>());
        existingkeys.remove(key);
        if(existingkeys.isEmpty()){
            freqToKeys.remove(freq);
        }

        if(freq >1){
            freqToKeys.putIfAbsent(freq-1, new LinkedList<>());
            freqToKeys.get(freq - 1).add(key);
        }

    }

    public String mostPopular() {
        if(freqToKeys.isEmpty()){
            return "";
        }
        return freqToKeys.get(freqToKeys.lastKey()).iterator().next();
    }

    public String getMinKey() {
        if(freqToKeys.isEmpty()){
            return "";
        }
        return freqToKeys.get(freqToKeys.firstKey()).iterator().next();
    }
}
