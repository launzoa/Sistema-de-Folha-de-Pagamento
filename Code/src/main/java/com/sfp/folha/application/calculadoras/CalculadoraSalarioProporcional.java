/**
 * @brief Classe responsável por calcular o salário proporcional
 */
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
        // Pega o salário bruto do funcionário
        BigDecimal salarioBruto = holerite.getFuncionario().getSalarioBruto();
        // Pega a quantidade de faltas do funcionário
        double diasFaltas = holerite.getQuantidadePorRubrica(102);
        // Pega a quantidade de dias úteis no mês
        int diasUteis = holerite.getQuantidadeDiasUteis();
        // Calcula a quantidade de dias trabalhados
        double diasTrabalhados = diasUteis - diasFaltas;
        // valor da diária = salário base / dias úteis
        BigDecimal valorDiaria = salarioBruto.divide(BigDecimal.valueOf(diasUteis), 2, RoundingMode.HALF_UP);
        // salário proporcional = valor da diária * dias trabalhados
        return valorDiaria.multiply(BigDecimal.valueOf(diasTrabalhados));
    }
}
