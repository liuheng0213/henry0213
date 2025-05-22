package basic.knowledge.henry.algorithm.InterviewExperience.eg;

import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) {
        int[] arr = new int[]{2,3,3,6,7,9,4,2,8};
        int res = new Solution().solution(arr,4);
        System.out.println(res);
    }

    private int solution(int[] arr, int len) {
        LinkedList<Integer> minQ = new LinkedList<>();
        LinkedList<Integer> maxQ = new LinkedList<>();
        int max = Integer.MIN_VALUE;
        for(int i = 0;i< arr.length;i++){
            while(!minQ.isEmpty() && arr[minQ.peekLast()]> arr[i]){
                minQ.pollLast();
            }
            minQ.addLast(i);

            while(!maxQ.isEmpty() && arr[maxQ.peekLast()]< arr[i]){
                maxQ.pollLast();
            }
            maxQ.addLast(i);

            if(minQ.peekFirst()< i - len + 1){
                minQ.pollFirst();
            }

            if(maxQ.peekFirst() < i - len+ 1){
                maxQ.pollFirst();
            }

            if(i - len+1>= 0){
                max = Math.max(max,arr[maxQ.peekFirst()] - arr[minQ.peekFirst()]);
            }

        }

        return max;
    }
}
