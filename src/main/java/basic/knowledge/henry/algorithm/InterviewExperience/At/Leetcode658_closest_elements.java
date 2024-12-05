package basic.knowledge.henry.algorithm.InterviewExperience.At;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Leetcode658_closest_elements {
    public static void main(String[] args) {
        Leetcode658_closest_elements obj = new Leetcode658_closest_elements();
        List<Integer> closestElements = obj.findClosestElements(new int[]{1, 1,2, 3, 4, 5}, 4, -1);
        System.out.println(closestElements);
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        //binary search
        int target = -1;
        int left = 0;
        int right = arr.length -1;
        while(left<= right){
            int mid = (left+ right)/2;
            if(arr[mid] == x){
                target = mid;
                break;
            }else if(arr[mid] < x){
                left = mid+1;
            }else{
                right = mid - 1;
            }
        }
        int i = -1;
        int j = -1;
        if(target ==-1){
            target = left;
            i = target-1;
            j = target;
        }else{
            i = target -1;
            j = target;

        }

        List<Integer> res = new ArrayList<>();

        while(j-i-1 < k){
            if(i < 0){
                res.add(arr[j]);
                j++;
            }else if(j> arr.length -1){
                res.add(arr[i]);
                i--;
            }else if(isCloser(arr,i,j,x)){
                res.add(arr[i]);
                i--;
            }else {
                res.add(arr[j]);
                j++;
            }
        }

        Collections.sort(res);
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
