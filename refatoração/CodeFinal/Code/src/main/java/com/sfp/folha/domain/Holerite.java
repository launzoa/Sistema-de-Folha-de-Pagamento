package com.sfp.folha.domain;

import java.util.List;
import java.math.BigDecimal;

import com.sfp.funcionario.domain.Funcionario;
import com.sfp.empresa.domain.Empresa;

/**
 * @brief Classe responsável por representar o holerite de um funcionário.
 */
public class Holerite {
    private final Empresa empresa;
    private final Funcionario funcionario;
    private BigDecimal totalProventos;
    private BigDecimal totalDescontos;
    private BigDecimal salarioLiquido;
    private BigDecimal descontoINSS;
    private BigDecimal descontoIRRF;
    private int diasUteis;
    private List<Lancamento> lancamentos;
    private BigDecimal valorFGTS = BigDecimal.ZERO;
    private FolhaMes folhaAtual;
    private BigDecimal dividaResidual = BigDecimal.ZERO;
    private BigDecimal baseINSS;
    private BigDecimal aliquotaEfetivaINSS;
    private BigDecimal baseIRRF;
    private BigDecimal aliquotaEfetivaIRRF;

    /**
     * @brief Construtor da classe Holerite.
     * @param empresa        Empresa patronal contendo regras.
     * @param funcionario    Funcionário responsável pelo holerite.
     * @param lancamentos    Lancamentos no holerite.
     * @param totalProventos Total de proventos do funcionário.
     * @param totalDescontos Total de descontos do funcionário.
     * @param salarioLiquido Salário líquido do funcionário.
     */
    public Holerite(Empresa empresa, Funcionario funcionario, List<Lancamento> lancamentos, BigDecimal totalProventos,
            BigDecimal totalDescontos,
            BigDecimal salarioLiquido) {
        this.empresa = empresa;
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
     * @brief Retorna a empresa responsável pelo holerite.
     * @return Empresa patronal.
     */
    public Empresa getEmpresa() {
        return this.empresa;
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

    /**
     * @brief Pega a folha vinculada a este holerite.
     * @return FolhaMes
     */
    public FolhaMes getFolhaAtual() {
        return folhaAtual;
    }

    /**
     * @brief Seta a folha vinculada a este holerite.
     * @param folhaAtual Folha de competência.
     */
    public void setFolhaAtual(FolhaMes folhaAtual) {
        this.folhaAtual = folhaAtual;
    }

    /**
     * @brief Pega a dívida residual que foi salva.
     * @return BigDecimal
     */
    public BigDecimal getDividaResidual() {
        return dividaResidual;
    }

    /**
     * @brief Seta a dívida residual do holerite.
     * @param dividaResidual Valor da dívida.
     */
    public void setDividaResidual(BigDecimal dividaResidual) {
        this.dividaResidual = dividaResidual;
    }

    public BigDecimal getBaseINSS() {
        return baseINSS;
    }

    public void setBaseINSS(BigDecimal baseINSS) {
        this.baseINSS = baseINSS;
    }

    public BigDecimal getAliquotaEfetivaINSS() {
        return aliquotaEfetivaINSS;
    }

    public void setAliquotaEfetivaINSS(BigDecimal aliquotaEfetivaINSS) {
        this.aliquotaEfetivaINSS = aliquotaEfetivaINSS;
    }

    public BigDecimal getBaseIRRF() {
        return baseIRRF;
    }

    public void setBaseIRRF(BigDecimal baseIRRF) {
        this.baseIRRF = baseIRRF;
    }

    public BigDecimal getAliquotaEfetivaIRRF() {
        return aliquotaEfetivaIRRF;
    }

    public void setAliquotaEfetivaIRRF(BigDecimal aliquotaEfetivaIRRF) {
        this.aliquotaEfetivaIRRF = aliquotaEfetivaIRRF;
    }
}
