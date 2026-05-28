package com.sfp.folha.application;

import com.sfp.core.domain.Funcionario;
import com.sfp.folha.domain.Holerite;
import com.sfp.folha.domain.RegraDeCalculo;
import java.util.List;
import java.math.BigDecimal;

public class ProcessadorDeFolha {

    private final List<RegraDeCalculo> regrasDeProvento;
    private final List<RegraDeCalculo> regrasDeDesconto;

    public ProcessadorDeFolha(List<RegraDeCalculo> regrasDeProvento, List<RegraDeCalculo> regrasDeDesconto) {
        this.regrasDeProvento = regrasDeProvento;
        this.regrasDeDesconto = regrasDeDesconto;
    }

    public Holerite processar(Funcionario funcionario, int diasUteis, int diasTrabalhados) {
        BigDecimal totalProventos = BigDecimal.ZERO;
        BigDecimal totalDescontos = BigDecimal.ZERO;

        for (RegraDeCalculo regras : this.regrasDeProvento) {
            BigDecimal provento = regras.calcular(funcionario, diasUteis, diasTrabalhados);
            totalProventos = totalProventos.add(provento);
        }

        for (RegraDeCalculo regras : this.regrasDeDesconto) {
            BigDecimal desconto = regras.calcular(funcionario, diasUteis, diasTrabalhados);
            totalDescontos = totalDescontos.add(desconto);
        }

        BigDecimal salarioLiquido = totalProventos.subtract(totalDescontos);

        if (salarioLiquido.compareTo(BigDecimal.ZERO) <= 0) { // salário não pode ser negativo
            return null;
        }

        return new Holerite(funcionario, totalProventos, totalDescontos, salarioLiquido);
    }
}
