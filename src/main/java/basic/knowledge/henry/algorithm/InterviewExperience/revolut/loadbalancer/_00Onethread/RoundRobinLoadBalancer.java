package basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._00Onethread;

import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer.Server;

import java.util.List;

public class RoundRobinLoadBalancer extends AbstractLoadBalancer{



    @Override
    public Server getNextServer(List<Server> servers) {
        List<Server> serverList = getServers();
        if (serverList.isEmpty()) {
            return null;
        }
        int index = ++currentIndex % serverList.size();
        return serverList.get(index);
    }
}
