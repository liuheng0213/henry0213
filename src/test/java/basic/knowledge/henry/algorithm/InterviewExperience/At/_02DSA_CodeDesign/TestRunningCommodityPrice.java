package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign;

import basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._04CommodityPrice.RunningCommodityPrice;
import basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._04CommodityPrice.RunningCommodityPriceTwice;
import org.junit.Test;
import org.testng.Assert;


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
public class TestRunningCommodityPrice {

    RunningCommodityPriceTwice runningCommodityPriceTwice = new RunningCommodityPriceTwice();

    RunningCommodityPrice runningCommodityPrice = new RunningCommodityPrice();

    @Test
    public void test1(){
        runningCommodityPrice.upsertCommodityPrice(4,27);
        runningCommodityPrice.upsertCommodityPrice(5,27);
        runningCommodityPrice.upsertCommodityPrice(6,26);
        runningCommodityPrice.upsertCommodityPrice(9,25);
        runningCommodityPrice.upsertCommodityPrice(4,25);

        int max1 = runningCommodityPrice.getMaxCommodityPrice();
        Assert.assertEquals(max1,27);
        runningCommodityPrice.upsertCommodityPrice(5,26);

        int max2 = runningCommodityPrice.getMaxCommodityPrice();
        Assert.assertEquals(max2,26);
    }
    @Test
    public void test2(){
        runningCommodityPriceTwice.upsertCommodityPrice(4,27);
        runningCommodityPriceTwice.upsertCommodityPrice(5,27);
        runningCommodityPriceTwice.upsertCommodityPrice(6,26);
        runningCommodityPriceTwice.upsertCommodityPrice(9,25);
        runningCommodityPriceTwice.upsertCommodityPrice(4,25);

        int max1 = runningCommodityPriceTwice.getMaxCommodityPrice();
        Assert.assertEquals(max1,27);
        runningCommodityPriceTwice.upsertCommodityPrice(5,26);

        int max2 = runningCommodityPriceTwice.getMaxCommodityPrice();
        Assert.assertEquals(max2,26);

    }
}
