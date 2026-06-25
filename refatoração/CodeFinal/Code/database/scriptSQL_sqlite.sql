PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS empresa (
    cnpj TEXT NOT NULL PRIMARY KEY,
    razao_social TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    resp_legal TEXT NOT NULL,
    dia_fechamento_ponto INTEGER NOT NULL DEFAULT 30,
    aliquota_fgts NUMERIC NOT NULL DEFAULT 0.08,
    teto_vr NUMERIC NOT NULL DEFAULT 500.0,
    dias_uteis INTEGER NOT NULL DEFAULT 22,
    horas_mes INTEGER NOT NULL DEFAULT 220
);

CREATE TABLE IF NOT EXISTS endereco_empresa (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    cnpj_empresa TEXT NOT NULL,
    cep TEXT NOT NULL,
    logradouro TEXT NOT NULL,
    bairro TEXT NOT NULL,
    complemento TEXT,
    FOREIGN KEY (cnpj_empresa) REFERENCES empresa(cnpj)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS usuario (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT,
    senha TEXT,
    perfil INTEGER,
    status INTEGER
);

CREATE TABLE IF NOT EXISTS funcionarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    cpf TEXT UNIQUE NOT NULL,
    cargo TEXT NOT NULL,
    data_admissao TEXT NOT NULL,
    salario_bruto NUMERIC NOT NULL,
    numero_dependentes INTEGER NOT NULL DEFAULT 0,
    status INTEGER DEFAULT 1
);

CREATE TABLE IF NOT EXISTS auditoria (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    timestamp TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario TEXT NOT NULL,
    perfil TEXT NOT NULL,
    acao TEXT NOT NULL,
    entidade TEXT,
    detalhes TEXT
);

CREATE TABLE IF NOT EXISTS rubrica (
    codigo INTEGER NOT NULL PRIMARY KEY,
    descricao TEXT NOT NULL,
    natureza TEXT NOT NULL,
    tipo TEXT NOT NULL,
    incide_inss INTEGER NOT NULL DEFAULT 0,
    incide_fgts INTEGER NOT NULL DEFAULT 0,
    incide_irrf INTEGER NOT NULL DEFAULT 0,
    padrao INTEGER NOT NULL DEFAULT 0,
    ativo INTEGER NOT NULL DEFAULT 1
);

INSERT OR IGNORE INTO rubrica VALUES (1,  'Salário Padrão',        'Provento', 'Fixo',     1, 1, 1, 1, 1);
INSERT OR IGNORE INTO rubrica VALUES (2,  'Horas Extras 50%',      'Provento', 'Variável', 1, 1, 1, 1, 1);
INSERT OR IGNORE INTO rubrica VALUES (3,  'Horas Extras 100%',     'Provento', 'Variável', 1, 1, 1, 1, 1);
INSERT OR IGNORE INTO rubrica VALUES (4,  'Atraso por hora',       'Desconto', 'Variável', 0, 0, 0, 1, 1);
INSERT OR IGNORE INTO rubrica VALUES (5,  'Falta por dia',         'Desconto', 'Variável', 0, 0, 0, 1, 1);

INSERT OR IGNORE INTO rubrica VALUES (100,'Desconto INSS',         'Desconto', 'Fixo',     0, 0, 0, 1, 1);
INSERT OR IGNORE INTO rubrica VALUES (101,'Desconto IRRF',         'Desconto', 'Fixo',     0, 0, 0, 1, 1);
INSERT OR IGNORE INTO rubrica VALUES (102,'Recolhimento FGTS',     'Desconto', 'Fixo',     0, 0, 0, 1, 1);
INSERT OR IGNORE INTO rubrica VALUES (103,'Desconto DSR',          'Desconto', 'Variável', 0, 0, 0, 1, 1);

INSERT OR IGNORE INTO rubrica VALUES (901,'Vale Transporte (VT)',  'Desconto', 'Fixo',     0, 0, 0, 1, 1);
INSERT OR IGNORE INTO rubrica VALUES (902,'Vale Alimentação (PAT)','Desconto', 'Fixo',     0, 0, 0, 1, 1);

CREATE TABLE IF NOT EXISTS folha_mes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    competencia TEXT NOT NULL,
    dias_uteis INTEGER NOT NULL,
    status TEXT NOT NULL DEFAULT 'Aberta',
    data_inicio TEXT,
    data_fim TEXT
);

CREATE TABLE IF NOT EXISTS lancamento (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_folha INTEGER NOT NULL,
    cpf_funcionario TEXT NOT NULL,
    codigo_rubrica INTEGER NOT NULL,
    quantidade REAL,
    data_clt TEXT,
    valor REAL,
    modalidade TEXT NOT NULL DEFAULT 'Valor',
    base_calculo TEXT,
    data_inicio TEXT,
    data_fim TEXT,
    FOREIGN KEY (codigo_rubrica) REFERENCES rubrica(codigo),
    FOREIGN KEY (id_folha) REFERENCES folha_mes(id),
    FOREIGN KEY (cpf_funcionario) REFERENCES funcionarios(cpf)
);

CREATE TABLE IF NOT EXISTS faixa_inss (
    piso NUMERIC NOT NULL,
    teto NUMERIC NOT NULL,
    aliquota NUMERIC NOT NULL,
    parcela_deduzir NUMERIC NOT NULL
);

CREATE TABLE IF NOT EXISTS faixa_irrf (
    piso NUMERIC NOT NULL,
    teto NUMERIC NOT NULL,
    aliquota NUMERIC NOT NULL,
    parcela_deduzir NUMERIC NOT NULL
);