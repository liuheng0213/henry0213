package basic.knowledge.henry.algorithm.InterviewExperience.At.stringGraph;

import java.util.ArrayList;
import java.util.List;

public class Karat2 {
    public static void main(String[] argv) {
        Karat2 karat2 = new Karat2();
        char[][] grid1 = {
                {'b', 'b', 'b', 'a', 'l', 'l', 'o', 'o'},
                {'b', 'a', 'c', 'c', 'e', 's', 'c', 'n'},
                {'a', 'l', 't', 'e', 'w', 'c', 'e', 'w'},
                {'a', 'l', 'o', 's', 's', 'e', 'c', 'c'},
                {'w', 'o', 'o', 'w', 'a', 'c', 'a', 'w'},
                {'i', 'b', 'w', 'o', 'w', 'w', 'o', 'w'},
        };
        String word1_1 = "access";
        String word1_2 = "balloon";
        String word1_3 = "wow";
        String word1_4 = "sec";
        String word1_5 = "bbaal";
        List<int[]> ints0 = karat2.find(grid1, word1_2);
        List<int[]> ints1 = karat2.find2(grid1, word1_2);

        System.out.println();


        char[][] grid2 = {
                {'a'},
        };
        String word2_1 = "a";
        List<int[]> ints2 = karat2.find(grid2, word2_1);
        List<int[]> ints3 = karat2.find2(grid2, word2_1);

        System.out.println();
        char[][] grid3 = {
                {'c', 'a'},
                {'t', 't'},
                {'h', 'a'},
                {'a', 'c'},
                {'t', 'g'},
        };
        String word3_1 = "cat";
        String word3_2 = "hat";
        List<int[]> ints6 = karat2.find(grid3, word3_1);
        List<int[]> ints7 = karat2.find2(grid3, word3_1);
        System.out.println();
        char[][] grid4 = {
                {'c', 'c', 'x', 't', 'i', 'b'},
                {'c', 'a', 't', 'n', 'i', 'i'},
                {'a', 'x', 'n', 'x', 'p', 't'},
                {'t', 'x', 'i', 'x', 't', 't'},
        };
        String word4_1 = "catnip";
        List<int[]> ints4 = karat2.find(grid4, word4_1);
        List<int[]> ints5 = karat2.find2(grid4, word4_1);
        System.out.println();
    }

    static List<int[]> list = new ArrayList<>();
    static int[][] dirs = new int[][]{{0, 1}, {1, 0}};
    public  List<int[]> find(char[][] grid, String target) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == target.charAt(0)) {
                    dfs(i, j, new ArrayList<>(),grid, target, 0);
                }
            }
        }
        return list;
    }

    private void dfs(int i, int j, ArrayList<int[]> tmp, char[][] grid, String target, int idx) {
         if(idx == target.length()){
             return;
         }

         if(idx == target.length() - 1 && grid[i][j] == target.charAt(idx)){
             list = new ArrayList<>(tmp);
             return;
         }
         boolean flag = grid[i][j] == target.charAt(idx);
         if(flag){
             tmp.add(new int[]{i,j});
             idx++;
         }
         for(int k =0;k< dirs.length;k++){
             int nextI = i + dirs[k][0];
             int nextJ = j + dirs[k][1];
             if (nextI > grid.length - 1 || nextJ > grid[0].length - 1) {
                 continue;
             }
             dfs(nextI,nextJ,tmp,grid,target,idx);
         }
        if(flag){
            idx--;
            tmp.remove(tmp.size() - 1);
        }

    }

    public static List<int[]> find2(char[][] grid, String target) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == target.charAt(0)) {
                    ArrayList<int[]> ints = new ArrayList<>();
                    ints.add(new int[]{i,j});
                    dfs2(i, j, grid, target, 0, ints);
                }
            }
        }
        return list;
    }

    private static <E> void dfs2(int i, int j, char[][] grid, String target, int index, ArrayList<int[]> tmp) {
        if (index == target.length()) {
            return;
        }

        if(i == grid.length - 1 && j == grid[0].length - 1){
            if (index == target.length() - 1 && grid[i][j] == target.charAt(index)) {
                list = new ArrayList<>(tmp);
                return;
            }
            return;
        }
        if (index == target.length() - 1 && grid[i][j] == target.charAt(index)) {
            list = new ArrayList<>(tmp);
            return;
        }

        for (int k = 0; k < dirs.length; k++) {
            int nextI = i + dirs[k][0];
            int nextJ = j + dirs[k][1];

            if (nextI > grid.length - 1 || nextJ > grid[0].length - 1) {
                continue;
            }

            boolean flag = grid[i][j] == target.charAt(index);
            if (flag) {
                tmp.add(new int[]{nextI, nextJ});
                index++;
            }
            dfs2(nextI, nextJ, grid, target, index, tmp);
            if (flag) {
                tmp.remove(tmp.size() - 1);
                index--;
            }
        }
    }

}
