# Sistema de Biblioteca - Java + PostgreSQL

Atividades 06, 07 e 08 do curso Dev. Full Stack Jr. (+praTi / Codifica).

- `exercicio1-java/` - sistema de biblioteca em Java (classes abstratas, herança e polimorfismo)
- `exercicio2-sql/` - criação das tabelas e dados de teste no PostgreSQL
- `exercicio3-consultas/` - as 4 consultas pedidas no exercício 3

## Exercício 1 - Java

Classes:

- `ItemBiblioteca` (abstrata): codigo, titulo e disponivel. Prazo e multa por dia são métodos abstratos, cada subclasse define o seu.
- `Livro`: 14 dias de prazo, multa de R$ 0,50 por dia.
- `Revista`: 7 dias de prazo, multa de R$ 1,00 por dia.
- `Usuario` (abstrata): nome e quantidade de itens emprestados. O limite é abstrato.
- `Aluno`: até 3 itens. `Professor`: até 5 itens.
- `Biblioteca`: guarda os itens e usuários em arrays e tem os métodos `emprestar()`, `devolver()` e `listarAcervo()`.
- `Main`: cenário de teste (cadastro, empréstimo ok, empréstimo recusado por limite, item já emprestado e devoluções com e sem multa).

Algumas coisas que fiz seguindo as dicas da lista:

- não tem `setDisponivel()` público, a disponibilidade só muda dentro de `emprestar()` e `devolver()`;
- `listarAcervo()` percorre o array num laço só e chama o `toString()` de cada item, sem `if` de tipo. Para criar um novo tipo (DVD, por exemplo) basta criar a subclasse.

Para compilar e rodar (Java 17+):

```bash
cd exercicio1-java
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```

## Exercício 2 - Modelagem

- `01_criar_tabelas.sql`: tabelas `item`, `usuario` e `emprestimo` (nessa ordem, por causa das chaves estrangeiras). Chaves primárias com `GENERATED ALWAYS AS IDENTITY` e `CHECK` nas colunas de tipo. `data_devolucao` fica `NULL` enquanto o item não é devolvido.
- `02_dados_teste.sql`: 4 itens, 2 usuários e 2 empréstimos (um em aberto e um devolvido com multa).

## Exercício 3 - Consultas

`consultas.sql` tem as 4 consultas na ordem: acervo completo, empréstimos em aberto (JOIN), total de multas por usuário (GROUP BY + SUM) e itens nunca emprestados (LEFT JOIN + IS NULL).

Para rodar:

```bash
psql -U postgres -f exercicio2-sql/01_criar_tabelas.sql
psql -U postgres -f exercicio2-sql/02_dados_teste.sql
psql -U postgres -f exercicio3-consultas/consultas.sql
```
