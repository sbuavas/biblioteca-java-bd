/**
 * Controla o acervo e os empréstimos usando arrays de tamanho fixo.
 */
public class Biblioteca {

    private final ItemBiblioteca[] acervo;
    private final Usuario[] usuarios;
    private int totalItens;
    private int totalUsuarios;

    public Biblioteca(int capacidadeAcervo, int capacidadeUsuarios) {
        this.acervo = new ItemBiblioteca[capacidadeAcervo];
        this.usuarios = new Usuario[capacidadeUsuarios];
        this.totalItens = 0;
        this.totalUsuarios = 0;
    }

    public boolean cadastrarItem(ItemBiblioteca item) {
        if (totalItens >= acervo.length) {
            System.out.println("Acervo cheio. Não foi possível cadastrar " + item.getTitulo());
            return false;
        }
        if (buscarItem(item.getCodigo()) != null) {
            System.out.println("Já existe um item com o código " + item.getCodigo());
            return false;
        }
        acervo[totalItens] = item;
        totalItens++;
        return true;
    }

    public boolean cadastrarUsuario(Usuario usuario) {
        if (totalUsuarios >= usuarios.length) {
            System.out.println("Cadastro de usuários cheio. Não foi possível cadastrar " + usuario.getNome());
            return false;
        }
        usuarios[totalUsuarios] = usuario;
        totalUsuarios++;
        return true;
    }

    /**
     * Empresta o item de código informado ao usuário.
     * Recusa se o item não existir, já estiver emprestado ou se o usuário
     * tiver atingido o limite de itens.
     */
    public boolean emprestar(String codigo, Usuario usuario) {
        ItemBiblioteca item = buscarItem(codigo);
        if (item == null) {
            System.out.println("Empréstimo recusado: item " + codigo + " não encontrado.");
            return false;
        }
        if (!item.isDisponivel()) {
            System.out.println("Empréstimo recusado: \"" + item.getTitulo() + "\" já está emprestado.");
            return false;
        }
        if (!usuario.podeEmprestar()) {
            System.out.println("Empréstimo recusado: " + usuario.getNome() + " atingiu o limite de "
                    + usuario.getLimiteItens() + " itens.");
            return false;
        }
        item.marcarEmprestado();
        usuario.registrarEmprestimo();
        System.out.println("Empréstimo realizado: \"" + item.getTitulo() + "\" para " + usuario.getNome()
                + " (prazo de " + item.getPrazoDias() + " dias).");
        return true;
    }

    /**
     * Devolve o item e retorna a multa devida pelos dias de atraso
     * (0 quando não há atraso).
     */
    public double devolver(String codigo, Usuario usuario, int diasAtraso) {
        ItemBiblioteca item = buscarItem(codigo);
        if (item == null) {
            System.out.println("Devolução recusada: item " + codigo + " não encontrado.");
            return 0.0;
        }
        if (item.isDisponivel()) {
            System.out.println("Devolução recusada: \"" + item.getTitulo() + "\" não estava emprestado.");
            return 0.0;
        }
        item.marcarDevolvido();
        usuario.registrarDevolucao();
        double multa = item.calcularMulta(diasAtraso);
        if (multa > 0) {
            System.out.printf("Devolução de \"%s\" com %d dia(s) de atraso. Multa: R$ %.2f%n",
                    item.getTitulo(), diasAtraso, multa);
        } else {
            System.out.println("Devolução de \"" + item.getTitulo() + "\" no prazo. Sem multa.");
        }
        return multa;
    }

    /**
     * Percorre o acervo em um único laço. Cada item sabe descrever a si mesmo
     * (polimorfismo), então não há verificação de tipo aqui.
     */
    public void listarAcervo() {
        System.out.println("---- Acervo (" + totalItens + " itens) ----");
        for (int i = 0; i < totalItens; i++) {
            System.out.println(acervo[i]);
        }
    }

    public ItemBiblioteca buscarItem(String codigo) {
        for (int i = 0; i < totalItens; i++) {
            if (acervo[i].getCodigo().equals(codigo)) {
                return acervo[i];
            }
        }
        return null;
    }
}
