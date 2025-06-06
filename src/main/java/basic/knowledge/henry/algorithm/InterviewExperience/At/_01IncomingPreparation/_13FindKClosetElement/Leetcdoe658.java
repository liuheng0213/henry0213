package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation._13FindKClosetElement;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * given arr is sorted ,and the result should be a sorted one as well
 */
public class Leetcdoe658 {
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

}
