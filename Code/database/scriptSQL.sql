CREATE TABLE IF NOT EXISTS empresa (
    cnpj varchar(18) not null primary key,
    razao_social varchar(120) not null,
    email varchar(120) not null unique,
    resp_legal varchar(120) not null,
    dia_fechamento_ponto int not null default 30,
    aliquota_fgts decimal(5,2) not null default 0.08,
    teto_vr decimal(10,2) not null default 500.0,
    dias_uteis int not null default 22,
    horas_mes int not null default 220
);

CREATE TABLE IF NOT EXISTS endereco_empresa (
    id int not null auto_increment primary key,
    cnpj_empresa varchar(18) not null,
    cep varchar(10) not null,
    logradouro varchar(120) not null,
    bairro varchar(80) not null,
    complemento varchar(120),
    foreign key (cnpj_empresa) references empresa(cnpj)
        on delete cascade
        on update cascade
); 

CREATE TABLE IF NOT EXISTS usuario (
    id INT auto_increment primary key,
    nome varchar(100),
    senha varchar(100),
    perfil boolean,
    status boolean
);
 
CREATE TABLE IF NOT EXISTS funcionarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    cargo VARCHAR(100) NOT NULL,
    data_admissao DATE NOT NULL,
    salario_bruto DECIMAL NOT NULL,
    numero_dependentes INT NOT NULL DEFAULT 0,
    status BOOLEAN DEFAULT TRUE
); 

CREATE TABLE IF NOT EXISTS auditoria (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    timestamp DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario VARCHAR(100) NOT NULL, -- Guardará o nome do usuário
    perfil VARCHAR(50) NOT NULL,   -- Guardará "Administrador" ou "Operador"
    acao VARCHAR(100) NOT NULL,
    entidade VARCHAR(100),
    detalhes VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS rubrica (
    codigo int not null primary key,
    descricao varchar(100) not null,
    natureza varchar(10) not null, -- Provento ou Desconto
    tipo varchar(10) not null, -- Fixo ou Variável
    incide_inss boolean not null default false,
    incide_fgts boolean not null default false,
    incide_irrf boolean not null default false,
    padrao boolean not null default false, -- true = rubrica padrão
    ativo boolean not null default true
);

-- Rubricas padrão (Blindadas)
INSERT IGNORE INTO rubrica VALUES (1,  'Salário Padrão',        'Provento', 'Fixo',     TRUE,  TRUE,  TRUE, TRUE, TRUE);
INSERT IGNORE INTO rubrica VALUES (2,  'Horas Extras 50%',      'Provento', 'Variável', TRUE,  TRUE,  TRUE, TRUE, TRUE);
INSERT IGNORE INTO rubrica VALUES (3,  'Horas Extras 100%',     'Provento', 'Variável', TRUE,  TRUE,  TRUE, TRUE, TRUE);
INSERT IGNORE INTO rubrica VALUES (4,  'Atraso por hora',       'Desconto', 'Variável', FALSE, FALSE, FALSE, TRUE, TRUE);
INSERT IGNORE INTO rubrica VALUES (5,  'Falta por dia',         'Desconto', 'Variável', FALSE, FALSE, FALSE, TRUE, TRUE);

-- Parâmetros Legais (Blindados)
INSERT IGNORE INTO rubrica VALUES (100,'Desconto INSS',         'Desconto', 'Fixo',     FALSE, FALSE, FALSE, TRUE, TRUE);
INSERT IGNORE INTO rubrica VALUES (101,'Desconto IRRF',         'Desconto', 'Fixo',     FALSE, FALSE, FALSE, TRUE, TRUE);
INSERT IGNORE INTO rubrica VALUES (102,'Recolhimento FGTS',     'Desconto', 'Fixo',     FALSE, FALSE, FALSE, TRUE, TRUE);
INSERT IGNORE INTO rubrica VALUES (103,'Desconto DSR',          'Desconto', 'Variável', FALSE, FALSE, FALSE, TRUE, TRUE);

-- Benefícios Padrões (Blindados)
INSERT IGNORE INTO rubrica VALUES (901,'Vale Transporte (VT)',  'Desconto', 'Fixo',     FALSE, FALSE, FALSE, TRUE, TRUE);
INSERT IGNORE INTO rubrica VALUES (902,'Vale Alimentação (PAT)','Desconto', 'Fixo',     FALSE, FALSE, FALSE, TRUE, TRUE);

CREATE TABLE IF NOT EXISTS folha_mes (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    competencia VARCHAR(7) NOT NULL, -- Ex: '06/2026'
    dias_uteis INT NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'Aberta',
    data_inicio DATE,
    data_fim DATE
);

CREATE TABLE IF NOT EXISTS lancamento (
    id              INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_folha        INT NOT NULL,          -- QUANDO (Mês/Ano da folha corrente)
    cpf_funcionario VARCHAR(14) NOT NULL,   -- QUEM (O funcionário que sofreu o evento)
    codigo_rubrica  INT NOT NULL,          -- O QUE (O link para a sua tabela de rubricas)
    
    -- Dados específicos do momento do lançamento:
    quantidade      DOUBLE,                -- QUANTO (2.5 horas, 1 dia de falta, 40 minutos de atraso)
    data_clt        DATE,                  -- Dia exato em que aconteceu
    valor           DOUBLE,                -- O montante financeiro (R$) ou a porcentagem exata (%)
    modalidade      VARCHAR(20) NOT NULL DEFAULT 'Valor', -- 'Quantidade', 'Valor', 'Porcentagem'
    base_calculo    VARCHAR(50),           -- 'Salário Bruto', 'Salário Líquido', 'Livre'
    
    -- Campos exclusivos de atestado (requisito 3.1.7.4.4)
    data_inicio     DATE,
    data_fim        DATE,

    -- AS TRÊS CHAVES ESTRANGEIRAS QUE SÃO O CORAÇÃO DA TABELA:
    FOREIGN KEY (codigo_rubrica) REFERENCES rubrica(codigo),
    FOREIGN KEY (id_folha) REFERENCES folha_mes(id),       
    FOREIGN KEY (cpf_funcionario) REFERENCES funcionarios(cpf) 
);

CREATE TABLE IF NOT EXISTS faixa_inss (
    piso DECIMAL(10,2) NOT NULL,
    teto DECIMAL(10,2) NOT NULL,
    aliquota DECIMAL(5,2) NOT NULL,
    parcela_deduzir DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS faixa_irrf (
    piso DECIMAL(10,2) NOT NULL,
    teto DECIMAL(10,2) NOT NULL,
    aliquota DECIMAL(5,2) NOT NULL,
    parcela_deduzir DECIMAL(10,2) NOT NULL
);
