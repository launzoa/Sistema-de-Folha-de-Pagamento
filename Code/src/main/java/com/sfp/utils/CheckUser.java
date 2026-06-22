/**
 * @brief Classe responsável por verificar os usuários cadastrados no sistema.
 */

package com.sfp.utils;

import java.sql.Connection;
import java.sql.ResultSet;

import com.sfp.core.database.ConexaoBD;

public class CheckUser {
    /**
     * @brief Método principal da classe.
     */
    public static void main(String[] args) {
        // Conecta ao BD
        try (Connection conn = ConexaoBD.getConnection()) {
            // Seleciona todos os usuarios cadastrados
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM usuario");
            // Imprime o nome e a senha do usuario
            while (rs.next()) {
                System.out.println("User: " + rs.getString("nome") + " Pass: " + rs.getString("senha"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
