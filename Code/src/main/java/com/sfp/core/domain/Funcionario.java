package com.sfp.core.domain;

import java.math.BigDecimal;

public class Funcionario {
    private BigDecimal salarioBase;
    private FolhaDePonto ponto;

    public Funcionario(BigDecimal salarioBase) {
        this.salarioBase = salarioBase;
        this.ponto = new FolhaDePonto();
    }

    public BigDecimal getSalarioBase() {
        return salarioBase;
    }

    public FolhaDePonto getPonto() {
        return ponto;
    }
}
