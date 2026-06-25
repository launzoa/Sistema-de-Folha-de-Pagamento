package com.sfp.autenticacao.application;

import com.sfp.usuario.domain.Usuario;
import com.sfp.usuario.domain.UsuarioRepository;
import com.sfp.usuario.infrastructure.persistence.MySQLUsuarioRepository;
import com.sfp.auditoria.application.ServicoAuditoria;

/**
 * @brief Controller de autenticação.
 */
public class ControladorAutenticacao {
    /**
     * @brief Repositório de usuários.
     */
    private UsuarioRepository usuarioRepository = new MySQLUsuarioRepository();

    /**
     * @brief Autentica um usuário.
     * @param nome  Nome do usuário.
     * @param senha Senha do usuário.
     * @return Usuário autenticado.
     */
    public Usuario autenticar(String nome, String senha) {
        Usuario user = usuarioRepository.buscarUsuarioSenha(nome, senha);
        if (user != null) {
            ServicoAuditoria.setUsuarioAtual(user);
            ServicoAuditoria.registrar("Login", "Sistema", null);
        }
        return user;
    }
}
