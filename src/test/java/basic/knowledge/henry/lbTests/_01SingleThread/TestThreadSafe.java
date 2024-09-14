package basic.knowledge.henry.lbTests._01SingleThread;

import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer.Server;

import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._01SingleThread.ILoadbalancerStrategy;
import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._01SingleThread.LoadBalancer;
import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._01SingleThread.RandomStrategy;
import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._01SingleThread.RobinStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;


/**
 * 要验证roundrobin的安全性，只有在应用代码中加List存储结果
 * unit test 是不可能的，
 */
public class TestThreadSafe {
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

        loadBalancer.registry(server1);
        loadBalancer.registry(server2);
        loadBalancer.registry(server3);
        loadBalancer.registry(server4);
        loadBalancer.registry(server5);
        loadBalancer.setStrategy(roundRobinStrategy);
    }

    @Test
    public void testRoundRobinInMultipleThreadsGPT() throws InterruptedException {
        int threadCount = 200;
        ExecutorService executorService = Executors.newFixedThreadPool(200);


        List<Server> selectedServers = new ArrayList<>();
        Object lock = new Object(); // To ensure thread-safe addition to the list

        for (int i = 0; i < threadCount; i++) {
            executorService.execute(new Runnable() {
                @Override
                public  void run() {
                    synchronized (lock){
                        Server server = loadBalancer.selectServer();
                        selectedServers.add(server);}
                }
            });

        }


        executorService.shutdown();

        // Verify that the servers are selected in round-robin order
        for (int i = 0; i < selectedServers.size(); i++) {
            Server expectedServer = loadBalancer.allServers().get(i % loadBalancer.allServers().size());
            Server actualServer = selectedServers.get(i);
            assertEquals("Round-robin order failed", expectedServer, actualServer);
        }
    }

}


