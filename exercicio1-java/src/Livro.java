public class Livro extends ItemBiblioteca {

    private final String autor;

    public Livro(String codigo, String titulo, String autor) {
        super(codigo, titulo);
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public int getPrazoDias() {
        return 14;
    }

    @Override
    public double getMultaPorDia() {
        return 0.50;
    }

    @Override
    public String getTipo() {
        return "Livro";
    }
}
