public class TreeIsomorphism {
    private final Graph graph;

    public TreeIsomorphism(Graph graph) {
        if (graph == null) {
            throw new IllegalArgumentException("graph nao pode ser nulo");
        }
        this.graph = graph;
    }

    public Graph getGraph() {
        return graph;
    }

    /**
     * Valida se o grafo é uma árvore:
     * 1. Conexão: Deve ser possível visitar todos os V vértices partindo do 0.
     * 2. Arestas: Deve possuir exatamente V-1 arestas.
     */
    public boolean isTree() {
        int V = graph.V();
        if (V == 0) return false;
        if (graph.E() != V - 1) return false;

        boolean[] marked = new boolean[V];
        int count = countVisited(0, marked);

        return count == V;
    }

    private int countVisited(int s, boolean[] marked) {
        int count = 1;
        marked[s] = true;
        for (int v : graph.adj(s)) {
            if (!marked[v]) {
                count += countVisited(v, marked);
            }
        }
        return count;
    }

    public String getValidationMessage() {
        return isTree() ? "A entrada e uma arvore valida." : "A entrada NAO e uma arvore (ciclos ou desconexa).";
    }

    /**
     * Encontra o(s) centro(s) da árvore removendo folhas sucessivamente.
     */
    public int[] getCenters() {
        int V = graph.V();
        if (V == 1) return new int[]{0};

        int[] degree = new int[V];
        java.util.List<Integer> leaves = new java.util.ArrayList<Integer>();

        for (int v = 0; v < V; v++) {
            degree[v] = graph.degree(v);
            if (degree[v] <= 1) leaves.add(v);
        }

        int processed = leaves.size();
        while (processed < V) {
            java.util.List<Integer> newLeaves = new java.util.ArrayList<Integer>();
            for (int u : leaves) {
                for (int v : graph.adj(u)) {
                    degree[v]--;
                    if (degree[v] == 1) newLeaves.add(v);
                }
            }
            processed += newLeaves.size();
            leaves = newLeaves;
        }

        int[] centers = new int[leaves.size()];
        for (int i = 0; i < leaves.size(); i++) centers[i] = leaves.get(i);
        return centers;
    }

    /**
     * Gera a codificação canônica baseada nos centros.
     */
    public String getCanonicalEncoding() {
        if (!isTree()) return null;

        int[] centers = getCenters();
        java.util.List<String> codes = new java.util.ArrayList<String>();

        for (int center : centers) {
            codes.add(encode(center, -1));
        }

        java.util.Collections.sort(codes);
        return codes.get(0);
    }

    /**
     * Codifica a subárvore recursivamente.
     */
    private String encode(int u, int p) {
        java.util.List<String> childCodes = new java.util.ArrayList<String>();

        for (int v : graph.adj(u)) {
            if (v != p) {
                childCodes.add(encode(v, u));
            }
        }

        java.util.Collections.sort(childCodes);

        StringBuilder sb = new StringBuilder("(");
        for (String code : childCodes) {
            sb.append(code);
        }
        sb.append(")");

        return sb.toString();
    }
}