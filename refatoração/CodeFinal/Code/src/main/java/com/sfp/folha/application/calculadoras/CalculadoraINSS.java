package com.sfp.folha.application.calculadoras;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.sfp.folha.domain.Holerite;
import com.sfp.core.domain.FaixaINSS;
import com.sfp.folha.domain.RegraDeCalculo;

/**
 * @brief Classe responsável por calcular o INSS do funcionário.
 */
public class CalculadoraINSS implements RegraDeCalculo {

    private final List<FaixaINSS> tabelaINSS;
    private final BigDecimal tetoMaximoDesconto;

    /**
     * @brief Construtor da classe
     * @param tabelaINSS         Tabela de faixas do INSS
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
        // Inicializa variáveis
        BigDecimal salarioBruto = holerite.getTotalProventos();
        boolean flag = false;
        BigDecimal desconto = BigDecimal.ZERO;
        // Percorre as faixas do INSS
        for (FaixaINSS faixa : this.tabelaINSS) {
            if (faixa.isSalarioNaFaixa(salarioBruto)) { // Verifica se o salário bruto está na faixa
                desconto = faixa.calcularDesconto(salarioBruto); // Calcula o desconto
                flag = true; // Seta flag para true
                break; // Sai do loop
            }
        }
        if (!flag) { // Se não encontrou faixa, aplica o teto máximo
            desconto = this.tetoMaximoDesconto;
        }
        // Arredonda o desconto para 2 casas decimais
        desconto = desconto.setScale(2, RoundingMode.HALF_UP);

        // Salva a Transparência Fiscal no Holerite
        holerite.setBaseINSS(salarioBruto);
        if (salarioBruto.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal aliquotaReal = desconto.multiply(new BigDecimal("100"))
                    .divide(salarioBruto, 2, RoundingMode.HALF_UP);
            holerite.setAliquotaEfetivaINSS(aliquotaReal);
        } else {
            holerite.setAliquotaEfetivaINSS(BigDecimal.ZERO);
        }

        // Seta o desconto do INSS no holerite
        holerite.setDescontoINSS(desconto);
        // Retorna o desconto do INSS
        return desconto;
    }
}
