package basic.knowledge.henry.algorithm.test.solution;

import java.util.*;

class Solution {
    public static void main(String[] args) {
        int i = new Solution().jobScheduling(new int[]{1, 2, 3, 4, 6}, new int[]{3, 5, 10, 6, 9}, new int[]{20, 20, 100, 70, 60});
        System.out.println(i);
    }
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = profit.length;
        int[][] jobs = new int[n][3];
        for(int i =0;i< n;i++){
            jobs[i] = new int[]{startTime[i],endTime[i],profit[i]};
        }

        //sorting is necessary, otherwise it may get the largest end firstly and the following job are all overlapped and cannot be updated
        Arrays.sort(jobs,(a,b)->a[1]-b[1]);

        TreeMap<Integer,Integer> dp = new TreeMap<>();
        dp.put(0,0);
        // 定义：在 0 到 i 这个时间区间内，最多能够获得的利润是 dp[i],
        for(int[] job : jobs){
            int start = job[0];
            int end = job[1];
            int value = job[2];

            dp.put(end,Math.max(dp.floorEntry(start).getValue() + value,dp.lastEntry().getValue()));
        }

        return dp.lastEntry().getValue();


    }
}



/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */