package basic.knowledge.henry.algorithm.InterviewExperience.At.snakeGame;


import basic.knowledge.henry.algorithm.InterviewExperience.ListNode;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class SnakeGame {


    public static void main(String[] args) {


    }

    LinkedList<int[]> snake;
    int width;
    int height;
    int[][] food;
    int idx = 0;

    boolean isGameOver;
    public SnakeGame(int width, int height, int[][] food) {
        this.snake = new LinkedList<>();
        this.snake.add(new int[]{0,0});
        this.width = width;
        this.height = height;
        this.food = food;
        this.isGameOver = false;
    }

    public int move(String direction) {
        if(isGameOver()){
            return  -1;
        }
        int[] head = this.snake.peekFirst();
        int i = head[0];
        int j = head[1];
        if(direction.equals("R")){
            j++;
        }else if(direction.equals("L")){
            j--;
        }else if(direction.equals("D")){
            i++;
        }else{
            i--;
        }


        if(i< 0 || i> height - 1 || j< 0 || j> width - 1){
            isGameOver = true;
            return -1;
        }

        if(idx>food.length -1 || (i != food[idx][0] || j != food[idx][1])){
            snake.pollLast();
        }else{
            idx++;
        }

        snake.addFirst(new int[]{i,j});
        for(int k = 1;k< snake.size();k++){
            if(i == snake.get(k)[0] && j == snake.get(k)[1]){
                isGameOver = true;
                return -1;
            }
        }

        return idx;
    }

    public boolean isGameOver(){
        return isGameOver;
    }
}
