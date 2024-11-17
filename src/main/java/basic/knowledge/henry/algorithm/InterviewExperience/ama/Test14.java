package basic.knowledge.henry.algorithm.InterviewExperience.ama;

import java.util.Arrays;

//system charge
//remove and merge
//类似爆气球那题
public class Test14 {
    public static void main(String[] args) {
        int res = new Test14().solution_dp(new int[]{-2,4,9,1,-1,3,13,2,3,-8,9,21,-18,22,122,-189});
        int res1 = new Test14().solution(new int[]{-2,4,9,1,-1,3,13,2,3,-8,9,21,-18,22,122,-189});
        System.out.println(res);
        System.out.println(res1);
    }
    int[][] dp;
    public int solution_dp(int[] arr){
        int n = arr.length;
        dp = new int[n][n];
        for(int[] d: dp){
            Arrays.fill(d,Integer.MIN_VALUE);
        }
        return helper_dp(arr,0,arr.length - 1);
    }

    private int helper_dp(int[] arr, int l, int r) {
        if(l == r){
            dp[l][r] = arr[l];
            return arr[l];
        }

        if(dp[l][r] != Integer.MIN_VALUE){
            return dp[l][r];
        }


        int max = Math.max(helper(arr,l+1,r), helper(arr,l,r-1));

        for(int i = l+1;i<=r -1;i++){
            int left = arr[i-1];
            int right = arr[i+1];
            int sum = left + right;
            arr[i-1] = sum;
            arr[i+1] = sum;
            int leftmax = helper(arr,l,i-1);
            int rightmax = helper(arr,i+1,r);
            arr[i-1] = left;
            arr[i+1] = right;

            if(leftmax == Integer.MIN_VALUE && rightmax != Integer.MIN_VALUE){
                max = Math.max(max,rightmax);
            }else if(leftmax != Integer.MIN_VALUE && rightmax == Integer.MIN_VALUE){
                max = Math.max(max,leftmax);
            }else if(leftmax != Integer.MIN_VALUE && rightmax == Integer.MIN_VALUE){
                max = Math.max(max,Math.max(leftmax,rightmax));
            }

        }
        dp[l][r] = max;
        return max;


    }

    public int solution(int[] arr){
        int n = arr.length;
//        int[] updated = new int[n+2];
//        updated[0] = 0;
//        updated[n+1] = 0;
//        for(int i =0;i< n;i++){
//            updated[i+1] = arr[i];
//        }
//        System.out.println(Arrays.toString(updated));

        //helper means under the index from 0~ len -1  最后剩下的最大的charge
        return helper(arr,0,arr.length - 1);


    }

    private int helper(int[] arr, int l, int r) {
        if(l == r){
            return arr[l];
        }


        int max = Math.max(helper(arr,l+1,r), helper(arr,l,r-1));

        for(int i = l+1;i<=r -1;i++){
            int left = arr[i-1];
            int right = arr[i+1];
            int sum = left + right;
            arr[i-1] = sum;
            arr[i+1] = sum;
            int leftmax = helper(arr,l,i-1);
            int rightmax = helper(arr,i+1,r);
            arr[i-1] = left;
            arr[i+1] = right;
            max = Math.max(max,Math.max(leftmax,rightmax));
        }

        return max;
    }
}
