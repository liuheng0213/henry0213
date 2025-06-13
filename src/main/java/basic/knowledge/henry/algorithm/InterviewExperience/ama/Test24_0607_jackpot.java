package basic.knowledge.henry.algorithm.InterviewExperience.ama;

import java.util.HashMap;

public class Test24_0607_jackpot {
    public static void main(String[] args) {
        int solution = new Test24_0607_jackpot().solution(new int[]{2, 3, 2, 4, 3, 2}, 2);
        System.out.println(solution);
        int solution1 = new Test24_0607_jackpot().solution(new int[]{6,4,4,6,4,4,6}, 6);
        System.out.println(solution1);
    }

    public int solution(int[] arr,int k){
        int n = arr.length;
        HashMap<Integer,Integer> tmp;
        int len = 0;
        int max = Integer.MIN_VALUE;
        for(int left = 0;left<n;left++){
            tmp = new HashMap<>();
            for(int right = left;right<n;right++){
                int rightNum = arr[right];
                tmp.put(rightNum,tmp.getOrDefault(rightNum,0) + 1);
                int contribution = getMaxDiff(tmp,k);
                if(contribution> max){
                    max = contribution;
                    len = right - left + 1;
                }
            }
        }
        return len;
    }

    private int getMaxDiff(HashMap<Integer, Integer> tmp,int k) {
        int kCount = tmp.getOrDefault(k,0);
        int notKmaxCount = 0;
        for(int num : tmp.keySet()){
            if(num != k){
                notKmaxCount= Math.max(notKmaxCount,tmp.get(num));
            }
        }

        if(kCount >= notKmaxCount){
            return 0;
        }else{
            return notKmaxCount - kCount;
        }
    }
}
