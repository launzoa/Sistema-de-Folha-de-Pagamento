package com.sfp.folha.application;

import com.sfp.core.domain.Funcionario;
import com.sfp.folha.domain.Holerite;
import com.sfp.folha.domain.RegraDeCalculo;
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
     * @param funcionario     Funcionário responsável pelo holerite.
     * @param diasUteis       Dias úteis no mês.
     * @param diasTrabalhados Dias trabalhados no mês.
     * @return Holerite do funcionário.
     */
    public Holerite processar(Funcionario funcionario, int diasUteis, int diasTrabalhados) {
        BigDecimal totalProventos = BigDecimal.ZERO;
        BigDecimal totalDescontos = BigDecimal.ZERO;

        // processa os proventos do funcionário.
        for (RegraDeCalculo regras : this.regrasDeProvento) {
            BigDecimal provento = regras.calcular(funcionario, diasUteis, diasTrabalhados);
            totalProventos = totalProventos.add(provento);
        }

        // processa os descontos do funcionário.
        for (RegraDeCalculo regras : this.regrasDeDesconto) {
            BigDecimal desconto = regras.calcular(funcionario, diasUteis, diasTrabalhados);
            totalDescontos = totalDescontos.add(desconto);
        }

        // calcula o salário líquido.
        BigDecimal salarioLiquido = totalProventos.subtract(totalDescontos);

        // salário não pode ser negativo
        if (salarioLiquido.compareTo(BigDecimal.ZERO) <= 0) {
            return null;
        }

        return new Holerite(funcionario, totalProventos, totalDescontos, salarioLiquido);
    }
}
