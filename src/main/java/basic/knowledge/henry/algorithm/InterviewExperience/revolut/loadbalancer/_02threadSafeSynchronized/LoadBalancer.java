package basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._02threadSafeSynchronized;

import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LoadBalancer{

    private List<Server> servers;
    private volatile ILoadbalancerStrategy strategy;

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public LoadBalancer(ILoadbalancerStrategy strategy){
        servers = new ArrayList<>();
        this.strategy = strategy;
    }
    public synchronized void registry(Server server){
        if(!servers.contains(server)){
            servers.add(server);
        }
    }
    public void unregistry(Server server){
        servers.remove(server);
    }

    public synchronized List<Server> allServers(){
        return this.servers;
    }

    public Server selectServer(){
        synchronized (strategy) {
            return strategy.selectServer(servers);
        }
        //return this.strategy.selectServer(servers);
    }

    public void setStrategy(ILoadbalancerStrategy strategy) {
        synchronized (this) {
            this.strategy = strategy;
        }
        //this.strategy = strategy;
    }


}
