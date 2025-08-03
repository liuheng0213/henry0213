package basic.knowledge.henry.algorithm.algorithm_4_Edition.ch4._02EulerPath.directed;

import basic.knowledge.henry.algorithm.algorithm_4_Edition.ch4._02EulerPath.Graph;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraph implements Graph {
    private List<Integer>[] adj;

    private int size;

    public DirectedGraph(int num) {
        this.size = num;
        adj = new ArrayList[num];
        for (int i = 0; i <num; i++) {
            adj[i] = new ArrayList<>();
        };
    }

    @Override
    public void addEdge(int v, int w) {
        this.adj[v].add(w);
    }

    @Override
    public List<Integer> neighbors(int v) {
        return this.adj[v];
    }

    @Override
    public int size() {
        return size;
    }

}
