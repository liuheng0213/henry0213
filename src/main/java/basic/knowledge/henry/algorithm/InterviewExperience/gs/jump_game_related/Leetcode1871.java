package basic.knowledge.henry.algorithm.InterviewExperience.gs.jump_game_related;

import java.util.*;

public class Leetcode1871 {
    public static void main(String[] args) {
        Leetcode1871 leetcode1871 = new Leetcode1871();
        boolean b = leetcode1871.canReach("0111111111111111111111111111111101111101111111111111111110", 5, 26);
        System.out.println(b);
    }

    public boolean canReach2(String s, int minJump, int maxJump) {
        int n = s.length();
        if(s.charAt(n - 1) == '1') return false;
        Queue<Integer> que = new LinkedList<>();
        que.add(0);
        int far = 0;
        while(que.size() > 0) {
            int cur = que.poll();
            int left = Math.max(cur + minJump, far + 1);
            int right = Math.min(cur + maxJump,n - 1);

            for(int i = left;i<= right;i++){
                if(s.charAt(i) == '0') que.add(i);
                if(i == n - 1) return true;
            }

            far = cur + maxJump;
        }
        return false;
    }
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] reachable = new boolean[n];
        reachable[0] = true;
        if (s.charAt(s.length() - 1) == '1') return false;
        int min = 0;
        int max =0;
        for(int i =0;i< n;i++){
            if (!reachable[i]) continue;
            min = Math.max(i + minJump,max + 1);
            max = Math.min(i + maxJump,n - 1);
            for(int j = min;j<= max;j++){
                if(s.charAt(j) != '0'){
                    continue;
                }
                reachable[j] = true;
                if (reachable[n - 1]) return true;
            }
        }
        return reachable[n - 1];
    }

    public boolean canReach3(String s, int minJump, int maxJump) {
        if (s.charAt(s.length() - 1) == '1') return false;
        boolean[] available = new boolean[s.length()];
        available[0] = true;
        int start;
        int end = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (!available[i]) continue;
            start = Math.max(i + minJump, end + 1);
            end = Math.min(i + maxJump, s.length() - 1);
            for (int j = start; j <= end; ++j) {
                if (s.charAt(j) == '0') {
                    available[j] = true;
                }
                if (available[available.length - 1]) return true;
            }
        }
        return available[s.length() - 1];
    }
}
