-- Exercício 2 - Dados de teste
-- 4 itens, 2 usuários e 2 empréstimos (um em aberto, um já devolvido).
-- Há também um item nunca emprestado, para a consulta 4 do Exercício 3.

INSERT INTO item (codigo, titulo, tipo, autor, edicao, disponivel) VALUES
    ('L001', 'Dom Casmurro',      'livro',   'Machado de Assis', NULL, FALSE),
    ('L002', 'O Cortiço',         'livro',   'Aluísio Azevedo',  NULL, TRUE),
    ('R001', 'Superinteressante', 'revista', NULL,               450,  TRUE),
    ('R002', 'Ciência Hoje',      'revista', NULL,               390,  TRUE);

INSERT INTO usuario (nome, tipo, limite_itens) VALUES
    ('Ana',    'aluno',     3),
    ('Carlos', 'professor', 5);

-- Empréstimo em aberto: Ana retirou "Dom Casmurro" (livro, prazo de 14 dias)
INSERT INTO emprestimo (item_id, usuario_id, data_retirada, data_devolucao_prevista, data_devolucao, valor_multa)
VALUES (
    (SELECT id FROM item WHERE codigo = 'L001'),
    (SELECT id FROM usuario WHERE nome = 'Ana'),
    DATE '2025-10-01', DATE '2025-10-15', NULL, 0
);

-- Empréstimo já devolvido com atraso: Carlos retirou "Superinteressante"
-- (revista, prazo de 7 dias) e devolveu 3 dias depois do previsto: 3 x R$ 1,00
INSERT INTO emprestimo (item_id, usuario_id, data_retirada, data_devolucao_prevista, data_devolucao, valor_multa)
VALUES (
    (SELECT id FROM item WHERE codigo = 'R001'),
    (SELECT id FROM usuario WHERE nome = 'Carlos'),
    DATE '2025-09-10', DATE '2025-09-17', DATE '2025-09-20', 3.00
);
