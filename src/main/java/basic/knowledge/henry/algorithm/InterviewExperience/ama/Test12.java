package basic.knowledge.henry.algorithm.InterviewExperience.ama;

import java.util.HashMap;
import java.util.HashSet;

//matching price
public class Test12 {
    public static void main(String[] args) {
        int mInPrice = new Test12().getMInPrice(new int[]{1, 2, 1,2});
        System.out.println(mInPrice);
    }
    public int getMInPrice(int[] arr){
        int n = arr.length;
        int[] presum = new int[n+1];

        for(int i =1;i< n+1;i++){
            presum[i] = presum[i-1]+arr[i-1];
        }

        HashMap<Integer,Integer> numToCount;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            numToCount = new HashMap<>();
            for(int j = i;j<n;j++){
                numToCount.put(arr[j],numToCount.getOrDefault(arr[j],0)+1);
                if(numToCount.get(arr[j])>1){
                    min = Math.min(min,presum[j+1]-presum[i]);
                }
            }
        }
        return min;
    }
}
