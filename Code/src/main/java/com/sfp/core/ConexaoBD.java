package com.sfp.core;

/**
 *
 * @author igor.nogueira_unesp
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBD {
    private static final String URL = "jdbc:mysql://localhost:3306/SFP"; // NAO ESQUEÇA DE ALTERAR O NOME DA INSTANCIA
    private static final String USER = "root";
    private static final String PASS = "admin"; // NAO ESQUEÇA DE ALTERAR SUA SENHA

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
/**
 * COLOQUE AQUI OS SQL USADOS
 * USE sfp;
 * 
 * CREATE TABLE IF NOT EXISTS empresa (
 * cnpj VARCHAR(18) NOT NULL PRIMARY KEY,
 * razao_social VARCHAR(120) NOT NULL,
 * email VARCHAR(120) NOT NULL,
 * resp_legal VARCHAR(120) NOT NULL,
 * aliquota_fgts DOUBLE DEFAULT 8.00,
 * horas_mensais INT DEFAULT 220,
 * val_cesta_basic DOUBLE DEFAULT 50.00,
 * perc_hora_extra50 DOUBLE DEFAULT 50.00,
 * perc_hora_extra100 DOUBLE DEFAULT 100.00
 * );
 * 
 * CREATE TABLE IF NOT EXISTS endereco_empresa (
 * id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 * cnpj_empresa VARCHAR(18) NOT NULL,
 * cep VARCHAR(10) NOT NULL,
 * logradouro VARCHAR(120) NOT NULL,
 * bairro VARCHAR(80) NOT NULL,
 * complemento VARCHAR(120),
 * FOREIGN KEY (cnpj_empresa) REFERENCES empresa(cnpj)
 * ON DELETE CASCADE
 * ON UPDATE CASCADE
 * );
 * 
 * CREATE TABLE usuario (
 * id INT auto_increment primary key,
 * nome varchar(100),
 * senha varchar(100),
 * perfil boolean,
 * status boolean
 * );
 * 
 * CREATE TABLE IF NOT EXISTS funcionarios (
 * id INT AUTO_INCREMENT PRIMARY KEY,
 * nome VARCHAR(100) NOT NULL,
 * cpf VARCHAR(14) UNIQUE NOT NULL,
 * cargo VARCHAR(100) NOT NULL,
 * data_admissao DATE NOT NULL,
 * salario_bruto DECIMAL NOT NULL,
 * status BOOLEAN DEFAULT TRUE
 * );
 * 
 **/