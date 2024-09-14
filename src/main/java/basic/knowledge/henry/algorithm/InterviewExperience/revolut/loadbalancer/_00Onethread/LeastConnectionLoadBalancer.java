package basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._00Onethread;

import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer.Server;

import java.util.List;

public class LeastConnectionLoadBalancer extends AbstractLoadBalancer{
    @Override
    public Server getNextServer(List<Server> servers) {
        List<Server> serverList = getServers();
        if (serverList.isEmpty()) {
            return null;
        }

        Server leastConnectionServer = serverList.get(0);

        for (Server server : serverList) {
            if (server.getActiveConnections() < leastConnectionServer.getActiveConnections()) {
                leastConnectionServer = server;
            }
        }
        leastConnectionServer.incrementConnections();
        return leastConnectionServer;
    }
}
