package basic.knowledge.henry.algorithm.algorithm_4_Edition.ch4._01undirected_graph.demo;

public class DepthFirstSearch implements Search {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph graph, int s) {
        marked = new boolean[graph.v()];
        this.count = 0;
        for (int v = 0; v < graph.v(); v++) {
            marked[v] = false;
        }
        dfs(graph, s);
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        this.count++;
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                dfs(graph, w);
            }
        }
    }

    @Override
    public boolean marked(int v) {
        return marked[v];
    }

    /**
     * 与s 联通的 顶点总数
     *
     * @return
     */
    @Override
    public int count() {
        return this.count;
    }
}
