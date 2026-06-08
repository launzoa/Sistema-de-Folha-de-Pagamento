package com.sfp.core.domain;

import java.math.BigDecimal;

// @brief: Classe que representa um funcionário
public class Funcionario {
    private BigDecimal salarioBase;
    private FolhaDePonto ponto;

    // @brief: Construtor da classe
    // @param salarioBase: Salário base do funcionário
    public Funcionario(BigDecimal salarioBase) {
        this.salarioBase = salarioBase;
        this.ponto = new FolhaDePonto();
    }

    // @brief: Retorna o salário base do funcionário
    // @return BigDecimal: Salário base do funcionário
    public BigDecimal getSalarioBase() {
        return salarioBase;
    }

    // @brief: Retorna a folha de ponto do funcionário
    // @return FolhaDePonto: Folha de ponto do funcionário
    public FolhaDePonto getPonto() {
        return ponto;
    }
}
