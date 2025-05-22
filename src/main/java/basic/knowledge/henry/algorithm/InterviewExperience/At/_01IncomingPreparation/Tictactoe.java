package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation;

public class Tictactoe {
    public static void main(String[] args) {
        Tictactoe tictactoe = new Tictactoe(3);
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

    public Tictactoe(int n) {
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

            if(checkWinner() == player){
                return player;
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

            if(checkWinner() == player){
                return player;
            }
        }

        return 0;
    }

    public int checkWinner(){
        int n = rows.length;
        int sum = 0;
        for(int i = 0;i< n;i++){
            sum+=rows[i];
        }
        if(sum == n){
            return 1;
        }else if(sum == -n ){
            return 2;
        }

        sum = 0;
        for(int i = 0;i< n;i++){
            sum+=cols[i];
        }
        if(sum == n){
            return 1;
        }else if(sum == -n ){
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
