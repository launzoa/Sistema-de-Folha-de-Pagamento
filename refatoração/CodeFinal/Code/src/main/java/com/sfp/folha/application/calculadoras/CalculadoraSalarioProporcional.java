package com.sfp.folha.application.calculadoras;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import com.sfp.folha.domain.Holerite;
import com.sfp.folha.domain.RegraDeCalculo;

/**
 * @brief Classe responsável por calcular o salário proporcional
 */
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

        // Se a folha não foi injetada, fallback para o salário integral.
        if (holerite.getFolhaAtual() == null) {
            return salarioBruto;
        }

        // Pega os dados referentes à data de admissão do funcionário e do período da
        // folha atual
        LocalDate dataAdmissao = holerite.getFuncionario().getDataAdmissao();
        LocalDate inicioFolha = holerite.getFolhaAtual().getDataInicio();
        LocalDate fimFolha = holerite.getFolhaAtual().getDataFim();

        // Se a pessoa foi admitida DEPOIS do início do mês da folha atual, aplica
        // Pro-Rata.
        if (dataAdmissao != null && dataAdmissao.isAfter(inicioFolha) && !dataAdmissao.isAfter(fimFolha)) {
            // Regra comercial da CLT: o mês tem 30 dias para cálculo salarial
            int diasTrabalhados = 30 - dataAdmissao.getDayOfMonth() + 1;

            // Garantia para meses com 31 dias
            if (diasTrabalhados < 0)
                diasTrabalhados = 0;
            if (diasTrabalhados > 30)
                diasTrabalhados = 30;

            BigDecimal valorDia = salarioBruto.divide(new BigDecimal("30"), 2, RoundingMode.HALF_UP);
            return valorDia.multiply(new BigDecimal(diasTrabalhados)).setScale(2, RoundingMode.HALF_UP);
        }

        // Se admitida antes, salário integral
        return salarioBruto;
    }
}
