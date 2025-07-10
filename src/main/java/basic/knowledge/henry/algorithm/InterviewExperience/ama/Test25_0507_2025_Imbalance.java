package basic.knowledge.henry.algorithm.InterviewExperience.ama;


import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * I meet a really hard problem in Amazon OA and don't know how to solve it efficiently, can anyone please help?
 *
 * The inputs are a string, integer x and integer y.
 *
 * string is made up of 0, 1 and !, each ! can be either 0 or 1
 *
 * Every subsequence of 01 in the string can produce error x
 *
 * Every subsequence of 10 in the string can produce error y
 *
 * 0<=len(string)<=50000, 0<=x<=50000, 0<=y<=50000
 *
 * Return the minimum error count modulo 10^9.
 *
 *
 * Example:
 *
 * string=01!!, x=2, y=3, there're 2 cases:
 *
 * 0100 => errorCount is 2 + 3*2 = 8
 *
 * 0111 => errorCount is 2*3=6
 * 0110
 * 0101
 * so the result is 6
 *
 *
 * Example 2:
 *
 * string=!!!!, x=2, y=5
 *
 * we can replace all ! to 0 or 1, so there will be no 01 or 10 in the string, the result is 0.
 *
 * 本题和数字字符串转换字母组合这题很像
 */
public class Test25_0507_2025_Imbalance {


    public static void main(String[] args) {
        String str ="101!1";
        int x = 2;
        int y = 3;
//
        str = "!!!!";
        x = 2;
        y = 5;
//
//        str = "01!!";
//        x = 2;
//        y = 3;
        List<Integer> rank = Arrays.asList(4,1,3,2);
        int res = new Test25_0507_2025_Imbalance().solution(rank);
        System.out.println(res);
    }

    private int solution(List<Integer> rank) {
        int n = rank.size();
        int[][]  dp = new int[n][n];

//        for(int i = 0;i< n;i++){
//            for(int j = i+1;j<n;j++){
//                if(rank.get(j)){
//
//                }
//                dp[i][j] =
//            }
//        }

        return 0;
    }


}
