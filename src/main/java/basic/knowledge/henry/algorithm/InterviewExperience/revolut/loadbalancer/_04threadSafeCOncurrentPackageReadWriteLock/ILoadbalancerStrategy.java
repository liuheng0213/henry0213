package basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._04threadSafeCOncurrentPackageReadWriteLock;

import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer.Server;

import java.util.List;

public interface ILoadbalancerStrategy {
    Server selectServer(List<Server> servers);
}
