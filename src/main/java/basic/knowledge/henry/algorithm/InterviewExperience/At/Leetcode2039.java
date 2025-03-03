package basic.knowledge.henry.algorithm.InterviewExperience.At;

import java.util.*;

public class Leetcode2039 {
    public static void main(String[] args) {

        Leetcode2039 leetcode = new Leetcode2039();
        int i = leetcode.networkBecomesIdle(new int[][]{{0, 1}, {1, 2},{0,2}}, new int[]{0, 1, 1});
        System.out.println(i);
    }
    public int extraTime(int dis, int pat)
    {
        if(pat>= 2*dis)
            return 0;

        else if((2*dis)%pat==0)
            return 2*dis - pat;
        else
            return 2*dis - (2*dis)%pat;
    }
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] e : edges){
            int src = e[0];
            int trg = e[1];
            graph.putIfAbsent(src, new ArrayList<>());
            graph.get(src).add(trg);
            graph.putIfAbsent(trg, new ArrayList<>());
            graph.get(trg).add(src);
        }

        int depth = 0;
        int max = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        HashSet<Integer> visited = new HashSet<>();
        visited.add(0);
        while(!queue.isEmpty()){
            int size = queue.size();
            depth++;
            for(int i =0;i< size;i++){
                int cur = queue.poll();
                if(graph.containsKey(cur)){
                    for(int next : graph.get(cur)){
                        if(visited.add(next)){

                            queue.add(next);
                            int extraT = extraTime(depth, patience[next]);
                            int totalT = extraT + 2 * depth;
                            max = Math.max(max, totalT +1);

                        }
                    }
                }
            }
        }

        return max;
    }
}
