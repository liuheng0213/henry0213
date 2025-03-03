package basic.knowledge.henry.algorithm.InterviewExperience.gs.jump_game_related;

public class JumpGame {
    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();
        int jump = jumpGame.jump(new int[]{3, 1, 4, 2,1,1,1});
        System.out.println(jump);
    }
    public int jump(int[] nums) {
        int n = nums.length;
        int lastReach = 0, farthest = 0;
        int jumps = 0;
        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(nums[i] + i, farthest);
            //why not jump when lastReach > i, because it does not suit greedy rule or it means it jump too early , we plan to get the minimum jump
            //why not jump when  lastReach < i, because it cannot jump to the farthest
            if (lastReach ==i) {
                jumps++;
                lastReach = farthest;
            }
        }
        return jumps;
    }
}
