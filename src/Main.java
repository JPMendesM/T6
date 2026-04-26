public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            StdOut.println("Uso: java Main <arquivo1.txt> <arquivo2.txt>");
            return;
        }

        StdOut.println("=== LEITURA DOS ARQUIVOS ===");
        Graph G1 = new Graph(new In(args[0]));
        Graph G2 = new Graph(new In(args[1]));

        StdOut.println("\n[Grafo 1] - " + args[0]);
        StdOut.println(G1.toString());

        StdOut.println("[Grafo 2] - " + args[1]);
        StdOut.println(G2.toString());

        StdOut.println("\n=== ANALISE ESTRUTURAL ===");
        TreeIsomorphism tree1 = new TreeIsomorphism(G1);
        TreeIsomorphism tree2 = new TreeIsomorphism(G2);

        imprimirRelatorio("Grafo 1", tree1);
        imprimirRelatorio("Grafo 2", tree2);

        StdOut.println("\n=== VEREDITO FINAL ===");
        if (!tree1.isTree() || !tree2.isTree()) {
            StdOut.println("ERRO: O isomorfismo nao pode ser calculado pois pelo menos uma das entradas nao e uma arvore valida.");
        } else {
            if (tree1.getCanonicalCode().equals(tree2.getCanonicalCode())) {
                StdOut.println("RESULTADO: As arvores SAO isomorfas! (Possuem a mesma estrutura canonica)");
            } else {
                StdOut.println("RESULTADO: As arvores NAO SAO isomorfas! (Estruturas diferem)");
            }
        }
    }

    private static void imprimirRelatorio(String nome, TreeIsomorphism tree) {
        StdOut.println("\n-> Relatorio do " + nome + ":");
        if (!tree.isTree()) {
            StdOut.println("   Status: INVALIDO (" + tree.getReasonNotTree() + ")");
        } else {
            StdOut.println("   Status: Arvore Valida");
            StdOut.println("   Centro(s): " + tree.getCenters());
            StdOut.println("   Codigo Canonico: " + tree.getCanonicalCode());
        }
    }
}