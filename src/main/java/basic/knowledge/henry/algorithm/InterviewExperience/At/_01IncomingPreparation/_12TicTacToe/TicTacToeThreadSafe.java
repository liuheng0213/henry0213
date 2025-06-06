package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation._12TicTacToe;

public class TicTacToeThreadSafe {


    public static void main(String[] args) {
        TicTacToe tictactoe = new TicTacToe(3);
        tictactoe.move(0,0,1);
        tictactoe.move(0,2,2);
        tictactoe.move(2,2,1);
        tictactoe.move(1,1,2);
        tictactoe.move(2,0,1);
        tictactoe.move(1,0,2);
        tictactoe.move(2,1,1);
        System.out.println("=====");
    }
    int[] rows; // change to
    int[] cols;
    int diagonal;
    int antiDiagonal;
    public TicTacToeThreadSafe(int n) {
        rows= new int[n];
        cols = new int[n];
        diagonal = 0;
        antiDiagonal = 0;

    }

    public int move(int row, int col, int player) {
        if(player == 1){
            rows[row]++;
            cols[col]++;
            if(row== col){
                diagonal++;
            }
            if(row + col == rows.length - 1){
                antiDiagonal++;
            }

        }else{
            rows[row]--;
            cols[col]--;
            if(row== col){
                diagonal--;
            }
            if(row + col == rows.length - 1){
                antiDiagonal--;
            }


        }
        if(checkWinner(row,col) == player){
            return player;
        }

        return 0;
    }

    public int checkWinner(int row,int col){
        int n = rows.length;

        if(rows[row] == n){
            return 1;
        }else if(rows[row] == -n ){
            return 2;
        }

        if(cols[col] == n){
            return 1;
        }else if(cols[col] == -n ){
            return 2;
        }

        if(diagonal == n || antiDiagonal == n){
            return 1;
        }else if(diagonal == -n || antiDiagonal == -n){
            return 2;
        }

        return 0;
    }
}
