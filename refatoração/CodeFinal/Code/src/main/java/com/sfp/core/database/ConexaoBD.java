package com.sfp.core.database;

import java.sql.Connection;
import java.sql.SQLException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.File;

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
            : "jdbc:sqlite:database/sfp.db";

    private static HikariDataSource dataSource; // Pool de conexões.

    static {
        // Bloco de inicialização estática que configura o pool de conexões.
        new File("database").mkdirs();
        
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.sqlite.JDBC");
        config.setJdbcUrl(URL);
        config.setMaximumPoolSize(1);
        config.setMinimumIdle(1);
        config.setConnectionTimeout(10000);
        config.setConnectionInitSql("PRAGMA foreign_keys = ON;");
        
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