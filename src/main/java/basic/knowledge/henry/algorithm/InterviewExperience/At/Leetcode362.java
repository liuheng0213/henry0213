package basic.knowledge.henry.algorithm.InterviewExperience.At;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Leetcode362 {
    public static void main(String[] args) {
        Leetcode362 obj = new Leetcode362();

    }


}

class HitCounter {
    LinkedList<Integer> times;
    HashMap<Integer,Integer> map;
    public HitCounter() {
        this.times = new LinkedList<>();
        this.map = new HashMap<>();
    }

    public void hit(int timestamp) {
        while(!times.isEmpty() && timestamp - times.peekFirst()> 299){
            int key = times.pollFirst();
            map.remove(key);
        }
        if(!times.isEmpty() && times.peekLast().intValue() == timestamp){
            map.put(timestamp,map.get(timestamp) + 1);
        }else{
            times.addLast(timestamp);
            map.put(timestamp,1);
        }

    }

    public int getHits(int timestamp) {
        while(!times.isEmpty() && timestamp - times.peekFirst()> 299){
            int key = times.pollFirst();
            map.remove(key);
        }
        int res = 0;
        for(int i =0;i<= timestamp && i< times.size();i++){
            res+= map.get(times.get(i));
        }
        return res;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
