package basic.knowledge.henry.algorithm.InterviewExperience.gs;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Leetcode1235 {

    public static void main(String[] args) {
        TreeMap<Integer, Integer> objectObjectTreeMap = new TreeMap<>();
        objectObjectTreeMap.put(1,1);
        objectObjectTreeMap.put(2,1);
        Map.Entry<Integer, Integer> integerIntegerEntry = objectObjectTreeMap.floorEntry(2);
        System.out.println(integerIntegerEntry.getKey());
        Leetcode1235 leetcode1235 = new Leetcode1235();

        int i = leetcode1235.jobScheduling(new int[]{1, 2,3,4,6}, new int[]{3, 5,10,6,9}, new int[]{20, 20,100,70,60});
        System.out.println(i);

    }


    // return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = profit.length;
        int[][] jobs = new int[n][3];
        for(int i =0;i< n;i++){
            jobs[i] = new int[]{startTime[i],endTime[i],profit[i]};
        }
        Arrays.sort(jobs,(a, b)->a[1]-b[1]);

        TreeMap<Integer,Integer> dp = new TreeMap<>();
        dp.put(0,0);
        // 定义：在 0 到 i 这个时间区间内，最多能够获得的利润是 dp[i]
        for(int[] job : jobs){
            int start = job[0];
            int end = job[1];
            int value = job[2];
            int i = dp.floorEntry(start).getValue() + value;
            int j = dp.lastEntry().getValue();
            dp.put(end,Math.max(i,j));
        }

        return dp.lastEntry().getValue();
    }
}
