package com.sfp;
import java.sql.*;
public class TestSchema {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/SFP";
        try (Connection conn = DriverManager.getConnection(url, "root", "admin")) {
            DatabaseMetaData meta = conn.getMetaData();
            
            String[] tables = {"empresa", "endereco_empresa"};
            for (String table : tables) {
                System.out.println("Columns in " + table + ":");
                ResultSet rs = meta.getColumns(null, null, table, null);
                while (rs.next()) {
                    System.out.println("  " + rs.getString("COLUMN_NAME") + " - " + rs.getString("TYPE_NAME"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
