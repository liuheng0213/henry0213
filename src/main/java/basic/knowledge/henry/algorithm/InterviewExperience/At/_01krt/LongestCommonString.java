package basic.knowledge.henry.algorithm.InterviewExperience.At._01krt;

public class LongestCommonString {

    public static void main(String[] args) {
        LongestCommonString longestCommonString = new LongestCommonString();
        String X = "1AB2345ECD";
        String Y = "12345EF";

        int res = longestCommonString.longestSubstring(X, Y);
        System.out.println(res);
    }

    public int longestSubstring(String str1,String str2){
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m+1][n+1];
        int max = 0;
        for(int i = 1;i< m+1;i++){
            for(int j = 1;j< n + 1;j++ ){
                dp[i][j] = str1.charAt(i - 1) != str2.charAt(j - 1) ? 0 :dp[i - 1][j - 1] + 1;
                max = Math.max(max,dp[i][j]);
            }
        }

        return max;
    }
}
