package basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer._00Onethread;

import basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer.Server;

public class Main {
    public static void main(String[] args) {
        LoadBalancer roundRobinLB = LoadBalancerFactory.createLoadBalancer("roundrobin");
        LoadBalancer leastConnectionLB = LoadBalancerFactory.createLoadBalancer("leastconnection");

        Server server1 = new Server("192.168.1.1", 80);
        Server server2 = new Server("192.168.1.2", 80);
        Server server3 = new Server("192.168.1.3", 80);
        Server server4 = new Server("192.168.1.1", 80); // Duplicate server

        roundRobinLB.register(server1);
        roundRobinLB.register(server2);
        roundRobinLB.register(server3);
        roundRobinLB.register(server4); // Should not be added as a duplicate

        System.out.println("Round Robin:");
        for (int i = 0; i < 6; i++) {
            System.out.println(roundRobinLB.getNextServer(roundRobinLB.getServers()).getIp());
        }

        roundRobinLB.unregister(server2);

        System.out.println("\nRound Robin after unregistering server2:");
        for (int i = 0; i < 6; i++) {
            System.out.println(roundRobinLB.getNextServer(roundRobinLB.getServers()).getIp());
        }

        leastConnectionLB.register(server1);
        leastConnectionLB.register(server2);
        leastConnectionLB.register(server3);

        System.out.println("\nLeast Connection:");
        for (int i = 0; i < 6; i++) {
            Server server = leastConnectionLB.getNextServer(leastConnectionLB.getServers());
            server.incrementConnections();
            System.out.println(server.getIp() + " (Connections: " + server.getActiveConnections() + ")");
        }
    }
}

