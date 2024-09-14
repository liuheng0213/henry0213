package basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._01SingleThread;

import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer.Server;

import java.util.ArrayList;
import java.util.List;

public class RobinStrategy implements ILoadbalancerStrategy {
    int current = -1;
    List<Server> selected = new ArrayList<>();
    @Override
    public Server selectServer(List<Server> servers) {
        if (servers.isEmpty()) {
            throw new IllegalStateException("No servers available");
        }
        current = (current + 1) % servers.size();
        Server server = servers.get(current);
        selected.add(server);
        return server;
    }

    public List<Server> getAllServerFromStrategy(){
        return selected;
    }
}
