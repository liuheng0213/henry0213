package basic.knowledge.henry.algorithm.InterviewExperience.At;

import java.util.Arrays;
import java.util.HashMap;

public class Leetcode1048 {
    public static void main(String[] args) {
        String str1= "pcxbc";
        System.out.println(str1.substring(0,0));
        String str2= "pcxbcf";
        String[] arr = new String[]{"xbc","pcxbcf","xb","cxbc","pcxbc"};

        System.out.println(new Leetcode1048().isPre(str1,str2));
        int res = new Leetcode1048().longestStrChain2(arr);
        System.out.println(res);
    }



    public int longestStrChain2(String[] words) {
        Arrays.sort(words,(a,b)->a.length() - b.length());
        int n = words.length;
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        int res = 1;
        for(int i = 1;i< n;i++){
            int tmp = 1;
            for(int j = 0;j< i;j++){
                boolean isPre = isPre(words[j],words[i]);
                if(isPre && dp[i] < 1 + dp[j]){
                    dp[i] = dp[j] + 1;
                }

            }

            res = Math.max(res,dp[i]);
        }

        return res;
    }

    public boolean isPre(String s1,String s2){
        int l1 = s1.length();
        int l2 = s2.length();

        if(l1 + 1 != l2) return false;

        int first = 0;
        int second = 0;
        while(second<l2){
            if(first < s1.length() && s1.charAt(first) == s2.charAt(second)){
                first++;
                second++;
            }else{
                second++;
            }

            if(second > first+1){
                return false;
            }
        }
        if(first+1 == second) return true;
        else return false;

    }

    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        //key value类型相同用数组做dp,不同用hashmap做dp
        HashMap<String, Integer> dp = new HashMap<>();
        int max_chain = 0;
        for (String word : words) {
            dp.put(word, 1);
            for (int i = 0; i < word.length(); i++) {
                String prev_word = word.substring(0, i) + word.substring(i + 1);
                if (dp.containsKey(prev_word)) {
                    dp.put(word, Math.max(dp.get(word), dp.get(prev_word) + 1));
                }
            }
            max_chain = Math.max(max_chain, dp.get(word));
        }
        return max_chain;
    }


}
