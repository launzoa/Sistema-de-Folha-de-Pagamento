package com.sfp.core.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @brief Classe que representa as faixas de INSS
 */
public class FaixaINSS {
    private BigDecimal piso;
    private BigDecimal teto;
    private BigDecimal aliquota;
    private BigDecimal parcelaADeduzir;

    /**
     * @brief Construtor da classe FaixaINSS
     * @param piso:            valor mínimo da faixa
     * @param teto:            valor máximo da faixa
     * @param aliquota:        alíquota da faixa
     * @param parcelaADeduzir: parcela a deduzir da faixa
     */
    public FaixaINSS(BigDecimal piso, BigDecimal teto, BigDecimal aliquota, BigDecimal parcelaADeduzir) {
        this.piso = piso;
        this.teto = teto;
        this.aliquota = aliquota;
        this.parcelaADeduzir = parcelaADeduzir;
    }

    /**
     * @brief Retorna o piso
     * @return BigDecimal: piso
     */
    public BigDecimal getPiso() {
        return piso;
    }

    /**
     * @brief Retorna o teto
     * @return BigDecimal: teto
     */
    public BigDecimal getTeto() {
        return teto;
    }

    /**
     * @brief Retorna a alíquota
     * @return BigDecimal: alíquota
     */
    public BigDecimal getAliquota() {
        return aliquota;
    }

    /**
     * @brief Retorna a parcela a deduzir
     * @return BigDecimal: parcela a deduzir
     */
    public BigDecimal getParcelaADeduzir() {
        return parcelaADeduzir;
    }

    /**
     * @brief Verifica se o salário está na faixa
     * @param salario: salário
     * @return boolean: true se o salário estiver na faixa, false caso contrário
     */
    public boolean isSalarioNaFaixa(BigDecimal salario) {
        return salario.compareTo(piso) >= 0 && salario.compareTo(teto) <= 0;
    }

    /**
     * @brief Calcula o desconto
     * @param salario: salário
     * @return BigDecimal: desconto
     */
    public BigDecimal calcularDesconto(BigDecimal salario) {
        BigDecimal aliquotaDecimal = aliquota.divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP);
        return salario.multiply(aliquotaDecimal).subtract(parcelaADeduzir);
    }

    /**
     * @brief Altera o piso
     * @param piso: piso
     */
    public void setPiso(BigDecimal piso) {
        this.piso = piso;
    }

    /**
     * @brief Altera o teto
     * @param teto: teto
     */
    public void setTeto(BigDecimal teto) {
        this.teto = teto;
    }

    /**
     * @brief Altera a alíquota
     * @param aliquota: alíquota
     */
    public void setAliquota(BigDecimal aliquota) {
        this.aliquota = aliquota;
    }

    /**
     * @brief Altera a parcela a deduzir
     * @param parcelaADeduzir: parcela a deduzir
     */
    public void setParcelaADeduzir(BigDecimal parcelaADeduzir) {
        this.parcelaADeduzir = parcelaADeduzir;
    }
}
