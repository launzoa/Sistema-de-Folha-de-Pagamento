/**
 * @brief Controlador para operações com usuários.
 */
package com.sfp.usuario.application;

import java.util.List;

import com.sfp.usuario.domain.Usuario;
import com.sfp.usuario.domain.UsuarioRepository;
import com.sfp.usuario.infrastructure.persistence.MySQLUsuarioRepository;
import com.sfp.auditoria.application.ServicoAuditoria;

public class ControladorUsuario {
    private UsuarioRepository usuarioRepository = new MySQLUsuarioRepository();

    /**
     * @brief Cadastra um novo usuário.
     * @param nome   Nome do usuário.
     * @param senha  Senha do usuário.
     * @param perfil Perfil do usuário.
     */
    public void cadastrarUsuario(String nome, String senha, boolean perfil) {
        usuarioRepository.cadastrarUsuario(nome, senha, perfil);
        ServicoAuditoria.registrar("Cadastro", "Usuário", "Login: " + nome);
    }

    /**
     * @brief Atualiza um usuário.
     * @param id     ID do usuário.
     * @param nome   Nome do usuário.
     * @param senha  Senha do usuário.
     * @param status Status do usuário.
     */
    public void atualizarUsuario(int id, String nome, String senha, boolean status) {
        usuarioRepository.atualizarUsuario(id, nome, senha, status);
        ServicoAuditoria.registrar("Edição", "Usuário", "Login: " + nome);
    }

    /**
     * @brief Desativa um usuário.
     * @param id ID do usuário.
     */
    public void desativarUsuario(int id) {
        usuarioRepository.desativarUsuario(id);
        ServicoAuditoria.registrar("Desativação", "Usuário", "ID: " + id);
    }

    /**
     * @brief Lista todos os usuários.
     * @return Retorna uma lista de usuários.
     */
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listarUsuarios();
    }

    /**
     * @brief Busca um usuário por nome e senha.
     * @param nome  Nome do usuário.
     * @param senha Senha do usuário.
     * @return Retorna o usuário encontrado.
     */
    public Usuario buscarUsuarioSenha(String nome, String senha) {
        return usuarioRepository.buscarUsuarioSenha(nome, senha);
    }

    /**
     * @brief Busca um usuário por nome.
     * @param nome Nome do usuário.
     * @return Retorna uma lista de usuários encontrados.
     */
    public List<Usuario> buscarUsuario(String nome) {
        return usuarioRepository.buscarUsuario(nome);
    }
}
