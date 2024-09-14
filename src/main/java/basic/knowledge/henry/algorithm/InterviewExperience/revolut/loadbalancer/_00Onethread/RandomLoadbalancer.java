package basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._00Onethread;

import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer.Server;

import java.util.List;
import java.util.Random;

public class RandomLoadbalancer extends AbstractLoadBalancer{
    @Override
    public Server getNextServer(List<Server> servers) {
        int size = servers.size();
        int random = new Random().nextInt(size);

        return servers.get(random);

    }
}
