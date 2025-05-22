package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation;

import java.util.TreeMap;
import java.util.TreeSet;


/**
 * 一个timestamp 对应 一个price , 因为如果新的 particular timestamp 来了 仅仅更新上面的price
 * 所以可知必须用hashmap
 *
 * 但是：
 * 一个price 可以对应多个stamp 这就是需要注意的地方 。所以需要TreeSet<Node> set;
 */
class StockPrice {
    TreeMap<Integer,Node> map =null;
    TreeSet<Node> set;
    public StockPrice() {
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
        return set.last().val;
    }

    public int minimum() {
        return set.first().val;
    }

    class Node implements Comparable<Node>{
        int val;
        int time;

        public int compareTo(Node that){
            if(this.val != that.val)
                return (this.val-that.val);

            return this.time-that.time;
        }
    }
}

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */
