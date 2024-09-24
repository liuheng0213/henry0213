package basic.knowledge.henry.algorithm.InterverviewFromRenowedITCompany._09others;

public class _24KMP_dp_third {
    public static void main(String[] args) {
        _24KMP_dp_third kmp = new _24KMP_dp_third();
//        int indexOf = kmp.getIndexOf("abcbccbccb", "bccbccb");
        int[] dp = kmp.getCommonNext("bccbccb");
        int j = kmp.getIndexOf("abccbccbccb", "bccbccb");
        System.out.println(j);
        int i = "abccbccbccb".indexOf("bccb");
        System.out.println(i);
    }

    private int getIndexOf(String str, String pat) {
        int[] common = getCommonNext(pat);
        int n = str.length();
        int[] dp = new int[n];

        dp[0] = (str.charAt(0) == pat.charAt(0)) ? 1 : 0;
        if(dp[0] == 1 && pat.length() == 1){
            return 0;
        }

        for(int i = 1;i< n;i++){
            int j = dp[i - 1];
            while(j> 0 && str.charAt(i) != pat.charAt(j)){
                j = common[j - 1];
            }

            dp[i] = str.charAt(i) == pat.charAt(j) ? j +1 : j;
            if(dp[i] == pat.length()){
                return i - pat.length() +1;
            }
        }

        return -1;
    }

    private int[] getCommonNext(String pat) {
        int[] dp = new int[pat.length()];
        dp[0] = 0;
        for(int i = 1;i< pat.length();i++){
            int j = dp[i - 1];
            while(j> 0 && pat.charAt(j) != pat.charAt(i)){
                j = dp[j - 1];
            }

            dp[i] = pat.charAt(j) == pat.charAt(i) ? j +1 : j;
        }
        return dp;
    }


}
