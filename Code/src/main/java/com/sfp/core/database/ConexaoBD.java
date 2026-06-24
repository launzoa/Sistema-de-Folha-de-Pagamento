package com.sfp.core.database;

import java.sql.Connection;
import java.sql.SQLException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @brief Classe responsável pela conexão com o banco de dados.
 *        Ele funciona sobre a biblioteca nativa do java, o JDBC, utilizando o
 *        HikariCP.
 *        O método getConnection() é responsável por obter a conexão com o BD a
 *        partir do Pool.
 */
public class ConexaoBD {
    private ConexaoBD() {
    }

    // Variáveis obtidas por ambiente (Environment Variables) ou fallback para
    // padrões
    private static final String URL = System.getenv("DB_URL") != null ? System.getenv("DB_URL")
            : "jdbc:mysql://br7ddsafkvibhcqlom19-mysql.services.clever-cloud.com:3306/br7ddsafkvibhcqlom19";
    private static final String USER = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "ufuvceluhfgvc5y5";
    private static final String PASS = System.getenv("DB_PASS") != null ? System.getenv("DB_PASS") : "0MjbF32gELzIZ4wwqEjm";

    private static HikariDataSource dataSource; // Pool de conexões.

    static {
        // Bloco de inicialização estática que configura o pool de conexões.
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASS);

        // Configurações recomendadas do HikariCP
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setIdleTimeout(30000);
        config.setConnectionTimeout(10000);

        dataSource = new HikariDataSource(config);
    }

    /**
     * @brief Método responsável por obter a conexão com o banco de dados do pool.
     * @return Connection - objeto de conexão com o banco de dados.
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}