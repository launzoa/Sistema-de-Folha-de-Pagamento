/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sfp.core.domain;

/**
 *
 * @author igor.nogueira_unesp
 */
public class Usuario {
    private int id;
    private String nome;
    private String senha;
    private boolean perfil; //Tenho em mente de fazer o perfil == True como adm e perfil == False como o operador
    private boolean status;

    public Usuario() {}

    public Usuario(int id, String nome, String senha, boolean perfil, boolean status) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.perfil = perfil;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isPerfil() {
        return perfil;
    }

    public void setPerfil(boolean perfil) {
        this.perfil = perfil;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    
    
}
