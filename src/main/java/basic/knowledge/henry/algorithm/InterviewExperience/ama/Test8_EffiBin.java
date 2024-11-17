package basic.knowledge.henry.algorithm.InterviewExperience.ama;

import java.util.Collections;
import java.util.List;

/**
 * With Amazon's new innovative EffiBin Kit users can effortlessly optimize the arrangement of their storage bins. This kit is designed to minimize the overall effort needed for efficient organization. The process starts with an array of bins, and the objective is to reduce the total effort required. The effort is the sum of efforts needed for each bin.
 *
 *
 * Formally, given an array effort of size n, utilizing the EffiBin Kit, users can perform operations on the array, in each operation, the user chooses two positions /andj, such that the effort of the bin at position (effort[i]) is divisible by the effort of the bin at position j(effort[j]). When this condition is satisfied, the effort of bin/can be updated to equal the effort of binj. This operation can be repeated as many times as possible, on different bins or positions.
 *
 *
 * An integer x is divisible by another integer yif x can be divided by y exactly, with nothing left over, for example, 6 is divisible by 3, while 7 is not.
 *
 *
 * Find the minimum total effort after applying some (possibly zero) numbe operations.
 *
 *
 * Example
 *
 *
 * effort [3, 6, 2, 5, 25]
 * output: 17
 *
 *
 * constraints :
 * n = effort.length
 * 1<=n, effort[i] <= 2 * 10^5
 * For this question my intuition was to sort the array them for each element calculate sqrt of it and perform binary search to find the upper bound of the element with the help of sqrt of the element. after find the upper bound I will traverse from 0 to the upper bound index and once I find a divisor I will break out from the loop. I'm sure my solution isn't efficient and I would be glad if anyone could give me a suggestion.
 */
public class Test8_EffiBin {

    public static void main(String[] args) {
        int[] arr = new int[]{3,6,2,5,25};
        System.out.println(solve(arr)); // Output: 17
    }
    public static int solve(int[] arr) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int a: arr){
            min = Math.min(min,a);
            max = Math.max(max,a);

        }

        int[] freq = new int[max+1];
        for(int a: arr){
            freq[a]++;
        }

        int ans = 0;
        for(int i = min;i<=max;i++){
            if(freq[i] == 0){
                continue;
            }

            for(int j = i;j<= max;j += i){
                if(freq[j]== 0){
                    continue;
                }
                ans += freq[j] * i;
                freq[j] = 0;
            }
        }
        return ans;
    }

    //  o(n)
    public static int solve(List<Integer> nums) {
        int mn = Collections.min(nums); // Minimum element in the list
        if (mn == 1) {
            return nums.size();
        }
        int mx = Collections.max(nums); // Maximum element in the list
        int[] mp = new int[mx + 1]; // Frequency array
        for (int n : nums) {
            mp[n]++;
        }
        int ans = 0;
        for (int i = mn; i <= mx; i++) {
            if (mp[i] == 0) {
                continue;
            }
            for (int j = i; j <= mx; j += i) {
                ans += mp[j] * i;
                mp[j] = 0; // Mark as processed
            }
        }
        return ans;
    }
}
