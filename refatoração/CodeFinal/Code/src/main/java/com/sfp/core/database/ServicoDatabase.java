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
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("PRAGMA foreign_keys = OFF;");

            stmt.execute("DELETE FROM lancamento;");
            stmt.execute("DELETE FROM folha_mes;");
            stmt.execute("DELETE FROM auditoria;");
            stmt.execute("DELETE FROM funcionarios;");
            stmt.execute("DELETE FROM endereco_empresa;");
            stmt.execute("DELETE FROM empresa;");
            stmt.execute("DELETE FROM usuario;");

            stmt.execute("DELETE FROM sqlite_sequence WHERE name IN ('lancamento', 'folha_mes', 'auditoria', 'funcionarios', 'endereco_empresa', 'empresa', 'usuario');");

            stmt.execute("DELETE FROM rubrica WHERE codigo NOT IN (1, 2, 3, 4, 5, 100, 101, 102, 103, 901, 902);");

            stmt.execute("INSERT OR IGNORE INTO rubrica VALUES (1, 'Salário Padrão', 'Provento', 'Fixo', 1, 1, 1, 1, 1);");
            stmt.execute("INSERT OR IGNORE INTO rubrica VALUES (2, 'Horas Extras 50%', 'Provento', 'Variável', 1, 1, 1, 1, 1);");
            stmt.execute("INSERT OR IGNORE INTO rubrica VALUES (3, 'Horas Extras 100%', 'Provento', 'Variável', 1, 1, 1, 1, 1);");
            stmt.execute("INSERT OR IGNORE INTO rubrica VALUES (4, 'Atraso por hora', 'Desconto', 'Variável', 0, 0, 0, 1, 1);");
            stmt.execute("INSERT OR IGNORE INTO rubrica VALUES (5, 'Falta por dia', 'Desconto', 'Variável', 0, 0, 0, 1, 1);");
            stmt.execute("INSERT OR IGNORE INTO rubrica VALUES (100, 'Desconto INSS', 'Desconto', 'Fixo', 0, 0, 0, 1, 1);");
            stmt.execute("INSERT OR IGNORE INTO rubrica VALUES (101, 'Desconto IRRF', 'Desconto', 'Fixo', 0, 0, 0, 1, 1);");
            stmt.execute("INSERT OR IGNORE INTO rubrica VALUES (102, 'Recolhimento FGTS', 'Desconto', 'Fixo', 0, 0, 0, 1, 1);");
            stmt.execute("INSERT OR IGNORE INTO rubrica VALUES (103, 'Desconto DSR', 'Desconto', 'Variável', 0, 0, 0, 1, 1);");
            stmt.execute("INSERT OR IGNORE INTO rubrica VALUES (901, 'Vale Transporte (VT)', 'Desconto', 'Fixo', 0, 0, 0, 1, 1);");
            stmt.execute("INSERT OR IGNORE INTO rubrica VALUES (902, 'Vale Alimentação (PAT)', 'Desconto', 'Fixo', 0, 0, 0, 1, 1);");

            stmt.execute("INSERT OR IGNORE INTO usuario (nome, senha, perfil, status) VALUES ('admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 1, 1);");

            stmt.execute("PRAGMA foreign_keys = ON;");
        }
    }

}
