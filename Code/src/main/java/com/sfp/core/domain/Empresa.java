package com.sfp.core.domain;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String cnpj;
    private String razaoSocial;
    private String email;
    private String respLegal;

    
    private List<EnderecoEmpresa> enderecos = new ArrayList<>();

    public Empresa() {}

    public Empresa(String cnpj, String razaoSocial, String email, String respLegal) {
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.email = email;
        this.respLegal = respLegal;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRespLegal() {
        return respLegal;
    }

    public void setRespLegal(String respLegal) {
        this.respLegal = respLegal;
    }

    public List<EnderecoEmpresa> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoEmpresa> enderecos) {
        this.enderecos = enderecos;
    }

    @Override
    public String toString() {
        return razaoSocial + this.respLegal;
    }
}
