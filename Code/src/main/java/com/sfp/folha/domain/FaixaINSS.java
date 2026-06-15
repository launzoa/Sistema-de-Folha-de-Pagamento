package com.sfp.folha.domain;

import java.math.BigDecimal;

public class FaixaINSS {
    private BigDecimal piso;
    private BigDecimal teto;
    private BigDecimal aliquota;
    private BigDecimal parcelaADeduzir;

    public FaixaINSS(BigDecimal piso, BigDecimal teto, BigDecimal aliquota, BigDecimal parcelaADeduzir) {
        this.piso = piso;
        this.teto = teto;
        this.aliquota = aliquota;
        this.parcelaADeduzir = parcelaADeduzir;
    }

    // brief: retorna o piso
    // @return BigDecimal: piso
    public BigDecimal getPiso() {
        return piso;
    }

    // brief: retorna o teto
    // @return BigDecimal: teto
    public BigDecimal getTeto() {
        return teto;
    }

    // brief: retorna a aliquota
    // @return BigDecimal: aliquota
    public BigDecimal getAliquota() {
        return aliquota;
    }

    // brief: retorna a parcela a deduzir
    // @return BigDecimal: parcela a deduzir
    public BigDecimal getParcelaDeduzir() {
        return parcelaADeduzir;
    }
}
