package com.sfp;
import java.sql.*;
public class CheckUser {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/SFP";
        try (Connection conn = DriverManager.getConnection(url, "root", "admin")) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM usuario");
            while (rs.next()) {
                System.out.println("User: " + rs.getString("nome") + " Pass: " + rs.getString("senha"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
