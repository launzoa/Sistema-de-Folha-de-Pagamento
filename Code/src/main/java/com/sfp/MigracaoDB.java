package com.sfp;
import com.sfp.core.database.ConexaoBD;
import java.sql.Connection;
import java.sql.Statement;
public class MigracaoDB {
    public static void main(String[] args) {
        try (Connection conn = ConexaoBD.getConnection();
             Statement stmt = conn.createStatement()) {
            System.out.println("Iniciando migração de banco de dados...");
            try {
                stmt.execute("ALTER TABLE empresa ADD COLUMN dia_fechamento_ponto INT NOT NULL DEFAULT 30;");
                System.out.println("Coluna dia_fechamento_ponto adicionada.");
            } catch (Exception e) { System.out.println("Coluna dia_fechamento_ponto já existe."); }
            try {
                stmt.execute("ALTER TABLE empresa ADD COLUMN aliquota_fgts DECIMAL(5,2) NOT NULL DEFAULT 0.08;");
                System.out.println("Coluna aliquota_fgts adicionada.");
            } catch (Exception e) { System.out.println("Coluna aliquota_fgts já existe."); }
            try {
                stmt.execute("ALTER TABLE empresa ADD COLUMN teto_vr DECIMAL(10,2) NOT NULL DEFAULT 500.0;");
                System.out.println("Coluna teto_vr adicionada.");
            } catch (Exception e) { System.out.println("Coluna teto_vr já existe."); }
            try {
                stmt.execute("ALTER TABLE empresa ADD COLUMN dias_uteis INT NOT NULL DEFAULT 22;");
                System.out.println("Coluna dias_uteis adicionada.");
            } catch (Exception e) { System.out.println("Coluna dias_uteis já existe."); }
            try {
                stmt.execute("ALTER TABLE empresa ADD COLUMN horas_mes INT NOT NULL DEFAULT 220;");
                System.out.println("Coluna horas_mes adicionada.");
            } catch (Exception e) { System.out.println("Coluna horas_mes já existe."); }
            System.out.println("Migração concluída com sucesso!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
