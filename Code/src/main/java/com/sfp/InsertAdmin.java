package com.sfp;
import java.sql.*;
public class InsertAdmin {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/SFP";
        try (Connection conn = DriverManager.getConnection(url, "root", "admin")) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT count(*) FROM usuario");
            rs.next();
            if (rs.getInt(1) == 0) {
                conn.createStatement().executeUpdate("INSERT INTO usuario (nome, senha, perfil, status) VALUES ('admin', 'admin', true, true)");
                System.out.println("Admin inserted");
            } else {
                System.out.println("Users already exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
