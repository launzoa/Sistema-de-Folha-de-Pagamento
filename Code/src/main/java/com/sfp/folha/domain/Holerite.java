package com.sfp.folha.domain;

import com.sfp.core.domain.Funcionario;
import java.math.BigDecimal;

// @brief Classe responsável por representar o holerite de um funcionário.
public class Holerite {
    private final Funcionario funcionario;
    private BigDecimal totalProventos;
    private BigDecimal descontoINSS;
    private BigDecimal totalDescontos;
    private BigDecimal salarioLiquido;

    /**
     * @brief Construtor da classe Holerite.
     * @param funcionario    Funcionário responsável pelo holerite.
     * @param totalProventos Total de proventos do funcionário.
     * @param totalDescontos Total de descontos do funcionário.
     * @param salarioLiquido Salário líquido do funcionário.
     */
    public Holerite(Funcionario funcionario, BigDecimal totalProventos,
            BigDecimal totalDescontos,
            BigDecimal salarioLiquido) {
        this.funcionario = funcionario;
        this.totalProventos = totalProventos;
        this.totalDescontos = totalDescontos;
        this.salarioLiquido = salarioLiquido;
        this.descontoINSS = BigDecimal.ZERO;
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
     * @brief Retorna o desconto do INSS do funcionário.
     * @return Desconto do INSS do funcionário.
     */
    public BigDecimal getDescontoINSS() {
        return this.descontoINSS;
    }

    /**
     * @brief Atualiza o desconto do INSS do funcionário.
     * @param descontoINSS Desconto do INSS do funcionário.
     */
    public void setDescontoINSS(BigDecimal descontoINSS) {
        this.descontoINSS = descontoINSS;
    }
}
