/**
 * @brief Interface que define o repositório de usuários.
 */

package com.sfp.usuario.domain;

import java.util.List;

public interface UsuarioRepository {
    /**
     * @brief Busca um usuário pelo nome e senha.
     * @param nome  Nome do usuário.
     * @param senha Senha do usuário.
     * @return Usuário encontrado.
     */
    Usuario buscarUsuarioSenha(String nome, String senha);

    /**
     * @brief Busca um usuário pelo nome.
     * @param nome Nome do usuário.
     * @return Usuário encontrado.
     */
    List<Usuario> buscarUsuario(String nome);

    /**
     * @brief Cadastra um novo usuário.
     * @param nome   Nome do usuário.
     * @param senha  Senha do usuário.
     * @param perfil Perfil do usuário.
     */
    void cadastrarUsuario(String nome, String senha, boolean perfil);

    /**
     * @brief Atualiza um usuário.
     * @param id     ID do usuário.
     * @param nome   Nome do usuário.
     * @param senha  Senha do usuário.
     * @param status Status do usuário.
     */
    void atualizarUsuario(int id, String nome, String senha, boolean status);

    /**
     * @brief Desativa um usuário.
     * @param id ID do usuário.
     */
    void desativarUsuario(int id);

    /**
     * @brief Lista todos os usuários.
     * @return Lista de usuários.
     */
    List<Usuario> listarUsuarios();
}
