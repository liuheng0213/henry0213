package basic.knowledge.henry.algorithm.InterviewExperience.revolut.loadbalancer;

import java.util.Objects;

public class Server {
    private final String ip;
    private final int port;

    private  int activeConnections; // For Least Connection Method

    public Server(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.activeConnections = 0;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }



    public int getActiveConnections() {
        return activeConnections;
    }



    public void incrementConnections() {
        activeConnections++;
    }

    public void decrementConnections() {
        activeConnections--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Server server = (Server) o;
        return port == server.port && Objects.equals(ip, server.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, port);
    }
}
