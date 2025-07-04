package basic.knowledge.henry.algorithm.InterviewExperience.At._01krt.k100_harder;

import java.util.Arrays;

public class karat3 {
    int[] dp;
    public static void main(String[] args) {
        boolean helper1 = new karat3().solution(new int[]{1, 3, 1, 1, 1, 3, 10, 9}, 1);
        boolean helper2 = new karat3().solution(new int[]{1, 3, 1, 1, 1, 3, 10, 9}, 0);
        System.out.println(helper2);
    }
    public boolean solution(int[] instructions,int balance){
        dp = new int[instructions.length];
        Arrays.fill(dp, -1);
        return helper(0,instructions,balance);
    }

    private boolean helper(int idx, int[] instructions, int balance) {
        if(idx == instructions.length - 1){
            return true;
        }

        if(idx > instructions.length - 1){
            return false;
        }
        if(dp[idx] != -1){
            return dp[idx] == 1? true:false;
        }

        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;

        if(balance == 0){
             flag1 = helper(idx+instructions[idx],instructions,balance);
             if(flag1){
                 dp[idx] = 1;
                return true;
             }
        }else if(balance > 0){
             flag2 = helper(idx+instructions[idx],instructions,balance);
             flag3 = helper(idx+instructions[idx] + 1,instructions,balance - 1);
             flag4 = helper(idx+instructions[idx] - 1,instructions,balance - 1);
             if(flag2 || flag3 || flag4){
                 dp[idx] = 1;
                 return true;
             }
        }

        if(flag1 || flag2 || flag3 || flag4){
            dp[idx] = 1;
            return true;
        }


        dp[idx] = 0;
        return false;
    }
}
