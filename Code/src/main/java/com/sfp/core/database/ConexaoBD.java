package com.sfp.core.database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @brief Classe responsável pela conexão com o banco de dados.
 *        Ele funciona sobre a biblioteca nativa do java, o JDBC.
 *        O método getConnection() é responsável por obter a conexão com o BD.
 *        Sendo chamado apenas quando preciso fazer uma alteração ou consulta ao
 *        BD.
 */
public class ConexaoBD {
    // Variáveis obtidas por ambiente (Environment Variables) ou fallback para
    // padrões
    private static final String URL = System.getenv("DB_URL") != null ? System.getenv("DB_URL")
            : "jdbc:mysql://localhost:3306/SFP";
    private static final String USER = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "root";
    private static final String PASS = System.getenv("DB_PASS") != null ? System.getenv("DB_PASS") : "admin";

    /**
     * @brief Método responsável por obter a conexão com o banco de dados.
     * @return Connection - objeto de conexão com o banco de dados.
     */
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}