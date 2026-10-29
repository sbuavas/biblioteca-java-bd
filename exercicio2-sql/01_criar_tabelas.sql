-- Exercício 2 - Modelagem do banco de dados (PostgreSQL)
-- Mesmo cenário do Exercício 1, sem herança: o tipo do item e do usuário
-- é uma coluna com valores restritos por CHECK.

DROP TABLE IF EXISTS emprestimo;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS usuario;

-- 1) item: não depende de ninguém
CREATE TABLE item (
    id         INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo     VARCHAR(10)  NOT NULL UNIQUE,
    titulo     VARCHAR(150) NOT NULL,
    tipo       VARCHAR(10)  NOT NULL CHECK (tipo IN ('livro', 'revista')),
    autor      VARCHAR(100),           -- preenchido para livro
    edicao     INTEGER,                -- preenchido para revista
    disponivel BOOLEAN      NOT NULL DEFAULT TRUE
);

-- 2) usuario: não depende de ninguém
CREATE TABLE usuario (
    id           INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome         VARCHAR(100) NOT NULL,
    tipo         VARCHAR(10)  NOT NULL CHECK (tipo IN ('aluno', 'professor')),
    limite_itens INTEGER      NOT NULL CHECK (limite_itens > 0)
);

-- 3) emprestimo: depende de item e usuario, por isso é criada por último
CREATE TABLE emprestimo (
    id                     INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    item_id                INTEGER NOT NULL REFERENCES item(id),
    usuario_id             INTEGER NOT NULL REFERENCES usuario(id),
    data_retirada          DATE    NOT NULL,
    data_devolucao_prevista DATE   NOT NULL,
    data_devolucao         DATE,                       -- NULL enquanto o item não voltar
    valor_multa            NUMERIC(10, 2) NOT NULL DEFAULT 0 CHECK (valor_multa >= 0),
    CHECK (data_devolucao_prevista >= data_retirada),
    CHECK (data_devolucao IS NULL OR data_devolucao >= data_retirada)
);
