/**
 * @brief Classe responsável por gerenciar as empresas
 */

package com.sfp.empresa.domain;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String cnpj;
    private String razaoSocial;
    private String email;
    private String respLegal;
    private int diaFechamentoPonto;

    // Lista de endereços
    private List<EnderecoEmpresa> enderecos = new ArrayList<>();

    /**
     * @brief construtor com parâmetros
     * @param cnpj               CNPJ da empresa
     * @param razaoSocial        Razão social da empresa
     * @param email              Email da empresa
     * @param respLegal          Responsável legal da empresa
     * @param diaFechamentoPonto Dia de fechamento do ponto
     */
    public Empresa(String cnpj, String razaoSocial, String email, String respLegal, int diaFechamentoPonto) {
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.email = email;
        this.respLegal = respLegal;
        this.diaFechamentoPonto = diaFechamentoPonto;
    }

    /**
     * @brief método que retorna o CNPJ da empresa
     * @return CNPJ da empresa
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @brief método que define o CNPJ da empresa
     * @param cnpj cnpj da empresa
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * @brief método que retorna a razão social da empresa
     * @return Razão social da empresa
     */
    public String getRazaoSocial() {
        return razaoSocial;
    }

    /**
     * @brief método que define a razão social da empresa
     * @param razaoSocial Razão social da empresa
     */
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    /**
     * @brief método que retorna o email da empresa
     * @return Email da empresa
     */
    public String getEmail() {
        return email;
    }

    /**
     * @brief método que define o email da empresa
     * @param email Email da empresa
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @brief método que retorna o responsável legal da empresa
     * @return Responsável legal da empresa
     */
    public String getRespLegal() {
        return respLegal;
    }

    /**
     * @brief método que define o responsável legal da empresa
     * @param respLegal Responsável legal da empresa
     */
    public void setRespLegal(String respLegal) {
        this.respLegal = respLegal;
    }

    /**
     * @brief método que retorna o dia de fechamento do ponto
     * @return Dia de fechamento do ponto
     */
    public int getDiaFechamentoPonto() {
        return diaFechamentoPonto;
    }

    /**
     * @brief método que define o dia de fechamento do ponto
     * @param diaFechamentoPonto Dia de fechamento do ponto
     */
    public void setDiaFechamentoPonto(int diaFechamentoPonto) {
        this.diaFechamentoPonto = diaFechamentoPonto;
    }

    /**
     * @brief método que retorna a lista de endereços
     * @return Lista de endereços
     */
    public List<EnderecoEmpresa> getEnderecos() {
        return enderecos;
    }

    /**
     * @brief método que define a lista de endereços
     * @param enderecos Lista de endereços
     */
    public void setEnderecos(List<EnderecoEmpresa> enderecos) {
        this.enderecos = enderecos;
    }

    /**
     * @brief método que retorna uma string com a razão social e o responsável legal
     *        da empresa
     * @return String com a razão social e o responsável legal da empresa
     */
    @Override
    public String toString() {
        return razaoSocial + this.respLegal;
    }
}
