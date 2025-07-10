package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._09TicketingSystem;

import java.util.*;

/**
 * follow up1 what if there is a tie
 * follow up2 查询加time range
 *
 * 变种：AverageScore 加一个lastScore
 * followup 两套AverageScore，注意用继承，另外要合理的修改rating 方法使得代码可读性，可扩展性更高
 */
public class TicktingSystem {
    public static void main(String[] args) {
        TicktingSystem ticktingSystem = new TicktingSystem();
        System.out.println(ticktingSystem.desSort());
    }
    int maxScore = 5;
    HashMap<String, AScore> name2Avg = new HashMap<>();
    HashMap<String,HashMap<Long,Integer>> name2Time2RatingMap = new HashMap<>();

    //follow up add time range

    public void rating(String name,int rating,long timestamp){
        if(rating > maxScore){
            throw new IllegalArgumentException("parameter wrong");
        }
        AScore as = name2Avg.getOrDefault(name, new AScore());
        as.totalScore += rating; // for solving a tie of average score
        as.count++;
        as.updateAvg();
        name2Avg.put(name,as);


        HashMap<Long, Integer> time2RatingMap = name2Time2RatingMap.computeIfAbsent(name, k -> new HashMap<>());
        time2RatingMap.put(timestamp,rating);

    }

    public List<AScore> desSortInTimeRange(long start, long end){
        Set<Map.Entry<String, HashMap<Long, Integer>>> entries = name2Time2RatingMap.entrySet();
        HashMap<String, AScore> name2As = new HashMap<>();
        for(Map.Entry<String, HashMap<Long, Integer>> entry : entries){
            String name = entry.getKey();
            HashMap<Long, Integer> time2RatingMap = entry.getValue();
            for(Long time: time2RatingMap.keySet()){
                if(time<=end && time >= start){
                    AScore as = name2As.computeIfAbsent(name, k -> new AScore());
                    as.count++;
                    as.totalScore+=time2RatingMap.get(time);
                    as.updateAvg();
                }
            }
        }
        List<AScore> aScores = new ArrayList<>(name2As.values());
        Collections.sort(aScores);
        return aScores;
    }

    public List<AScore> desSort(){
        ArrayList<AScore> aScores = new ArrayList<>(name2Avg.values());
        Collections.sort(aScores);
        return aScores;
    }
}
