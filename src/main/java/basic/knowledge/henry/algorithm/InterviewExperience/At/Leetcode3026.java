package basic.knowledge.henry.algorithm.InterviewExperience.At;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class Leetcode3026 {
    public static void main(String[] args) {
        Leetcode3026 leetcode3026 = new Leetcode3026();
        long l = leetcode3026.maximumSubarraySum_better(new int[]{-1,-2,-3,-4}, 2);
        System.out.println(l);
    }

    /**
     * 下面的solution will get time limit error
     * @param nums
     * @param k
     * @return
     */
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

    Map<Integer, Queue<Integer>> map = new HashMap<>();

    /**
     * beat 5.8%
     * @param nums
     * @param k
     * @return
     */
    public long maximumSubarraySum_better(int[] nums, int k) {
        Map<Integer, Queue<Integer>> map = new HashMap<>();
        int n = nums.length;
        long[] sum = new long[n];
        for (int i = 0; i < n; i++) {
            sum[i] = nums[i] + (i > 0 ? sum[i - 1] : 0);
            map.computeIfAbsent(nums[i], v -> new PriorityQueue<>((a, b) -> Long.compare(sum[b], sum[a]))).add(i);
        }
        long res = Long.MIN_VALUE;

        for (int i = 0; i < n; i++) {

            long presum = i> 0 ? sum[i - 1] : 0;

            if(map.containsKey(nums[i]-k)){
                Queue<Integer> pq = map.get(nums[i]-k);
                while(!pq.isEmpty() && pq.peek() < i){
                    pq.poll();
                }
                if(!pq.isEmpty()){
                    res = Math.max(res,sum[pq.peek()] - presum);
                }

            }

            if(map.containsKey(nums[i]+k)){
                Queue<Integer> pq = map.get(nums[i]+k);
                while(!pq.isEmpty() && pq.peek() < i){
                    pq.poll();
                }
                if(!pq.isEmpty()){
                    res = Math.max(res,sum[pq.peek()] - presum);
                }

            }

        }
        return res == Long.MIN_VALUE ? 0 : res;
    }


    /**
     * 如果map 直接存储的是基于ends with idx的presum
     * beat 98%
     * @param nums
     * @param k
     * @return
     */
    public long maximumSubarraySum_Best(int[] nums, int k) {
        Map<Integer,Long> numToPreSum = new HashMap<>();
        long res = Long.MIN_VALUE;

        long presum = 0;

        for(int i =0;i< nums.length;i++){
            presum += nums[i];
            if(numToPreSum.containsKey(nums[i] - k)){
                res = Math.max(res,presum - numToPreSum.get(nums[i] - k) + (nums[i] - k));
            }

            if(numToPreSum.containsKey(nums[i] + k)){
                res = Math.max(res,presum - numToPreSum.get(nums[i] + k) + (nums[i] + k));
            }


            if(numToPreSum.containsKey(nums[i])){
                if(presum - numToPreSum.get(nums[i])< 0){
                    numToPreSum.put(nums[i],presum);
                }
            }else {
                numToPreSum.put(nums[i],presum);
            }
        }

        return res == Long.MIN_VALUE? -1: res;
    }

}
