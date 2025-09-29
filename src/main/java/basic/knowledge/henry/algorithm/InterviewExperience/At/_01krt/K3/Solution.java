package basic.knowledge.henry.algorithm.InterviewExperience.At._01krt.K3;

/*
You are working on a video game where the player has to go through a level without falling into any obstacles.

The player starts at position zero and can move in three ways:
- L (left)  => one position to the left
- R (right) => one position to the right
- J (jump)  => move two positions, in the direction of the previous move

The player starts at position 0 and the exit will always be at position 10.

The instructions never lead the player outside the level boundaries, and the first move is always right.

Write a function that given the instructions and the positions of the obstacles, returns True if the instructions lead to the exit position, and False otherwise.

For example:

Obstacles 1: [4,6]

--------------------------------------------------------
  ->                X         X                   Exit
--------------------------------------------------------
0    1    2    3    4    5    6    7    8    9    10


Instructions 1: "RRRJJRRR" -> True.

                  JUMP      JUMP
-----------------^    ^----^    ^-----------------------
  ->   ->   ->      X         X      ->   ->   -> Exit
--------------------------------------------------------
0    1    2    3    4    5    6    7    8    9    10


Instructions 2: "RRRLJ" -> False, it would just lead back to the start.

Instructions 3: "RRRJJRRRL" -> True, extra instructions can be ignored.

Instructions 4: "RRRLRJJRRR" -> True, the player is allowed to move back and forth.

Instructions 5: "RRRRRRRRRR" -> False, due to falling into an obstacle.

Instructions 6: "RRJJJJ" -> False, as the jump would land on an obstacle.

Instructions 7: "RLRRRJJRRLLJJJLRRRJJRRR" -> True, even after many instructions, exit is reached.

Instructions 8: "RRRJJRLJJJRRR" -> False, as directions of jumps must be observed.

Instructions 9: "R" -> False, as the exit position isn't reached.

Instructions 10: "RJJJJR" -> True, as it gets to the exit after avoiding the obstacles.

Instructions 11: "RJJRRRRR" -> False, as it hits an obstacle.

Instructions 12: "RJJRRRJ" -> True, as it avoids all obstacles.

Instructions 13: "RJJJLJRJRJ" -> False, as it jumps to an obstacle.

Obstacles 2: [9,4,2], Instructions 12: "RJJRRRJ" -> True, as it gets to the exit after avoiding the obstacles.

Obstacles 3: [], Instructions 9: -> False

All test cases:

obstacles_1 = [4,6]
obstacles_2 = [9,4,2]
obstacles_3 = []

level(obstacles_1, instructions_1) # True
level(obstacles_1, instructions_2) # False
level(obstacles_1, instructions_3) # True
level(obstacles_1, instructions_4) # True
level(obstacles_1, instructions_5) # False
level(obstacles_1, instructions_6) # False
level(obstacles_1, instructions_7) # True
level(obstacles_1, instructions_8) # False
level(obstacles_1, instructions_9) # False
level(obstacles_1, instructions_10) # True
level(obstacles_2, instructions_11) # False
level(obstacles_2, instructions_12) # True
level(obstacles_2, instructions_13) # False
level(obstacles_3, instructions_9)  # False

Complexity variables:

N - number of instructions.

*/


public class Solution {
    public static void main(String[] argv) {
        int[] obstacles_1 = {4, 6};
        int[] obstacles_2 = {9, 4, 2};
        int[] obstacles_3 = {};

        int[] paths = new int[11];

        String instructions_1 = "RRRJJRRR";
        String instructions_2 = "RRRLJ";
        String instructions_3 = "RRRJJRRRL";
        String instructions_4 = "RRRLRJJRRR";
        String instructions_5 = "RRRRRRRRRR";
        String instructions_6 = "RRJJJJ";
        String instructions_7 = "RLRRRJJRRLLJJJLRRRJJRRR";
        String instructions_8 = "RRRJJRLJJJRRR";
        String instructions_9 = "R";
        String instructions_10 = "RJJJJR";
        String instructions_11 = "RJJRRRRR";
        String instructions_12 = "RJJRRRJ";
        String instructions_13 = "RJJJLJRJRJ";
        // boolean res1 = new Solution().solution(obstacles_1,instructions_1);
        // System.out.println(res1);
        //  boolean res2 = new Solution().solution(obstacles_1,instructions_2);
        // System.out.println(res2);
//        System.out.println(res3);


        boolean re1 = level(obstacles_1, instructions_1); //# True
        boolean re2 =level(obstacles_1, instructions_2); //# False
        boolean re3 =level(obstacles_1, instructions_3); //# True
        boolean re4 =level(obstacles_1, instructions_4); //# True
        boolean re5 =level(obstacles_1, instructions_5); //# False
        boolean re6 = level(obstacles_1, instructions_7); //# True
        boolean re7 =level(obstacles_1, instructions_8); //# False
        boolean re8 =level(obstacles_1, instructions_9); //# False
        boolean re9 =level(obstacles_1, instructions_10);// # True
        boolean re10 =level(obstacles_2, instructions_11);// # False
        boolean re11 =level(obstacles_2, instructions_12) ;//# True
        boolean re12 =level(obstacles_2, instructions_13); //# False
        boolean re13 =level(obstacles_3, instructions_9);
    }
    public static boolean level(int[] obstacles,String dirs){
        int[] paths = new int[11];
        for(int ob: obstacles){
            paths[ob] = -1;
        }

        char[] chs = dirs.toCharArray();
        char preDir = 'R';
        int cur = 0;
        for(char dir : chs){
            if(dir == 'R'){
                cur++;
                System.out.println(cur);
                if(cur >= 10){
                    return true;
                }
                boolean flag =validate(paths,cur);
                if(!flag){
                    return false;
                }
                preDir = dir;
            }else if(dir == 'L'){
                cur--;
                boolean flag =validate(paths,cur);
                if(!flag){
                    return false;
                }
                preDir = dir;
            }else{
                if(preDir == 'R'){
                    cur+=2;
                    if(cur >= 10){
                        return true;
                    }
                    boolean flag =validate(paths,cur);
                    if(!flag){
                        return false;
                    }
                }else{
                    cur-=2;
                    boolean flag =validate(paths,cur);
                    if(!flag){
                        return false;
                    }
                }
            }
        }

        return cur >= 10;
    }

    private static boolean validate(int[] paths,int position){
        if(position < 0){
            return false;
        }
        if(paths[position] == -1){
            return false;
        }
        return true;
    }


}