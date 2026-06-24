package com.sfp.core.database;

import java.sql.Connection;
import java.sql.Statement;

/**
 * @brief Classe responsável por gerenciar teste para o sistema conectado com o
 *        banco de dados.
 */

public class ServicoDatabase {

        /**
         * @brief Método responsável por zerar o banco de dados. Usado para fins de
         *        teste.
         */
        public void zerarBancoDeDados() throws Exception {

                // Conecta com o BD
                try (Connection conn = ConexaoBD.getConnection();
                                Statement stmt = conn.createStatement()) {

                        // Desativa checagem de chaves estrangeiras
                        stmt.execute("SET FOREIGN_KEY_CHECKS = 0;");

                        // Zera todas as tabelas de forma estática (evitando injeção de SQL via
                        // concatenação)
                        stmt.execute("TRUNCATE TABLE lancamento;");
                        stmt.execute("TRUNCATE TABLE folha_mes;");
                        stmt.execute("TRUNCATE TABLE auditoria;");
                        stmt.execute("TRUNCATE TABLE funcionarios;");
                        stmt.execute("TRUNCATE TABLE endereco_empresa;");
                        stmt.execute("TRUNCATE TABLE empresa;");
                        stmt.execute("TRUNCATE TABLE usuario;");
                        // Limpa qualquer rubrica customizada que tenha sido criada (mantém apenas as
                        // blindadas 1 a 5, 100 a 103, e os Vales 901/902)
                        stmt.execute("DELETE FROM rubrica WHERE codigo NOT IN (1, 2, 3, 4, 5, 100, 101, 102, 103, 901, 902);");

                        // Reconstrói as rubricas blindadas caso o banco tenha sido corrompido antes da
                        // blindagem
                        stmt.execute("INSERT IGNORE INTO rubrica VALUES (1, 'Salário Padrão', 'Provento', 'Fixo', TRUE, TRUE, TRUE, TRUE, TRUE);");
                        stmt.execute("INSERT IGNORE INTO rubrica VALUES (2, 'Horas Extras 50%', 'Provento', 'Variável', TRUE, TRUE, TRUE, TRUE, TRUE);");
                        stmt.execute("INSERT IGNORE INTO rubrica VALUES (3, 'Horas Extras 100%', 'Provento', 'Variável', TRUE, TRUE, TRUE, TRUE, TRUE);");
                        stmt.execute("INSERT IGNORE INTO rubrica VALUES (4, 'Atraso por hora', 'Desconto', 'Variável', FALSE, FALSE, FALSE, TRUE, TRUE);");
                        stmt.execute("INSERT IGNORE INTO rubrica VALUES (5, 'Falta por dia', 'Desconto', 'Variável', FALSE, FALSE, FALSE, TRUE, TRUE);");
                        stmt.execute("INSERT IGNORE INTO rubrica VALUES (100, 'Desconto INSS', 'Desconto', 'Fixo', FALSE, FALSE, FALSE, TRUE, TRUE);");
                        stmt.execute("INSERT IGNORE INTO rubrica VALUES (101, 'Desconto IRRF', 'Desconto', 'Fixo', FALSE, FALSE, FALSE, TRUE, TRUE);");
                        stmt.execute("INSERT IGNORE INTO rubrica VALUES (102, 'Recolhimento FGTS', 'Desconto', 'Fixo', FALSE, FALSE, FALSE, TRUE, TRUE);");
                        stmt.execute("INSERT IGNORE INTO rubrica VALUES (103, 'Desconto DSR', 'Desconto', 'Variável', FALSE, FALSE, FALSE, TRUE, TRUE);");
                        stmt.execute("INSERT IGNORE INTO rubrica VALUES (901, 'Vale Transporte (VT)', 'Desconto', 'Fixo', FALSE, FALSE, FALSE, TRUE, TRUE);");
                        stmt.execute("INSERT IGNORE INTO rubrica VALUES (902, 'Vale Alimentação (PAT)', 'Desconto', 'Fixo', FALSE, FALSE, FALSE, TRUE, TRUE);");

                        // Recria o admin padrão com a senha 'admin' hasheada em SHA-256
                        stmt.execute(
                                        "INSERT IGNORE INTO usuario (nome, senha, perfil, status) VALUES ('admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', TRUE, TRUE);");

                        // Reativa chaves estrangeiras
                        stmt.execute("SET FOREIGN_KEY_CHECKS = 1;");
                }
        }

}
