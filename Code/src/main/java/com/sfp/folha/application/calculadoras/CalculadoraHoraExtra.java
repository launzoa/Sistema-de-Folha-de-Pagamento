package com.sfp.folha.application.calculadoras;

import com.sfp.core.domain.FolhaDePonto;
import com.sfp.core.domain.Funcionario;
import com.sfp.folha.domain.RegraDeCalculo;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculadoraHoraExtra implements RegraDeCalculo {

    private final BigDecimal percentualAcrescimo; // Ex: 0.50 para 50%, 1.00 para 100%
    private final BigDecimal divisorMensal; // Ex: 200 para escala 5x2 : 40hx5 = 200
    private final boolean ehCemPorcento; // Flag para saber onde buscar as horas no ponto

    public CalculadoraHoraExtra(BigDecimal percentualAcrescimo, BigDecimal divisorMensal, boolean ehCemPorcento) {
        this.percentualAcrescimo = percentualAcrescimo;
        this.divisorMensal = divisorMensal;
        this.ehCemPorcento = ehCemPorcento;
    }

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

        // hora = (salário/divisor)
        BigDecimal horaNormal = funcionario.getSalarioBase().divide(this.divisorMensal, 2, RoundingMode.HALF_UP);
        // horaExtra = (salário/divisor) * (1 + acréscimo) = hora + (hora * acréscimo)
        BigDecimal horaExtra = horaNormal.add(horaNormal.multiply(this.percentualAcrescimo));

        return horaExtra.multiply(qntHoras).setScale(2, RoundingMode.HALF_UP);
    }
}
