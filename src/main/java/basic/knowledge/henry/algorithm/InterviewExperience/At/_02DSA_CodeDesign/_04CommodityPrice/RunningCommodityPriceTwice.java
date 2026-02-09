package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._04CommodityPrice;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
public class RunningCommodityPriceTwice {
    Map<Integer,Integer> time2Price;

    TreeMap<Integer, Integer> price2Count;

    public RunningCommodityPriceTwice() {
        this.time2Price = new HashMap<>();
        this.price2Count = new TreeMap<>();
    }

    public void upsertCommodityPrice(int timestamp,int price){
        if(!time2Price.containsKey(timestamp)){
            time2Price.put(timestamp,price);
//            price2Count.put(price,1); 这不正确，timestamp 没出现过，并不代表price 没出现过
        }else{
            int oldPrice = time2Price.get(timestamp);
            int oldCount = price2Count.get(oldPrice);

            //update time2Price
//            time2Price.put(timestamp,price);

            // update price2Count
            if(oldCount <= 1){
                price2Count.remove(oldPrice);
            }else {
                price2Count.put(oldPrice,oldCount - 1);
            }

        }
        price2Count.put(price,price2Count.getOrDefault(price,0) + 1);
    }

    public int getMaxCommodityPrice(){
       return price2Count.lastKey();
    }

    public int getCommodityPrice(int timestamp,int idx){
       return 0;
    }
}

