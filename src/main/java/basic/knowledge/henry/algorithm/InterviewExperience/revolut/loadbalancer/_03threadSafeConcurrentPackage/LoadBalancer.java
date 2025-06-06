package basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._03threadSafeConcurrentPackage;

import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class LoadBalancer{
    public static void main(String[] args) {
        LoadBalancer loadBalancer = new LoadBalancer(new RandomStrategy());
        Server server = new Server("1", 1);
        Server server1 = new Server("1", 1);
        loadBalancer.registry(server);
        loadBalancer.registry(server1);
        loadBalancer.unregistry(server);
        loadBalancer.unregistry(server1);
        loadBalancer.unregistry(server1);
        System.out.println();
    }
    private List<Server> servers;
    private final AtomicReference<ILoadbalancerStrategy> strategy;


    public LoadBalancer(ILoadbalancerStrategy strategy){
        servers = new CopyOnWriteArrayList<>();
        this.strategy = new AtomicReference<>(strategy);
    }

    /**
     * if adding new servers is allowed during the usage,
     * synchronized is necessary
     * @param server
     */
    public void registry(Server server){
        synchronized (servers){
            if(!servers.contains(server)){
                servers.add(server);
            }
        }

        //servers.add(server); // thread safe

    }
    public void unregistry(Server server){
        servers.remove(server); // thread safe
    }

    public List<Server> allServers(){
        return this.servers;
    }

    public Server selectServer(){
        return strategy.get().selectServer(servers);
    }

    public void setStrategy(ILoadbalancerStrategy strategy) {
        this.strategy.set(strategy);
        //this.strategy = strategy;
    }


}
