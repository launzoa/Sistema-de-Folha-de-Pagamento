/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sfp.auditoria.application;

import com.sfp.core.domain.RegistroAuditoria;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author manoe
 */
public class ControladorAuditoria {
    private CatalogoAuditoria catalogoAuditoria = new CatalogoAuditoria();    
    
    public void registrar(String usuario,String perfil, String acao, String entidade, String detalhes)
    {
        catalogoAuditoria.registrar(usuario, perfil, acao, entidade, detalhes);
    }
    
    public List<RegistroAuditoria> buscar(String usuario, LocalDate data) 
    {
        return catalogoAuditoria.buscar(usuario, data);
    }
}
