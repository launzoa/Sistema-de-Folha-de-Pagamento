/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sfp.core;

/**
 *
 * @author igor.nogueira_unesp
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBD {
    private static final String URL = "jdbc:mysql://localhost:3306/sfp"; //NAO ESQUEÇA DE ALTERAR O NOME DA INSTANCIA
    private static final String USER = "root";
    private static final String PASS = "igor"; //NAO ESQUEÇA DE ALTERAR SUA SENHA

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
/**
COLOQUE AQUI OS SQL USADOS 
 USE sfp;

create table if not exists empresa (
cnpj varchar(18) not null primary key,
razao_social varchar(120) not null,
email varchar(120) not null unique,
resp_legal varchar(120) not null
);

create table if not exists endereco_empresa (
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

CREATE TABLE usuario (
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
    status BOOLEAN DEFAULT TRUE
); 

CREATE TABLE auditoria (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    timestamp DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario VARCHAR(100) NOT NULL, -- Guardará o nome do usuário
    perfil VARCHAR(50) NOT NULL,   -- Guardará "Administrador" ou "Operador"
    acao VARCHAR(100) NOT NULL,
    entidade VARCHAR(100),
    detalhes VARCHAR(255)
);

create table rubrica (
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

-- Rubricas padrão
INSERT INTO rubrica VALUES (1,  'Salário Base',          'Provento', 'Fixo',     TRUE,  TRUE,  TRUE, TRUE, TRUE);
INSERT INTO rubrica VALUES (2,  'Hora Extra 50%',        'Provento', 'Variável', TRUE,  TRUE,  TRUE, TRUE, TRUE);
INSERT INTO rubrica VALUES (3,  'Hora Extra 100%',       'Provento', 'Variável', TRUE,  TRUE,  TRUE, TRUE, TRUE);
INSERT INTO rubrica VALUES (4,  'Bônus / Gratificação',  'Provento', 'Variável', TRUE,  TRUE,  FALSE, TRUE, TRUE);
INSERT INTO rubrica VALUES (5,  'Cesta Básica',          'Provento', 'Fixo',     FALSE, FALSE, FALSE, TRUE, TRUE);
INSERT INTO rubrica VALUES (6,  'PLR',                   'Provento', 'Variável', FALSE, FALSE, FALSE, TRUE, TRUE);
INSERT INTO rubrica VALUES (7,  'Adiantamento Salarial', 'Desconto', 'Variável', FALSE, FALSE, FALSE, TRUE, TRUE);
INSERT INTO rubrica VALUES (8,  'Outros Proventos',      'Provento', 'Variável', FALSE, FALSE, FALSE, TRUE, TRUE);
INSERT INTO rubrica VALUES (101,'Desconto INSS',         'Desconto', 'Fixo',     FALSE, FALSE, FALSE, TRUE, TRUE);
INSERT INTO rubrica VALUES (102,'Desconto por Falta',    'Desconto', 'Variável', FALSE, FALSE, FALSE, TRUE, TRUE);
INSERT INTO rubrica VALUES (103,'Desconto por Atraso',   'Desconto', 'Variável', FALSE, FALSE, FALSE, TRUE, TRUE);
INSERT INTO rubrica VALUES (104,'Desconto DSR',          'Desconto', 'Variável', FALSE, FALSE, FALSE, TRUE, TRUE);
INSERT INTO rubrica VALUES (105,'Desconto Atestado',     'Desconto', 'Variável', FALSE, FALSE, FALSE, TRUE, TRUE);
INSERT INTO rubrica VALUES (106,'Outros Descontos',      'Desconto', 'Variável', FALSE, FALSE, FALSE, TRUE, TRUE);

CREATE TABLE lancamento (
    id              INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_folha        INT NOT NULL,          -- QUANDO (Mês/Ano da folha corrente)
    cpf_funcionario VARCHAR(14) NOT NULL,   -- QUEM (O funcionário que sofreu o evento)
    codigo_rubrica  INT NOT NULL,          -- O QUE (O link para a sua tabela de rubricas)
    
    -- Dados específicos do momento do lançamento:
    quantidade      DOUBLE,                -- QUANTO (2.5 horas, 1 dia de falta, 40 minutos de atraso)
    data_clt        DATE,                  -- Dia exato em que aconteceu
    valor           DOUBLE,                -- Só usado se for um bônus com valor livre inserido na hora
    
    -- Campos exclusivos de atestado (requisito 3.1.7.4.4)
    data_inicio     DATE,
    data_fim        DATE,
    path_pdf        VARCHAR(255),

    -- AS TRÊS CHAVES ESTRANGEIRAS QUE SÃO O CORAÇÃO DA TABELA:
    FOREIGN KEY (codigo_rubrica) REFERENCES rubrica(codigo),
    FOREIGN KEY (id_folha) REFERENCES folha_mes(id),       -- Garanta que o nome da tabela/coluna é esse
    FOREIGN KEY (cpf_funcionario) REFERENCES funcionarios(cpf) -- Garanta que o nome da tabela/coluna é esse
);



**/