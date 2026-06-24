package com.sfp.core.domain;

import java.math.BigDecimal;

/**
 * @brief Classe que representa as faixas de IRRF
 */
public class FaixaIRRF {
    private BigDecimal piso;
    private BigDecimal teto;
    private BigDecimal aliquota;
    private BigDecimal parcelaADeduzir;

    /**
     * @brief Construtor da classe FaixaIRRF
     * @param piso            valor mínimo da faixa
     * @param teto            valor máximo da faixa
     * @param aliquota        alíquota da faixa
     * @param parcelaADeduzir parcela a deduzir da faixa
     */
    public FaixaIRRF(BigDecimal piso, BigDecimal teto, BigDecimal aliquota, BigDecimal parcelaADeduzir) {
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
     * @param baseCalculo base de cálculo do imposto
     * @return boolean: true se o salário está na faixa, false caso contrário
     */
    public boolean isSalarioNaFaixa(BigDecimal baseCalculo) {
        return baseCalculo.compareTo(teto) <= 0;
    }

    /**
     * @brief Calcula o desconto do imposto
     * @param baseCalculo base de cálculo do imposto
     * @return BigDecimal: desconto do imposto
     */
    public BigDecimal calcularDesconto(BigDecimal baseCalculo) {
        BigDecimal aliquotaDecimal = aliquota.divide(new BigDecimal("100"), 4, java.math.RoundingMode.HALF_UP);
        return baseCalculo.multiply(aliquotaDecimal).subtract(parcelaADeduzir);
    }

    /**
     * @brief Altera o piso
     * @param piso: novo valor do piso
     */
    public void setPiso(BigDecimal piso) {
        this.piso = piso;
    }

    /**
     * @brief Altera o teto
     * @param teto: novo valor do teto
     */
    public void setTeto(BigDecimal teto) {
        this.teto = teto;
    }

    /**
     * @brief Altera a alíquota
     * @param aliquota: nova alíquota
     */
    public void setAliquota(BigDecimal aliquota) {
        this.aliquota = aliquota;
    }

    /**
     * @brief Altera a parcela a deduzir
     * @param parcelaADeduzir: nova parcela a deduzir
     */
    public void setParcelaADeduzir(BigDecimal parcelaADeduzir) {
        this.parcelaADeduzir = parcelaADeduzir;
    }
}
