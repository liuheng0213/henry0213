package basic.knowledge.henry.algorithm.InterviewExperience.At._03update_1;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class _01InfectionSequences {

    private static final long MOD = 1_000_000_007L;

    public int countInfectionSequences(int n, int[] infectedHouses) {
        // If all houses are initially infected, the "new infection" sequence is empty: exactly 1 way.
        if (infectedHouses.length == n) {
            return 1;
        }

        // 1) Multi-source BFS to compute infection day for each house
        // the ith house will be infected at which date
        int[] day = new int[n + 1]; // 1-based index, day[i] = day i gets infected
        Arrays.fill(day, -1);

        Queue<Integer> queue = new ArrayDeque<>();
        for (int h : infectedHouses) {
            day[h] = 0;
            queue.offer(h);
        }

        int maxDay = 0;

        while (!queue.isEmpty()) {
            int x = queue.poll();
            int d = day[x];

            // Try neighbors x-1 and x+1
            if (x - 1 >= 1 && day[x - 1] == -1) {
                day[x - 1] = d + 1;
                maxDay = Math.max(maxDay, d + 1);
                queue.offer(x - 1);
            }
            if (x + 1 <= n && day[x + 1] == -1) {
                day[x + 1] = d + 1;
                maxDay = Math.max(maxDay, d + 1);
                queue.offer(x + 1);
            }
        }

        // 2) Count how many houses are infected on each day (exclude day 0 – initial ones)
        int[] cnt = new int[maxDay + 1]; // cnt[d] = how many houses get infected on day d
        for (int i = 1; i <= n; i++) {
            if (day[i] > 0) {// exclude day[i]== 0 which means at the i-th day , it is affected 0 times
                cnt[day[i]]++;
            }
        }
        //求阶乘
        long ans = 1;
        for(int i = 0;i< cnt.length;i++){
            if(cnt[i] > 0){
                ans *= calcfactor(cnt[i]);
            }
        }



//        // 3) Precompute factorials up to n
//        long[] fact = new long[n + 1];
//        fact[0] = 1;
//        for (int i = 1; i <= n; i++) {
//            fact[i] = fact[i - 1] * i % MOD;
//        }
//
//        // 4) Answer = product over d >= 1 of (cnt[d]!) mod MOD
//        long ans = 1;
//        for (int d = 1; d <= maxDay; d++) {
//            if (cnt[d] > 0) {
//                ans = ans * fact[cnt[d]] % MOD;
//            }
//        }

        return (int) ans;
    }

    private long calcfactor(int n) {
        long fact= 1;
        for(int i = 1;i<=n;i++){
            fact*= i;
        }

        return fact;
    }


    public static void main(String[] args) {
        _01InfectionSequences infectionSequences = new _01InfectionSequences();
        // Test examples
        System.out.println(infectionSequences.countInfectionSequences(5, new int[]{1, 5}));  // Expected: 2
        System.out.println(infectionSequences.countInfectionSequences(6, new int[]{3, 5}));  // Expected: 6
        System.out.println(infectionSequences.countInfectionSequences(4, new int[]{1}));     // Expected: 1

        // Additional test cases
        System.out.println(infectionSequences.countInfectionSequences(1, new int[]{1}));     // Expected: 1
        System.out.println(infectionSequences.countInfectionSequences(3, new int[]{1, 3}));  // Expected: 1
    }
}
