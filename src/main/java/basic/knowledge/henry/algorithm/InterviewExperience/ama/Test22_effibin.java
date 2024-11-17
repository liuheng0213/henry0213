package basic.knowledge.henry.algorithm.InterviewExperience.ama;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test22_effibin {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(3, 3,6, 9,2, 5, 25);
        System.out.println(solve(nums)); // 17
    }
    public static long solve(List<Integer> nums) {
        // Find the minimum and maximum values in the list
        int mn = Collections.min(nums);
        if (mn == 1) {
            return (long) nums.size();
        }
        int mx = Collections.max(nums);

        // Create a frequency array
        int[] mp = new int[mx + 1];
        for (int n : nums) {
            mp[n]++;
        }

        long ans = 0;

        // Iterate through numbers starting from mn to mx
        for (int i = mn; i <= mx; i++) {
            if (mp[i] == 0) {
                continue;
            }
            for (int j = i; j <= mx; j += i) {//j+=i 是常见的遍历可整除数字的方式
                ans += (long) mp[j] * i;
                mp[j] = 0; // Mark the value as processed
            }
        }

        return ans;
    }


}
