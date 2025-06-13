package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._04CommodityPrices;


import java.util.*;

/**
 * interface RunningCommodityPrice {
 *
 *     void upsertCommodityPrice(int timestamp, int commodityPrice);
 *
 *     int getMaxCommodityPrice();
 *
 * }
 *
 * RunningCommodityPrice r = new RunningCommodityPrice();
 *
 * r.upsertCommodityPrice(4, 27);
 * r.upsertCommodityPrice(5, 27);

 * r.upsertCommodityPrice(6, 26);
 *
 * r.upsertCommodityPrice(9, 25);
 *
 * r.getMaxCommodityPrice();         // output should be 27 which is at timestamp 4
 *
 * r.upsertCommodityPrice(4, 28);    // timestamps can come out of order and there can be duplicates
 * r.upsertCommodityPrice(5, 28);    // timestamps can come out of order and there can be duplicates
 *
 * // the commodity price at timestamp 4 got updated to 28, so the max commodity price is 28
 *
 * r.getMaxCommodityPrice();         // output should be 28 from timestamp 4
 *
 *
 *
 */
public class RunningCommodityPrice {
    HashMap<Integer,Integer> time2Price; //o(1) //o(n)
    TreeMap<Integer,Integer> price2Count;// o(logn)
    List<TreeMap<Integer,Integer>> checkpoints_price2Count;
    List<TreeMap<Integer,Integer>> time2PriceCheckPoints;
    public RunningCommodityPrice() {
        this.time2Price = new HashMap<>();
        this.price2Count = new TreeMap<>();
        this.checkpoints_price2Count = new ArrayList<>();
        time2PriceCheckPoints= new ArrayList<>();
    }

    public int upsertCommodityPrice(int timestamp,int price){
        if(timestamp <= 0 || price<= 0){
            throw new IllegalArgumentException("wrong arguments");
        }
        int prePrice = time2Price.getOrDefault(timestamp,0);
        int count = price2Count.getOrDefault(price,0);

        //update time2Price
        time2Price.put(timestamp,price);

        //update price2Count
        price2Count.put(price,count + 1);
        Integer preCount = price2Count.getOrDefault(prePrice,0);
        if(preCount >= 1){
            price2Count.put(prePrice,preCount - 1);
            if(preCount- 1== 0){
                price2Count.remove(prePrice);
            }
        }

        checkpoints_price2Count.add(new TreeMap<>(price2Count));
        time2PriceCheckPoints.add(new TreeMap<>(time2Price));
        return checkpoints_price2Count.size();
    }

    public int getMaxCommodityPrice(){
        if(price2Count.isEmpty()){
            throw new IllegalArgumentException("wrong arguments");
        }
        return price2Count.lastKey(); //o(1)
    }

    public int getCommodityPrice(int timestamp,int idx){
        if(timestamp <= 0 || idx< 0 || checkpoints_price2Count.size() - 1 < idx){
            throw new IllegalArgumentException("wrong arguments");
        }
        TreeMap<Integer, Integer> price2Count = checkpoints_price2Count.get(idx);
        TreeMap<Integer, Integer> time2price = time2PriceCheckPoints.get(idx);
        SortedMap<Integer, Integer> tailedTime2PriceCheckPoints = time2price.tailMap(timestamp);
        ArrayList<Integer> prices = new ArrayList<>(tailedTime2PriceCheckPoints.values());
        Collections.sort(prices,(a,b)->a-b);
        for(int i = 0;i< prices.size();i++){
            Integer p = prices.get(i);
            if(price2Count.containsKey(p)){
                return p;
            }
        }
        return -1;
    }
}

