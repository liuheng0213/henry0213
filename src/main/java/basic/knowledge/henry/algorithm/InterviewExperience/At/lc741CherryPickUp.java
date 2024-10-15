package basic.knowledge.henry.algorithm.InterviewExperience.At;

public class lc741CherryPickUp {
    public static void main(String[] args) {
        lc741CherryPickUp lc741CherryPickUp = new lc741CherryPickUp();
        int[][] grid = new int[][]{{0,1,-1}, {1,0,-1}, {1,1,1}};
        lc741CherryPickUp.cherryPickup(grid);
    }


    //tle
    int max = 0;

    public int cherryPickup(int[][] grid) {
        TopToBottom(grid, 0, 0, 0);
        return max;
    }

    public void TopToBottom(int[][] grid, int i, int j, int totalCount) {
        if (i >= grid.length || j >= grid[0].length || grid[i][j] == -1) {
            return;
        }
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            bottomUp(grid, i, j, totalCount);
            return;
        }
        int temp = grid[i][j];
        grid[i][j] = 0;
        TopToBottom(grid, i + 1, j, totalCount + temp);
        TopToBottom(grid, i, j + 1, totalCount + temp);
        grid[i][j] = temp;
    }

    public void bottomUp(int[][] grid, int i, int j, int totalCount) {
        if (i < 0 || j < 0 || grid[i][j] == -1) {
            return;
        }
        if (i == 0 && j == 0) {
            totalCount += grid[i][j];
            max = Math.max(max, totalCount);
            return;
        }
        int temp = grid[i][j];
        grid[i][j] = 0;
        bottomUp(grid, i - 1, j, totalCount + temp);
        bottomUp(grid, i, j - 1, totalCount + temp);
        grid[i][j] = temp;
    }
}


class Solution741 {

    int m = 0;
    int n = 0;
    Integer[][][][] dp = null;
    public int cherryPickup(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        dp= new Integer[m][n][m][n];

        int ans =  helper(0,0,0,0,grid);
        return ans == Integer.MIN_VALUE ? 0 : ans;
    }

    private int helper(int r1,int c1,int r2,int c2,int[][] grid){
        if(r1 > m - 1 || r2 > m - 1 || c1 > n - 1 || c2 > n - 1 || grid[r1][c1] == -1 ||  grid[r2][c2] == -1){
            return Integer.MIN_VALUE;
        }

        if(r1 == m - 1 && c1 == n - 1 || r2 == m - 1 && c2 == n - 1){
            return grid[m-1][n-1];
        }
        if(dp[r1][c1][r2][c2] != null){
            return dp[r1][c1][r2][c2];
        }
        int cherry = 0;
        if(r1== r2 && c1 == c2){
            cherry +=grid[r1][c1];
        }else{
            cherry +=grid[r1][c1] + grid[r2][c2];
        }

        int res1 = helper(r1+1,c1,r2+1,c2,grid);
        int res2 = helper(r1+1,c1,r2,c2+1,grid);
        int res3 = helper(r1,c1+1,r2+1,c2,grid);
        int res4 = helper(r1,c1+1,r2,c2 +1,grid);
        int max = Math.max(Math.max(res1,res2),Math.max(res3,res4));
        dp[r1][c1][r2][c2] = max == Integer.MIN_VALUE ? max:max + cherry;
        return dp[r1][c1][r2][c2];



    }


}
