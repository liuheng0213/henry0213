package basic.knowledge.henry.algorithm.InterverviewFromRenowedITCompany._09others;

public class _24KMP_including_dp_best {
    public static void main(String[] args) {
        _24KMP_including_dp_best kmp = new _24KMP_including_dp_best();
        int indexOf = kmp.getIndexOf("abcbcc", "bcc");
        System.out.println(indexOf);

        int i = "abcbcc".indexOf("bcc");
        System.out.println(i);

    }

    public int getIndexOf(String str, String pat) {
        int n = str.length();
        //dp[i] means the string ends with index i in the str has how many matching letters with pat
        int[] dp = new int[n];
        dp[0] = (str.charAt(0) == pat.charAt(0)) ? 1 : 0;
        if(dp[0] == 1 && pat.length() == 1){
            return 0;
        }

        int[] next = getNext(pat.toCharArray());
        for(int i = 1;i< n;i++){
            int j = dp[i - 1];
            // must specify j== pat.length() as a edge condition which also means two characters are not equal and
            // als means shrinking j is necessary
            while(j== pat.length() || j> 0 && str.charAt(i)!=pat.charAt(j)){
                j = next[j - 1];
            }

            dp[i] = j + (str.charAt(i)==pat.charAt(j) ? 1 : 0);

            if(dp[i] == pat.length()){
                return i - pat.length() + 1;
            }
        }

        return -1;


    }

    private int[] getNext(char[] chp) {
       int n = chp.length;
        //dp[i] means s ends with index i has the longest common sub string of number dp[i]
       int[] dp = new int[n];

       for(int i = 1;i< n;i++){
          int j=dp[i - 1];
          while(j> 0 && chp[j] != chp[i]){
              j = chp[i - 1];
          }

          dp[i] = j + (chp[j] == chp[i] ? 1 : 0);
       }

       return dp;

    }
}
