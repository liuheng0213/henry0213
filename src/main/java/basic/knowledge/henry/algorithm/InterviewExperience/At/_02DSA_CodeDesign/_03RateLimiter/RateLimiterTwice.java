package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._03RateLimiter;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * follow up How would you handle this in a multithreaded env
 */
public class RateLimiterTwice {
   HashMap<Integer,Integer> id2Count;
   HashMap<Integer,Integer> id2Credit;

   ScheduledExecutorService service;

   int maxCount = 5;
   int maxCredit = 6;

    public RateLimiterTwice() {
        this.id2Credit = new HashMap<>();
        this.id2Count = new HashMap<>();
        this.service =  Executors.newSingleThreadScheduledExecutor();
        this.service.scheduleAtFixedRate(new ClearTask(id2Count,id2Credit,maxCount,maxCredit),5,5, TimeUnit.SECONDS);
    }



    public boolean sendRequest(int id){
        int count = id2Count.getOrDefault(id,0);
        int credit = id2Credit.getOrDefault(id,0);

        if(count >= maxCount){
            if(credit > 0 ){
                id2Credit.put(id,credit - 1);
                return true;
            }
            return false;
        }else {
            id2Count.put(id,count+ 1);
            return true;
        }
    }



   private class ClearTask implements Runnable{
        HashMap<Integer,Integer> id2Count;
        HashMap<Integer,Integer> id2Credit;

        int maxCount;

        int maxCredit;

        public ClearTask(HashMap<Integer, Integer> id2Count,HashMap<Integer, Integer> id2Credit,int maxCount,int maxCredit) {
            this.id2Count = id2Count;
            this.id2Credit = id2Credit;
            this.maxCount = maxCount;
            this.maxCredit = maxCredit;
        }

        @Override
        public void run() {
            for(int id: id2Count.keySet()){
                int leftCount = maxCount- id2Count.get(id);
                int credit = Math.min(maxCredit,id2Credit.getOrDefault(id,0) + leftCount);
                id2Credit.put(id,credit);

                if(credit == 0){
                    id2Credit.remove(id);
                }
            }

            id2Count.clear();

        }
    }


}
