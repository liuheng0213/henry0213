package basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._00Onethread;

import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer.Server;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractLoadBalancer implements LoadBalancer{
    int currentIndex = -1;
    final LinkedHashSet<Server> serverSet = new LinkedHashSet<>();

    List<Server> serverList = null;
    @Override
    public void register(Server server) {
        serverSet.add(server);
        updateStatus();

    }

    private void updateStatus() {
        this.currentIndex = -1;
        this.serverList = serverSet.stream().collect(Collectors.toList());
    }

    @Override
    public void unregister(Server server) {
        serverSet.remove(server);
        updateStatus();
    }

    @Override
    public List<Server> getServers() {
        return this.serverList;
    }
}
