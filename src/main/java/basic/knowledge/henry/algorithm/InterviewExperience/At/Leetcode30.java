package basic.knowledge.henry.algorithm.InterviewExperience.At;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode30 {
    public static void main(String[] args) {
        String s = "barfoofoobarthefoobarman";
        String[] words = new String[]{"foo","bar","foo"};
        List<Integer> substring = new Leetcode30().findSubstring(s, words);
        System.out.println(substring);
    }
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return result;

        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        //You are given a string s and an array of strings words.
        // All the strings of words are of the same length.
        int wordLength = words[0].length();
        int numWords = words.length;
        int totalLength = wordLength * numWords;

        for (int i = 0; i < wordLength; i++) {
            int left = i, right = i;
            Map<String, Integer> seenWords = new HashMap<>();
            while (right + wordLength <= s.length()) {
                String word = s.substring(right, right + wordLength);
                right += wordLength;//没必要一个个的增加，所以每次增加word的长度

                if (!wordCount.containsKey(word)) {
                    seenWords.clear();
                    left = right;
                    continue;
                }
                //此时加进来的word绝对是words 里的
                seenWords.put(word, seenWords.getOrDefault(word, 0) + 1);

                /**
                 * sliding window逻辑
                 */
                while (seenWords.get(word) > wordCount.get(word)) {
                    String leftWord = s.substring(left, left + wordLength);
                    seenWords.put(leftWord, seenWords.get(leftWord) - 1);
                    if (seenWords.get(leftWord) == 0) {
                        seenWords.remove(leftWord);
                    }
                    left += wordLength;
                }
                //为什么这个判断足够，
                //因为right - left 里的所有word都是list里的 且里面所有的word 的次数都是符合wordCount的
                if (right - left == totalLength) {
                    result.add(left);
                }
            }
        }

        return result;
    }
}
