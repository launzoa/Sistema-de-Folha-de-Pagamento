package com.sfp.folha.domain;

import java.math.BigDecimal;

public class FaixaINSS {
    private BigDecimal valorMinimo;
    private BigDecimal valorMaximo;
    private BigDecimal aliquota;
    private BigDecimal parcelaADeduzir;

    public FaixaINSS(BigDecimal valorMinimo, BigDecimal valorMaximo, BigDecimal aliquota, BigDecimal parcelaADeduzir) {
        this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
        this.aliquota = aliquota;
        this.parcelaADeduzir = parcelaADeduzir;
    }

    public BigDecimal getValorMinimo() {
        return valorMinimo;
    }

    public BigDecimal getValorMaximo() {
        return valorMaximo;
    }

    public BigDecimal getAliquota() {
        return aliquota;
    }

    public BigDecimal getParcelaADeduzir() {
        return parcelaADeduzir;
    }
}
