package basic.knowledge.henry.algorithm.test.solution;

import java.util.*;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

        List<Integer> res = solution.findClosestElements(new int[]{0,0,1,2,3,3,4,7,7,8},3,5);
        System.out.println(res);
    }
    //x
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        //binary search
        int idx = Arrays.binarySearch(arr, x);


        if(idx < 0){
            idx = 0 - idx;
            idx--;
        }

        int left = idx-1;
        int right = idx;


        LinkedList<Integer> res = new LinkedList<>();

        while(right-left-1 < k){
            if(left < 0){
                res.addLast(arr[right]);
                right++;
            }else if(right> arr.length -1){
                res.addFirst(arr[left]);
                left--;
            }else if(x- arr[left] <= arr[right] - x){
                res.addFirst(arr[left]);
                left--;
            }else {
                res.addLast(arr[right]);
                right++;
            }
        }

        return res;
    }

}



/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */