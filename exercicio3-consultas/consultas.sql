-- Exercício 3 - Consultas SQL
-- Executar depois de 01_criar_tabelas.sql e 02_dados_teste.sql.

-- 1) Todo o acervo, com código, título, tipo e disponibilidade
SELECT codigo,
       titulo,
       tipo,
       CASE WHEN disponivel THEN 'disponível' ELSE 'emprestado' END AS situacao
FROM item
ORDER BY codigo;

-- 2) Empréstimos em aberto, com o nome do usuário e o título do item
--    (em aberto = data_devolucao IS NULL)
SELECT e.id            AS emprestimo_id,
       u.nome          AS usuario,
       i.titulo        AS item,
       e.data_retirada,
       e.data_devolucao_prevista
FROM emprestimo e
JOIN usuario u ON u.id = e.usuario_id
JOIN item    i ON i.id = e.item_id
WHERE e.data_devolucao IS NULL
ORDER BY e.data_retirada;

-- 3) Total de multas acumuladas por usuário
--    LEFT JOIN para que usuários sem empréstimo apareçam com total 0
SELECT u.nome,
       COALESCE(SUM(e.valor_multa), 0) AS total_multas
FROM usuario u
LEFT JOIN emprestimo e ON e.usuario_id = u.id
GROUP BY u.id, u.nome
ORDER BY total_multas DESC, u.nome;

-- 4) Itens que nunca foram emprestados
--    LEFT JOIN com emprestimo e teste IS NULL do lado do empréstimo
SELECT i.codigo,
       i.titulo,
       i.tipo
FROM item i
LEFT JOIN emprestimo e ON e.item_id = i.id
WHERE e.id IS NULL
ORDER BY i.codigo;
