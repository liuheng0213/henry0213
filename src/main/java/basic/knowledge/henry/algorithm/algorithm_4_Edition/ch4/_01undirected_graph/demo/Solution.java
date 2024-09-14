package basic.knowledge.henry.algorithm.algorithm_4_Edition.ch4._01undirected_graph.demo;

import java.util.ArrayList;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.canFinish(2,new int[][]{{1,0}});
    }

    boolean hasCycle = false;
    boolean[] visited = null;
    boolean[] paths = null;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        //    int[] degrees = new int[numCourses];

        for(int i = 0;i< prerequisites.length;i++){
            //    degrees[prerequisites[i][0]]++;
            int idx = prerequisites[i][1];
            if(graph[idx] == null){
                graph[idx] = new ArrayList<>();
            }
            graph[idx].add(prerequisites[i][0]);
        }

        visited = new boolean[numCourses];
        paths = new boolean[numCourses];


        for(int i = 0;i< numCourses;i++){
            dfs(graph,i);
        }

        return !hasCycle;


    }


    private void dfs(ArrayList<Integer>[] graph,int s){
        if(paths[s]){
            hasCycle = true;
            return;
        }
        if(visited[s]){
            return;
        }

        visited[s] = true;
        paths[s] = true;
        if(graph[s] != null){
            for(int next : graph[s]){
                dfs(graph,next);
            }
        }

//        paths[s] = false;

    }
}