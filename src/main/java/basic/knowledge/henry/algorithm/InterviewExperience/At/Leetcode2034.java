package basic.knowledge.henry.algorithm.InterviewExperience.At;

import java.util.TreeMap;
import java.util.TreeSet;

public class Leetcode2034 {
    public static void main(String[] args) {
        Leetcode2034 leetcode2034 = new Leetcode2034();
        leetcode2034.update(1,10);
        leetcode2034.update(2,10);
        leetcode2034.update(1,3);
        leetcode2034.update(4,2);

        int minimum = leetcode2034.minimum();
        System.out.println(minimum);
    }
    TreeMap<Integer,Node> map =null;
    TreeSet<Node> set;
    public Leetcode2034() {
        map = new TreeMap<>();
        set = new TreeSet<>();
    }

    public void update(int timestamp, int price) {
        if(map.containsKey(timestamp)){
            set.remove(map.get(timestamp));
        }
        Node n = new Node();
        n.val = price;
        n.time = timestamp;

        map.put(timestamp,n);
        set.add(n);
    }

    public int current() {
        return this.map.get(map.lastKey()).val;
    }

    public int maximum() {
        return map.get(map.lastKey()).val;
//        return set.last().val;
    }

    public int minimum() {
        return map.get(map.firstKey()).val;
//        return set.first().val;
    }

    class Node implements Comparable<Node>{
        int val;
         int time;

        public int compareTo(Node that){
            if(this.val != that.val)
                return (this.val-that.val);
//            return 0;
             return this.time-that.time;
        }
    }
}
