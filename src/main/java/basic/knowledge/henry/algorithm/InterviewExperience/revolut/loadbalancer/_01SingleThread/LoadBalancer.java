package basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._01SingleThread;

import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer.Server;

import java.util.ArrayList;
import java.util.List;

public class LoadBalancer{
    List<Server> servers;
    ILoadbalancerStrategy strategy;

    public LoadBalancer(ILoadbalancerStrategy strategy){
        servers = new ArrayList<>();
        this.strategy = strategy;
    }
    public void registry(Server server){
        if(!servers.contains(server)){
            servers.add(server);
        }
    }
    public void unregistry(Server server){
        if(servers.contains(server)){
            servers.remove(server);
        }
    }

    public List<Server> allServers(){
        return this.servers;
    }

    public Server selectServer(){
        return this.strategy.selectServer(servers);
    }

    public void setStrategy(ILoadbalancerStrategy strategy) {
        this.strategy = strategy;
    }


}
