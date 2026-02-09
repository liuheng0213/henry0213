package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._15Envelope;

import java.util.Arrays;

public class Leetcode354 {
    public static void main(String[] args) {
        int[] arr = new int[]{2,1,5,3,6,4,8,9,7,100};
        int res = new Leetcode354().getdp1(arr);
        int res2 = new Leetcode354().getdp2(arr);
        System.out.println(res == res2);
    }

    /**
     * 1）如果 X 之前的信封长度小于 X 的长度。那么只要之前信封的宽度小于 X 的宽度，一定可
     * 以放在 X 内。所以在宽度组成的数组中，X 的宽度如果作为最后一个数，求宽度数组的最长递
     * 增子序列即可。
     * 2）如果 X 之前的信封长度等于 X 的长度。因为长度相等的信封之间按照宽度从大到小排序，，
     * 所以宽度更大的信封必然长度更大
     * 所以这些信封的宽度一定大于或等于 X 的宽度，这样就不可能是 X 的宽度作为最后一个数的情况下，
     * 宽度数组的最长递增子序列的一部分。
     *        //so in this case as long as width is increasing,
     *        //and len is not equal to the previous one .definately len is increasing

     * @param arr
     * @return
     */
    public int maxEnvelopes(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        //make sure len is non decreasing !!!!
        // and when it is equaling to the previous one , width is decreasing
        //so in this case as long as width is increasing, and len is not equal to the previous one .definately len is increasing
        // the previous envelop can be inside the current envelope
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

        int i = getdp2(widths);





        return i;
        // int max = 0;
        // for(int n :ints){
        //     max= Math.max(n,max);
        // }
        // return max;
    }

    /**
     *
     * for  int[] arr = new int[]{2,1,5,3,6,4,8,9,7};
     * 如果有 ends[b]==c，则表示遍历到目前为止，在所有长度为 b+1 的递增序列中，
     * 最小的结尾数是 c。 注意是最小
     *
     *      * till end . ends[0..4]=[1,3,4,8,9]
     *      * 递增长度为1 时， 最小的结尾数 1；  而不是2，
     *        递增长度为2 时， 最小的结尾数 3，；而不是5，
     *        递增长度为3 时， 最小的结尾数 4，；而不是6，
     *       因此 ends 必然是递增的
     *
     *       时间复杂度从n2 ->nlogn
     * @param arr
     * @return
     */
    public int getdp2(int[] arr) {
        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];
        ends[0] = arr[0];
        dp[0] = 1;
        int right = 0;//effective right
        for (int i = 1; i < arr.length; i++) {
            int idx = binarySearch(ends, 0, right, arr[i]);//必须在0~right之间二分搜索，因为这里才递增。

            //没找到比arr[i] 更大的， 一定要找最左边的比arr[i]大的，
            // 这意味着是找到ends里最小的比arr[i]大的值
            //ends[1, 3, 5] 来了个2 则找出3 并且替换成1，2，5
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

    public int getdp1(int[] widths ){
        int[] dp = new int[widths.length];
        int max = 1;
        dp[0] = 1;
        for(int i = 1;i< dp.length;i++){
             dp[i] = 1;
             for(int j = 0;j<i;j++){
                 if(widths[j]< widths[i]){
                     dp[i] = Math.max(dp[i],dp[j] + 1);
                 }
             }
             max = Math.max(dp[i],max);
        }

        return max;
    }
}
