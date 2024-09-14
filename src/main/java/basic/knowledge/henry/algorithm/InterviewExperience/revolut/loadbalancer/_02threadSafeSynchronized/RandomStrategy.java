package basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._02threadSafeSynchronized;

import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer.Server;

import java.util.List;
import java.util.Random;

public class RandomStrategy implements ILoadbalancerStrategy {
    private final Random rd = new Random();

    @Override
    public synchronized Server selectServer(List<Server> servers) {
        if (servers.isEmpty()) {
            throw new IllegalStateException("No servers available");
        }
        return servers.get( rd.nextInt(servers.size()));

    }

    @Override
    public List<Server> getAllServerFromStrategy() {
        return null;
    }
}
