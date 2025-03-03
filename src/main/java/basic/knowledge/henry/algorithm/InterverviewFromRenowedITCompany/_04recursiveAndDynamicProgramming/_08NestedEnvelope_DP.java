package basic.knowledge.henry.algorithm.InterverviewFromRenowedITCompany._04recursiveAndDynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 类似最长递增子序列
 * 合理排序  可形成宽度满足最长子序列条件 长度必定满足条件
 */
public class _08NestedEnvelope_DP {
    public static void main(String[] args) {
        _08NestedEnvelope_DP envelopeNesting = new _08NestedEnvelope_DP();
        int[][] arr = new int[][]{{2,100},{3,200},{4,300},{5,500},{5,400},{5,250},{6,370},{6,360},{7,380}};
        int res = envelopeNesting.maxEnvelopes(arr);
        System.out.println(res);

    }

    public int maxEnvelopes(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr,(a,b)->{
            if(a[0] == b[0]){
                return b[1] - a[1];
            }

            return a[0] - b[0];
        });

        int[] widths = new int[arr.length];
        for(int i =0;i< arr.length;i++){
            widths[i] = arr[i][1];
            System.out.println(widths[i]);
        }


        int[] ints = getdp2(widths);
           System.out.println(Arrays.toString(ints));

         int max = 0;
         for(int n :ints){
             max= Math.max(n,max);
         }

         return max;
    }

    public int[] getdp2(int[] arr) {
        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];
        ends[0] = arr[0];
        dp[0] = 1;
        int right = 0;//effective right
        for (int i = 1; i < arr.length; i++) {
            System.out.println("i " + i + "right " + right);
            System.out.println("ends " + Arrays.toString(ends));
            int idx = binarySearch(ends, 0, right, arr[i]);

            if(idx < 0){
                ends[right+ 1] = arr[i];
                right++;
                dp[i] = right+1;
            }else{
                ends[idx] = arr[i];
                System.out.println( "update " + ends[right] + "i " + i +  "right " + right);
            }

        }
        return dp;
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
