package com.sfp.folha.application.calculadoras;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.sfp.folha.domain.Holerite;
import com.sfp.folha.domain.FaixaIRRF;
import com.sfp.folha.domain.RegraDeCalculo;

public class CalculadoraIRRF implements RegraDeCalculo {

    private static final BigDecimal DEDUCAO_POR_DEPENDENTE = new BigDecimal("189.59");
    private static final BigDecimal DESCONTO_SIMPLIFICADO = new BigDecimal("564.80");

    private final List<FaixaIRRF> tabelaIRRF;
    private final BigDecimal tetoMaximoDesconto;

    // @brief: Construtor da classe
    // @param tabelaIRRF: Tabela de faixas do IRRF
    // @param tetoMaximoDesconto: Teto máximo de desconto do IRRF
    public CalculadoraIRRF(List<FaixaIRRF> tabelaIRRF, BigDecimal tetoMaximoDesconto) {
        this.tabelaIRRF = tabelaIRRF;
        this.tetoMaximoDesconto = tetoMaximoDesconto;
    }

    // @brief: Calcula o desconto do IRRF
    // @param funcionario: Objeto Funcionario que contém as informações do
    // funcionário
    // @param diasUteis: Número de dias úteis no mês
    // @param diasTrabalhados: Número de dias trabalhados no mês
    // @return BigDecimal: Desconto do IRRF
    @Override
    public BigDecimal calcular(Holerite holerite) {
        // Pega o salário bruto do funcionário e o número de dependentes
        BigDecimal salarioBruto = holerite.getFuncionario().getSalarioBruto();
        BigDecimal numeroDependentes = BigDecimal.valueOf(holerite.getFuncionario().getNumeroDependentes());
        // Pega o desconto do INSS
        BigDecimal descontoINSS = holerite.getDescontoINSS();

        // Calcula a base legal
        BigDecimal baseLegal = salarioBruto.subtract(descontoINSS)
                .subtract(numeroDependentes.multiply(DEDUCAO_POR_DEPENDENTE));

        // Calcula a base simplificada
        BigDecimal baseSimplificada = salarioBruto.subtract(DESCONTO_SIMPLIFICADO);

        // Inicialização das variáveis
        BigDecimal desconto = BigDecimal.ZERO;
        BigDecimal aliquota = BigDecimal.ZERO;
        BigDecimal parcela = BigDecimal.ZERO;
        BigDecimal baseCalculo;
        // Flag para indicar se encontrou uma faixa
        boolean flag = false;

        // Verifica qual base é maior e aplica o desconto
        if (baseLegal.compareTo(baseSimplificada) < 0) {
            baseCalculo = baseLegal;
        } else {
            baseCalculo = baseSimplificada;
        }

        // Percorre a tabela do IRRF para encontrar a faixa correspondente
        for (FaixaIRRF faixa : tabelaIRRF) {
            // Verifica se a base de cálculo se encaixa na faixa
            if (baseCalculo.compareTo(faixa.getTeto()) <= 0) {
                aliquota = faixa.getAliquota();
                parcela = faixa.getParcelaADeduzir();
                flag = true;

                break;
            }
        }

        // Calcula o desconto do IRRF
        if (flag) {
            desconto = baseCalculo.multiply(aliquota).subtract(parcela);
        }

        // Verifica se o desconto é menor que zero
        if (desconto.compareTo(BigDecimal.ZERO) < 0) {
            desconto = BigDecimal.ZERO;
        }

        // Verifica se o desconto é maior que o teto máximo
        if (desconto.compareTo(tetoMaximoDesconto) > 0) {
            desconto = tetoMaximoDesconto;
        }

        return desconto.setScale(2, RoundingMode.HALF_UP);
    }
}