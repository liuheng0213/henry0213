package basic.knowledge.henry.algorithm.InterviewExperience.ama;
//Minimum Rooks in a Peaceful State

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * You are given a 2D grid (board) that represents a chessboard. The board contains multiple cells, where:
 *
 *
 * 0 indicates an empty cell.
 * 1 indicates a cell containing a rook.
 * A rook can only move vertically or horizontally if there is another rook in its row or column to capture.
 * It cannot move if there are no other rooks in its row or column.
 *
 *
 * Your task is to determine the minimum number of rooks that can be left on the board in a peaceful state after capturing as many rooks as possible.
 * A peaceful state is defined as a state where no two rooks can capture each other (i.e., no two rooks share the same row or column).
 */
public class Test7 {
    public static void main(String[] args) {
        int i = new Test7().rooksLeft(new int[][]{{0, 0, 1,0,0}, {1, 0, 1,0,0}, {1, 0, 0,0,1},{0,0,1,1,0}});
        System.out.println(i);
    }

    public int rooksLeft(int[][] board){
        int m = board.length;
        int n = board[0].length;

        HashMap<Integer, Integer> codeToId = new HashMap<>();
        int capacity = 0;
       for(int i =0;i< m;i++){
           for(int j =0;j< n;j++){
               if(board[i][j] == 1){
                   int encoded =encode(i,j,n);
                   codeToId.put(encoded,capacity++);
               }
           }
       }

       if(capacity == 0){
           return 0;
       }
        UF uf = new UF(capacity);

        // 记录每一行每一列有哪些节点
//        HashMap<Integer, List<Integer>> colIndexToCodes = new HashMap<>();
//        HashMap<Integer, List<Integer>> rowIndexToCodes = new HashMap<>();
//        for(int i =0;i< m;i++){
//
//        }


        for(int i = 0;i< m;i++){
            List<Integer> oneIdx = new ArrayList<>();
           for(int j = 0;j< n;j++){
               if(board[i][j] == 1){
                   oneIdx.add(encode(i,j,n));
               }
           }

           if(oneIdx.size() > 1){
               int pre = codeToId.get(oneIdx.get(0));
               for(int k = 1;k< oneIdx.size();k++){
                   int cur = codeToId.get(oneIdx.get(k));
                   uf.union(pre,cur);
                   pre = cur;
               }
           }
        }

        for(int j = 0;j< n;j++){
            List<Integer> oneIdx = new ArrayList<>();
            for(int i = 0;i< m;i++){
                if(board[i][j] == 1){
                    oneIdx.add(encode(i,j,n));
                }
            }

            if(oneIdx.size() > 1){
                int pre = codeToId.get(oneIdx.get(0));
                for(int k = 1;k< oneIdx.size();k++){
                    int cur = codeToId.get(oneIdx.get(k));
                    uf.union(pre,cur);
                    pre = cur;
                }
            }
        }

        return uf.count();
    }

    private Integer encode(int i, int j,int n) {
        return i * n + j;
    }

    private class UF {
        int n = 0;
        int[] ids;
        public UF(int n) {
            this.n = n;
            this.ids = new int[this.n];
            for(int i =0;i< ids.length;i++){
                ids[i] = i;
            }
        }

        public boolean isConnected(int i, int j) {
            return find(i) == find(j);
        }

        private int find(int i) {
            if(ids[i] == i){
                return ids[i];
            }
            return find(ids[i]);
        }

        public int count() {
            return this.n;
        }

        public void union(int i, int j) {
            if(isConnected(i,j)){
                return;
            }

            int parentI = find(i);
            int parentJ = find(j);

            ids[parentI] = parentJ;
            this.n--;
        }
    }
}
