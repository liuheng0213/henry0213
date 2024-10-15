package basic.knowledge.henry.algorithm.InterviewExperience.At;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Leetcode3026 {
    public static void main(String[] args) {
        Leetcode3026 leetcode3026 = new Leetcode3026();
        long l = leetcode3026.maximumSubarraySum(new int[]{1,5}, 2);
        System.out.println(l);
    }

    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] preSum = new long[n + 1];

        for(int i =1;i< n + 1;i++){
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        HashMap<Integer, List<Integer>> mark = new HashMap<>();
        Long res = Long.MIN_VALUE;
        for(int i = 0;i< n;i++){
            if(mark.containsKey(nums[i] - k)){
                for(int idx : mark.get(nums[i] - k)){
                    res = Math.max(res,preSum[i+1] - preSum[idx]);
                }
            }

            if(mark.containsKey(nums[i] + k)){
                for(int idx : mark.get(nums[i] + k)){
                    res = Math.max(res,preSum[i+1] - preSum[idx]);
                }
            }

            if(mark.containsKey(nums[i])){
                mark.get(nums[i]).add(i);
            }else{
                List<Integer> sub = new ArrayList<>();
                sub.add(i);
                mark.put(nums[i],sub);
            }
        }
        return res == Long.MIN_VALUE? 0 : res;
    }
}
