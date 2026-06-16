/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sfp.autenticacao.application;

/**
 *
 * @author igor.nogueira_unesp
 */

import com.sfp.autenticacao.domain.CatalogoUsuario;
import com.sfp.core.domain.Usuario;

public class ControladorAutenticacao {
    private CatalogoUsuario catalogo = new CatalogoUsuario();

    public Usuario autenticar(String nome, String senha) {
        return catalogo.buscarUsuarioSenha(nome, senha);
    }
}
