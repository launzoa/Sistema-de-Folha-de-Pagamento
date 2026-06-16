/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sfp.core.domain;

import java.time.LocalDateTime;

/**
 *
 * @author manoe
 */
public class RegistroAuditoria {
    private int id;
    private LocalDateTime data_hora;
    private String usuario;
    private String perfil;
    private String acao;
    private String entidade;
    private String detalhes; 

    public RegistroAuditoria() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }
    
    public String getDataHoraFormatada() 
    {
        if (data_hora == null) return "";
        return data_hora.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getEntidade() {
        return entidade;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
    
    
}
