package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign;

import basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._03RateLimiter.FixedWindowRateLimiter;
import basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._03RateLimiter.RateLimiterTwice;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

public class TestRateLimiter {
    FixedWindowRateLimiter rl;
    RateLimiterTwice r2;
    @Before
    public void setup(){
        rl = new FixedWindowRateLimiter();
        r2 = new RateLimiterTwice();
    }

    @Test
    public void testSendRequest() throws InterruptedException {
        Assert.assertEquals(rl.sendRequest(1), true);
        Assert.assertEquals(rl.sendRequest(1), true);


        Thread.sleep(5000);
        Assert.assertEquals(rl.sendRequest(1), true);
        Assert.assertEquals(rl.sendRequest(1), true);
        Assert.assertEquals(rl.sendRequest(1), true);


        Thread.sleep(5000);
        Assert.assertEquals(rl.sendRequest(1), true);
        Assert.assertEquals(rl.sendRequest(1), true);

        Thread.sleep(5000);
        Assert.assertEquals(rl.sendRequest(1), true);
        Assert.assertEquals(rl.sendRequest(1), true);
        Assert.assertEquals(rl.sendRequest(1), true);
        Assert.assertEquals(rl.sendRequest(1), true);
        Assert.assertEquals(rl.sendRequest(1), true);
        Assert.assertEquals(rl.sendRequest(1), true);
        Assert.assertEquals(rl.sendRequest(1), true);
        Assert.assertEquals(rl.sendRequest(1), true);
        Assert.assertEquals(rl.sendRequest(1), true);
        Assert.assertEquals(rl.sendRequest(1), true);
        Assert.assertEquals(rl.sendRequest(1), true);
        Assert.assertEquals(rl.sendRequest(1), false);
    }


    @Test
    public void testSendRequest2() throws InterruptedException {
        Assert.assertEquals(r2.sendRequest(1), true);
        Assert.assertEquals(r2.sendRequest(1), true);


        Thread.sleep(5000);
        Assert.assertEquals(r2.sendRequest(1), true);
        Assert.assertEquals(r2.sendRequest(1), true);
        Assert.assertEquals(r2.sendRequest(1), true);


        Thread.sleep(5000);
        Assert.assertEquals(r2.sendRequest(1), true);
        Assert.assertEquals(r2.sendRequest(1), true);

        Thread.sleep(5000);
        Assert.assertEquals(r2.sendRequest(1), true);
        Assert.assertEquals(r2.sendRequest(1), true);
        Assert.assertEquals(r2.sendRequest(1), true);
        Assert.assertEquals(r2.sendRequest(1), true);
        Assert.assertEquals(r2.sendRequest(1), true);
        Assert.assertEquals(r2.sendRequest(1), true);
        Assert.assertEquals(r2.sendRequest(1), true);
        Assert.assertEquals(r2.sendRequest(1), true);
        Assert.assertEquals(r2.sendRequest(1), true);
        Assert.assertEquals(r2.sendRequest(1), true);
        Assert.assertEquals(r2.sendRequest(1), true);
        Assert.assertEquals(r2.sendRequest(1), false);
    }


}
