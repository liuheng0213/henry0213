package basic.knowledge.henry.algorithm.InterviewExperience.tt;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class SumIsZero {
    public static void main(String[] args) {
        int[] arr =new int[]{0,0,0};
        SumIsZero sumIsZero = new SumIsZero();
        List<List<Integer>> lists = sumIsZero.threeSum(arr);
        System.out.println(lists);
    }
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0;i< nums.length - 2;i++){
            if(i == 0 || nums[i] != nums[i - 1]){
                List<List<Integer>> twosumlist = twosum(nums,i+1,0-nums[i]);
                if(twosumlist != null){
                    for(List<Integer> sub : twosumlist){
                        sub.add(nums[i]);
                        res.add(sub);
                    }
                }
            }
        }
        return res;
    }

    private List<List<Integer>> twosum(int[] nums,int start,int target){
        List<List<Integer>> twosumlist = new ArrayList<>();
        int i = start;
        int j = nums.length - 1;
        while(i< j){
            int sum = nums[i] + nums[j];
            if(sum < target){
                i++;
            }else if(sum > target){
                j--;
            }else{
                if(i == start || nums[i] != nums[i - 1]){
                    twosumlist.add(new ArrayList<>(Arrays.asList(nums[i],nums[j])));
                }
                i++;
                j--;
            }
        }
        if(twosumlist.size() > 0){
            return twosumlist;
        }else{
            return null;
        }
    }
}
