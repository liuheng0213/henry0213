package basic.knowledge.henry.algorithm.InterviewExperience.At;

import java.util.*;

public class Leetcode911 {
    public static void main(String[] args) {

        int[] arr1 = new int[]{0,1,1,0,0,1,0};
        int[] arr2 = new int[]{0,5,10,15,20,25,30};
        TopVotedCandidate obj = new TopVotedCandidate(arr1, arr2);
        int q1 = obj.q(0);
        int q = obj.q(12);

        System.out.println(q);
    }
    static class TopVotedCandidate {
        Map<Integer, Integer> m = new HashMap<>();
        int[] time;
        public TopVotedCandidate(int[] persons, int[] times) {
            int n = persons.length, lead = -1;
            Map<Integer, Integer> count = new HashMap<>();
            time = times;
            for (int i = 0; i < n; ++i) {
                count.put(persons[i], count.getOrDefault(persons[i], 0) + 1);
                if (i == 0 || count.get(persons[i]) >= count.get(lead)) lead = persons[i];
                m.put(times[i], lead);
            }
        }

        public int q(int t) {
            int i = Arrays.binarySearch(time, t);
            return i < 0 ? m.get(time[-i-2]) : m.get(time[i]);
        }
    }

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */
    
    
}
