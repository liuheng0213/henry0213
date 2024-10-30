package basic.knowledge.henry.algorithm.InterviewExperience.rk;

public class Leetcode242 {
    public static void main(String[] args) {
        String s = "aabc";
        String t = "abc";
        Leetcode242 leetcode242 = new Leetcode242();

        System.out.println(leetcode242.isAnagram2(s,t) == leetcode242.isAnagram1(s,t));
    }

    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26];  // Array to store count of each character

        for (char c : s.toCharArray()) {
            count[c - 'a']++;  // Increment the count for characters in string `s`
        }

        for (char c : t.toCharArray()) {
            if (count[c - 'a'] == 0) {
                return false;  // If character count is zero, it's not an anagram
            }
            count[c - 'a']--;  // Decrement the count for characters in string `t`
        }

        return true;
    }


    public boolean isAnagram1(String s, String t) {
        int[] count1 = encode(s);
        int[] count2 = encode(t);
        // 确保两个字符串中所有字符出现的数量相同
        for (int i = 0; i < count1.length; i++) {
            if (count1[i] != count2[i]) {
                return false;
            }
        }

        return true;
    }

    // 计算字符的出现次数
    int[] encode(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            int delta = c - 'a';
            count[delta]++;
        }
        return count;
    }
}
