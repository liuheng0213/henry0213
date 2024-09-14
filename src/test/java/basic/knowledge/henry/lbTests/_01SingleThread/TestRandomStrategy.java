package basic.knowledge.henry.lbTests._01SingleThread;

import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer.Server;
import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._03threadSafeCOncurrentPackage.ILoadbalancerStrategy;
import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._03threadSafeCOncurrentPackage.LoadBalancer;
import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._03threadSafeCOncurrentPackage.LoadBalancerStrategyFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestRandomStrategy {
    private Server server1, server2, server3;

    @Before
    public void setUp(){
        server1 = new Server("1.1.1.1",111);
        server2 = new Server("1.1.1.2",222);
        server3 = new Server("1.1.1.3",333);

    }



    @Test
    public void testSelectServer(){
        ILoadbalancerStrategy robinStrategy = LoadBalancerStrategyFactory.createStrategy("random");
        LoadBalancer lb = new LoadBalancer(robinStrategy);

        lb.registry(server1);
        lb.registry(server2);
        lb.registry(server3);

        List<Server> servers = lb.allServers();
        Server server = lb.selectServer();
        Assert.assertEquals(servers.contains(server),true);
        Server server1 = lb.selectServer();
        Assert.assertEquals(servers.contains(server1),true);
        Server server2 = lb.selectServer();
        Assert.assertEquals(servers.contains(server2),true);
        Server server3= lb.selectServer();
        Assert.assertEquals(servers.contains(server3),true);
    }

}
