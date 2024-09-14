package basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._02threadSafeSynchronized;

import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer.Server;

import java.util.ArrayList;
import java.util.List;

public class RobinStrategy implements ILoadbalancerStrategy {
    private volatile int current = -1;//volatile 可有可无
    List<Server> selected = new ArrayList<>();
    @Override
    public synchronized Server selectServer(List<Server> servers) {
        if (servers.isEmpty()) {
            throw new IllegalStateException("No servers available");
        }

        current = (current + 1) % servers.size();

        return servers.get(current);
    }

    @Override
    public List<Server> getAllServerFromStrategy() {
        return null;
    }
}
