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

    // @brief: Construtor da classe
    // @param tabelaINSS: Tabela de faixas do INSS
    // @param tetoMaximoDesconto: Teto máximo de desconto do INSS
    public CalculadoraINSS(List<FaixaINSS> tabelaINSS, BigDecimal tetoMaximoDesconto) {
        this.tabelaINSS = tabelaINSS;
        this.tetoMaximoDesconto = tetoMaximoDesconto;
    }

    // @brief: Calcula o desconto do INSS
    // @param funcionario: Objeto Funcionario que contém as informações do
    // funcionário
    // @param diasUteis: Número de dias úteis no mês
    // @param diasTrabalhados: Número de dias trabalhados no mês
    // @return BigDecimal: Desconto do INSS
    @Override
    public BigDecimal calcular(Funcionario funcionario, int diasUteis, int diasTrabalhados) {
        BigDecimal salarioBruto = funcionario.getSalarioBruto();
        BigDecimal aliquota = BigDecimal.ZERO;
        BigDecimal parcela = BigDecimal.ZERO;
        boolean flag = false;

        // Busca a faixa do INSS correspondente ao salário bruto
        for (FaixaINSS faixa : this.tabelaINSS) {
            if (salarioBruto.compareTo(faixa.getTeto()) <= 0) {
                aliquota = faixa.getAliquota();
                parcela = faixa.getParcelaDeduzir();

                flag = true;
                break;
            }
        }

        BigDecimal desconto = BigDecimal.ZERO;

        if (flag) { // se encontrou uma faixa, calcula o desconto
            // fórmula: (salário bruto * alíquota) - parcela a deduzir
            desconto = salarioBruto.multiply(aliquota).subtract(parcela);
        } else { // se não encontrou faixa aplica o teto máximo
            desconto = this.tetoMaximoDesconto;
        }

        return desconto.setScale(2, RoundingMode.HALF_UP);
    }
}
