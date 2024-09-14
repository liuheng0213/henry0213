package basic.knowledge.henry.lbTests._02threadSafeSynchronized;

import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer.Server;
import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._02threadSafeSynchronized.RandomStrategy;
import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._02threadSafeSynchronized.RobinStrategy;
import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._02threadSafeSynchronized.ILoadbalancerStrategy;
import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._02threadSafeSynchronized.LoadBalancer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;


import static org.junit.Assert.*;



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
    public void testSelectServerConcurrency() throws InterruptedException {
        loadBalancer.registry(server1);
        loadBalancer.registry(server2);
        loadBalancer.registry(server3);

        int threadCount = 100;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    Server server = loadBalancer.selectServer();
                    assertNotNull(server);
                    assertTrue(Arrays.asList(server1, server2, server3).contains(server));
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        executor.shutdown();
    }

    @Test
    public void testSetStrategyConcurrent() throws InterruptedException {
        loadBalancer.registry(server1);
        loadBalancer.registry(server2);
        loadBalancer.registry(server3);

        int threadCount = 50;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            executor.submit(() -> {
                try {
                    if (index % 2 == 0) {
                        loadBalancer.setStrategy(new RobinStrategy());
                    } else {
                        loadBalancer.setStrategy(new RandomStrategy());
                    }
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        executor.shutdown();
    }
    @Test
    public void testSelectServerSingleThread(){


        List<Server> res = new CopyOnWriteArrayList<>();
        for(int i =0;i< 200;i++){
            res.add(loadBalancer.selectServer());
        }
        for(int i = 0;i< res.size();i++){
            if(i == 0){
                Assert.assertEquals("1.1.1.1",res.get(i).getIp());
            }else{
                int last = Integer.valueOf(res.get(i - 1).getIp().substring(6));
                int expected_cur = (last+ 1) % 5 == 0? last+ 1: (last+ 1) % 5;
                int cur = Integer.valueOf(res.get(i).getIp().substring(6));

                Assert.assertEquals("last index is "  + res.get(i - 1).getIp() + "; currect index is " +res.get(i).getIp(), expected_cur,cur);
            }

        }
    }

    @Test
    public void testRoundRobinInMultipleThreadsSimple() throws InterruptedException {
        int threadCount = 200;
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        CountDownLatch latch = new CountDownLatch(threadCount);

        List<Server> selectedServers = new ArrayList<>();
        Object lock = new Object(); // To ensure thread-safe addition to the list

        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                try {

                    synchronized (lock) {
                        Server server = loadBalancer.selectServer();
                        selectedServers.add(server);
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(5, TimeUnit.SECONDS);
        executorService.shutdown();

        // Verify that the servers are selected in round-robin order
        for (int i = 0; i < selectedServers.size(); i++) {
            Server expectedServer = loadBalancer.allServers().get(i % loadBalancer.allServers().size());
            Server actualServer = selectedServers.get(i);
            assertEquals("Round-robin order failed", expectedServer, actualServer);
        }
    }

    @Test
    public void testRoundRobinInMultipleThreadsGPT() throws InterruptedException {
        int threadCount = 200;
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        CountDownLatch latch = new CountDownLatch(threadCount);

        List<Server> selectedServers = new ArrayList<>();
        Object lock = new Object(); // To ensure thread-safe addition to the list

        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                try {

//                    synchronized (lock) {
                        Server server = loadBalancer.selectServer();
                        System.out.println(server.getIp());
                        //selectedServers.add(server);
//                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(5, TimeUnit.SECONDS);
        executorService.shutdown();

        // Verify that the servers are selected in round-robin order
//        for (int i = 0; i < selectedServers.size(); i++) {
//            Server expectedServer = loadBalancer.allServers().get(i % loadBalancer.allServers().size());
//            Server actualServer = selectedServers.get(i);
//            assertEquals("Round-robin order failed", expectedServer, actualServer);
//        }
    }

    @Test
    public void testSelectServerConcurrent_ROBIN() throws InterruptedException {

        final int numThreads = 200;
        final CountDownLatch latch = new CountDownLatch(numThreads);
        final ExecutorService executor = Executors.newFixedThreadPool(20);
        List<Server> res = new CopyOnWriteArrayList<>();

        for(int i = 0;i< numThreads;i++){
            executor.submit(new Task(latch,res,loadBalancer));
        }

        latch.await(5, TimeUnit.SECONDS); // Ensure both threads have completed
        executor.shutdown();

        // Verify that the servers are selected in round-robin order
//        for (int i = 0; i < res.size(); i++) {
//            Server expectedServer = loadBalancer.allServers().get(i % loadBalancer.allServers().size());
//            Server actualServer = res.get(i);
//            assertEquals("Round-robin order failed", expectedServer, actualServer);
//        }

//        // Verify the servers were selected in round-robin order
        for(int i = 0;i< res.size();i++){
            if(i == 0){
                Assert.assertEquals("1.1.1.1",res.get(i).getIp());
            }else{
                int last = Integer.valueOf(res.get(i - 1).getIp().substring(6));
                int expected_cur = (last+ 1) % 5 == 0? last+ 1: (last+ 1) % 5;
                int cur = Integer.valueOf(res.get(i).getIp().substring(6));

                Assert.assertEquals("last server is "  + res.get(i - 1).getIp() + "; current server is " +res.get(i).getIp(), expected_cur,cur);
            }

        }

        System.out.println(res.size());



    }


    @Test
    public void testSelectServerConcurrent_RANDOM() throws InterruptedException {

        loadBalancer.registry(server1);
        loadBalancer.registry(server2);
        loadBalancer.registry(server3);
        loadBalancer.registry(server4);

        loadBalancer.setStrategy(randomStrategy);

        final int numThreads = 200;
        final CountDownLatch latch = new CountDownLatch(numThreads);
        final ExecutorService executor = Executors.newFixedThreadPool(20);
        List<Server> res = new CopyOnWriteArrayList<>();

        for(int i = 0;i< numThreads;i++){
            executor.submit(new Task(latch,res,loadBalancer));
        }



        latch.await(); // Ensure both threads have completed
        executor.shutdown();

        // Verify the servers were selected in round-robin order
        for(int i = 0;i< res.size();i++){
            System.out.println(res.get(i).getIp());
        }

        System.out.println(res.size());





    }

}

class Task implements Runnable{
    CountDownLatch latch;
     List<Server> servers;
    LoadBalancer loadBalancer;

    public Task(CountDownLatch latch,List<Server> servers,LoadBalancer loadBalancer){
        this.latch = latch;
        this.servers = servers;
        this.loadBalancer = loadBalancer;
    }

    @Override
    public synchronized void run() {
        try{
            synchronized (servers) {
                Server s = loadBalancer.selectServer();
                servers.add(s);
            }

        }finally {
            latch.countDown();
        }

    }
}
