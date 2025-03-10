package basic.knowledge.henry.algorithm.InterviewExperience.At;

import java.util.*;

public class Leetcode658 {
    //x
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        //binary search
        int idx = Arrays.binarySearch(arr, x);
        if(idx < 0){
            idx = 0 - idx;
            idx--;
        }

        int left = idx - 1;
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

    private boolean isCloser(int[] arr,int i,int j,int x){
        if(Math.abs(arr[i]-x) < Math.abs(arr[j]-x)){
            return true;
        }else if(Math.abs(arr[i]-x) == Math.abs(arr[j]-x)){
            if(arr[i] < arr[j]){
                return true;
            }else if(arr[i] == arr[j]){
                return i< j;
            }
        }
        return false;
    }
}
