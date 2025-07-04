package basic.knowledge.henry.algorithm.InterviewExperience.At._01krt.k100_harder;

import java.util.ArrayList;
import java.util.List;

public class Karat2 {
   static List<int[]> list = new ArrayList<>();
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

        List<int[]> ints1 = karat2.find2(grid1, word1_2);

        System.out.println();
        list = new ArrayList<>();

        char[][] grid2 = {
                {'a'},
        };
        String word2_1 = "a";

        List<int[]> ints3 = karat2.find2(grid2, word2_1);

        System.out.println();
        list = new ArrayList<>();
        char[][] grid3 = {
                {'c', 'a'},
                {'t', 't'},
                {'h', 'a'},
                {'a', 'c'},
                {'t', 'g'},
        };
        String word3_1 = "cat";
        String word3_2 = "hat";

        List<int[]> ints7 = karat2.find2(grid3, word3_1);
        System.out.println();
        list = new ArrayList<>();
        char[][] grid4 = {
                {'c', 'c', 'x', 't', 'i', 'b'},
                {'c', 'a', 't', 'n', 'i', 'i'},
                {'a', 'x', 'n', 'x', 'p', 't'},
                {'t', 'x', 'i', 'x', 't', 't'},
        };
        String word4_1 = "catnip";

        List<int[]> ints5 = karat2.find2(grid4, word4_1);
        System.out.println();
    }

    private List<int[]> find2(char[][] grid, String word) {
        for(int i= 0;i< grid.length;i++){
            for(int j =0;j< grid[0].length;j++){
                if(word.charAt(0)==grid[i][j]){
                    dfs2(0,word,grid,i,j,new ArrayList<>());
                }
            }
        }

        return list;
    }

    private void dfs2(int idx, String word, char[][] grid,int i,int j,ArrayList<int[]> tmp) {
        if(idx == word.length()){
            list = new ArrayList<>(tmp);
            return;
        }

        if(i< 0|| j<0 || i> grid.length - 1 || j> grid[0].length - 1 || grid[i][j] == '#'){
            return;
        }

        char ch = grid[i][j];
        if(ch != word.charAt(idx)){
            return;
        }

        tmp.add(new int[]{i,j});
        grid[i][j] = '#';

        dfs2(idx+1,word,grid,i+1,j,tmp);
        dfs2(idx+1,word,grid,i,j+1,tmp);

        tmp.remove(tmp.size() - 1);
        grid[i][j] = ch;
    }


    static int[][] dirs = new int[][]{{0, 1}, {1, 0}};




}
