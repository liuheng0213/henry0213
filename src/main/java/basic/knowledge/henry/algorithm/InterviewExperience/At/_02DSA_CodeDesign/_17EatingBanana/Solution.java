package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._17EatingBanana;

/**
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas.
 * The guards have gone and will come back in h hours.
 * Koko can decide her bananas-per-hour eating speed of k.
 * Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas,
 * she eats all of them instead and will not eat any more bananas during this hour.
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 */
public class Solution {
    public static void main(String[] args) {

        int i = new Solution().minEatingSpeed(new int[]{3, 6, 7, 10}, 8);
        System.out.println(i);
    }
    //f(k) is the hour: ak is decreasing when k is increasing
    //need to find minimum k which will get the maximum ak,but still smaller or equal to k
    public int minEatingSpeed(int[] piles, int h) {
        long right = 0;
        for(int p : piles){
            right = Math.max(right,p);
        }
        long left = 1;
        long res = -1;
        while(left<=right){
            long midSpeed = (left+right)/2;
            int ak = count(piles,midSpeed);
            if(ak > h){ //midSpeed is slow
                left = midSpeed+1;
            }else if(ak<=h){//midSpeed will cause h or midSpeed is fast
                res = midSpeed;
                right = midSpeed - 1;
            }
        }

        return res== -1? (int)left : (int)res;
    }

    private int count(int[] piles, long speed) {
        int hour = 0;
        for(int p : piles){
            hour+=p/speed;
            if(p%speed != 0){
                hour++;
            }
        }
        return hour;
    }
}
