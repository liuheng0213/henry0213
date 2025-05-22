package basic.knowledge.henry.algorithm.InterviewExperience.tt;

public class Leetcode162 {
    /**
     *
 *         任意取一个连续的两个数字，这两个数字不是前面大就是后面大
 *         前面大：那么这个数组的峰值就在前面 end=mid-1;
 *         后面大：那么这个数组的峰值就在后面 start=mid+1;
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,1,3,4,5,6,9,8};
        int peakElement = new Leetcode162().findPeakElement(arr);
        System.out.println(peakElement);
    }
    public int findPeakElement(int[] nums) {
        if(nums.length==1) {
            return 0;
        }else if(nums[0]>nums[1]) {
            return 0;
        }else if(nums[nums.length-1]>nums[nums.length-2]) {
            return nums.length-1;
        } else {
            int start=1;
            int end=nums.length-2;

            while(start<=end) {
                int mid=(start+end)/2;
                if(nums[mid]>nums[mid+1] && nums[mid]>nums[mid-1]) {
                    return mid;
                } else if(nums[mid-1]>nums[mid]) {
                    end = mid-1;
                } else if(nums[mid+1] > nums[mid]){
                    start = mid+1;
                }
            }
        }
        return -1;
    }
}
