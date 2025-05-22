package basic.knowledge.henry.algorithm.test.solution;

class Solution {
    public static void main(String[] args) {
        int i = new Solution().maxProfit(new int[]{1,2,4});
        System.out.println(i);
    }
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][3][2];

        dp[0][0][0] = 0;
        dp[0][0][1] = -prices[0];//buy
        dp[0][0][0] = 0;
        dp[0][1][1] = -prices[0];//buy
        dp[0][0][0] = 0;
        dp[0][2][1] = -prices[0];//buy
        for(int i =0;i< n;i++){
            dp[i][0][1] = -prices[i];
        }
        // dp[0][2][0] = 0;
        // dp[0][2][1] = -prices[0];//buy
        //when k = 1,i>= 1;k = 2,i>=3;k=3,i>=5. so k<=(i+1)/2
        for(int i =1;i< n;i++){
            for(int k = 1;k< 3&&k<=(i+1)/2;k++){
                dp[i][k][0] = Math.max(dp[i- 1][k][0], dp[i- 1][k-1][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i- 1][k][1], dp[i- 1][k][0] - prices[i]);
            }
        }
        int max = 0;
        for(int k = 0;k< 3;k++){
            max = Math.max(max,dp[n - 1][k][0]);
        }

        return max;
    }
}