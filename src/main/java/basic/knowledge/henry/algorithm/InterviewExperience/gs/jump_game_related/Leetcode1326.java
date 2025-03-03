package basic.knowledge.henry.algorithm.InterviewExperience.gs.jump_game_related;

import java.util.Arrays;

public class Leetcode1326 {
    public static void main(String[] args) {
        Leetcode1326 leetcode1326 = new Leetcode1326();
        int res = leetcode1326.minTaps(5, new int[]{3,4,1,1,0,0});
        System.out.println(res);

    }

    /**
     * reach[i]的更新思路也很简单，我们选择在所有i能跳到的位置中，能到达最远的那个位置。
     * 也就是说，如果我们记start = i-ranges[i],end = i+ ranges[i]，
     * 我们去找start和end之间的reach[j]和end的最大值。如果我们得到的最大值还是它本身（没有办法跳到其他位置），那么我们就返回-1。

     * @param n
     * @param ranges
     * @return
     */
    // easy to understand
    public int minTaps3(int n, int[] ranges) {
        int[] reach = new int[n+1];
        //reach[i] = j means coverage range is 0, 3
        // for (0,3) we have reach[0] = 3;reach[1] = 3;reach[2] = 3;reach[3] = 3
        // for (2,4) we have reach[2] = 4;reach[3] = 4;reach[4] = 4
        for(int i=0;i<=n;i++)
        {
            int start = Math.max(0, i-ranges[i]);
            int end = Math.min(n,i+ranges[i]);
            for(int j=start;j<=end;j++)
            {
                reach[j] = Math.max(end,reach[j]);
            }
            System.out.println("===");
        }
        int res = 0;
        int curPos = 0;
        while(curPos<n)
        {
            if(reach[curPos]==curPos)  // == is also correct, strictly  reach[curPos] is impossibly less than curPos
            {
                return -1;
            }
            curPos = reach[curPos];
            res++;
        }
        return res;
    }



    public int minTaps2(int n, int[] ranges) {
        //definition is_reachable[i] = j means the minimum steps is j  to reach i
         int[] is_reachable = new int[n+1];

        int inf = (int) 1e9;

        Arrays.fill(is_reachable, inf);
        is_reachable[0] = 0;

        for(int i = 0;i < ranges.length;i++){
            int mn = Math.max(0,i-ranges[i]);
            int mx = Math.min(n,i+ranges[i]);

            for(int j = mn;j < mx;j++){
                is_reachable[mx] = Math.min(is_reachable[mx],is_reachable[j]+1);
            }

        }

        return is_reachable[n] == inf ? -1 : is_reachable[n];
    }
    public int minTaps(int n, int[] ranges) {
        int[][] intervals = new int[n+1][2];
        for(int i =0;i< n+ 1;i++){
            int left = Math.max(0,i - ranges[i]);
            int right = Math.min(n,i + ranges[i]);
            intervals[i]= new int[]{left,right};
        }


        Arrays.sort(intervals,(a,b)-> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        int res = 0;
        int farthest = 0;
        int idx = 0;
        while(idx < n + 1){
            if(farthest < intervals[idx][0]){
                break;
            }

            int rtest = farthest;
            while(idx< n +1 && farthest >= intervals[idx][0]){
                rtest = Math.max(rtest,intervals[idx][1]);
                idx++;
            }
            farthest = rtest;
            res++;


            if(farthest == n){
                break;
            }
        }

        return farthest == n ? res : -1;

    }
}
