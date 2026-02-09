package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign;

import basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._02Popularity.PopularityTracker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPopularityTracker {
    PopularityTracker popularityTracker;

    @Before
    public void setUp(){
        popularityTracker = new PopularityTracker();
    }

    @Test
    public void test1(){
        popularityTracker.inc("1");
        popularityTracker.inc("2");
        popularityTracker.inc("3");
        popularityTracker.inc("1");
        popularityTracker.inc("2");
        popularityTracker.inc("2");
        popularityTracker.inc("3");
        popularityTracker.inc("3");
        popularityTracker.inc("1");
        popularityTracker.inc("1");
        popularityTracker.inc("1");
        popularityTracker.inc("2");
        popularityTracker.inc("4");
        String maxKey = popularityTracker.getMaxKey();
        String minKey = popularityTracker.getMinKey();
        Assert.assertEquals("1",maxKey);
        Assert.assertEquals("4",minKey);
        popularityTracker.dec("1");
        popularityTracker.dec("1");
        popularityTracker.dec("1");
        popularityTracker.dec("1");
        popularityTracker.dec("1");
        popularityTracker.dec("4");
        String maxKey1 = popularityTracker.getMaxKey();
        String minKey1 = popularityTracker.getMinKey();
        Assert.assertEquals("2",maxKey1);
        Assert.assertEquals("3",minKey1);
        popularityTracker.dec("3");
        popularityTracker.dec("3");
        popularityTracker.dec("3");


        String maxKey2 = popularityTracker.getMaxKey();
        String minKey2 = popularityTracker.getMinKey();
        Assert.assertEquals("2",maxKey2);
        Assert.assertEquals("2",minKey2);


    }


}
