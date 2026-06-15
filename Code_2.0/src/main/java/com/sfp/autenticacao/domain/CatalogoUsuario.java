/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sfp.autenticacao.domain;

/**
 *
 * @author igor.nogueira_unesp
 */

import com.sfp.core.ConexaoBD;
import com.sfp.core.domain.Usuario;
import java.sql.*;


public class CatalogoUsuario {
    
    public Usuario buscarUsuario(String nome, String senha)
    {
        String sql = "SELECT * FROM usuario WHERE nome = ? AND senha = ? AND status = true";
        try(Connection con = ConexaoBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql))
        {
           ps.setString(1, nome);
           ps.setString(2, senha);
           ResultSet rs = ps.executeQuery();
           
           if (rs.next())
           {
               Usuario user = new Usuario();
               user.setId(rs.getInt("id"));
               user.setNome(rs.getString("nome"));
               user.setSenha(rs.getString("senha"));
               user.setPerfil(rs.getBoolean("perfil"));
               user.setStatus(rs.getBoolean("status"));
               return user;
               
           }
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
}
