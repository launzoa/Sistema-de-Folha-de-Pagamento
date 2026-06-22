/**
 * @brief Classe responsável por representar o holerite de um funcionário.
 */
package com.sfp.folha.domain;

import java.util.List;
import java.math.BigDecimal;

import com.sfp.funcionario.domain.Funcionario;

public class Holerite {
    private final Funcionario funcionario;
    private BigDecimal totalProventos;
    private BigDecimal totalDescontos;
    private BigDecimal salarioLiquido;
    private BigDecimal descontoINSS;
    private BigDecimal descontoIRRF;
    private int diasUteis;
    private List<Lancamento> lancamentos;
    private BigDecimal valorFGTS = BigDecimal.ZERO;

    /**
     * @brief Construtor da classe Holerite.
     * @param funcionario    Funcionário responsável pelo holerite.
     * @param lancamentos    Lancamentos no holerite.
     * @param totalProventos Total de proventos do funcionário.
     * @param totalDescontos Total de descontos do funcionário.
     * @param salarioLiquido Salário líquido do funcionário.
     */
    public Holerite(Funcionario funcionario, List<Lancamento> lancamentos, BigDecimal totalProventos,
            BigDecimal totalDescontos,
            BigDecimal salarioLiquido) {
        this.funcionario = funcionario;
        this.totalProventos = totalProventos;
        this.totalDescontos = totalDescontos;
        this.salarioLiquido = salarioLiquido;
        this.lancamentos = lancamentos;

        this.descontoINSS = BigDecimal.ZERO;
        this.descontoIRRF = BigDecimal.ZERO;
        this.diasUteis = 0;
    }

    /**
     * @brief Atualiza o holerite com os valores calculados.
     * @param totalProventos Total de proventos do funcionário.
     * @param totalDescontos Total de descontos do funcionário.
     * @param salarioLiquido Salário líquido do funcionário.
     */
    public void atualizar(BigDecimal totalProventos,
            BigDecimal totalDescontos, BigDecimal salarioLiquido) {
        this.totalProventos = totalProventos;
        this.totalDescontos = totalDescontos;
        this.salarioLiquido = salarioLiquido;
    }

    /**
     * @brief Retorna o funcionário responsável pelo holerite.
     * @return Funcionário responsável pelo holerite.
     */
    public Funcionario getFuncionario() {
        return this.funcionario;
    }

    /**
     * @brief Retorna o total de proventos do funcionário.
     * @return Total de proventos do funcionário.
     */
    public BigDecimal getTotalProventos() {
        return this.totalProventos;
    }

    /**
     * @brief Retorna o total de descontos do funcionário.
     * @return Total de descontos do funcionário.
     */
    public BigDecimal getTotalDescontos() {
        return this.totalDescontos;
    }

    /**
     * @brief Retorna o salário líquido do funcionário.
     * @return Salário líquido do funcionário.
     */
    public BigDecimal getSalarioLiquido() {
        return this.salarioLiquido;
    }

    /**
     * @brief Retorna a quantidade de uma rubrica específica.
     * @param codigoRubrica Código da rubrica.
     * @return Quantidade da rubrica específica.
     */
    public double getQuantidadePorRubrica(int codigoRubrica) {
        if (lancamentos == null) {
            return 0.0;
        }
        return lancamentos.stream()
                .filter(lancamento -> lancamento.getCodigoRubrica() == codigoRubrica)
                .mapToDouble(Lancamento::getQuantidade)
                .sum();
    }

    /**
     * @brief Retorna o desconto do INSS.
     * @return Desconto do INSS.
     */
    public BigDecimal getDescontoINSS() {
        return this.descontoINSS;
    }

    /**
     * @brief Seta o desconto do INSS.
     * @param descontoINSS Desconto do INSS.
     */
    public void setDescontoINSS(BigDecimal descontoINSS) {
        this.descontoINSS = descontoINSS;
    }

    /**
     * @brief Retorna a quantidade de dias úteis.
     * @return Quantidade de dias úteis.
     */
    public int getQuantidadeDiasUteis() {
        return this.diasUteis;
    }

    /**
     * @brief Seta a quantidade de dias úteis.
     * @param diasUteis Quantidade de dias úteis.
     */
    public void setQuantidadeDiasUteis(int diasUteis) {
        this.diasUteis = diasUteis;
    }

    /**
     * @brief Retorna o desconto do IRRF.
     * @return Desconto do IRRF.
     */
    public BigDecimal getDescontoIRRF() {
        return this.descontoIRRF;
    }

    /**
     * @brief Seta o desconto do IRRF.
     * @param descontoIRRF Desconto do IRRF.
     */
    public void setDescontoIRRF(BigDecimal descontoIRRF) {
        this.descontoIRRF = descontoIRRF;
    }

    /**
     * @brief Retorna os lançamentos do holerite.
     * @return Lançamentos do holerite.
     */
    public List<Lancamento> getLancamentos() {
        return this.lancamentos;
    }

    /**
     * @brief Retorna o valor do FGTS.
     * @return Valor do FGTS.
     */
    public BigDecimal getValorFGTS() {
        return this.valorFGTS;
    }

    /**
     * @brief Seta o valor do FGTS.
     * @param valorFGTS Valor do FGTS.
     */
    public void setValorFGTS(BigDecimal valorFGTS) {
        this.valorFGTS = valorFGTS;
    }
}
