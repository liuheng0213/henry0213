package basic.knowledge.henry.algorithm.InterviewExperience.At.rateLimiter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RateLimiter {
    int maxRequests = 5;
    int maxCredits = 5;

    long maxinterval = 1000;

    HashMap<Integer,Integer> user2Count;
    HashMap<Integer, Integer> user2Credits;


    private ScheduledExecutorService scheduler;


    public RateLimiter(int maxRequests, int maxCredits, long maxinterval) {
        this.maxRequests = maxRequests;
        this.maxCredits = maxCredits;
        this.maxinterval = maxinterval;
        this.user2Count = new HashMap<>();
        this.user2Credits = new HashMap<>();
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(()->fill(),maxinterval,maxinterval, TimeUnit.MILLISECONDS);
    }

    private void fill() {
        for(Map.Entry<Integer,Integer> entry : user2Count.entrySet()){
            int customerId = entry.getKey();
            int count = entry.getValue();
            int left = maxRequests - count;
            entry.setValue(0);

            int credit = user2Credits.get(customerId);
            credit = Math.min(maxCredits,credit + left);
            user2Credits.put(customerId,credit);
        }

    }

    public boolean canSendRequest(int userId){
        int count =0;
        if(!user2Count.containsKey(userId)){
            user2Count.put(userId,0);
        }
        count = user2Count.get(userId);
        if(count < maxRequests){
            count++;
            user2Count.put(userId,count);
            return true;
        }else if(user2Credits.get(userId) > 0){
            int credit = user2Credits.get(userId) - 1;
            user2Credits.put(userId,--credit);
            return true;
        }

        return false;
    }




}
