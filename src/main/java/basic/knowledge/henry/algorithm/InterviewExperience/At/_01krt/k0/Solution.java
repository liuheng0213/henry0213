package basic.knowledge.henry.algorithm.InterviewExperience.At._01krt.k0;

public class Solution {
    public static void main(String[] args) {
        String[] words = new String[]{"baby", "referee", "cat", "dada", "dog", "bird", "ax", "baz"};
        String note1 = "bbbblkkjbaby";
        String res = new Solution().solution(words,note1);
        System.out.println(res);
    }

    private String solution(String[] words, String note1) {
        int[] chs = createMetrics(note1);
        for(String w: words){

            boolean flag = true;
            for(int i = 0;i< w.length();i++){
                char ch  = w.charAt(i);
                chs[ch - 'a']--;
                if(chs[ch - 'a'] < 0){
                    flag = false;
                    break;
                }
            }

            if(flag){
                return w;
            }
        }

        return "-";


    }

    private int[] createMetrics(String note1) {
        int[] chs = new int[26];
        for(int i =0;i< note1.length();i++){
            chs[note1.charAt(i) - 'a']++;
        }
        return chs;
    }
}
