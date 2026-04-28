public class Main {
    public static void main(String[] args) {
        String path1, path2;

        if (args.length < 2) {
            path1 = "dados/iso-path4-a.txt";
            path2 = "dados/iso-path4-b.txt";
            StdOut.println("1: " + path1);
            StdOut.println("2: " + path2 + "\n");
        } else {
            path1 = args[0];
            path2 = args[1];
        }

        Graph tree1 = new Graph(new In(path1));
        Graph tree2 = new Graph(new In(path2));

        StdOut.println("--- Arvore 1 (Lista de Adjacencia) ---");
        StdOut.println(tree1);

        StdOut.println("--- Arvore 2 (Lista de Adjacencia) ---");
        StdOut.println(tree2);

        TreeIsomorphism analysis1 = new TreeIsomorphism(tree1);
        TreeIsomorphism analysis2 = new TreeIsomorphism(tree2);

        StdOut.println("Validacao Arvore 1: " + analysis1.getValidationMessage());
        StdOut.println("Validacao Arvore 2: " + analysis2.getValidationMessage());

        if (!analysis1.isTree() || !analysis2.isTree()) {
            StdOut.println("\nErro: Uma das entradas nao e uma arvore valida.");
            return;
        }

        int[] c1 = analysis1.getCenters();
        int[] c2 = analysis2.getCenters();

        StdOut.print("Centros Arvore 1: ");
        for(int v : c1) StdOut.print(v + " ");
        StdOut.println();

        StdOut.print("Centros Arvore 2: ");
        for(int v : c2) StdOut.print(v + " ");
        StdOut.println("\n");

        String code1 = analysis1.getCanonicalEncoding();
        String code2 = analysis2.getCanonicalEncoding();

        StdOut.println("Codificacao 1: " + code1);
        StdOut.println("Codificacao 2: " + code2);

        StdOut.println("-------------------------------------------");
        if (code1 != null && code1.equals(code2)) {
            StdOut.println("RESULTADO: As arvores sao ISOMORFAS.");
        } else {
            StdOut.println("RESULTADO: As arvores NAO sao isomorfas.");
        }
        StdOut.println("-------------------------------------------");
    }
}