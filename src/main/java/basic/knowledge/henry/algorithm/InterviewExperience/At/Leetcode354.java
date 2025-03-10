package basic.knowledge.henry.algorithm.InterviewExperience.At;

import java.util.Arrays;

public class Leetcode354 {

    /**
     * 1）如果 X 之前的信封长度小于 X 的长度。那么只要之前信封的宽度小于 X 的宽度，一定可
     * 以放在 X 内。所以在宽度组成的数组中，X 的宽度如果作为最后一个数，求宽度数组的最长递
     * 增子序列即可。
     * 2）如果 X 之前的信封长度等于 X 的长度。因为长度相等的信封之间按照宽度从大到小排序，
     * 所以这些信封的宽度一定大于或等于 X 的宽度，这样就不可能是 X 的宽度作为最后一个数的情况下，
     * 宽度数组的最长递增子序列的一部分。
     * @param arr
     * @return
     */
    public int maxEnvelopes(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr,(a, b)->{
            if(a[0] == b[0]){
                return b[1] - a[1];
            }

            return a[0] - b[0];
        });

        int[] widths = new int[arr.length];
        for(int i =0;i< arr.length;i++){
            widths[i] = arr[i][1];
        }

        return  getdp2(widths);
        // int max = 0;
        // for(int n :ints){
        //     max= Math.max(n,max);
        // }
        // return max;
    }

    public int getdp2(int[] arr) {
        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];
        ends[0] = arr[0];
        dp[0] = 1;
        int right = 0;//effective right
        for (int i = 1; i < arr.length; i++) {
            int idx = binarySearch(ends, 0, right, arr[i]);
            if(idx < 0){
                ends[right+ 1] = arr[i];
                right++;
                dp[i] = right+1;
            }else{
                ends[idx] = arr[i];
            }

        }
        return right + 1;
    }

    private int binarySearch(int[] ends,int left,int right,int target){

        int res = -1;
        while(left <= right){
            int mid = (left+ right) /2;
            if(ends[mid] >= target){
                res = mid;
                right = mid - 1;
            }else if(ends[mid] < target){
                left = mid + 1;
            }
        }

        return res;
    }
}
