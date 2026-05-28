package com.sfp.folha.application.calculadoras;

import com.sfp.core.domain.Funcionario;
import com.sfp.folha.domain.RegraDeCalculo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculadoraSalarioProporcional implements RegraDeCalculo {

    @Override
    public BigDecimal calcular(Funcionario funcionario, int diasUteis, int diasTrabalhados) {
        BigDecimal salarioBase = funcionario.getSalarioBase();

        BigDecimal diaria = salarioBase.divide(BigDecimal.valueOf(diasUteis), 2, RoundingMode.HALF_UP);

        return diaria.multiply(BigDecimal.valueOf(diasTrabalhados));
    }
}
