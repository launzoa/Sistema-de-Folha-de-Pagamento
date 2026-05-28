package com.sfp.folha.application.calculadoras;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.sfp.core.domain.Funcionario;
import com.sfp.folha.domain.FaixaINSS;
import com.sfp.folha.domain.RegraDeCalculo;

public class CalculadoraINSS implements RegraDeCalculo {

    private final List<FaixaINSS> tabelaINSS;
    private final BigDecimal tetoMaximoDesconto;

    public CalculadoraINSS(List<FaixaINSS> tabelaINSS, BigDecimal tetoMaximoDesconto) {
        this.tabelaINSS = tabelaINSS;
        this.tetoMaximoDesconto = tetoMaximoDesconto;
    }

    @Override
    public BigDecimal calcular(Funcionario funcionario, int diasUteis, int diasTrabalhados) {
        BigDecimal salarioBruto = funcionario.getSalarioBase();
        BigDecimal aliquota = BigDecimal.ZERO;
        BigDecimal parcela = BigDecimal.ZERO;

        for (FaixaINSS faixa : this.tabelaINSS) {
            if (salarioBruto.compareTo(faixa.getValorMaximo()) <= 0) {
                aliquota = faixa.getAliquota();
                parcela = faixa.getParcelaADeduzir();

                break;
            }
        }

        BigDecimal desconto = salarioBruto.multiply(aliquota).subtract(parcela);

        if (salarioBruto.compareTo(BigDecimal.ZERO) > 0 && desconto.compareTo(BigDecimal.ZERO) == 0) {
            desconto = this.tetoMaximoDesconto;
        }

        return desconto.setScale(2, RoundingMode.HALF_UP);

    }
}
