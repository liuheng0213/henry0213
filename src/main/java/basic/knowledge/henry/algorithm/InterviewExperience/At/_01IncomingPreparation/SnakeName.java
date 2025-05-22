package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation;

import java.util.LinkedList;

public class SnakeName {
    LinkedList<int[]> body;
    int m;
    int n;

    int steps;

    boolean isGameOver;

    public SnakeName(LinkedList<int[]> body, int[][] board, int m, int n) {
        this.body = body;
        this.m = m;
        this.n = n;
        this.steps = 0;
        isGameOver= false;
    }


    public void move(String dir){
        if(isGameOver){
            return;
        }
        int[] head = this.body.peekFirst();
        int i = head[0];
        int j = head[1];
        if(dir.equals("D")){
            i++;
        }else if(dir.equals("U")){
            i--;
        }else if(dir.equals("L")){
            j--;
        }else{
            j++;
        }
        if(i == -1){
            i = m - 1;
        }else if(i == m){
            i = 0;
        }else if(j == -1){
            j = n - 1;
        }else if(j == n){
            j = 0;
        }

        head = new int[]{i,j};
        body.addFirst(head);
        steps++;
        if(steps % 5 != 0){
            body.pollLast();
        }

        for(int k = 1;k< this.body.size();k++){
            int[] part = this.body.get(k);
            if(part[0] == head[0] && part[1] == head[1]){
                isGameOver = true;

            }
        }

    }
}
