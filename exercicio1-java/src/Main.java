public class Main {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(10, 5);

        // Cadastro do acervo
        biblioteca.cadastrarItem(new Livro("L001", "Dom Casmurro", "Machado de Assis"));
        biblioteca.cadastrarItem(new Livro("L002", "O Cortiço", "Aluísio Azevedo"));
        biblioteca.cadastrarItem(new Livro("L003", "Vidas Secas", "Graciliano Ramos"));
        biblioteca.cadastrarItem(new Livro("L004", "Clean Code", "Robert C. Martin"));
        biblioteca.cadastrarItem(new Revista("R001", "Superinteressante", 450));
        biblioteca.cadastrarItem(new Revista("R002", "Ciência Hoje", 390));

        // Cadastro de usuários
        Aluno ana = new Aluno("Ana");
        Professor carlos = new Professor("Carlos");
        biblioteca.cadastrarUsuario(ana);
        biblioteca.cadastrarUsuario(carlos);

        System.out.println("== Acervo inicial ==");
        biblioteca.listarAcervo();

        System.out.println();
        System.out.println("== Empréstimos ==");
        // Empréstimo bem-sucedido
        biblioteca.emprestar("L001", ana);

        // Ana (aluna, limite 3) pega mais dois itens e tenta um quarto
        biblioteca.emprestar("L002", ana);
        biblioteca.emprestar("R001", ana);
        biblioteca.emprestar("L003", ana); // recusado por limite atingido

        // Item já emprestado
        biblioteca.emprestar("L001", carlos); // recusado: já emprestado

        // Professor consegue, pois o limite dele é 5
        biblioteca.emprestar("L003", carlos);

        System.out.println();
        System.out.println("== Situação dos usuários ==");
        System.out.println(ana);
        System.out.println(carlos);

        System.out.println();
        System.out.println("== Acervo após os empréstimos ==");
        biblioteca.listarAcervo();

        System.out.println();
        System.out.println("== Devoluções ==");
        biblioteca.devolver("L001", ana, 0);  // no prazo
        biblioteca.devolver("R001", ana, 3);  // 3 dias de atraso: 3 x R$ 1,00
        biblioteca.devolver("L003", carlos, 4); // 4 dias de atraso: 4 x R$ 0,50

        // Agora Ana tem 1 item e volta a poder emprestar
        biblioteca.emprestar("L003", ana);

        System.out.println();
        System.out.println("== Acervo final ==");
        biblioteca.listarAcervo();
    }
}
