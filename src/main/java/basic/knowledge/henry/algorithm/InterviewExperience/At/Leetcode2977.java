package basic.knowledge.henry.algorithm.InterviewExperience.At;

import java.util.*;

public class Leetcode2977 {
    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.MIN_VALUE);
        Leetcode2977 leetcode2977 = new Leetcode2977();
        String source = "abcdefgh";
        String target = "acdeeghh";
        String[] original = new String[]{"bcd","fgh","thh"};
        String[] changed = new String[]{"cde","thh","ghh"};
        int[] cost = new int[]{1,3,5};
        long res = leetcode2977.minimumCost(source, target, original, changed, cost);
        System.out.println(res);
    }




    Trie trie = new Trie();
    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {


        //adding all string to get index (like adding to arraylist)
        for(int i=0;i<original.length;i++){
            trie.get(original[i]);
            trie.get(changed[i]);
        }
        //adjacent matrix for original to target
        int n=trie.len;
        int[][] adj=new int[n][n];
        for(int i=0;i<n;i++){Arrays.fill(adj[i],Integer.MAX_VALUE);adj[i][i]=0;}
        for(int i=0;i<cost.length;i++){
            int oriIndex=trie.get(original[i]);
            int chaIndex=trie.get(changed[i]);
            adj[oriIndex][chaIndex]=Math.min(adj[oriIndex][chaIndex],cost[i]);
        }
        //as a->b, b->c is there then a->c will be there so doing floyd warshall algorithm
        floydWarshall(adj);
        return helper(source.toCharArray(),target.toCharArray(),adj);
    }

    public long helper(char[] s, char[] t, int[][] adj){
        int m=s.length;
        //dp[i] represent source in 0~i-1 convert to target in 0~i-1
        long[] dp=new long[m+1];
        for(int i=0;i<=m;i++)dp[i]=Long.MAX_VALUE;
        dp[0]=0;
//        for(int i = 0;i< m;i++){
//            if(dp[i]== Long.MAX_VALUE){
//                continue;
//            }
//            if(s[i]==t[i]){
//                // System.out.println(" dp i " + dp[i]);
//                // System.out.println(" dp i+1 " + dp[i+1]);
//                // dp[i+1] = dp[i];;
//                //  System.out.println(" dp i+1 final " + dp[i+1])
//                dp[i+1]= Math.min(dp[i],dp[i+1]);
//            }
//
//            Node souNode =trie.root;
//            Node tarNode =trie.root;
//
//            for(int j = i+1;j<m+1;j++){
//                //if we get index from source and target we can find adj[i][j] of that
//                souNode=souNode.child[(int)(s[j-1]-'a')];
//                tarNode=tarNode.child[(int)(t[j - 1]-'a')];
//                //if we do not have any souce or target then there is no point in going further
//                if(souNode==null || tarNode == null){
//                    break;
//                }
//                if(souNode.isEnd && tarNode.isEnd){
//                    if(adj[souNode.idx][tarNode.idx] != Integer.MAX_VALUE && dp[i] != Long.MAX_VALUE){
//                        dp[j]=Math.min(dp[j],adj[souNode.idx][tarNode.idx]+dp[i]);
//                    }
//                }
//
//            }
//        }


        for(int i = 1;i< m+1;i++){
//            long tmp = dp[i];
            if(s[i - 1] == t[i -1]){
                dp[i] = dp[i-1];
            }

            long tmp = dp[i];
            for(int j = 0;j<= i -1;j++){
                if(dp[j] == Long.MAX_VALUE) {
                    continue;
                };

                //dp[j] is done
                String source =new String(s,j,i-j);
                String target = new String(t,j,i-j);


                int s_idx = trie.query(source);
                int t_idx = trie.query(target);
                if(s_idx != -1 && t_idx != -1){
                    if(adj[s_idx][t_idx]!= Integer.MAX_VALUE && Long.MAX_VALUE != dp[j]){
                        tmp =dp[j] + adj[s_idx][t_idx];
                    }
                }


                dp[i] = Math.min(dp[i],tmp);
            }
        }


        return dp[m]==Long.MAX_VALUE?-1:dp[m];
    }
    public void floydWarshall(int [][] adj){
        int n=adj.length;
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                if(adj[i][k]!=Integer.MAX_VALUE)
                    for(int j=0;j<n;j++){
                        if(adj[k][j]!=Integer.MAX_VALUE && adj[i][j]>adj[i][k]+adj[k][j]){
                            adj[i][j]=adj[i][k]+adj[k][j];
                        }
                    }
            }
        }
    }

    class Trie{
        int len;
        Node root;
        public Trie(){
            this.root =new Node();
            this.len = 0;
        }

        int query(String str){
            Node cur = this.root;
            char[] chs = str.toCharArray();
            for(int i=0;i<chs.length;i++){
                int index=(int)(chs[i]-'a');
                if(cur.child[index]==null) {
                    return -1;
                }
                cur=cur.child[index];
            }
            return cur.isEnd? cur.idx : -1;
        }
        int get(String str){//serves as both add or get str
            Node cur = this.root;
            char[] chstr =str.toCharArray();

            for(int i=0;i<chstr.length;i++){
                int index=(int)(chstr[i]-'a');
                if(cur.child[index]==null) {
                    cur.child[index] = new Node();
                }
                cur=cur.child[index];
            }
            cur.isEnd = true;
            if(cur.idx==-1){
                cur.idx=len++;
            }
            return cur.idx;
        }
    }

    class Node{
        Node[] child;
        int idx;

        boolean isEnd;
        public Node(){
            child=new Node[26];
            idx=-1;
            isEnd = false;
        }
    }







}
