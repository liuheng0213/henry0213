package basic.knowledge.henry.algorithm.InterviewExperience.microsoft;

import java.util.ArrayList;
import java.util.List;

public class Task1 {
    int n = 0;
    int[] dp;
    public static void main(String[] args) {
        Task1 task1 = new Task1();
        int[][] A = new int[][]{{5,1},{2,6},{6,1},{3,1},{4,3},{4,3},{4,6},{1,2},{4,1},{6,2}};
        int n = A.length;

        int res = task1.solution_dfs(A);
        System.out.println(res);
    }

    private int solution_dfs(int[][] A) {
        dp = new int[A.length];
        //generate graph
        List<List<Integer>> graph = new ArrayList<>();
        for(int i =0;i< A.length;i++){
            graph.add(new ArrayList<>());
        }
        for(int i = 0;i< A.length;i++){
            for(int j = i+1;j<A.length;j++){
                int[] pre = A[i];
                int[] cur = A[j];
                if(pre[1] == cur[0]){
                    graph.get(i).add(j);
                }
            }
        }
        int max = 0;
        for(int i = 0;i< graph.size();i++){
            if(dp[i] == 0){
                max = Math.max(max,dfs(i,graph));
            }
        }

        return A.length - max;
    }

    private int dfs(int cur, List<List<Integer>> graph) {
        if(cur == graph.size()){
            return 0;
        }

        if(dp[cur] != 0){
            return dp[cur];
        }

        int len = 1;
        for(int next : graph.get(cur)){
            len = Math.max(len,1+ dfs(next,graph));
        }

        dp[cur] = len;
        return len;
    }
}
