package basic.knowledge.henry.algorithm.InterviewExperience.At;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class Leetcode2976 {
    public static void main(String[] args) {
        HashMap<int[], Integer> map = new HashMap<>();
        map.put(new int[]{1,1},2);
        map.put(new int[]{1,1},3);
        Leetcode2976 leetcode2976 = new Leetcode2976();
        long l = leetcode2976.minimumCostByDijkstra("abcd", "acbe", new char[]{'a','b','c','c','e','d'}, new char[]{'b','c','b','e','b','e'}, new int[]{2,5,5,1,2,20});
        System.out.println(l);
    }

    public long minimumCostByFloyd(String source, String target, char[] original, char[] changed, int[] cost) {

        int[][] adj = new int[26][26];
        for(int i = 0;i< 26;i++){
            Arrays.fill(adj[i],Integer.MAX_VALUE);
            adj[i][i] = 0;
        }

        int n = original.length;
        for(int i =0;i< n;i++){
            adj[original[i]-'a'][changed[i]- 'a'] = Math.min(adj[original[i]-'a'][changed[i]- 'a'] ,cost[i]);
        }
        for(int k =0;k< 26;k++){
            for(int i =0;i< 26;i++){
                if(adj[i][k] != Integer.MAX_VALUE){
                    for(int j =0;j< 26;j++){
                        if( adj[k][j] != Integer.MAX_VALUE){
                            adj[i][j] = Math.min(adj[i][j],adj[i][k] + adj[k][j]);
                        }
                    }
                }

            }
        }
        long res = 0;
        for(int i =0;i< source.length();i++){
            if(adj[source.charAt(i) - 'a'][target.charAt(i) - 'a'] == Integer.MAX_VALUE){
                return -1;
            }
            res+= adj[source.charAt(i) - 'a'][target.charAt(i) - 'a'];
        }


        return res;
    }
    class Pair{
        private final int start;
        private final int end;


        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return start == pair.start && end == pair.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }


    public long minimumCostByDijkstra(String source, String target, char[] original, char[] changed, int[] cost) {
        List<int[]>[] graph = new ArrayList[26];
        for(int i =0;i< 26;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i =0;i< original.length;i++){
            int i1 = original[i]-'a';
            int i2 = changed[i]-'a';
            int i3 = cost[i];
            graph[i1].add(new int[]{i2,i3});
        }
        Map<Pair,Integer> map = new HashMap<>();
        long res = 0;
        for(int i = 0;i< source.length();i++){
            int start = source.charAt(i)-'a';
            int end = target.charAt(i) - 'a';
            Pair pair = new Pair(start, end);
            if(map.containsKey(pair)){
                res+= map.get(pair);
                continue;
            }

            int[] minCosts = new int[26];
            Arrays.fill(minCosts,Integer.MAX_VALUE);
            int[] startNode = new int[]{start,0};
            PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
            pq.add(startNode);
            minCosts[startNode[0]]=0;
            boolean found = false;
            while(!pq.isEmpty()){
                int[] curNode = pq.poll();
                int cur = curNode[0];
                int curCost = curNode[1];
                if(curCost > minCosts[cur]){
                    continue;
                }
                minCosts[cur] = curCost;

                if(cur == end){
                    Pair pair2 = new Pair(start, cur);
                    map.put(pair2,curCost);
                    res += curCost;
                    found = true;
                    break;
                }

                for(int[] nextNode: graph[cur]){
                    int next = nextNode[0];
                    int nextCost = nextNode[1];
                    if(minCosts[next] > minCosts[cur] + nextCost){
                        minCosts[next] = minCosts[cur] + nextCost;
                        pq.add(new int[]{next,minCosts[next]});
                    }
                }
            }

            if(!found){
               return -1;
            }

        }

        return res;
    }

    public long minimumCostByDijkstra_2(String source, String target, char[] original, char[] changed, int[] cost) {

        List<int[]>[] graph = new ArrayList[26];
        for(int i =0;i< 26;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i =0;i< original.length;i++){
            int i1 = original[i]-'a';
            int i2 = changed[i]-'a';
            int i3 = cost[i];
           graph[i1].add(new int[]{i2,i3});
        }
        HashMap<Integer,int[]> map = new HashMap<>();
        for(int i = 0;i< source.length();i++){
            if(map.containsKey(source.charAt(i)-'a')){
                continue;
            }
            int[] minCosts = new int[26];
            Arrays.fill(minCosts,Integer.MAX_VALUE);
            int[] startNOde = new int[]{source.charAt(i)-'a',0};
            PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
            pq.add(startNOde);
            minCosts[source.charAt(i)-'a'] =0;
            while(!pq.isEmpty()){
                int[] curNode = pq.poll();
                int cur = curNode[0];
                int curCost = curNode[1];

                if(curCost > minCosts[cur]){
                    continue;
                }
                minCosts[cur] = curCost;

                for(int[] nextNode: graph[cur]){
                    int next = nextNode[0];
                    int nextCost = nextNode[1];
                    if(minCosts[next] > minCosts[cur] + nextCost){
                        minCosts[next] = minCosts[cur] + nextCost;
                        pq.add(new int[]{next,minCosts[next]});
                    }
                }
            }
            map.put(source.charAt(i)-'a',minCosts);
        }

        long res = 0;
        for(int i = 0;i< source.length();i++){
            int[] minCosts = map.get(source.charAt(i) - 'a');
            if(minCosts[target.charAt(i) -'a'] == Integer.MAX_VALUE){
                return -1;
            }
            res+= minCosts[target.charAt(i) -'a'];
        }
        return res;
    }
   
}
