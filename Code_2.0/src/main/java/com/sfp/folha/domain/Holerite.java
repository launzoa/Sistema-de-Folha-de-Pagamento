package com.sfp.folha.domain;

import com.sfp.core.domain.Funcionario;
import java.math.BigDecimal;

// @brief Classe responsável por representar o holerite de um funcionário.
public class Holerite {
    private final Funcionario funcionario;
    private final BigDecimal totalProventos;
    private final BigDecimal totalDescontos;
    private final BigDecimal salarioLiquido;

    /**
     * @brief Construtor da classe Holerite.
     * @param funcionario    Funcionário responsável pelo holerite.
     * @param totalProventos Total de proventos do funcionário.
     * @param totalDescontos Total de descontos do funcionário.
     * @param salarioLiquido Salário líquido do funcionário.
     */
    public Holerite(Funcionario funcionario, BigDecimal totalProventos, BigDecimal totalDescontos,
            BigDecimal salarioLiquido) {
        this.funcionario = funcionario;
        this.totalProventos = totalProventos;
        this.totalDescontos = totalDescontos;
        this.salarioLiquido = salarioLiquido;
    }

    /**
     * @brief Retorna o funcionário responsável pelo holerite.
     * @return Funcionário responsável pelo holerite.
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * @brief Retorna o total de proventos do funcionário.
     * @return Total de proventos do funcionário.
     */
    public BigDecimal getTotalProventos() {
        return totalProventos;
    }

    /**
     * @brief Retorna o total de descontos do funcionário.
     * @return Total de descontos do funcionário.
     */
    public BigDecimal getTotalDescontos() {
        return totalDescontos;
    }

    /**
     * @brief Retorna o salário líquido do funcionário.
     * @return Salário líquido do funcionário.
     */
    public BigDecimal getSalarioLiquido() {
        return salarioLiquido;
    }
}
