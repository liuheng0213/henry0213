package basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._03threadSafeCOncurrentPackage;

import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer.Server;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomStrategy implements ILoadbalancerStrategy{
//    private final THread rd = new Random();

    @Override
    public  Server selectServer(List<Server> servers) {
        if (servers.isEmpty()) {
            throw new IllegalStateException("No servers available");
        }
        return servers.get(ThreadLocalRandom.current().nextInt(servers.size()));

    }
}
