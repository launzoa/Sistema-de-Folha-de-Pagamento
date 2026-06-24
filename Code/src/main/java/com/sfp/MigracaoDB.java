package com.sfp;
import com.sfp.core.database.ConexaoBD;
import java.sql.Connection;
import java.sql.Statement;
public class MigracaoDB {
    public static void main(String[] args) {
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            java.util.logging.Logger.getGlobal().info("Iniciando migração de banco de dados...");
            try {
                stmt.execute("ALTER TABLE empresa ADD COLUMN dia_fechamento_ponto INT NOT NULL DEFAULT 30;");
                java.util.logging.Logger.getGlobal().info("Coluna dia_fechamento_ponto adicionada.");
            } catch (Exception e) { java.util.logging.Logger.getGlobal().info("Coluna dia_fechamento_ponto já existe."); }
            try {
                stmt.execute("ALTER TABLE empresa ADD COLUMN aliquota_fgts DECIMAL(5,2) NOT NULL DEFAULT 0.08;");
                java.util.logging.Logger.getGlobal().info("Coluna aliquota_fgts adicionada.");
            } catch (Exception e) { java.util.logging.Logger.getGlobal().info("Coluna aliquota_fgts já existe."); }
            try {
                stmt.execute("ALTER TABLE empresa ADD COLUMN teto_vr DECIMAL(10,2) NOT NULL DEFAULT 500.0;");
                java.util.logging.Logger.getGlobal().info("Coluna teto_vr adicionada.");
            } catch (Exception e) { java.util.logging.Logger.getGlobal().info("Coluna teto_vr já existe."); }
            try {
                stmt.execute("ALTER TABLE empresa ADD COLUMN dias_uteis INT NOT NULL DEFAULT 22;");
                java.util.logging.Logger.getGlobal().info("Coluna dias_uteis adicionada.");
            } catch (Exception e) { java.util.logging.Logger.getGlobal().info("Coluna dias_uteis já existe."); }
            try {
                stmt.execute("ALTER TABLE empresa ADD COLUMN horas_mes INT NOT NULL DEFAULT 220;");
                java.util.logging.Logger.getGlobal().info("Coluna horas_mes adicionada.");
            } catch (Exception e) { java.util.logging.Logger.getGlobal().info("Coluna horas_mes já existe."); }
            
            try {
                stmt.execute("ALTER TABLE folha_mes ADD COLUMN data_inicio DATE;");
                java.util.logging.Logger.getGlobal().info("Coluna data_inicio adicionada em folha_mes.");
            } catch (Exception e) { java.util.logging.Logger.getGlobal().info("Coluna data_inicio já existe em folha_mes."); }
            
            try {
                stmt.execute("ALTER TABLE folha_mes ADD COLUMN data_fim DATE;");
                java.util.logging.Logger.getGlobal().info("Coluna data_fim adicionada em folha_mes.");
            } catch (Exception e) { java.util.logging.Logger.getGlobal().info("Coluna data_fim já existe em folha_mes."); }
            java.util.logging.Logger.getGlobal().info("Migração concluída com sucesso!");
        } catch(Exception e) {
            java.util.logging.Logger.getGlobal().severe(e.getMessage());
        }
    }
}
