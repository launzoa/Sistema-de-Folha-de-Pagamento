package com.sfp.folha.application;

import com.sfp.folha.domain.Holerite;
import com.sfp.folha.domain.RegraDeCalculo;
import com.sfp.folha.domain.Lancamento;
import com.sfp.folha.domain.ConstantesFolha;
import com.sfp.folha.domain.FolhaMes;
import com.sfp.funcionario.domain.Funcionario;
import com.sfp.empresa.domain.Empresa;
import com.sfp.rubrica.domain.Rubrica;
import com.sfp.rubrica.domain.RubricaRepository;
import com.sfp.rubrica.infrastructure.persistence.MySQLRubricaRepository;

import java.util.List;
import java.util.regex.Matcher;
import java.math.BigDecimal;
import java.util.regex.Pattern;
import java.math.RoundingMode;

/**
 * @brief Classe responsável por processar a folha de pagamento de um
 *        funcionário
 */
public class ProcessadorDeFolha {

    private final List<RegraDeCalculo> regrasDeProvento;
    private final List<RegraDeCalculo> regrasDeDesconto;
    private final List<RegraDeCalculo> regrasInformativas;
    private RubricaRepository rubricaRepository;
    private FolhaMes folhaContexto;

    /**
     * @brief Define a folha atual no processador.
     * @param folhaContexto A folha sendo processada
     */
    public void setFolhaContexto(FolhaMes folhaContexto) {
        this.folhaContexto = folhaContexto;
    }

    /**
     * @brief Método responsável por setar o repositório de rubricas.
     * @param repo Repositório de rubricas.
     */
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
     * @param empresa     Empresa que o funcionário trabalha.
     * @param funcionario Funcionário responsável pelo holerite.
     * @param lancamentos Lista de lançamentos.
     * @param diasUteis   Quantidade de dias úteis.
     * @return Holerite do funcionário.
     */
    public Holerite processar(Empresa empresa, Funcionario funcionario, List<Lancamento> lancamentos, int diasUteis) {
        // Inicializa os valores totais.
        BigDecimal totalProventos = BigDecimal.ZERO;
        BigDecimal totalDescontos = BigDecimal.ZERO;
        BigDecimal salarioLiquido = BigDecimal.ZERO;

        // Cria o holerite com os valores iniciais.
        Holerite holerite = new Holerite(empresa, funcionario, lancamentos, totalProventos, totalDescontos,
                salarioLiquido);
        holerite.setQuantidadeDiasUteis(diasUteis);
        if (this.folhaContexto != null) {
            holerite.setFolhaAtual(this.folhaContexto);
        }

        // Percorre e calcula os proventos do funcionário.
        for (RegraDeCalculo regras : this.regrasDeProvento) {
            BigDecimal provento = regras.calcular(holerite);
            totalProventos = totalProventos.add(provento);
        }

        // Percorre e soma os lançamentos do funcionário.
        if (lancamentos != null) {
            // Se o repositório de rubricas não estiver definido, cria um novo.
            RubricaRepository rubricaRepo = this.rubricaRepository != null ? this.rubricaRepository
                    : new MySQLRubricaRepository();
            // Percorre e soma os lançamentos do funcionário.
            for (Lancamento l : lancamentos) {
                // Busca a rubrica correspondente ao lançamento.
                Rubrica r = rubricaRepo.buscarPorCodigo(l.getCodigoRubrica());
                if (r != null) { // Se existir a rubrica, então
                    BigDecimal valorCalculado = BigDecimal.ZERO;
                    // Pega a modalidade de cálculo do lançamento.
                    String modalidade = l.getModalidade() != null ? l.getModalidade() : "Valor";
                    if ("Quantidade".equalsIgnoreCase(modalidade)) { // Se a modalidade for "Quantidade", então
                        String desc = r.getDescricao().toLowerCase();
                        BigDecimal baseRate;
                        if (desc.contains("dia") || desc.contains("falta")) { // Se a descrição contém "dia" ou "falta",
                                                                              // então calcula a taxa diária.
                            // Taxa diária = Salario Base / Dias Uteis Padrão (ex: 22 dias)
                            baseRate = funcionario.getSalarioBruto().divide(
                                    new BigDecimal(String.valueOf(ConstantesFolha.DIAS_UTEIS_PADRAO)), 2,
                                    RoundingMode.HALF_UP);
                        } else { // Se a descrição não contém "dia" ou "falta", então calcula a taxa horária.
                            // Taxa horária = Salario Base / Horas Mês Padrão (ex: 176 horas/mês)
                            baseRate = funcionario.getSalarioBruto()
                                    .divide(new BigDecimal(
                                            String.valueOf(ConstantesFolha.HORAS_MES_PADRAO)), 2,
                                            RoundingMode.HALF_UP);
                        }

                        // Busca multiplicador na descrição (ex: 50%)
                        BigDecimal multiplicador = BigDecimal.ONE;
                        Matcher m = Pattern.compile("(\\d{1,3})%").matcher(desc);
                        if (m.find()) {
                            try {
                                BigDecimal pct = new BigDecimal(m.group(1));
                                // Se for Hora Extra 50%, o multiplicador é 1.50
                                multiplicador = BigDecimal.ONE
                                        .add(pct.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP));
                            } catch (Exception ignored) {
                            }
                        }

                        valorCalculado = baseRate.multiply(new BigDecimal(String.valueOf(l.getQuantidade())))
                                .multiply(multiplicador).setScale(2, RoundingMode.HALF_UP);
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
                            } catch (Exception e) {
                                baseCalculo = funcionario.getSalarioBruto();
                            }
                        }
                        BigDecimal pct = l.getValor() != null ? l.getValor() : BigDecimal.ZERO;
                        valorCalculado = baseCalculo.multiply(pct).divide(new BigDecimal("100"), 2,
                                RoundingMode.HALF_UP);
                    } else { // Valor absoluto
                        valorCalculado = l.getValor() != null ? l.getValor() : BigDecimal.ZERO;
                    }

                    // Teto legal para Vale Transporte e PAT baseados no salário base
                    if (r.getCodigo() == 901) { // Vale Transporte (max 6% do salario base)
                        BigDecimal limiteVT = funcionario.getSalarioBruto().multiply(new BigDecimal("0.06")).setScale(2, RoundingMode.HALF_UP);
                        if (valorCalculado.compareTo(limiteVT) > 0) {
                            valorCalculado = limiteVT;
                        }
                    } else if (r.getCodigo() == 902) { // PAT (max 20% do salario base)
                        BigDecimal limitePAT = funcionario.getSalarioBruto().multiply(new BigDecimal("0.20")).setScale(2, RoundingMode.HALF_UP);
                        if (valorCalculado.compareTo(limitePAT) > 0) {
                            valorCalculado = limitePAT;
                        }
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

        // Atualiza o holerite parcialmente para que as calculadoras de desconto
        // tenham acesso à base correta (totalProventos real)
        holerite.atualizar(totalProventos, totalDescontos, salarioLiquido);

        // Percorre e calcula os descontos do funcionário (agora APÓS somar todos os
        // proventos).
        for (RegraDeCalculo regras : this.regrasDeDesconto) {
            BigDecimal desconto = regras.calcular(holerite);
            totalDescontos = totalDescontos.add(desconto);
        }

        // Calcula o salário líquido.
        salarioLiquido = totalProventos.subtract(totalDescontos);
        // Salário não pode ser negativo.
        if (salarioLiquido.compareTo(BigDecimal.ZERO) < 0) {
            holerite.setDividaResidual(salarioLiquido.abs());
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
