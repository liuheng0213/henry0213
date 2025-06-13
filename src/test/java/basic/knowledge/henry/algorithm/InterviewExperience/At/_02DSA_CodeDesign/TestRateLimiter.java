package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign;

import basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._03RateLimiter.RateLimiter;
import basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._03RateLimiter.SlidingWindow;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

public class TestRateLimiter {
    RateLimiter rl;
    @Before
    public void setup(){
        rl = new RateLimiter();
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
    public void testSlidingWindow() throws InterruptedException {
        SlidingWindow sw = new SlidingWindow(5,5);
        System.out.println(System.currentTimeMillis());
        Assert.assertEquals(sw.sendRequest(1),true);
        Assert.assertEquals(sw.sendRequest(1),true);
        Assert.assertEquals(sw.sendRequest(1),true);
//        Thread.sleep(1000);
        System.out.println(System.currentTimeMillis());
        Assert.assertEquals(sw.sendRequest(1),true);
        Assert.assertEquals(sw.sendRequest(1),true);
//        Thread.sleep(4000);
//        Assert.assertEquals(sw.sendRequest(1),true);
                Thread.sleep(5000);
        Assert.assertEquals(sw.sendRequest(1),true);
    }
}
