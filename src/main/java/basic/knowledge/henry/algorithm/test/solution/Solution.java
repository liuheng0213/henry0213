package basic.knowledge.henry.algorithm.test.solution;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

class Solution {
    public static void main(String[] args) {

        int[] res= new Solution().calculateGreaterElement(new int[]{2,1,2,4,3});
        System.out.println(res);
    }
    int[] calculateGreaterElement(int[] nums) {
        int n = nums.length;
        // 存放答案的数组
        int[] res = new int[n];
        Stack<Integer> s = new Stack<>();
        // 倒着往栈里放
        for (int i = n - 1; i >= 0; i--) {
            // 判定个子高矮
            while (!s.isEmpty() && s.peek() <= nums[i]) {
                // 矮个起开，反正也被挡着了。。。
                s.pop();
            }
            // nums[i] 身后的更大元素
            res[i] = s.isEmpty() ? -1 : s.peek();
            s.push(nums[i]);
        }
        return res;
    }
}