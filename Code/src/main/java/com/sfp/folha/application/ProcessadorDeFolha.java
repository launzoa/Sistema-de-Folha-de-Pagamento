package com.sfp.folha.application;

import com.sfp.folha.domain.Holerite;
import com.sfp.folha.domain.RegraDeCalculo;
import com.sfp.folha.domain.Lancamento;

import com.sfp.funcionario.domain.Funcionario;

import java.util.List;
import java.math.BigDecimal;

// @brief Classe responsável por processar a folha de pagamento.
public class ProcessadorDeFolha {

    private final List<RegraDeCalculo> regrasDeProvento;
    private final List<RegraDeCalculo> regrasDeDesconto;
    private final List<RegraDeCalculo> regrasInformativas;

    /**
     * @brief Construtor da classe ProcessadorDeFolha.
     * @param regrasDeProvento Lista de regras de provento.
     * @param regrasDeDesconto Lista de regras de desconto.
     * @param regrasInformativas Lista de regras informativas (ex: FGTS).
     */
    public ProcessadorDeFolha(List<RegraDeCalculo> regrasDeProvento, List<RegraDeCalculo> regrasDeDesconto, List<RegraDeCalculo> regrasInformativas) {
        this.regrasDeProvento = regrasDeProvento;
        this.regrasDeDesconto = regrasDeDesconto;
        this.regrasInformativas = regrasInformativas;
    }


    /**
     * @brief Processa a folha de pagamento de um funcionário.
     * @param funcionario Funcionário responsável pelo holerite.
     * @return Holerite do funcionário.
     */
    public Holerite processar(Funcionario funcionario, List<Lancamento> lancamentos, int diasUteis) {
        BigDecimal totalProventos = BigDecimal.ZERO;
        BigDecimal totalDescontos = BigDecimal.ZERO;
        BigDecimal salarioLiquido = BigDecimal.ZERO;

        Holerite holerite = new Holerite(funcionario, lancamentos, totalProventos, totalDescontos, salarioLiquido);
        holerite.setQuantidadeDiasUteis(diasUteis);

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

        // Soma lançamentos fixos/variáveis do banco de dados
        if (lancamentos != null) {
            com.sfp.rubrica.domain.RubricaRepository rubricaRepo = new com.sfp.rubrica.infrastructure.persistence.MySQLRubricaRepository();
            for (Lancamento l : lancamentos) {
                if (l.getValor() != null && l.getValor().compareTo(BigDecimal.ZERO) > 0) {
                    com.sfp.rubrica.domain.Rubrica r = rubricaRepo.buscarPorCodigo(l.getCodigoRubrica());
                    if (r != null) {
                        if ("Provento".equalsIgnoreCase(r.getNatureza())) {
                            totalProventos = totalProventos.add(l.getValor());
                        } else if ("Desconto".equalsIgnoreCase(r.getNatureza())) {
                            totalDescontos = totalDescontos.add(l.getValor());
                        }
                    }
                }
            }
        }

        // Calcula o salário líquido.
        salarioLiquido = totalProventos.subtract(totalDescontos);

        // Salário não pode ser negativo.
        if (salarioLiquido.compareTo(BigDecimal.ZERO) < 0) {
            salarioLiquido = BigDecimal.ZERO;
        }

        holerite.atualizar(totalProventos, totalDescontos, salarioLiquido);

        // Executa as regras informativas (após o líquido, pois não afetam o total)
        if (this.regrasInformativas != null) {
            for (RegraDeCalculo regras : this.regrasInformativas) {
                regras.calcular(holerite);
            }
        }

        return holerite;
    }
}
