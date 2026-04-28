public class Main {
    public static void main(String[] args) {
        String path1, path2;

        if (args.length < 2) {
            path1 = "dados/iso-path4-a.txt";
            path2 = "dados/iso-path4-b.txt";
            StdOut.println("Aviso: Argumentos insuficientes. Usando padrao:");
            StdOut.println("1: " + path1);
            StdOut.println("2: " + path2 + "\n");
        } else {
            path1 = args[0];
            path2 = args[1];
        }

        Graph tree1 = new Graph(new In(path1));
        Graph tree2 = new Graph(new In(path2));

        StdOut.println("Arvore 1:");
        StdOut.println(tree1);
        StdOut.println();

        StdOut.println("Arvore 2:");
        StdOut.println(tree2);
        StdOut.println();

        TreeIsomorphism analysis1 = new TreeIsomorphism(tree1);
        TreeIsomorphism analysis2 = new TreeIsomorphism(tree2);

        StdOut.println("TODO: complete a validacao das entradas, o calculo dos centros,");
        StdOut.println("a codificacao canonica e o veredito final de isomorfismo.");

        StdOut.println(analysis1.getValidationMessage());
        StdOut.println(analysis2.getValidationMessage());
        StdOut.println(analysis1.getCanonicalEncoding());
        StdOut.println(analysis2.getCanonicalEncoding());
    }
}