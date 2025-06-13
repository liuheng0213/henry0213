package basic.knowledge.henry.algorithm.test.solution;

import java.util.*;

class Solution {


        public static int maxLengthOfMaxSumSubarray(int[] nums) {
            int maxSum = Integer.MIN_VALUE;
            int currentSum = 0;

            int maxLength = 0;
            int tempStart = 0;

            for (int i = 0; i < nums.length; i++) {
                if (currentSum <= 0) {
                    currentSum = nums[i];
                    tempStart = i;
                } else {
                    currentSum += nums[i];
                }

                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    maxLength = i - tempStart + 1;
                } else if (currentSum == maxSum) {
                    maxLength = Math.max(maxLength, i - tempStart + 1);
                }
            }

            return maxLength;
        }

        public static void main(String[] args) {
            int[] nums = {-2, 1, -3, 4, -1,0, 2, 1,0,0, -5, 4};  // Max sum = 6; max length = 4 ([4,-1,2,1])
            System.out.println("Max length of subarray with max sum: " + maxLengthOfMaxSumSubarray(nums));
        }


}