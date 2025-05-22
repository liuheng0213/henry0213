package basic.knowledge.henry.algorithm.InterviewExperience.orl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class LRUCacheTTL {
    LinkedHashMap<Integer,Integer> cache;
    HashMap<Integer,Node> key2Node;
    int capacity;
    Queue<Node> pq;

    public LRUCacheTTL(int capacity) {
        this.capacity = capacity;
        key2Node = new HashMap<>();
        cache = new LinkedHashMap();
        pq = new PriorityQueue<>((a,b)->a.durationTime.compareTo(b.durationTime));
    }

    public int get(int key){
        evictExpired();

        if(!cache.containsKey(key)){
            return  -1;
        }
        move2Tail(key);
        return cache.get(key);
    }

    public void put(int key,int val,long durationTime){
        evictExpired();
        if(cache.containsKey(key)){
            move2Tail(key);
            Node node = key2Node.get(key);
            node.durationTime = durationTime;
            cache.put(key,val);
            return;
        }

        if(cache.size() == capacity){
            evictHead();
        }

        Node node = new Node(key,durationTime);
        cache.put(key,val);
        key2Node.put(key,node);
        pq.add(node);
    }

    private void evictExpired() {
        while(!pq.isEmpty() && pq.peek().durationTime < System.currentTimeMillis()){
            Node node  = pq.poll();
            cache.remove(node.key);
            key2Node.remove(node.key);
        }
    }

    private void evictHead() {
        int firstKey = cache.keySet().iterator().next();
        cache.remove(firstKey);
        Node tobeDeleted = key2Node.get(firstKey);
        key2Node.remove(firstKey);
        pq.remove(tobeDeleted);
    }


    private void move2Tail(int key){
        int val = cache.get(key);
        cache.remove(key);
        cache.put(key,val);
    }

    class Node{
        private int key;
        private Long durationTime;

        public Node(int key, Long durationTime) {
            this.key = key;
            this.durationTime = System.currentTimeMillis() + durationTime;
        }
    }

    public static void main(String[] args) throws Exception {
        LRUCacheTTL lruCacheTTL = new LRUCacheTTL(2);
        lruCacheTTL.put(1, 1, 1000);
        System.out.println(lruCacheTTL.get(1)); // 1, key:1 not expired yet
        Thread.sleep(1500);
        System.out.println(lruCacheTTL.get(1)); // -1, key:1 expired
        lruCacheTTL.put(2, 2, 5000);
        lruCacheTTL.put(3, 3, 5000);
        lruCacheTTL.put(4, 4, 1000);
        System.out.println(lruCacheTTL.get(2)); // -1, LRU hence removed
        System.out.println(lruCacheTTL.get(3)); // 3, key:3 not expired yet
        System.out.println(lruCacheTTL.get(4)); // 4, key:4 not expired yet
        Thread.sleep(1500);
        System.out.println(lruCacheTTL.get(3)); // 3, key:3 not expired yet
        System.out.println(lruCacheTTL.get(4)); // -1, key:4 expired
    }

}
