package basic.knowledge.henry.algorithm.InterviewExperience.At;

import java.util.*;

public class Leetcode212 {


        static int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public List<String> findWords(char[][] board, String[] words) {
            int m = board.length;
            int n = board[0].length;

            Map<String, Boolean> targetMap = new HashMap<>();
            for (String str : words) {
                for (int i = 0; i < str.length() - 1; i++) {
                    String sub = str.substring(0, i + 1);
                    if(!targetMap.containsKey(sub)){
                        targetMap.put(sub,false);
                    }
                }
                targetMap.put(str,true);
            }
            HashSet<String> tempRes = new HashSet<>();
            boolean[][] used = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    used[i][j] = true;
                    dfs(board, used, targetMap, tempRes, String.valueOf(board[i][j]), i, j, m, n);
                    used[i][j] = false;
                }
            }

            return new ArrayList<>(tempRes);

        }

        private void dfs(char[][] board, boolean[][] used, Map<String, Boolean> targetMap, HashSet<String> tempRes, String tempStr, int i, int j, int rowLen, int colLen) {
            if (!targetMap.containsKey(tempStr)) {
                return;
            }

            if (targetMap.get(tempStr)) {
                tempRes.add(new String(tempStr));
            }

            for (int k = 0; k < dirs.length; k++) {
                int newI = i + dirs[k][0];
                int newJ = j + dirs[k][1];
                if (newI < 0 || newI >= rowLen || newJ < 0 || newJ >= colLen || used[newI][newJ]) {
                    continue;
                }
                used[newI][newJ] = true;
                tempStr += board[newI][newJ];
                dfs(board, used, targetMap, tempRes, tempStr, newI, newJ, rowLen, colLen);
                used[newI][newJ] = false;
                tempStr = tempStr.substring(0, tempStr.length() - 1);
            }

        }

}
