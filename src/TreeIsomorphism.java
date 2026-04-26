import java.util.*;

public class TreeIsomorphism {
    private Graph G;
    private boolean isTree;
    private String reasonNotTree;
    private List<Integer> centers;
    private String canonicalCode;

    public TreeIsomorphism(Graph G) {
        this.G = G;
        this.centers = new ArrayList<>();

        validateTree();
        if (isTree) {
            findCenters();
            computeCanonicalCode();
        }
    }

    // 1. Valida se o grafo é realmente uma árvore conectada e sem ciclos
    private void validateTree() {
        int V = G.V();
        if (V == 0) {
            isTree = false;
            reasonNotTree = "O grafo esta vazio (0 vertices).";
            return;
        }

        // Uma árvore deve ter exatamente V - 1 arestas
        if (G.E() != V - 1) {
            isTree = false;
            reasonNotTree = "Arestas (" + G.E() + ") diferentes de V-1 (" + (V - 1) + "). Contem ciclos ou e desconexo.";
            return;
        }

        // Verifica se todos os vértices estão conectados
        boolean[] visited = new boolean[V];
        dfs(0, visited);

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                isTree = false;
                reasonNotTree = "O grafo e desconexo. O vertice " + i + " nao foi alcancado.";
                return;
            }
        }

        isTree = true;
        reasonNotTree = "Valido";
    }

    private void dfs(int v, boolean[] visited) {
        visited[v] = true;
        for (int w : G.adj(v)) {
            if (!visited[w]) dfs(w, visited);
        }
    }

    // 2. Encontra os centros removendo as folhas iterativamente
    private void findCenters() {
        int V = G.V();
        if (V == 1) {
            centers.add(0);
            return;
        }

        int[] degree = new int[V];
        List<Integer> leaves = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            int d = 0;
            for (int w : G.adj(i)) d++;
            degree[i] = d;

            if (degree[i] == 0 || degree[i] == 1) {
                leaves.add(i);
            }
        }

        int processed = leaves.size();
        while (processed < V) {
            List<Integer> newLeaves = new ArrayList<>();
            for (int u : leaves) {
                for (int v : G.adj(u)) {
                    degree[v]--;
                    if (degree[v] == 1) {
                        newLeaves.add(v);
                    }
                }
            }
            processed += newLeaves.size();
            leaves = newLeaves;
        }
        centers = leaves;
    }

    // 3. Computa o código canônico
    private void computeCanonicalCode() {
        if (centers.size() == 1) {
            canonicalCode = encode(centers.get(0), -1);
        } else if (centers.size() == 2) {
            // Em caso de bi-centro, calculamos a partir de ambos e pegamos o lexicograficamente menor.
            // Isso garante uma única representação textual absoluta para a árvore.
            String code1 = encode(centers.get(0), -1);
            String code2 = encode(centers.get(1), -1);
            canonicalCode = code1.compareTo(code2) < 0 ? code1 : code2;
        }
    }

    // 4. Codificação Recursiva (DFS enraizada)
    private String encode(int u, int parent) {
        List<String> childCodes = new ArrayList<>();

        for (int v : G.adj(u)) {
            if (v != parent) {
                childCodes.add(encode(v, u));
            }
        }

        if (childCodes.isEmpty()) {
            return "()"; // É folha
        }

        // Ordena para garantir que a ordem de leitura das arestas não influencie
        Collections.sort(childCodes);

        StringBuilder sb = new StringBuilder("(");
        for (String code : childCodes) {
            sb.append(code);
        }
        sb.append(")");

        return sb.toString();
    }

    public boolean isTree() { return isTree; }
    public String getReasonNotTree() { return reasonNotTree; }
    public List<Integer> getCenters() { return centers; }
    public String getCanonicalCode() { return canonicalCode; }
}