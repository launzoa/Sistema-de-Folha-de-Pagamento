package com.sfp.folha.domain;

import com.sfp.core.domain.Funcionario;
import java.math.BigDecimal;

public class Holerite {
    private final Funcionario funcionario;
    private final BigDecimal totalProventos;
    private final BigDecimal totalDescontos;
    private final BigDecimal salarioLiquido;

    public Holerite(Funcionario funcionario, BigDecimal totalProventos, BigDecimal totalDescontos,
            BigDecimal salarioLiquido) {
        this.funcionario = funcionario;
        this.totalProventos = totalProventos;
        this.totalDescontos = totalDescontos;
        this.salarioLiquido = salarioLiquido;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public BigDecimal getTotalProventos() {
        return totalProventos;
    }

    public BigDecimal getTotalDescontos() {
        return totalDescontos;
    }

    public BigDecimal getSalarioLiquido() {
        return salarioLiquido;
    }
}
