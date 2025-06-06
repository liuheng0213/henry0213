package basic.knowledge.henry.algorithm.InterviewExperience.At;

import java.util.Arrays;
import java.util.HashSet;

public class Leetcode2975 {
    public static void main(String[] args) {
        Leetcode2975 leetcode2975 = new Leetcode2975();
        int i = leetcode2975.maximizeSquareArea(4, 3, new int[]{2, 3}, new int[]{2});
        System.out.println(i);
    }
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        long ans = 0, mod = 1000000007;
        HashSet<Integer> hs = new HashSet<>();
        int[] hFen = Arrays.copyOf(hFences, hFences.length + 2);
        int[] vFen = Arrays.copyOf(vFences, vFences.length + 2);
        hFen[hFences.length] = 1; hFen[hFences.length + 1] = m;
        vFen[vFences.length] = 1; vFen[vFences.length + 1] = n;
        for(int i = 0; i < hFen.length; ++i){
            for(int j = 0; j < hFen.length; ++j){
                if(i != j ) hs.add(Math.abs(hFen[i] - hFen[j]));
            }
        }
        for(int i = 0; i < vFen.length; ++i){
            for(int j = 0; j < vFen.length; ++j){
                if(hs.contains(Math.abs(vFen[i] - vFen[j])) == true) {
                    ans = Math.max(ans, Math.abs(vFen[i] - vFen[j]));
                }
            }
        }
        return (ans == 0)?-1: (int)((ans * ans) % mod);
    }
}
