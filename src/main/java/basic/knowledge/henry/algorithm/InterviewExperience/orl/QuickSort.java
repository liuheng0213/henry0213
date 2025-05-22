package basic.knowledge.henry.algorithm.InterviewExperience.orl;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{3,3,4,5,1,1,2,3,7,-9};
        new QuickSort().sort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private void sort(int[] arr, int start, int end) {
        if(start >= end){
            return;
        }

        int idx = partition(arr,start,end);

        sort(arr,start,idx- 1);
        sort(arr,idx + 1,end);

    }

    private int partition(int[] arr, int start, int end) {
        int val = arr[start];
        int i = start + 1;
        int j = end;

        while(true){
            while(arr[i] < val){
                i++;
                if(i == end + 1){
                    break;
                }
            }
            i--;
            //arr[i] >= val start+1...i less than val

            while(arr[j] > val){
                j--;
                if(j == start - 1){
                    break;
                }
            }
            j++;
            //arr[j] <= val j...end  more than val
            if(i>=j){
                break;
            }
            //i< j
            swap(arr,i,j);
        }
        swap(arr,start,j);
        return j;

    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
