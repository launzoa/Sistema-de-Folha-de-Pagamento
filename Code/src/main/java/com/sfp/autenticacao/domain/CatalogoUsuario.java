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

    public java.util.List<Usuario> buscarTodos() {
        java.util.List<Usuario> lista = new java.util.ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try(Connection con = ConexaoBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setSenha(rs.getString("senha"));
                user.setPerfil(rs.getBoolean("perfil"));
                user.setStatus(rs.getBoolean("status"));
                lista.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public void salvar(Usuario user) {
        String sql = "INSERT INTO usuario (nome, senha, perfil, status) VALUES (?, ?, ?, ?)";
        try(Connection con = ConexaoBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getNome());
            ps.setString(2, user.getSenha());
            ps.setBoolean(3, user.isPerfil());
            ps.setBoolean(4, user.isStatus());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void atualizar(Usuario user) {
        String sql = "UPDATE usuario SET nome = ?, senha = ?, perfil = ?, status = ? WHERE id = ?";
        try(Connection con = ConexaoBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getNome());
            ps.setString(2, user.getSenha());
            ps.setBoolean(3, user.isPerfil());
            ps.setBoolean(4, user.isStatus());
            ps.setInt(5, user.getId());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try(Connection con = ConexaoBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
