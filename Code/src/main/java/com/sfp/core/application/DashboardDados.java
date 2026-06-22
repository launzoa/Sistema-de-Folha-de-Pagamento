/**
 * @brief Classe que armazena os dados do dashboard.
 */
package com.sfp.core.application;

import java.math.BigDecimal;

import com.sfp.folha.domain.FolhaMes;

public class DashboardDados {
    private int totalFuncionarios;
    private FolhaMes folhaAtual;
    private BigDecimal totalLiquido = BigDecimal.ZERO;
    private BigDecimal totalImpostos = BigDecimal.ZERO;
    private BigDecimal totalDescontosDiversos = BigDecimal.ZERO;
    private BigDecimal custoEmpresa = BigDecimal.ZERO;

    /**
     * @brief Método responsável por retornar o total de funcionários.
     */
    public int getTotalFuncionarios() {
        return totalFuncionarios;
    }

    /**
     * @brief Método responsável por definir o total de funcionários.
     */
    public void setTotalFuncionarios(int totalFuncionarios) {
        this.totalFuncionarios = totalFuncionarios;
    }

    /**
     * @brief Método responsável por retornar a folha atual.
     */
    public FolhaMes getFolhaAtual() {
        return folhaAtual;
    }

    /**
     * @brief Método responsável por definir a folha atual.
     */
    public void setFolhaAtual(FolhaMes folhaAtual) {
        this.folhaAtual = folhaAtual;
    }

    /**
     * @brief Método responsável por retornar o total líquido.
     */
    public BigDecimal getTotalLiquido() {
        return totalLiquido;
    }

    /**
     * @brief Método responsável por definir o total líquido.
     * @param totalLiquido - Total líquido a ser definido.
     */
    public void setTotalLiquido(BigDecimal totalLiquido) {
        this.totalLiquido = totalLiquido;
    }

    /**
     * @brief Método responsável por retornar o total de impostos.
     */
    public BigDecimal getTotalImpostos() {
        return totalImpostos;
    }

    /**
     * @brief Método responsável por definir o total de impostos.
     * @param totalImpostos - Total de impostos a ser definido.
     */
    public void setTotalImpostos(BigDecimal totalImpostos) {
        this.totalImpostos = totalImpostos;
    }

    /**
     * @brief Método responsável por retornar o total de descontos diversos.
     */
    public BigDecimal getTotalDescontosDiversos() {
        return totalDescontosDiversos;
    }

    /**
     * @brief Método responsável por definir o total de descontos diversos.
     * @param totalDescontosDiversos - Total de descontos diversos a ser definido.
     */
    public void setTotalDescontosDiversos(BigDecimal totalDescontosDiversos) {
        this.totalDescontosDiversos = totalDescontosDiversos;
    }

    /**
     * @brief Método responsável por retornar o custo da empresa.
     */
    public BigDecimal getCustoEmpresa() {
        return custoEmpresa;
    }

    /**
     * @brief Método responsável por definir o custo da empresa.
     * @param custoEmpresa - Custo da empresa a ser definido.
     */
    public void setCustoEmpresa(BigDecimal custoEmpresa) {
        this.custoEmpresa = custoEmpresa;
    }
}
