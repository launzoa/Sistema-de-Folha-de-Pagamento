/**
 * @brief Classe responsável por processar a folha de pagamento de um funcionário
 */

package com.sfp.folha.application;

import com.sfp.folha.domain.Holerite;
import com.sfp.folha.domain.RegraDeCalculo;
import com.sfp.folha.domain.Lancamento;

import com.sfp.funcionario.domain.Funcionario;
import com.sfp.rubrica.domain.Rubrica;
import com.sfp.rubrica.domain.RubricaRepository;
import com.sfp.rubrica.infrastructure.persistence.MySQLRubricaRepository;

import java.util.List;
import java.math.BigDecimal;

public class ProcessadorDeFolha {

    private final List<RegraDeCalculo> regrasDeProvento;
    private final List<RegraDeCalculo> regrasDeDesconto;
    private final List<RegraDeCalculo> regrasInformativas;
    private RubricaRepository rubricaRepository;

    public void setRubricaRepository(RubricaRepository repo) {
        this.rubricaRepository = repo;
    }

    /**
     * @brief Construtor da classe ProcessadorDeFolha.
     * @param regrasDeProvento   Lista de regras de provento.
     * @param regrasDeDesconto   Lista de regras de desconto.
     * @param regrasInformativas Lista de regras informativas (ex: FGTS).
     */
    public ProcessadorDeFolha(List<RegraDeCalculo> regrasDeProvento, List<RegraDeCalculo> regrasDeDesconto,
            List<RegraDeCalculo> regrasInformativas) {
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
        // Inicializa os valores totais.
        BigDecimal totalProventos = BigDecimal.ZERO;
        BigDecimal totalDescontos = BigDecimal.ZERO;
        BigDecimal salarioLiquido = BigDecimal.ZERO;

        // Cria o holerite com os valores iniciais.
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
            RubricaRepository rubricaRepo = this.rubricaRepository != null ? this.rubricaRepository : new MySQLRubricaRepository();
            // Percorre e soma os lançamentos do funcionário.
            for (Lancamento l : lancamentos) {
                Rubrica r = rubricaRepo.buscarPorCodigo(l.getCodigoRubrica());
                if (r != null) { // Se existir a rubrica, então
                    BigDecimal valorCalculado = BigDecimal.ZERO;
                    String modalidade = l.getModalidade() != null ? l.getModalidade() : "Valor";

                    if ("Quantidade".equalsIgnoreCase(modalidade)) {
                        String desc = r.getDescricao().toLowerCase();
                        BigDecimal baseRate;
                        if (desc.contains("dia") || desc.contains("falta")) {
                            // Taxa diária = Salario Base / 30
                            baseRate = funcionario.getSalarioBruto().divide(new BigDecimal("30"), 2, java.math.RoundingMode.HALF_UP);
                        } else {
                            // Taxa horária = Salario Base / 220
                            baseRate = funcionario.getSalarioBruto().divide(new BigDecimal("220"), 2, java.math.RoundingMode.HALF_UP);
                        }
                        
                        // Busca multiplicador na descrição (ex: 50%)
                        BigDecimal multiplicador = BigDecimal.ONE;
                        java.util.regex.Matcher m = java.util.regex.Pattern.compile("(\\d+)%").matcher(desc);
                        if (m.find()) {
                            try {
                                BigDecimal pct = new BigDecimal(m.group(1));
                                // Se for Hora Extra 50%, o multiplicador é 1.50
                                multiplicador = BigDecimal.ONE.add(pct.divide(new BigDecimal("100"), 2, java.math.RoundingMode.HALF_UP));
                            } catch (Exception ignored) {}
                        }
                        
                        valorCalculado = baseRate.multiply(new BigDecimal(String.valueOf(l.getQuantidade()))).multiply(multiplicador).setScale(2, java.math.RoundingMode.HALF_UP);
                    } else if ("Porcentagem".equalsIgnoreCase(modalidade)) {
                        BigDecimal baseCalculo;
                        String tipoBase = l.getBaseCalculo();
                        if ("Salário Bruto".equalsIgnoreCase(tipoBase) || tipoBase == null) {
                            baseCalculo = funcionario.getSalarioBruto();
                        } else if ("Salário Líquido".equalsIgnoreCase(tipoBase)) {
                            // Fallback para salario base, já que o líquido real é calculado só no final
                            baseCalculo = funcionario.getSalarioBruto(); 
                        } else {
                            try {
                                baseCalculo = new BigDecimal(tipoBase);
                            } catch(Exception e) {
                                baseCalculo = funcionario.getSalarioBruto();
                            }
                        }
                        BigDecimal pct = l.getValor() != null ? l.getValor() : BigDecimal.ZERO;
                        valorCalculado = baseCalculo.multiply(pct).divide(new BigDecimal("100"), 2, java.math.RoundingMode.HALF_UP);
                    } else { // Valor absoluto
                        valorCalculado = l.getValor() != null ? l.getValor() : BigDecimal.ZERO;
                    }
                    
                    if (valorCalculado.compareTo(BigDecimal.ZERO) > 0) {
                        l.setValor(valorCalculado); // Salva em memória para exibir no holerite
                        
                        // Verifica se a rubrica é provento ou desconto.
                        if ("Provento".equalsIgnoreCase(r.getNatureza())) {
                            totalProventos = totalProventos.add(valorCalculado);
                        } else if ("Desconto".equalsIgnoreCase(r.getNatureza())) {
                            totalDescontos = totalDescontos.add(valorCalculado);
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
        // Atualiza o holerite com os valores calculados.
        holerite.atualizar(totalProventos, totalDescontos, salarioLiquido);
        // Executa as regras informativas (após o líquido, pois não afetam o total)
        if (this.regrasInformativas != null) {
            for (RegraDeCalculo regras : this.regrasInformativas) {
                regras.calcular(holerite);
            }
        }
        return holerite; // Retorna o holerite calculado.
    }
}
