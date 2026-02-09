package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._09TicketingSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * follow up1 what if there is a tie
 * follow up2 查询加time range
 *
 * 变种：AverageScore 加一个lastScore
 * followup 两套AverageScore，注意用继承，另外要合理的修改rating 方法使得代码可读性，可扩展性更高
 *
 *
 * a) Imagine we have a customer support ticketing system. The system allows customers to rate the support agent out of 5. To start with, write a function which accepts a rating, and another which will show me all of the agents and the average rating each one has received, ordered highest to lowest.
 * b) Currently your solution does not account for what happens if two agents have the same average rating. What options are there for handling ties and how can we implement that in code?
 * c) Now I want to be able to see who the best agents are each month. Change the implementation so I can get that information.
 * d) Write a new function that will allow me to export of each agent’s average ratings per month. You can export in any format you like- for example csv,json or xml.
 * e) Make it return the average ratings unsorted./ Make it return the total rating for each agent without the average
 */
public class TicktingSystemTimeRange {
    public static void main(String[] args) {
        TicktingSystemTimeRange ticktingSystem = new TicktingSystemTimeRange();
        System.out.println(ticktingSystem.desSort());
    }
    int maxScore = 5;
    HashMap<String, AScore> name2Avg = new HashMap<>();

    //name-> timestamp-> Ascore
    HashMap<String,HashMap<Long,AScore>> name2Time2AvgRatingMap = new HashMap<>();

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


        HashMap<Long, AScore> time2RatingMap = name2Time2AvgRatingMap.computeIfAbsent(name, k -> new HashMap<>());
        time2RatingMap.put(timestamp,new AScore(as.avg, as.count,as.totalScore));

    }

    public List<AScore> desSortInTimeRange(long start, long end){

        List<AScore> aScores = new ArrayList<>();
        for(HashMap<Long, AScore> time2Avg: name2Time2AvgRatingMap.values()){
             for(Long time : time2Avg.keySet()){
                  if(time>= start && time <= end){
                      aScores.add(time2Avg.get(time));
                  }
              }
         }
        Collections.sort(aScores);
        return aScores;
    }

    public List<AScore> desSort(){
        ArrayList<AScore> aScores = new ArrayList<>(name2Avg.values());
        Collections.sort(aScores);
        return aScores;
    }
}
