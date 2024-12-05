package basic.knowledge.henry.algorithm.InterviewExperience.At;

public class Leetcode410 {
    public static void main(String[] args) {
        int i = new Leetcode410().splitArray(new int[]{5,5,5,5}, 2);
        System.out.println(i);
    }
    public int splitArray(int[] nums, int m) {
        if (nums == null || nums.length == 0 || m > nums.length || m < 1) {
            return -1;
        }
        //res is more, the spli num is less
        int left = 0;
        int right = 0;

        for(int i= 0;i< nums.length;i++){
            left = Math.max(left,nums[i]);
            right += nums[i];
        }
        int target = -1;
        while(left<= right){
            int mid = (left + right)>> 1;
            int splitNum = f(nums,mid);
            if(splitNum==m){
                target = mid;
                right = mid -1;
            }else if(splitNum > m){
                left = mid +1;
            }else{
                right = mid -1;
            }
        }
        return target == -1? left: target;


    }

    /**
     *if sum of number <= sum ,get the splitting number
     * @param nums
     * @param sum
     * @return
     */
    private int f(int[] nums,int sum){
        int tmp = 0;
        int splitNum = 0;

        for(int i =0;i< nums.length;i++){
            tmp+= nums[i];
            if(tmp> sum){
                splitNum++;
                tmp = nums[i];
            }
        }
        splitNum++;

        return splitNum;
    }
}
