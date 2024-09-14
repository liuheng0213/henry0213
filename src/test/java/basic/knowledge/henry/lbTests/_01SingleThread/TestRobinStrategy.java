package basic.knowledge.henry.lbTests._01SingleThread;

import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer.Server;
import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._03threadSafeCOncurrentPackage.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class TestRobinStrategy {
    private Server server1, server2, server3;
    private ILoadbalancerStrategy roundRobinStrategy;
    private ILoadbalancerStrategy randomStrategy;
    private LoadBalancer loadBalancer;

    @Before
    public void setUp(){
        server1 = new Server("1.1.1.1",111);
        server2 = new Server("1.1.1.2",222);
        server3 = new Server("1.1.1.3",333);
        roundRobinStrategy = new RobinStrategy();
        randomStrategy = new RandomStrategy();
        loadBalancer = new LoadBalancer(roundRobinStrategy);

    }



    @Test
    public void testSelectServer(){
        ILoadbalancerStrategy robinStrategy = LoadBalancerStrategyFactory.createStrategy("roundrobin");
        LoadBalancer lb = new LoadBalancer(robinStrategy);

        lb.registry(server1);
        lb.registry(server2);
        lb.registry(server3);

        Server server = robinStrategy.selectServer(lb.allServers());
        Assert.assertEquals(this.server1,server);
        server = lb.selectServer();
        Assert.assertEquals(this.server2,server);
        server = lb.selectServer();
        Assert.assertEquals(this.server3,server);
        server= lb.selectServer();
        Assert.assertEquals(this.server1,server);
    }

    /**
     * 验证loadBalancer 调用了selectServer strategy 一定调用了selectServer(servers)
     */
    @Test
    public void testGetNextServerWithMockStrategy() {
        ILoadbalancerStrategy mockStrategy = mock(ILoadbalancerStrategy.class);
        loadBalancer = new LoadBalancer(mockStrategy);

        List<Server> servers = Arrays.asList(server1, server2, server3);
        loadBalancer.registry(server1);
        loadBalancer.registry(server2);
        loadBalancer.registry(server3);

        when(mockStrategy.selectServer(servers)).thenReturn(server2);

        Assert.assertEquals(server2, loadBalancer.selectServer());
        Mockito.verify(mockStrategy, times(1)).selectServer(servers);
    }
}
