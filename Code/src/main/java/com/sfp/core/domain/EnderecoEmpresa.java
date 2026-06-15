package com.sfp.core.domain;

public class EnderecoEmpresa {
    private int id;
    private String cnpjEmpresa;
    private String cep;
    private String logradouro;
    private String bairro;
    private String complemento;

    public EnderecoEmpresa() {}

    public EnderecoEmpresa(int id, String cnpjEmpresa, String cep, String logradouro, String bairro, String complemento) {
        this.id = id;
        this.cnpjEmpresa = cnpjEmpresa;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.complemento = complemento;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCnpjEmpresa() { return cnpjEmpresa; }
    public void setCnpjEmpresa(String cnpjEmpresa) { this.cnpjEmpresa = cnpjEmpresa; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }
}
