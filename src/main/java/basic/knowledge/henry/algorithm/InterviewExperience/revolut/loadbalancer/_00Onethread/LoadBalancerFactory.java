package basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._00Onethread;

public class LoadBalancerFactory {
    public static LoadBalancer createLoadBalancer(String type) {
//        if(type == Type.RANDOM.name()){
//
//        }

        switch (type.toLowerCase()) {
            case "roundrobin":
                return new RoundRobinLoadBalancer();
            case "leastconnection":
                return new LeastConnectionLoadBalancer();
            default:
                throw new IllegalArgumentException("Unknown load balancer type: " + type);
        }
    }
}
