package basic.knowledge.henry.algorithm.InterviewExperience.ama;

import java.util.Arrays;

//find outlier
public class Test3_outlier {
    public static void main(String[] args) {
        int solution = new Test3_outlier().solution(new int[]{4, 1, 3, 17, 3, 10}, 6);
        System.out.println(solution);
    }
    public int solution(int[] arr,int n){
        n = arr.length;
        Arrays.sort(arr);
        int max =0;
        for(int i =0;i< n;i++){
            max += arr[i];
        }
        System.out.println(max);

        int sumidx= n-2;
        for(int i = n-1;i>=0;i--){
            if(i == n- 1){
                sumidx =n -2;
            }else{
                sumidx = n-1;
            }

            if(max - arr[i]-arr[sumidx] == arr[sumidx]){
                return arr[i];
            }
        }

        return -1000;
    }
}
