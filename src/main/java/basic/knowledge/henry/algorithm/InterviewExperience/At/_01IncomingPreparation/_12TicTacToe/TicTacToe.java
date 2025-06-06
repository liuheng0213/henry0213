package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation._12TicTacToe;



/**
 * requirements:
 *
 * Create a TicTacToe component that manages the game state
 *
 * Track the state of the board using React state
 *
 * Handle player moves and turn switching
 *
 * Implement win detection logic for all possible win conditions:
 *
 * 3 in a row horizontally
 * 3 in a row vertically
 * 3 in a row diagonally
 * Display a message when a player wins or when the game ends in a draw
 *
 * Include a "Reset Game" button to start over
 *
 * Style the game board appropriately
 */
public class TicTacToe {

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
    int[] rows;
    int[] cols;
    int diagonal;
    int antiDiagonal;
    public TicTacToe(int n) {
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
