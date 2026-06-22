package com.sfp.folha.application.calculadoras;

import com.sfp.folha.domain.Holerite;
import com.sfp.folha.domain.RegraDeCalculo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculadoraSalarioProporcional implements RegraDeCalculo {
    /**
     * @brief: Calcula o salário proporcional
     * @param funcionario:     Objeto Funcionario que contém as informações do
     *                         funcionário
     * @param diasUteis:       Número de dias úteis no mês
     * @param diasTrabalhados: Número de dias trabalhados no mês
     * @return BigDecimal: Salário proporcional
     */
    @Override
    public BigDecimal calcular(Holerite holerite) {
        BigDecimal salarioBruto = holerite.getFuncionario().getSalarioBruto();
        double diasFaltas = holerite.getQuantidadePorRubrica(102);
        int diasUteis = holerite.getQuantidadeDiasUteis();
        double diasTrabalhados = diasUteis - diasFaltas;

        // valor da diária = salário base / dias úteis
        BigDecimal valorDiaria = salarioBruto.divide(BigDecimal.valueOf(diasUteis), 2, RoundingMode.HALF_UP);

        // salário proporcional = valor da diária * dias trabalhados
        return valorDiaria.multiply(BigDecimal.valueOf(diasTrabalhados));
    }
}
