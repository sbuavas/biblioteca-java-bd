/**
 * Item do acervo da biblioteca. Prazo e multa variam conforme o tipo do item,
 * por isso são métodos abstratos e não atributos comuns.
 */
public abstract class ItemBiblioteca {

    private final String codigo;
    private final String titulo;
    private boolean disponivel;

    protected ItemBiblioteca(String codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.disponivel = true;
    }

    /** Quantidade de dias que o item pode ficar emprestado. */
    public abstract int getPrazoDias();

    /** Valor da multa por dia de atraso. */
    public abstract double getMultaPorDia();

    /** Nome do tipo do item, usado na listagem (Livro, Revista...). */
    public abstract String getTipo();

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    /** Calcula a multa para uma quantidade de dias de atraso. */
    public double calcularMulta(int diasAtraso) {
        if (diasAtraso <= 0) {
            return 0.0;
        }
        return diasAtraso * getMultaPorDia();
    }

    // A disponibilidade só muda como consequência de emprestar() e devolver()
    // na Biblioteca. Por isso não existe setDisponivel() público: os métodos
    // abaixo são visíveis apenas dentro do pacote.
    void marcarEmprestado() {
        this.disponivel = false;
    }

    void marcarDevolvido() {
        this.disponivel = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %-8s %-35s prazo: %2d dias | multa: R$ %.2f/dia | %s",
                codigo, getTipo(), titulo, getPrazoDias(), getMultaPorDia(),
                disponivel ? "disponível" : "emprestado");
    }
}
