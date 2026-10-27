/**
 * Usuário da biblioteca. O limite de itens simultâneos depende do tipo
 * (aluno ou professor), por isso é um método abstrato.
 */
public abstract class Usuario {

    private final String nome;
    private int quantidadeEmprestada;

    protected Usuario(String nome) {
        this.nome = nome;
        this.quantidadeEmprestada = 0;
    }

    /** Quantidade máxima de itens que o usuário pode ter ao mesmo tempo. */
    public abstract int getLimiteItens();

    /** Nome do tipo do usuário, usado nas mensagens (Aluno, Professor...). */
    public abstract String getTipo();

    public String getNome() {
        return nome;
    }

    public int getQuantidadeEmprestada() {
        return quantidadeEmprestada;
    }

    public boolean podeEmprestar() {
        return quantidadeEmprestada < getLimiteItens();
    }

    // Assim como a disponibilidade do item, a contagem de empréstimos só muda
    // por consequência de emprestar() e devolver() na Biblioteca.
    void registrarEmprestimo() {
        quantidadeEmprestada++;
    }

    void registrarDevolucao() {
        if (quantidadeEmprestada > 0) {
            quantidadeEmprestada--;
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s (%d/%d itens)", getTipo(), nome,
                quantidadeEmprestada, getLimiteItens());
    }
}
