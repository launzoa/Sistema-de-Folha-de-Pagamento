/**
 * @brief classe que representa um endereço de uma empresa
 */
package com.sfp.empresa.domain;

public class EnderecoEmpresa {
    private int id;
    private String cnpjEmpresa;
    private String cep;
    private String logradouro;
    private String bairro;
    private String complemento;

    /**
     * @brief construtor com parâmetros
     * @param id          id do endereço
     * @param cnpjEmpresa CNPJ da empresa
     * @param cep         CEP do endereço
     * @param logradouro  Logradouro do endereço
     * @param bairro      Bairro do endereço
     * @param complemento Complemento do endereço
     */
    public EnderecoEmpresa(int id, String cnpjEmpresa, String cep, String logradouro, String bairro,
            String complemento) {
        this.id = id;
        this.cnpjEmpresa = cnpjEmpresa;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.complemento = complemento;
    }

    /**
     * @brief método que retorna o id do endereço
     * @return id do endereço
     */
    public int getId() {
        return id;
    }

    /**
     * @brief método que define o id do endereço
     * @param id id do endereço
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @brief método que retorna o CNPJ da empresa
     * @return CNPJ da empresa
     */
    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    /**
     * @brief método que define o CNPJ da empresa
     * @param cnpjEmpresa CNPJ da empresa
     */
    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    /**
     * @brief método que retorna o CEP do endereço
     * @return CEP do endereço
     */
    public String getCep() {
        return cep;
    }

    /**
     * @brief método que define o CEP do endereço
     * @param cep CEP do endereço
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @brief método que retorna o logradouro do endereço
     * @return Logradouro do endereço
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * @brief método que define o logradouro do endereço
     * @param logradouro Logradouro do endereço
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * @brief método que retorna o bairro do endereço
     * @return Bairro do endereço
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @brief método que define o bairro do endereço
     * @param bairro Bairro do endereço
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @brief método que retorna o complemento do endereço
     * @return Complemento do endereço
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @brief método que define o complemento do endereço
     * @param complemento Complemento do endereço
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

}
