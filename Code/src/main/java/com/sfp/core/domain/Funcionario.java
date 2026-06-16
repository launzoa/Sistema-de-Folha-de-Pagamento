package com.sfp.core.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

// @brief: Classe que representa um funcionário
public class Funcionario {
    private String nome;
    private String cpf;
    private LocalDate dataAdmissao;
    private String cargo;
    private BigDecimal salarioBruto;
    private boolean status;
    private int numeroDependentes;
    private FolhaDePonto ponto;

    /**
     * @brief Construtor da classe
     * @param nome              Nome do funcionário
     * @param cpf               CPF do funcionário
     * @param cargo             Cargo do funcionário
     * @param dataAdmissao      Data de admissão do funcionário
     * @param salarioBruto      Salário bruto do funcionário
     * @param status            Status do funcionário
     * @param numeroDependentes Número de dependentes do funcionário
     */
    public Funcionario(String nome, String cpf, String cargo, LocalDate dataAdmissao, BigDecimal salarioBruto,
            boolean status, int numeroDependentes) {
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.dataAdmissao = dataAdmissao;
        this.salarioBruto = salarioBruto;
        this.status = status;
        this.numeroDependentes = numeroDependentes;
        this.ponto = new FolhaDePonto();
    }

    // @brief: Retorna o nome do funcionário
    // @return String: Nome do funcionário
    public String getNome() {
        return nome;
    }

    // @brief: Retorna o cpf do funcionário
    // @return String: CPF do funcionário
    public String getCpf() {
        return cpf;
    }

    // @brief: Retorna a data de admissão do funcionário
    // @return LocalDate: Data de admissão do funcionário
    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    // @brief: Retorna o cargo do funcionário
    // @return String: Cargo do funcionário
    public String getCargo() {
        return cargo;
    }

    // @brief: Retorna o status do funcionário
    // @return boolean: Status do funcionário
    public boolean getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return this.nome + " (" + this.cpf + ")";
    }

    // @brief: Retorna o salário bruto do funcionário
    // @return BigDecimal: Salário bruto do funcionário
    public BigDecimal getSalarioBruto() {
        return salarioBruto;
    }

    // @brief: Retorna o número de dependentes do funcionário
    // @return int: Número de dependentes do funcionário
    public int getNumeroDependentes() {
        return numeroDependentes;
    }

    // @brief: Retorna a folha de ponto do funcionário
    // @return FolhaDePonto: Folha de ponto do funcionário
    public FolhaDePonto getPonto() {
        return ponto;
    }
}
