/**
 * @brief Classe que representa um lançamento
 */
package com.sfp.folha.domain;

import java.math.BigDecimal;

import java.time.LocalDate;

public class Lancamento {
    private int id;
    private int idFolha;
    private String cpfFuncionario;
    private int codigoRubrica;
    private double quantidade;
    private LocalDate dataClt;
    private BigDecimal valor;
    private String modalidade;
    private String baseCalculo;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    /**
     * @brief Constructor da classe Lancamento
     * @param id             ID do lançamento
     * @param idFolha        ID da folha
     * @param cpfFuncionario CPF do funcionário
     * @param codigoRubrica  Código da rubrica
     * @param quantidade     Quantidade de horas da rubrica
     * @param dataClt        Data CLT
     * @param valor          Valor da rubrica
     * @param modalidade     Modalidade (Valor, Porcentagem, Quantidade)
     * @param baseCalculo    Base de Cálculo (Salário Bruto, Salário Líquido)
     * @param dataInicio     Data de início do lançamento
     * @param dataFim        Data de fim do lançamento
     */
    public Lancamento(int id, int idFolha, String cpfFuncionario, int codigoRubrica, double quantidade,
            LocalDate dataClt, BigDecimal valor, String modalidade, String baseCalculo, LocalDate dataInicio,
            LocalDate dataFim) {
        this.id = id;
        this.idFolha = idFolha;
        this.cpfFuncionario = cpfFuncionario;
        this.codigoRubrica = codigoRubrica;
        this.quantidade = quantidade;
        this.dataClt = dataClt;
        this.valor = valor;
        this.modalidade = modalidade;
        this.baseCalculo = baseCalculo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    /**
     * @brief Retorna o código da rubrica
     * @return int
     */
    public int getCodigoRubrica() {
        return this.codigoRubrica;
    }

    /**
     * @brief Retorna a quantidade de Horas da rubrica
     * @return double
     */
    public double getQuantidade() {
        return this.quantidade;
    }

    /**
     * @brief Retorna o cpf do funcionário
     * @return String
     */
    public String getCpfFuncionario() {
        return this.cpfFuncionario;
    }

    /**
     * @brief Retorna o id da folha
     * @return int
     */
    public int getIdFolha() {
        return this.idFolha;
    }

    /**
     * @brief Retorna a data clt
     * @return LocalDate
     */
    public LocalDate getDataClt() {
        return this.dataClt;
    }

    /**
     * @brief Retorna o valor da rubrica
     * @return BigDecimal
     */
    public BigDecimal getValor() {
        return this.valor;
    }

    /**
     * @brief Retorna a data de início do lançamento
     * @return LocalDate
     */
    public LocalDate getDataInicio() {
        return this.dataInicio;
    }

    /**
     * @brief Retorna a data de fim do lançamento
     * @return LocalDate
     */
    public LocalDate getDataFim() {
        return this.dataFim;
    }

    /**
     * @brief Retorna o id do lançamento
     * @return int
     */
    public int getId() {
        return this.id;
    }

    /**
     * @brief Retorna a modalidade do lançamento
     * @return String
     */
    public String getModalidade() {
        return this.modalidade;
    }

    /**
     * @brief Seta a modalidade do lançamento
     * @param modalidade Modalidade do lançamento
     */
    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    /**
     * @brief Retorna a base de cálculo do lançamento
     * @return String
     */
    public String getBaseCalculo() {
        return this.baseCalculo;
    }

    /**
     * @brief Seta a base de cálculo do lançamento
     * @param baseCalculo Base de cálculo do lançamento
     */
    public void setBaseCalculo(String baseCalculo) {
        this.baseCalculo = baseCalculo;
    }

    /**
     * @brief Seta o cpf do funcionário
     * @param cpfFuncionario CPF do funcionário
     */
    public void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    /**
     * @brief Seta o código da rubrica
     * @param codigoRubrica Código da rubrica
     */
    public void setCodigoRubrica(int codigoRubrica) {
        this.codigoRubrica = codigoRubrica;
    }

    /**
     * @brief Seta a quantidade de horas da rubrica
     * @param quantidade Quantidade de horas da rubrica
     */
    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @brief Seta a data CLT
     * @param dataClt Data CLT
     */
    public void setDataClt(LocalDate dataClt) {
        this.dataClt = dataClt;
    }

    /**
     * @brief Seta o valor da rubrica
     * @param valor Valor da rubrica
     */
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
