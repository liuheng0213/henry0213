package basic.knowledge.henry.algorithm.InterviewExperience.ama;


import java.util.Arrays;

/**
 * I meet a really hard problem in Amazon OA and don't know how to solve it efficiently, can anyone please help?
 *
 * The inputs are a string, integer x and integer y.
 *
 * string is made up of 0, 1 and !, each ! can be either 0 or 1
 *
 * Every subsequence of 01 in the string can produce error x
 *
 * Every subsequence of 10 in the string can produce error y
 *
 * 0<=len(string)<=50000, 0<=x<=50000, 0<=y<=50000
 *
 * Return the minimum error count modulo 10^9.
 *
 *
 * Example:
 *
 * string=01!!, x=2, y=3, there're 2 cases:
 *
 * 0100 => errorCount is 2 + 3*2 = 8
 *
 * 0111 => errorCount is 2*3=6
 * 0110
 * 0101
 * so the result is 6
 *
 *
 * Example 2:
 *
 * string=!!!!, x=2, y=5
 *
 * we can replace all ! to 0 or 1, so there will be no 01 or 10 in the string, the result is 0.
 *
 * 本题和数字字符串转换字母组合这题很像
 */
public class Test25_0507_2025_mincost {
    int M = (int) (1e9) + 7;

    public static void main(String[] args) {
        String str ="101!1";
        int x = 2;
        int y = 3;
//
        str = "!!!!";
        x = 2;
        y = 5;
//
//        str = "01!!";
//        x = 2;
//        y = 3;

        int res = new Test25_0507_2025_mincost().solution(str,x,y);
        System.out.println(res);
    }




    private int solution(String msg, int x, int y) {
//        TreeMap<Integer,Integer> zeroToCount = new TreeMap<>();
        int[] dp = new int[msg.length()];
        //dp[i] or helper means min val from i ~ len - 1
        for(int i =0;i< msg.length();i++){
            dp[i] = Integer.MAX_VALUE;
        }

        //when idx = 0 ;zeroCount = 0 zeroCount means count of zero from 0 ~i
        return helper(dp,0,x,y,0,msg);
    }
    //合理的设计helper是关键
    //在idx-1 已经确定的情况下，idx~len还没确定的情况下 能返回的min cost.这个方法每次实际就加一个字符idx
    private int helper(int[] dp, int zeroCount, int x, int y, int idx,String msg) {
        if(idx == msg.length()){
            return 0;
        }

        if(dp[idx] != Integer.MAX_VALUE){
            return dp[idx];
        }
        int ones = idx - zeroCount;
        if(msg.charAt(idx) == '1'){
            int a = zeroCount * x + helper(dp,zeroCount,x,y,idx+1,msg);
            dp[idx] = a%M;
            return a;
        }else if(msg.charAt(idx) == '0'){
            int b = ones * y + helper(dp,zeroCount+1,x,y,idx + 1,msg);
            dp[idx] = b%M;
            return b;
        }else{
            int a = zeroCount * x + helper(dp,zeroCount,x,y,idx+1,msg);
            int b = ones * y + helper(dp,zeroCount+1,x,y,idx + 1,msg);
            int min = Math.min(a%M,b%M);
            dp[idx] = min;
            return min;
        }
    }



    private int solve2(String s, int x, int y) {
        int n = s.length();
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int minimumErrors = getMinError(s, 0, 0, x, y);
        return minimumErrors;
    }


    // dp[i][j] => minimum errors for first i elements with j number of zeros
    private  int[][] dp;

    public  int getMinError(String s, int index, int zeros, int x, int y) {
        if (index == s.length()) {
            return 0;
        }

        if (dp[index][zeros] != -1) {
            return dp[index][zeros];
        }

        int ones = index - zeros; // number of ones
        int ans = Integer.MAX_VALUE;

        if (s.charAt(index) == '0') {
            ans = getMinError(s, index + 1, zeros + 1, x, y) + ones * y;
        } else if (s.charAt(index) == '1') {
            ans = getMinError(s, index + 1, zeros, x, y) + zeros * x;
        } else {
            ans = Math.min(
                    getMinError(s, index + 1, zeros + 1, x, y) + ones * y,
                    getMinError(s, index + 1, zeros, x, y) + zeros * x
            );
        }

        dp[index][zeros] = ans;
        return ans;
    }




    public int solve(String s, int x, int y) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }

        long[][] dp = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE);
        }
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) == '0' || s.charAt(i - 1) == '!') {
                for (int j = 0; j <= i; j++) {
                    if (dp[i - 1][j] < Long.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + j * y);
                    }
                }
            }
            if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '!') {
                for (int j = 1; j <= i; j++) {
                    if (dp[i - 1][j - 1] < Long.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + x * (i - j));
                    }
                }
            }
        }

        long min = Long.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            min = Math.min(min, dp[n][i]);
        }

        return (int) min;
    }



}
