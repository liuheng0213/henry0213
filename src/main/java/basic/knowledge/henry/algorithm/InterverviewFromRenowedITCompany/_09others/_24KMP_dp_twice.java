package basic.knowledge.henry.algorithm.InterverviewFromRenowedITCompany._09others;

public class _24KMP_dp_twice {
    public static void main(String[] args) {
        _24KMP_dp_twice kmp = new _24KMP_dp_twice();
//        int indexOf = kmp.getIndexOf("abcbccbccb", "bccbccb");
        int[] dp = kmp.getCommonNext("bccbccb");
        int j = kmp.getIndexOf("abccbccbccb", "bccbccb");
        System.out.println(j);
        int i = "abccbccbccb".indexOf("bccbccb");
        System.out.println(i);

    }

    private int getIndexOf(String str, String pat) {
        int n = str.length();
        int[] dp = new int[n];
        dp[0] = (str.charAt(0) == pat.charAt(0)) ? 1 : 0;
        if(str.charAt(0) == pat.charAt(0) && pat.length() == 1){
            return 0;
        }
        int[] nexts = getCommonNext(pat);
        for(int i = 1;i< n;i++){
            int j = dp[i - 1];
            while(j == pat.length() || j > 0 && str.charAt(i) != pat.charAt(j)){
                j = nexts[j - 1];
            }

            dp[i] = j + (str.charAt(i)==pat.charAt(j) ? 1 : 0);

            if(dp[i] == pat.length()){
                return i - pat.length() + 1;
            }
        }

        return -1;
    }

    private int[] getCommonNext(String s){
        int n = s.length();
        int[] dp = new int[n];

        for(int i = 1;i< n;i++){
            int j = dp[i - 1];
            while(j > 0 && s.charAt(j) != s.charAt(i)){
                j = dp[j - 1];
            }
            dp[i] = j + (s.charAt(j) == s.charAt(i) ? 1: 0);
        }

        return dp;
    }


}
