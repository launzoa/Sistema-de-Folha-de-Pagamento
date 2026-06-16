package com.sfp.folha.application;

import com.sfp.folha.domain.Holerite;
import com.sfp.folha.domain.RegraDeCalculo;

import com.sfp.core.domain.Funcionario;

import java.util.List;
import java.math.BigDecimal;

// @brief Classe responsável por processar a folha de pagamento.
public class ProcessadorDeFolha {

    private final List<RegraDeCalculo> regrasDeProvento;
    private final List<RegraDeCalculo> regrasDeDesconto;

    /**
     * @brief Construtor da classe ProcessadorDeFolha.
     * @param regrasDeProvento Lista de regras de provento.
     * @param regrasDeDesconto Lista de regras de desconto.
     */
    public ProcessadorDeFolha(List<RegraDeCalculo> regrasDeProvento, List<RegraDeCalculo> regrasDeDesconto) {
        this.regrasDeProvento = regrasDeProvento;
        this.regrasDeDesconto = regrasDeDesconto;
    }

    /**
     * @brief Processa a folha de pagamento de um funcionário.
     * @param funcionario Funcionário responsável pelo holerite.
     * @return Holerite do funcionário.
     */
    public Holerite processar(Funcionario funcionario) {
        BigDecimal totalProventos = BigDecimal.ZERO;
        BigDecimal totalDescontos = BigDecimal.ZERO;
        BigDecimal salarioLiquido = BigDecimal.ZERO;

        Holerite holerite = new Holerite(funcionario, totalProventos, totalDescontos, salarioLiquido);

        // Percorre e calcula os proventos do funcionário.
        for (RegraDeCalculo regras : this.regrasDeProvento) {
            BigDecimal provento = regras.calcular(holerite);
            totalProventos = totalProventos.add(provento);
        }

        // Percorre e calcula os descontos do funcionário.
        for (RegraDeCalculo regras : this.regrasDeDesconto) {
            BigDecimal desconto = regras.calcular(holerite);
            totalDescontos = totalDescontos.add(desconto);
        }

        // Calcula o salário líquido.
        salarioLiquido = totalProventos.subtract(totalDescontos);

        // Salário não pode ser negativo.
        if (salarioLiquido.compareTo(BigDecimal.ZERO) <= 0) {
            return null;
        }

        holerite.atualizar(totalProventos, totalDescontos, salarioLiquido);

        return holerite;
    }
}
