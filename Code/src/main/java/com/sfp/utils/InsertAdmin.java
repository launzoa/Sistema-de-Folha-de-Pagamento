/**
 * @brief Classe responsável por inserir um usuário administrador no sistema.
 */

package com.sfp.utils;

import java.sql.Connection;
import java.sql.ResultSet;

import com.sfp.core.database.ConexaoBD;

public class InsertAdmin {
    /**
     * @brief Método principal responsável por inserir um usuário administrador no
     *        sistema.
     */
    public static void main(String[] args) {
        // Conecta ao BD
        try (Connection conn = ConexaoBD.getConnection()) {
            // Conta todos os usuários cadastrados
            ResultSet rs = conn.createStatement().executeQuery("SELECT count(*) FROM usuario");
            rs.next();
            // Verifica se existe algum admin cadastrado
            if (rs.getInt(1) == 0) { // Se não tiver, insere o admin
                conn.createStatement().executeUpdate(
                        "INSERT INTO usuario (nome, senha, perfil, status) VALUES ('admin', 'admin', true, true)");
                System.out.println("Usuário admin inserido.");
            } else { // Se já tiver, só avisa
                System.out.println("Usuário admin já existe.");
            }
        } catch (Exception e) { // Se der erro, mostra o erro
            e.printStackTrace();
        }
    }
}
