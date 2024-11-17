package basic.knowledge.henry.algorithm.InterviewExperience.ama;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

//dominance  prefix
public class Test10 {
    public static void main(String[] args) {
        List<String> words1 = Arrays.asList("abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce");
        List<String> words2 = Arrays.asList("abba", "aabb", "abab");


        String[] arr1 = new String[]{"abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce","abc", "abd", "acc", "bcd", "bce"};
        String[] arr2 = new String[]{"abba", "aabb", "abab"};



        List<Integer> result = getDominantPrefixLengths(words1);
        List<Integer> result2 = new Test10().getDominantPrefixLengths(arr1);
        System.out.println(result); // Output the result
        System.out.println(result2); // Output the result
    }

    public  List<Integer> getDominantPrefixLengths(String[] words) {
        long start= System.currentTimeMillis();
        List<Integer> res = new ArrayList<>();
        Trie trie = new Trie();
        for(String w : words){
            trie.insert(w);
        }
        HashSet<String> marked;
        int m = words[0].length();
        int max;
        for(int len = 1;len<=m;len++){
            marked = new HashSet<>();
            max = 1;
            for(String w: words){
                String pre = w.substring(0,len);
                if(marked.contains(pre)){
                    continue;
                }
                marked.add(pre);
                int preNum = trie.preNum(pre);
                if(preNum > max){
                    max = preNum;
                }
            }
            res.add(max);
        }
        long end= System.currentTimeMillis();
        System.out.println(end- start);
        return res;
    }
    public static List<Integer> getDominantPrefixLengths(List<String> words) {
        long start= System.currentTimeMillis();
        int N = words.size();
        int M = words.get(0).length();

        // Result array initialized to 1 (at least one string has each prefix)
        int[] ans = new int[M];
        Arrays.fill(ans, 1);

        // Helper function for MSD Radix Sort
        class MSDRadixSort {
            void sort(int l, int r, int i) {
                if (r - l + 1 <= 1) return; // ans[i:] is already at least one

                if (i > 0) ans[i - 1] = Math.max(ans[i - 1], r - l + 1); // update ans
                if (i == M) return;

                // Create buckets for further splitting strings by prefix length i+1
                List<List<String>> buckets = new ArrayList<>(26);
                for (int j = 0; j < 26; j++) {
                    buckets.add(new ArrayList<>());
                }

                for (int k = l; k <= r; k++) {
                    String word = words.get(k);
                    int o = word.charAt(i) - 'a';
                    buckets.get(o).add(word);
                }

                int idx = l;
                for (List<String> bucket : buckets) {
                    for (String word : bucket) {
                        words.set(idx, word);
                        idx++;
                    }
                    if (!bucket.isEmpty()) {
                        sort(l, idx - 1, i + 1); // Recursive sort on this bucket
                        l = idx;
                    }
                }
            }
        }

        new MSDRadixSort().sort(0, N - 1, 0); // Begin the sort with all strings and the empty prefix
        List<Integer> result = new ArrayList<>();
        for (int value : ans) {
            result.add(value);
        }
        long end= System.currentTimeMillis();
        System.out.println(end- start);
        return result;
    }

    class Trie{
        Node root;

        public Trie(){
            this.root = new Node();
        }


        public void insert(String str){
            char[] chs = str.toCharArray();
            Node cur = this.root;
            for(int i =0;i< chs.length;i++){
                char ch = chs[i];
                if(cur.children[ch - 'a'] == null){
                    cur.children[ch - 'a'] = new Node();
                }
                cur = cur.children[ch-'a'];
                cur.count++;
            }
            cur.end = true;
        }


        public int preNum(String prefix){
            char[] chs = prefix.toCharArray();
            Node cur = this.root;
            for(int i =0;i< chs.length;i++){
                char ch = chs[i];
                if(cur.children[ch - 'a'] == null){
                    return 0;
                }
                cur = cur.children[ch-'a'];
            }
           return cur.count;
        }
    }

    class Node{
        int count;
        boolean end;

        Node[] children;

        public Node(){
            this.count = 0;
            children = new Node[26];
        }
    }
}
