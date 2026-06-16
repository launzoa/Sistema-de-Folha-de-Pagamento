package com.sfp.folha.domain;

import java.math.BigDecimal;

public class FaixaIRRF {
    private BigDecimal piso;
    private BigDecimal teto;
    private BigDecimal aliquota;
    private BigDecimal parcelaADeduzir;

    public FaixaIRRF(BigDecimal piso, BigDecimal teto, BigDecimal aliquota, BigDecimal parcelaADeduzir) {
        this.piso = piso;
        this.teto = teto;
        this.aliquota = aliquota;
        this.parcelaADeduzir = parcelaADeduzir;
    }

    // @brief: Retorna o piso
    // @return BigDecimal: piso
    public BigDecimal getPiso() {
        return piso;
    }

    // @brief: Retorna o teto
    // @return BigDecimal: teto
    public BigDecimal getTeto() {
        return teto;
    }

    // @brief: Retorna a alíquota
    // @return BigDecimal: alíquota
    public BigDecimal getAliquota() {
        return aliquota;
    }

    // @brief: Retorna a parcela a deduzir
    // @return BigDecimal: parcela a deduzir
    public BigDecimal getParcelaADeduzir() {
        return parcelaADeduzir;
    }
}
