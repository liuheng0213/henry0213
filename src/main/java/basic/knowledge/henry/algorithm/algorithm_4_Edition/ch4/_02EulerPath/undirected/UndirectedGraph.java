package basic.knowledge.henry.algorithm.algorithm_4_Edition.ch4._02EulerPath.undirected;

import basic.knowledge.henry.algorithm.algorithm_4_Edition.ch4._02EulerPath.Graph;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraph implements Graph {

    private List<Integer>[] adj;

    public UndirectedGraph(int num) {
        adj = new ArrayList[num];
        for (int i = 0; i < num; i++) {
            adj[i] = new ArrayList<>();
        };
    }

    @Override
    public void addEdge(int v, int w) {
        this.adj[v].add(w);
        this.adj[w].add(v);
    }

    @Override
    public List<Integer> neighbors(int v) {
        return this.adj[v];
    }

    @Override
    public int size() {
        return 0;
    }


}
