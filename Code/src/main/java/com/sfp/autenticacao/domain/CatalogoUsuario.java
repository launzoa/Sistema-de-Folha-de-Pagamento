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
import java.util.ArrayList;
import java.util.List;


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
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public void cadastrarUsuario(String nome, String senha, boolean perfil)
    {
        String sql = "INSERT INTO usuario (nome, senha, perfil, status) VALUES (?, ?, ?, ?)";    
        try (Connection conexao = ConexaoBD.getConnection(); PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setString(2, senha);
            ps.setBoolean(3, perfil);
            ps.setBoolean(4, true);

            ps.executeUpdate();

        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    public void atualizarUsuario(int id, String nome, String senha, boolean status) {
        String sql = "UPDATE usuario SET nome = ?, senha = ?, status = ? WHERE id = ?";

        try (Connection conexao = ConexaoBD.getConnection(); PreparedStatement ps = conexao.prepareStatement(sql))
        {

            ps.setString(1, nome);
            ps.setString(2, senha);
            ps.setBoolean(3, status);
            ps.setInt(4, id);

            ps.executeUpdate();

        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }  
    public void desativarUsuario(int id)
    {
        String sql = "UPDATE usuario SET status = false WHERE id = ?";

        try (Connection conexao = ConexaoBD.getConnection(); PreparedStatement ps = conexao.prepareStatement(sql))
        {

            ps.setInt(1, id);
            ps.executeUpdate();

        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public List<Usuario> listarUsuarios()
    {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id, nome, senha, perfil, status FROM usuario";

        try (Connection conexao = ConexaoBD.getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery())
        {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("senha"),
                        rs.getBoolean("perfil"),
                        rs.getBoolean("status")
                );

                usuarios.add(usuario);
            }

        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return usuarios;
    }
}
