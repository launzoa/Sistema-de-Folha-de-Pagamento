package com.sfp.folha.application.calculadoras;

import com.sfp.core.domain.FolhaDePonto;
import com.sfp.core.domain.Funcionario;
import com.sfp.folha.domain.RegraDeCalculo;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculadoraHoraExtra implements RegraDeCalculo {

    private final BigDecimal percentualAcrescimo;
    private final BigDecimal divisorMensal;
    private final boolean ehCemPorcento;

    // @brief: Construtor da classe
    // @param percentualAcrescimo: Percentual de acréscimo (e.g., 0.50 para 50%,
    // 1.00 para 100%)
    // @param divisorMensal: Divisor mensal (e.g., 200h para escala 5x2 : 40hx5 =
    // 200h)
    // @param ehCemPorcento: Flag para saber onde buscar as horas no ponto
    public CalculadoraHoraExtra(BigDecimal percentualAcrescimo, BigDecimal divisorMensal, boolean ehCemPorcento) {
        this.percentualAcrescimo = percentualAcrescimo;
        this.divisorMensal = divisorMensal;
        this.ehCemPorcento = ehCemPorcento;
    }

    // brief: Calcula o valor das horas extras
    // @param funcionario: Objeto Funcionario que contém as informações do
    // funcionário
    // @param diasUteis: Número de dias úteis no mês
    // @param diasTrabalhados: Número de dias trabalhados no mês
    // @return BigDecimal: Valor total das horas extras
    @Override
    public BigDecimal calcular(Funcionario funcionario, int diasUteis, int diasTrabalhados) {
        FolhaDePonto ponto = funcionario.getPonto();
        BigDecimal qntHoras = BigDecimal.ZERO;

        if (this.ehCemPorcento) {
            if (ponto.getHorasExtras100() == 0) {
                return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
            }

            qntHoras = new BigDecimal(ponto.getHorasExtras100());

        } else {
            if (ponto.getHorasExtras50() == 0) {
                return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
            }

            qntHoras = new BigDecimal(ponto.getHorasExtras50());

        }

        // valor da hora normal = (salário/divisor)
        BigDecimal valorHoraNormal = funcionario.getSalarioBruto().divide(this.divisorMensal, 2, RoundingMode.HALF_UP);
        // valor da hora extra = (salário/divisor) * (1 + percentual de acréscimo)
        BigDecimal valorHoraExtra = valorHoraNormal.multiply(BigDecimal.ONE.add(this.percentualAcrescimo));

        // valor total das horas extras = valor da hora extra * quantidade de horas
        return valorHoraExtra.multiply(qntHoras).setScale(2, RoundingMode.HALF_UP);
    }
}
