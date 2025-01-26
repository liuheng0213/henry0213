package basic.knowledge.henry.lbTests._01SingleThread;

import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer.Server;
import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._03threadSafeConcurrentPackage.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;


public class TestLoadBalancer {
    private Server server1, server2, server3,server4,server5;
    private ILoadbalancerStrategy roundRobinStrategy;
    private ILoadbalancerStrategy randomStrategy;
    private LoadBalancer loadBalancer;



    @Before
    public void setUp(){
        server1 = new Server("1.1.1.1",111);
        server2 = new Server("1.1.1.2",222);
        server3 = new Server("1.1.1.3",333);
        server4 = new Server("1.1.1.4",444);
        server5 = new Server("1.1.1.5",555);
        roundRobinStrategy = new RobinStrategy();
        randomStrategy = new RandomStrategy();
        loadBalancer = new LoadBalancer(roundRobinStrategy);

    }

    @Test
    public void testRegistry(){
        LoadBalancer lb = new LoadBalancer(new RobinStrategy());
        lb.registry(server1);
        assertEquals(lb.allServers().size(),1);
        lb.registry(server1);
        assertEquals(lb.allServers().size(),1);
        lb.registry(server2);
        assertEquals(lb.allServers().size(),2);
    }

    @Test
    public void testUnRegistry(){
        ILoadbalancerStrategy robinStrategy = LoadBalancerStrategyFactory.createStrategy("roundrobin");

        LoadBalancer lb = new LoadBalancer(robinStrategy);
        lb.registry(server1);
        lb.registry(server2);
        lb.registry(server3);
        assertEquals(lb.allServers().size(),3);
        lb.unregistry(server4);
        assertEquals(lb.allServers().size(),3);
    }

    @Test
    public void testAddAndRemoveServer() {
        loadBalancer.registry(server1);
        loadBalancer.registry(server2);

        // Test the added servers
        Server nextServer = loadBalancer.selectServer();
        assertNotNull(nextServer);
        assertTrue(nextServer.equals(server1) || nextServer.equals(server2));

        loadBalancer.unregistry(server1);
        nextServer = loadBalancer.selectServer();
        assertEquals(server2, nextServer);
    }




}
