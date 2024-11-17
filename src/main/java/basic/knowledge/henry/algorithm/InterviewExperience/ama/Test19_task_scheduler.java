package basic.knowledge.henry.algorithm.InterviewExperience.ama;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test19_task_scheduler {
    public static void main(String[] args) {
        int minTime = new Test19_task_scheduler().getMinTime( "acabdb".toCharArray(), 1);//ab_ab_ab
        System.out.println(minTime);
    }


    public int getMinTime(char[] tasks, int minGap) {
        int[] frequencies = new int[26];
        for (char task : tasks) {
            frequencies[task - 'a']++;
        }
        Arrays.sort(frequencies);
        int maxFrequency = frequencies[25] - 1;
        int idleSlots = maxFrequency * minGap;

        for (int i = 24; i >= 0 && frequencies[i] > 0; i--) {
            idleSlots -= Math.min(frequencies[i],maxFrequency);
//            idleSlots -= frequencies[i];
        }

        return idleSlots > 0 ? idleSlots + tasks.length : tasks.length;

    }

}
