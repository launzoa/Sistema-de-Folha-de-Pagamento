package com.sfp.folha.application.calculadoras;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.sfp.folha.domain.Holerite;
import com.sfp.core.domain.FaixaINSS;
import com.sfp.folha.domain.RegraDeCalculo;

public class CalculadoraINSS implements RegraDeCalculo {

    private final List<FaixaINSS> tabelaINSS;
    private final BigDecimal tetoMaximoDesconto;

    /**
     * @brief Construtor da classe
     * @param tabelaINSS         Tabela de faixas do INSS
     * 
     * @param tetoMaximoDesconto Teto máximo de desconto do INSS
     */
    public CalculadoraINSS(List<FaixaINSS> tabelaINSS, BigDecimal tetoMaximoDesconto) {
        this.tabelaINSS = tabelaINSS;
        this.tetoMaximoDesconto = tetoMaximoDesconto;
    }

    /**
     * @brief Calcula o desconto do INSS
     * @param holerite Objeto Holerite que contém as informações do funcionário
     * @return BigDecimal Desconto do INSS
     */
    @Override
    public BigDecimal calcular(Holerite holerite) {
        BigDecimal salarioBruto = holerite.getFuncionario().getSalarioBruto(); // Pega o salário bruto
        boolean flag = false;
        BigDecimal desconto = BigDecimal.ZERO;
        for (FaixaINSS faixa : this.tabelaINSS) {
            if (faixa.isSalarioNaFaixa(salarioBruto)) {
                desconto = faixa.calcularDesconto(salarioBruto);
                flag = true;
                break;
            }
        }

        if (!flag) { // Se não encontrou faixa, aplica o teto máximo
            desconto = this.tetoMaximoDesconto;
        }

        // Arredonda o desconto para 2 casas decimais
        desconto = desconto.setScale(2, RoundingMode.HALF_UP);
        // Seta o desconto do INSS no holerite
        holerite.setDescontoINSS(desconto);
        // Retorna o desconto do INSS
        return desconto;
    }
}
