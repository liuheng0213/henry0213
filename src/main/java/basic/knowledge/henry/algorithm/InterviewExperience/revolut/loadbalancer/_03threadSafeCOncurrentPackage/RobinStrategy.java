package basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._03threadSafeCOncurrentPackage;

import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RobinStrategy implements ILoadbalancerStrategy{
    private final AtomicInteger index = new AtomicInteger(-1);


    /**
     * synchronized 在这里是必须的，即使用例atomic
     * @param servers
     * @return
     */
    @Override
    public  Server selectServer(List<Server> servers) {
        if (servers.isEmpty()) {
            throw new IllegalStateException("No servers available");
        }
        int  currentIndex = index.incrementAndGet() % servers.size();
        return servers.get(currentIndex);
    }
}
