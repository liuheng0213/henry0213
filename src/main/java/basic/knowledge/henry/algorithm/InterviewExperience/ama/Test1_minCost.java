package basic.knowledge.henry.algorithm.InterviewExperience.ama;

import java.util.HashMap;
import java.util.Map;

public class Test1_minCost {
    public static void main(String[] args) {
        int minimumCost = new Test1_minCost().getMinimumCost(new int[]{2, 1, 1}, new int[]{1, 2, 3}, 4);
        System.out.println(minimumCost);
    }
    public int getMinimumCost(int[] a, int[] b, int m) {
        // write your code here
        Map<Integer,Integer> idxToCount = new HashMap<>();

        int n = a.length;
        int cost = 0;
        for(int i =0;i< m;i++){
            int min = Integer.MAX_VALUE;
            int minIdx = 0;
            for(int idx =0;idx< n;idx++){
                int j = idxToCount.getOrDefault(idx,1);
                if(a[idx] + (j - 1) * b[idx] < min){
                    min = a[idx] + (j - 1) * b[idx];
                    minIdx = idx;
                }
            }
            idxToCount.put(minIdx,idxToCount.getOrDefault(minIdx,1) + 1);
            cost+=min;
        }

        return cost;
    }

}
