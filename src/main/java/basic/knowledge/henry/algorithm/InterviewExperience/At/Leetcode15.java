package basic.knowledge.henry.algorithm.InterviewExperience.At;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode15 {


    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0;i< nums.length -2;i++){
            if(i >0 && nums[i] == nums[i - 1]){
                continue;
            }
            handle(nums[i],i+1,nums,0 - nums[i],list);

        }

        return list;
    }

    private void handle(int first,int idx,int[] nums,int target,List<List<Integer>> list){
        int left = idx;
        int right = nums.length - 1;

        while(left < right){
            if((left == idx || nums[left] != nums[left - 1] ) && nums[left]+nums[right] == target){
                List<Integer> sub = new ArrayList<>();
                sub.add(first);
                sub.add(nums[left]);
                sub.add(nums[right]);
                list.add(sub);
                left++;
                right--;
            }else if(nums[left]+nums[right] < target){
                left++;
            }else{
                right--;
            }
        }

    }
}
