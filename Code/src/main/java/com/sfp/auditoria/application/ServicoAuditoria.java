/**
 * @brief Classe que representa o serviço de auditoria.
 */
package com.sfp.auditoria.application;

import com.sfp.usuario.domain.Usuario;

public class ServicoAuditoria {
    private static Usuario usuarioAtual = null;
    private static final ControladorAuditoria controlador = new ControladorAuditoria();

    /**
     * @brief Define o usuário atual.
     * @param usuario O usuário atual.
     */
    public static void setUsuarioAtual(Usuario usuario) {
        usuarioAtual = usuario;
    }

    /**
     * @brief Registra uma ação de auditoria.
     * @param acao     A ação realizada.
     * @param entidade A entidade afetada.
     * @param detalhes Os detalhes da ação.
     */
    public static void registrar(String acao, String entidade, String detalhes) {
        if (usuarioAtual == null) // Se o usuário atual for nulo, não registra a ação.
            return;
        // Se houver usuário, chama o controlador para registrar a ação.
        controlador.registrar(
                usuarioAtual.getNome(),
                getPerfil(),
                acao,
                entidade,
                detalhes);
    }

    /**
     * @brief Obtém o perfil do usuário atual.
     * @return O perfil do usuário atual.
     */
    private static String getPerfil() {
        return usuarioAtual.isPerfil() ? "Administrador" : "Operador";
    }
}
