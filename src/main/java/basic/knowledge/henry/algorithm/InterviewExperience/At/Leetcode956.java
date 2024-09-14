package basic.knowledge.henry.algorithm.InterviewExperience.At;

public class Leetcode956 {
    public static void main(String[] args) {
        Leetcode956 leetcode956 = new Leetcode956();
        int i = leetcode956.tallestBillboard(new int[]{2,4,8,16});
        System.out.println(i);
    }

    int maxHeight = 0;

    public int tallestBillboard_2(int[] rods) {
        helper(rods, 0, 0, 0);
        return maxHeight;    }

    private void helper(int[] rods, int i, int height1, int height2) {
        if (height1 == height2)
            maxHeight = Math.max(maxHeight, height1);

        if (i >= rods.length)
            return;

        helper(rods, i + 1, height1 + rods[i], height2);
        helper(rods, i + 1, height1, height2 + rods[i]);
        helper(rods, i + 1, height1, height2);
    }


    /**
     *
     * 其中 dp[i][j] 表示从前i个数字中选出数字组成两组（组0和组1，这里假设组0数字之和一定小于组1），此时的二者中的较大长度，其实也就是组1的数字之和，并且j表示二组数字之和的差值。
     * 将 rod[i] 加入组1时，由于组1的数字和大，所以增加新数字会拉大两组原本的差值，若加入之后的差值为j，则加入之前则为 j-rods[i]，所以可以用 dp[i-1][j-rods[i]] + rods[i] 来更新 dp[i][j]。
     *
     * 将将 rod[i] 加入组0时，且加入之后组0的数字之和仍小于组1，但此时二者的差距变小了，若加入之后的差值为j，则加入之前则为 j+rods[i]，所以可以用 dp[i-1][j+rods[i]] 来更新 dp[i][j]。
     *
     * 将将 rod[i] 加入组0时，且加入之后组0的数字之和超过了组1，说明这个新数字要大于原本两个组之间的差值，若加入之后的差值为j，则加入之前则为 rods[i]-j，所以可以用 dp[i-1][rods[i]-j] + j 来更新 dp[i][j]
     * @param rods
     * @return
     */
    public int tallestBillboard(int[] rods) {
        int n = rods.length;
        int[][] dp = new int[n][5001];
        for (int i = 0; i <= 5000; i++) {
            if(i == rods[0]){
                dp[0][i] = rods[0];
            }else{
                dp[0][i] = Integer.MIN_VALUE;
            }
        }
        dp[0][0] = 0;
//        dp[0][0] = 0;
        for(int i = 1;i< n;i++){
            for(int j = 0;j<= 5000;j++){
                dp[i][j] = dp[i - 1][j];// not used at all

                if(j >= rods[i]){
                    dp[i][j] = Math.max(dp[i][j],dp[i - 1][j - rods[i]] + rods[i]);
                }else{
                    dp[i][j] = Math.max(dp[i][j],dp[i - 1][rods[i] - j] + j);
                }

                if (j + rods[i] <= 5000) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j + rods[i]]);
                }
            }
        }

        /**
         * dp[i][j] = dp[i - 1][j];
         *         		if (j >= rods[i - 1]) {
         *         			dp[i][j] = max(dp[i][j], dp[i - 1][j - rods[i - 1]] + rods[i - 1]);
         *                                } else {
         *         			dp[i][j] = max(dp[i][j], dp[i - 1][rods[i - 1] - j] + j);
         *                }
         *         		if (j + rods[i - 1] <= 5000) {
         *         			dp[i][j] = max(dp[i][j], dp[i - 1][j + rods[i - 1]]);
         *                }
         */

        return dp[n - 1][0];

    }


}
