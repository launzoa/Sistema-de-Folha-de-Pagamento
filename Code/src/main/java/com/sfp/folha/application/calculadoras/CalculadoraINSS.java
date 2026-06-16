package com.sfp.folha.application.calculadoras;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.sfp.folha.domain.Holerite;
import com.sfp.folha.domain.FaixaINSS;
import com.sfp.folha.domain.RegraDeCalculo;

public class CalculadoraINSS implements RegraDeCalculo {

    private final List<FaixaINSS> tabelaINSS;
    private final BigDecimal tetoMaximoDesconto;

    /**
     * @brief: Construtor da classe
     * @param tabelaINSS:         Tabela de faixas do INSS
     * 
     * @param tetoMaximoDesconto: Teto máximo de desconto do INSS
     */
    public CalculadoraINSS(List<FaixaINSS> tabelaINSS, BigDecimal tetoMaximoDesconto) {
        this.tabelaINSS = tabelaINSS;
        this.tetoMaximoDesconto = tetoMaximoDesconto;
    }

    /**
     * @brief: Calcula o desconto do INSS
     * @param holerite: Objeto Holerite que contém as informações do funcionário
     * @return BigDecimal: Desconto do INSS
     */
    @Override
    public BigDecimal calcular(Holerite holerite) {
        BigDecimal salarioBruto = holerite.getFuncionario().getSalarioBruto(); // Pega o salário bruto
        // Inicializa a aliquota e a parcela em zero
        BigDecimal aliquota = BigDecimal.ZERO;
        BigDecimal parcela = BigDecimal.ZERO;
        // Flag para indicar se encontrou uma faixa
        boolean flag = false;

        // Busca a faixa do INSS correspondente ao salário bruto
        for (FaixaINSS faixa : this.tabelaINSS) {
            // Verifica se o salário bruto está dentro da faixa
            if (salarioBruto.compareTo(faixa.getTeto()) <= 0) {
                // Se encontrou a faixa, define a alíquota e a parcela
                aliquota = faixa.getAliquota();
                parcela = faixa.getParcelaDeduzir();
                // Define a flag como true
                flag = true;

                break;
            }
        }

        BigDecimal desconto = BigDecimal.ZERO;

        if (flag) { // Se encontrou uma faixa, calcula o desconto
            // Fórmula: (salário bruto * alíquota) - parcela a deduzir
            desconto = salarioBruto.multiply(aliquota).subtract(parcela);
        } else { // Se não encontrou faixa, aplica o teto máximo
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
