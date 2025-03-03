package basic.knowledge.henry.algorithm.InterviewExperience.gs.jump_game_related;

import java.util.Arrays;
import java.util.HashSet;

public class Leetcode1306 {
    public static void main(String[] args) {
        Leetcode1306 leetcode1306 = new Leetcode1306();
        boolean b = leetcode1306.canReach(new int[]{3,0,2,1,2}, 2);
        System.out.println(b);
    }
    int[] dp;

    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        dp = new int[n];
        Arrays.fill(dp,-1);
        int val =  dfs(start,arr);
        if(val == 0){
            return false;
        }else {
            return true;
        }

    }


    private int dfs(int cur,int[] arr){

        if(cur > arr.length - 1 || cur < 0){
            return 0;
        }
        if(dp[cur] != -1){
            return dp[cur];
        }
        if(arr[cur] == 0){
            dp[cur] = 1;
            return 1;
        }

        //iniailiza
        dp[cur] = 0;

        int right = dfs(cur + arr[cur],arr);
        int left = dfs(cur - arr[cur],arr);
        if(left == 1 || right == 1){
            dp[cur] = 1;
            return 1;
        }else{
            dp[cur] = 0;
        }


        return dp[cur];
    }
}
