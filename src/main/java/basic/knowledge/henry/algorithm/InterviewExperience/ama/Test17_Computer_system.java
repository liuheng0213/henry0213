package basic.knowledge.henry.algorithm.InterviewExperience.ama;

import java.util.Arrays;
import java.util.List;


/**
 * For part 1 notice that we can't increase numbers relative to each other, we can only make relative decreases. So at index i suppose we've made the prefix the same, x x ... x y where y is the value at i
 *
 *
 * if y > x we have to decrease y relative to x, our only option is a type 2 operation. So do y-x operations to get x x x ... x x
 * if y < x then we have to decrease x relative to y, our only option is a type 1 operation. Do x-y ops to get y y y ... y y
 * At the end of this process we have some array x x x x .... x. Now we need to know what x is to make all the elements 0 instead of x. In principle we can pick any index, @v73 picked 0, and track how all of our type1 and type2 operations affect it.
 *
 *
 * in the y > x case we do type2 operations. If we look closely we realize that we never to type2 operations in the loop at i=0, so the first element doesn't change
 * in the x > y case we do type1 operations, every such operation hits index zero
 * therefore the final value of the first element is nums[0]-prefix, and we did ops to make all the elements equal so this is the value of x
 */
public class Test17_Computer_system {

    //在递减里找到最小的数 arr[i] 不断的减1
    //
    public static int solve(List<Integer> nums) {
        int n = nums.size();
        int prefix = 0, suffix = 0;

        for (int i = 1; i < n; i++) {
            if (nums.get(i) < nums.get(i - 1)) {
                prefix += nums.get(i - 1) - nums.get(i);
            } else {
                suffix += nums.get(i) - nums.get(i - 1);
            }
        }

        return prefix + suffix + Math.abs(nums.get(0) - prefix);
    }

    public static void main(String[] args) {
//        System.out.println(solve(Arrays.asList(2, -2, -3, -1))); // 10
//        System.out.println(solve(Arrays.asList(1, 0, 1)));       // 2
//        System.out.println(solve(Arrays.asList(-1, 0, -1)));     // 4
        System.out.println(solve(Arrays.asList(-2, 2, -3, 1)));  // 20
    }

    class Diff{
        int[] diff;

        public Diff(int[] arr){
            diff = new int[arr.length];
            diff[0] = arr[0];
            for(int i =1;i< arr.length;i++){
                diff[i] = arr[i] - arr[i - 1];
            }
        }

        public void substract(int i,int j){
            diff[i]++;
            if(j+1 < diff.length){
                diff[j+1]--;
            }
        }

        public int[] result(){
            int[] arr = new int[diff.length];
            arr[0] = diff[0];
            for(int i = 1;i< diff.length;i++){
                arr[i] = arr[i - 1] + diff[i];
            }
            return arr;
        }
    }
}
