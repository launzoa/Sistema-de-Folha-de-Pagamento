/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sfp.auditoria.application;

import com.sfp.core.domain.Usuario;

/**
 *
 * @author manoe
 */
public class ServicoAuditoria {
    private static Usuario usuarioAtual = null;
    private static final ControladorAuditoria controlador = new ControladorAuditoria();

    public static void setUsuarioAtual(Usuario usuario) {
        usuarioAtual = usuario;
    }

    public static void registrar(String acao, String entidade, String detalhes) 
    {
        if (usuarioAtual == null) return;
            controlador.registrar(
                usuarioAtual.getNome(),
                getPerfil(),
                acao,
                entidade,
                detalhes
        );
    } 
    private static String getPerfil() {
        return usuarioAtual.isPerfil() ? "Administrador" : "Operador";
    }
}
