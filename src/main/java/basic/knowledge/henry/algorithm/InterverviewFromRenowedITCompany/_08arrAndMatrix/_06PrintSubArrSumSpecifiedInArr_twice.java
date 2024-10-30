package basic.knowledge.henry.algorithm.InterverviewFromRenowedITCompany._08arrAndMatrix;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//不重复打印排序数组中相加和为给定值的所有二元组和三元组
public class _06PrintSubArrSumSpecifiedInArr_twice {
    public static void main(String[] args) {
        _06PrintSubArrSumSpecifiedInArr_twice obj = new _06PrintSubArrSumSpecifiedInArr_twice();
        int[] arr = new int[]{-2,0,0,2,2};
        //obj.printUnqiuePair(arr, 10);
        List<List<Integer>> lists = obj.threeSum(arr);
        System.out.println(lists);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0;i< nums.length;i++){
            if(i == 0 || nums[i] != nums[i - 1]){
                List<List<Integer>> partsRes  = twosum(nums,i);
                res.addAll(partsRes);
            }
        }
        return res;

    }

    private List<List<Integer>> twosum(int[] nums,int idx){
        List<List<Integer>> res = new ArrayList<>();
        int target = 0 - nums[idx];
        int left = idx+1;
        int right = nums.length-1;
        while(left< right){
            int sum = nums[left] + nums[right];
            if(sum < target){
                left++;
            }else if(sum > target){
                right--;
            }else{
                List<Integer> sub = new ArrayList<>();
                if(left == idx+1 || nums[left] != nums[left - 1]){
                    sub.addAll(Arrays.asList(nums[idx],nums[left],nums[right]));
                    res.add(sub);
                }
                left++;
                right--;
            }
        }

        return res;
    }

    private void printUnqiueThree(int[] arr, int sum) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length - 2; i++) {
            if (i == 0 || arr[i] != arr[i - 1]) {
                printUnqiueThree(arr, i, i + 1, arr.length - 1, sum - arr[i]);
            }
        }
    }

    private void printUnqiueThree(int[] arr, int i, int left, int right, int sum) {
        while (left < right) {
            if(arr[left] + arr[right] == sum){
                if(left == i + 1 || arr[left] != arr[left - 1]){
                    System.out.println(arr[i] + "==" + arr[left] + "===" + arr[right]);
                }
                left++;
                right--;
            }else if(arr[left] + arr[right] < sum){
                left++;
            }else{
                right--;
            }
        }
    }

    private void printUnqiuePair(int[] arr, int sum) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int left = 0;
        int right = arr.length - 1;

        //贪心算法,只专注于当前
        while (left < right) {
            if (arr[left] + arr[right] == sum) {
                if (left == 0 || arr[left] != arr[left - 1]) {
                    System.out.println(arr[left] + "----" + arr[right]);
                }
                left++;
                right--;
            } else if (arr[left] + arr[right] > sum) {
                right--;
            } else {
                left++;
            }
        }
    }
}


