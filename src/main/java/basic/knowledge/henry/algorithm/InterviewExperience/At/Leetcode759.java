package basic.knowledge.henry.algorithm.InterviewExperience.At;

import edu.princeton.cs.algs4.In;

import java.util.*;


/**
 * 给你一堆范围，覆盖的范围表示覆盖的区域已经被使用了，让你输出所有的长度有限的没有被覆盖的范围，
 *
 * 在解这种区域覆盖的题目的时候一般是使用一个技巧，就是用一个数组n，n[i]记录位置i开始的范围减去在i结束的范围，
 * 这样遍历一遍数组n就能得到任何一个位置有多少个范围覆盖，当范围覆盖数为0的时候，这个范围就是所求的范围，
 * 这里还需要注意一点，就是最后一个覆盖数为0的范围和第一个覆盖数为0的范围是不参与计数的，因为这两个范围是无限的

 */
public class Leetcode759 {
    public static void main(String[] args) {
        Leetcode759 leetcode759 = new Leetcode759();
        List<List<Interval>> schedule = new ArrayList<>();
        schedule.add(Arrays.asList(new Interval(1,3),new Interval(6,7)));
        schedule.add(Arrays.asList(new Interval(2,4)));
        schedule.add(Arrays.asList(new Interval(2,5),new Interval(9,12)));

        List<Interval> intervals = leetcode759.employeeFreeTime_sweepingline(schedule);

        for (Interval interval : intervals){
            System.out.println(interval);
        }

    }
    public List<Interval> employeeFreeTime_sweepingline(List<List<Interval>> schedules){
        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();

        for(List<Interval> schedule : schedules){
            for(Interval interval :schedule){
                starts.add(interval.start);
                ends.add(interval.end);
            }
        }

        Collections.sort(starts);
        Collections.sort(ends);

        List<Interval> res = new ArrayList<>();
        int count = 0;
        int preTime = Integer.MIN_VALUE;
        int i = 0;
        int j = 0;
        while(i< starts.size() && j< starts.size()){
            if(starts.get(i) < ends.get(j)){
                if(count == 0 && preTime != Integer.MIN_VALUE && preTime != starts.get(i)){
                    res.add(new Interval(preTime,starts.get(i)));
                }
                preTime = starts.get(i);
                count++;
                i++;
            }else{
                if(count == 0 && preTime != Integer.MIN_VALUE&& preTime != ends.get(j)){
                    res.add(new Interval(preTime,ends.get(j)));
                }
                preTime = ends.get(j);
                count--;
                j++;
            }
        }
        return res;
    }

    public List<Interval> employeeFreeTime2(List<List<Interval>> schedules) {
        List<Interval> result = new ArrayList<>();
        TreeMap<Integer, Integer> mark = new TreeMap<>();

        // Mark the start and end times
        for (List<Interval> schedule : schedules) {
            for (Interval interval : schedule) {
                mark.put(interval.start, mark.getOrDefault(interval.start, 0) + 1);
                mark.put(interval.end, mark.getOrDefault(interval.end, 0) - 1);
            }
        }

        int count = 0;
        Integer prevTime = null;

        // Iterate over the map
        for (Map.Entry<Integer, Integer> entry : mark.entrySet()) {
            if (count == 0 && prevTime != null) {
                result.add(new Interval(prevTime, entry.getKey()));
            }
            count += entry.getValue();
            prevTime = entry.getKey();
        }

        return result;
    }




    static class Interval {
        public int start;
        public int end;

        public Interval() {}

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
