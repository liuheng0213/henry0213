package basic.knowledge.henry.algorithm.InterviewExperience.At;

public class Leetcode2851 {
    public static void main(String[] args) {
        Leetcode2851 obj = new Leetcode2851();
        int res = obj.numberOfWays("ababab", "ababab", 1);

        System.out.println(res);
    }

    Long mod = 1000000007L;
    public int numberOfWays(String s, String t, long k) {
        int n = s.length();

        String twoS = s+s;

        String newS =twoS.substring(0,2* n - 1);


        int p = kmpNum(newS,t);
        int f = s.equals(t) ? 0: 1;
        int g = s.equals(t)? 1: 0;
        int[] fg = new int[]{f,g};//f(0)

        long[] matrix = new long[]{n-p-1,n-p,p,p-1};
        long[] matrixRes= calculate(matrix,k);
        return (int)(matrixRes[2] * (long)f%mod) + (int)(matrixRes[3]* (long)g%mod);
    }

    private long[] calculate(long[] matrix, long k) {
        if(k == 0){
            return new long[]{1,0,0,1};
        }
        if(k == 1){
            return matrix;
        }

        long[] a = calculate(matrix,k/2);

        if(k % 2 == 0){
            return muliply(a,a);
        }else{
            return muliply(muliply(a,a),matrix);
        }
    }

    private long[] muliply(long[] a, long[] b) {
        return  new long[]{(a[0] * b[0])%mod+ (a[1] * b[2])%mod , (a[0] * b[1])%mod + (a[1]* b[3])%mod ,(a[2] * b[0])%mod+ (a[3]*b[2])%mod ,(a[2] * b[1])%mod + (a[3]*b[3])%mod};
    }


    public int kmpNum(String str, String pat) {
        int n =str.length();

        //dp[i] means the string ends with index i in the str has how many matching letters with pat
        int[] dp = new int[n];

        int res = 0;
        dp[0] = str.charAt(0) == pat.charAt(0) ? 1: 0;

        if(dp[0] == 1 && pat.length() ==1){
            res++;
        }
        int[] next = getNext(pat);
        for(int i = 1;i< n;i++){
            int j = dp[i - 1];
            while(j> 0  && (j == pat.length() ||str.charAt(i) != pat.charAt(j))){
                j = next[j - 1];
            }
            // j == 0 or str.charAt(i) == pat.charAt(j)
            dp[i] = j< pat.length() && str.charAt(i) == pat.charAt(j)?  j+1 : j;

            if(dp[i] == pat.length()){
                res++;
            }
        }

        return res;

    }


    private int[] getNext(String s) {
        int n = s.length();
        int[] next = new int[n];
        int i = 1, j = 0;
        while (i < n) {
            if (s.charAt(i) == s.charAt(j)) {
                j++;
                next[i++] = j;
            } else if (j > 0) {
                j = next[j - 1];
            } else {
                i++;
            }
        }
        return next;
    }
}
