/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sfp.usuario.application;

import com.sfp.autenticacao.domain.CatalogoUsuario;
import com.sfp.core.domain.Usuario;
import java.util.List;

/**
 *
 * @author manoe
 */
public class ControladorUsuario {
    
    private CatalogoUsuario catalogoUsuario = new CatalogoUsuario();
    public void cadastrarUsuario(String nome, String senha, boolean perfil)
    {
        catalogoUsuario.cadastrarUsuario(nome, senha, perfil);
    }
    
    public void atualizarUsuario(int id, String nome, String senha, boolean status)
    {
        catalogoUsuario.atualizarUsuario(id, nome, senha, status);
    }
    
    public void desativarUsuario(int id) {
        catalogoUsuario.desativarUsuario(id);
    }

    public List<Usuario> listarUsuarios() {
        return catalogoUsuario.listarUsuarios();
    }
}
