package basic.knowledge.henry.algorithm.InterviewExperience.At;

public class Leetcode79 {
    class Solution {
        int[][] dirs = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        private boolean visited[][];
        public boolean exist(char[][] board, String word) {
            int m = board.length;
            int n = board[0].length;
            for(int i =0;i< m;i++){
                for(int j = 0;j< n;j++){
                    if(dfs(board,word,0,i,j)){
                        return true;
                    }
                }
            }
            return false;

        }

        //method meaning: 从i, j ,index 起 做dfs. 凡是board[i][j]往后 = word[index~ len]的返回true
        public boolean dfs(char[][] board,String word,int index,int i,int j){
            if(index == word.length()){
                return true;
            }
            if(i < 0 || j < 0 || i>board.length - 1|| j >  board[0].length - 1 ||board[i][j] == ' ' ){
                return false;
            }


            if(word.charAt(index) != board[i][j]){
                return false;
            }

            boolean flag = false;
            char ch = board[i][j];
            board[i][j] =' ';
            for(int k = 0;k< dirs.length;k++){
                int nextI = i + dirs[k][0];
                int nextJ = j+ dirs[k][1];
                flag = flag || dfs(board,word,index + 1,nextI,nextJ);
            }
            board[i][j] = ch;
            return flag;
        }
    }
}
