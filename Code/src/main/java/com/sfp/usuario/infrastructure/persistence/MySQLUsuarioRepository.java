/**
 * @brief Classe que implementa o repositório de usuários no MySQL.
 */

package com.sfp.usuario.infrastructure.persistence;

import com.sfp.core.database.ConexaoBD;
import com.sfp.usuario.domain.Usuario;
import com.sfp.usuario.domain.UsuarioRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.security.MessageDigest;

public class MySQLUsuarioRepository implements UsuarioRepository {

    /**
     * @brief Busca um usuário pelo nome e senha.
     * @param nome  Nome do usuário.
     * @param senha Senha do usuário.
     * @return Retorna o usuário encontrado ou null se não existir.
     */
    @Override
    public Usuario buscarUsuarioSenha(String nome, String senha) {
        // SQL para buscar usuário pelo nome e senha
        String sql = "SELECT * FROM usuario WHERE nome = ? AND senha = ? AND status = true";
        // Tenta obter a conexão e preparar a query
        try (Connection con = ConexaoBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            // Insere os parâmetros na query
            ps.setString(1, nome);
            ps.setString(2, criptografarSenha(senha));
            // Executa a query
            ResultSet rs = ps.executeQuery();
            // Se encontrar um usuário, cria o objeto Usuario
            if (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setSenha(rs.getString("senha"));
                user.setPerfil(rs.getBoolean("perfil"));
                user.setStatus(rs.getBoolean("status"));
                // Retorna o usuário encontrado
                return user;
            }
        } catch (Exception ex) {
            throw new IllegalStateException("Erro ao buscar usuário e senha no Banco de Dados", ex);
        }
        return null;
    }

    /**
     * @brief Busca usuários que contenham o nome informado.
     * @param nome Nome do usuário a ser buscado.
     * @return Retorna uma lista de usuários que contenham o nome informado.
     */
    @Override
    public List<Usuario> buscarUsuario(String nome) {
        List<Usuario> usuarios = new ArrayList<>();
        // SQL para buscar usuários pelo nome
        String sql = "SELECT * FROM usuario WHERE nome LIKE ? AND status = true";
        // Tenta obter a conexão e preparar a query
        try (Connection con = ConexaoBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            // Insere os parâmetros na query
            ps.setString(1, "%" + nome + "%");
            // Executa a query
            ResultSet rs = ps.executeQuery();
            // Se encontrar um usuário, cria o objeto Usuario
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setSenha(rs.getString("senha"));
                user.setPerfil(rs.getBoolean("perfil"));
                user.setStatus(rs.getBoolean("status"));
                // Adiciona o usuário na lista
                usuarios.add(user);
            }
        } catch (Exception ex) {
            throw new IllegalStateException("Erro ao buscar usuários pelo nome", ex);
        }
        return usuarios;
    }

    /**
     * @brief Cadastra um novo usuário.
     * @param nome   Nome do usuário.
     * @param senha  Senha do usuário.
     * @param perfil Perfil do usuário.
     */
    @Override
    public void cadastrarUsuario(String nome, String senha, boolean perfil) {
        // SQL para cadastrar usuário
        String sql = "INSERT INTO usuario (nome, senha, perfil, status) VALUES (?, ?, ?, ?)";
        // Tenta obter a conexão e preparar a query
        try (Connection conexao = ConexaoBD.getConnection();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            // Insere os parâmetros na query
            ps.setString(1, nome);
            ps.setString(2, criptografarSenha(senha));
            ps.setBoolean(3, perfil);
            ps.setBoolean(4, true);
            // Executa a query
            ps.executeUpdate();
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao cadastrar novo usuário", e);
        }
    }

    /**
     * @brief Atualiza um usuário.
     * @param id     ID do usuário.
     * @param nome   Nome do usuário.
     * @param senha  Senha do usuário.
     * @param status Status do usuário.
     */
    @Override
    public void atualizarUsuario(int id, String nome, String senha, boolean status) {
        boolean atualizarSenha = (senha != null && !senha.trim().isEmpty());
        // SQL para atualizar usuário
        String sql = atualizarSenha 
            ? "UPDATE usuario SET nome = ?, senha = ?, status = ? WHERE id = ?"
            : "UPDATE usuario SET nome = ?, status = ? WHERE id = ?";
            
        // Tenta obter a conexão e preparar a query
        try (Connection conexao = ConexaoBD.getConnection();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            // Insere os parâmetros na query
            ps.setString(1, nome);
            if (atualizarSenha) {
                ps.setString(2, criptografarSenha(senha));
                ps.setBoolean(3, status);
                ps.setInt(4, id);
            } else {
                ps.setBoolean(2, status);
                ps.setInt(3, id);
            }
            // Executa a query
            ps.executeUpdate();
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao atualizar usuário", e);
        }
    }

    /**
     * @brief Desativa um usuário.
     * @param id ID do usuário.
     */
    @Override
    public void desativarUsuario(int id) {
        // SQL para desativar usuário
        String sql = "UPDATE usuario SET status = false WHERE id = ?";
        // Tenta obter a conexão e preparar a query
        try (Connection conexao = ConexaoBD.getConnection();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            // Insere os parâmetros na query
            ps.setInt(1, id);
            // Executa a query
            ps.executeUpdate();
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao desativar usuário", e);
        }
    }

    /**
     * @brief Lista todos os usuários.
     * @return Retorna uma lista de usuários.
     */
    @Override
    public List<Usuario> listarUsuarios() {
        // Lista de usuários
        List<Usuario> usuarios = new ArrayList<>();
        // SQL para listar usuários
        String sql = "SELECT id, nome, senha, perfil, status FROM usuario";
        // Tenta obter a conexão e preparar a query
        try (Connection conexao = ConexaoBD.getConnection();
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            // Enquanto houver um usuário, adiciona o objeto Usuario na lista
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("senha"),
                        rs.getBoolean("perfil"),
                        rs.getBoolean("status"));
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao listar todos os usuários", e);
        }
        return usuarios;
    }

    /**
     * @brief Criptografa uma string usando o algoritmo SHA-256.
     * @param texto String em texto claro.
     * @return String criptografada em hexadecimal.
     */
    private String criptografarSenha(String texto) {
        if (texto == null)
            return null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(texto.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao criptografar senha", e);
        }
    }
}
