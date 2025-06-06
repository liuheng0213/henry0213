package basic.knowledge.henry.algorithm.InterverviewFromRenowedITCompany._01stackAndQueue;

import java.util.Arrays;
import java.util.Stack;

public class _07MonontousStack {

    /**
     * [-1, 2]
     * [0, 2]
     * [-1, -1]
     * [2, 5]
     * [3, 5]
     * [2, -1]
     * [5, -1]
     * @param args
     */
    public static void main(String[] args) {
        _07MonontousStack obj = new _07MonontousStack();
        int[] arr = new int[]{3, 4, 1, 5, 6, 2, 7};
        int[][] res = obj.getNearestLessNoRepetitive(arr);
        for(int[] a: res){
            System.out.println(Arrays.toString(a));
        }
    }

    public int[][] getNearestLessNoRepetitive(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        int[][] res = new int[arr.length][2];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
           while(!stack.isEmpty() && arr[stack.peek()] > arr[i]){
               int idx = stack.pop();
               int pre = stack.isEmpty()? -1: stack.peek();
               res[idx][0] = pre;
               res[idx][1] = i;
           }
           stack.push(i);
        }

        while(!stack.isEmpty()){
            int idx = stack.pop();
            int pre = stack.isEmpty()? -1: stack.peek();
            res[idx][0] = pre;
            res[idx][1] = -1;
        }

        return res;
    }
}
