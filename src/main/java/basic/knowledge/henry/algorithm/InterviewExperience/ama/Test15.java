package basic.knowledge.henry.algorithm.InterviewExperience.ama;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//online market FindMaximumPackages
public class Test15 {
    public static void main(String[] args) {
        int maximumPackages = new Test15().findMaximumPackages(new int[]{1,1,2,2,1,4});
        System.out.println(maximumPackages);
    }

    public int findMaximumPackages(int[] arr){
        Arrays.sort(arr);
        int minCost = arr[0];
        int n = arr.length;
        int maxCost = arr[n-2]+ arr[n-1];


        int maxNum = Integer.MIN_VALUE;
        //for each cost .there should be a unique number

        for(int cost = minCost;cost<=maxCost;cost++){
            maxNum = Math.max(maxNum,calc(cost,arr));
        }
        return maxNum;
    }

    private int calc(int cost, int[] arr) {
      int left =0;
      int right = arr.length -1;
      int res = 0;

      while(left < right){
         int sum = arr[left] + arr[right];

         if(sum< cost){
             if(arr[left] ==cost){
                 res++;
             }
             left++;
         }else if(sum>cost){
             if(arr[right]==cost){
                 res++;
             }
             right--;
         }else{
             res++;
             left++;
             right--;
         }
      }
      return res;
    }


}
