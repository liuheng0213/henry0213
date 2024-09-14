package basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._01SingleThread;

import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer.Server;

import java.util.List;
import java.util.Random;

public class RandomStrategy implements ILoadbalancerStrategy {
    Random rd = new Random();
    @Override
    public Server selectServer(List<Server> servers) {
        if (servers.isEmpty()) {
            throw new IllegalStateException("No servers available");
        }
        int idx = rd.nextInt(servers.size());

        return servers.get(idx);
    }

    @Override
    public List<Server> getAllServerFromStrategy() {
        return null;
    }
}
