package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._02Popularity;


import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

/**
 *
 * Implement a system to track the popularity of content based on user interactions (thumbs up or thumbs down).
 * Your task is to design an interface called ContentPopularity with the following methods:
 *
 * 1. increasePopularity(contentId: int) -> None: Increases the popularity of the specified content ID by one (representing a thumbs up).
 *
 * 2. decreasePopularity(contentId: int) -> None: Decreases the popularity of the specified content ID by one (representing a thumbs down).
 *
 * 3. mostPopular() -> int: Returns the content ID with the highest popularity. If there are ties, return any one of them. -1 if no content.
 *
 *
 */
public class PopularityTracker {
    public static void main(String[] args) {
        PopularityTracker popularityTracker = new PopularityTracker();
        popularityTracker.inc("hello");
        popularityTracker.inc("hello");
        popularityTracker.inc("leet");

        System.out.println("===");

    }
    HashMap<String,Integer> id2Count = new HashMap<>();
    TreeMap<Integer, HashSet<String>> count2Id = new TreeMap<>();

    int max = 0;

    public void inc(String id){
        //update id2Count
        Integer count = id2Count.getOrDefault(id, 0);
        id2Count.put(id,count + 1);
        
        //count2Id
        HashSet<String> ids = count2Id.getOrDefault(count,new HashSet<>());
        ids.remove(id);

        if(ids.isEmpty()){
            count2Id.remove(count);

        }
        count2Id.putIfAbsent(count + 1,new HashSet<>());
        count2Id.get(count + 1).add(id);
        if(max == count){
            max++;
        }
    }

    public void dec(String id){
        if(id2Count.containsKey(id)){
            return;
        }
        //update id2Count
        Integer count = id2Count.get(id);
        if(count > 0){
            id2Count.put(id,count - 1);
        }
        if(count - 1 == 0){
            id2Count.remove(id);
        }

        //update count2Id
        HashSet<String> ids = count2Id.get(count);
        ids.remove(id);

        if(ids.isEmpty()){
            count2Id.remove(count);

        }

        if(count > 1){
            count2Id.putIfAbsent(count - 1,new HashSet<>());
            count2Id.get(count - 1).add(id);
        }



    }

    public String getMaxKey(){
        if(id2Count.isEmpty()){
            return "";
        }
        return count2Id.get(max).iterator().next();
    }


    public String getMinKey(){
        if(id2Count.isEmpty()){
            return "";
        }
        return count2Id.get(count2Id.firstKey()).iterator().next();
    }

}
