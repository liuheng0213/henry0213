package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign;

import basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._01Subscription.CostExplorer;
import basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._01Subscription.Product;
import basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._01Subscription.Subscription;
import basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._01Subscription.SubscriptionInformation;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestCostExplorer {
    CostExplorer ce = new CostExplorer();
    @Before
    public void setup() throws ParseException {

        System.out.println(Integer.valueOf("01"));
        Subscription s = new Subscription("BASIC","2021-01-01");
        Product product = new Product("Jira",s);
        SubscriptionInformation st = new SubscriptionInformation("c1",product);

        Subscription s1 = new Subscription("PREMIUM","2021-03-01");
        Product product1 = new Product("Confluence",s1);
        SubscriptionInformation st1 = new SubscriptionInformation("c2",product1);
//
//
        Subscription s2 = new Subscription("BASIC","2021-04-01");
        Product product2 = new Product("Confluence",s2);
        SubscriptionInformation st2 = new SubscriptionInformation("c2",product2);


        Subscription s3 = new Subscription("STANDARD","2021-06-01");
        Product product3 = new Product("Jira",s3);
        SubscriptionInformation st3 = new SubscriptionInformation("c3",product3);
        ce.subscribe(st);
        ce.subscribe(st1);
        ce.subscribe(st2);
        ce.subscribe(st3);
    }


    @Test
    public void test1() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        Date date = simpleDateFormat.parse("2012-11-13");
        int month = date.getMonth();
        Assert.assertEquals(month,10);

        double[] doubles = ce.monthlyCostList();
        Double[] expected = new Double[]{0d,0d,0d,5d,5d,20d,10d,10d,20d,20d,20d,20d};

        for(int i =0;i< 12;i++){
            Assert.assertEquals(doubles[i],expected[i]);
            System.out.println(i);
        }

    }

}
