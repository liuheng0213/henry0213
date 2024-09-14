package basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._00Onethread;

import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer.Server;

import java.util.List;

public interface LoadBalancer {

    Server getNextServer(List<Server> servers);
    void register(Server server);
    void unregister(Server server);
    List<Server> getServers();
}
