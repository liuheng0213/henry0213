package basic.knowledge.henry.algorithm.InterviewExperience.ama;


import org.example.Main;

//first index where pre sum becomes non-positive
//maximum money in bags
public class Test2 {
    public static void main(String[] args) {
        int[] arr = new int[]{-4,0,1,16,-15,27,2,0,0,-13};
        int solution = new Test2().solution(arr);
        System.out.println(solution);
    }

    public int solution(int[] arr){
        int n = arr.length;
        int[] presum = new int[n];
        presum[0] = arr[0];
        for(int i =1;i< n;i++){
            presum[i] = presum[i - 1] + arr[i];
        }

        int min = presum[0];
        int res = Integer.MIN_VALUE;
        for(int i = 1;i< n;i++){
            min = Math.min(min,presum[i]);
            res = Math.max(presum[i] - min,res);
        }

        return res;
    }
}
