package com.sfp.core.domain;

// @brief: Classe que representa a folha de ponto
public class FolhaDePonto {
    private double horasExtras50;
    private double horasExtras100;
    private int diasTrabalhados;
    private int diasUteis;

    // @brief: Construtor da classe
    public FolhaDePonto() {
        this.horasExtras50 = 0.0;
        this.horasExtras100 = 0.0;
        this.diasTrabalhados = 0;
        this.diasUteis = 0;
    }

    // @brief: Retorna o total de horas extras com acréscimo de 50%
    // @return double: Total de horas extras com acréscimo de 50%
    public double getHorasExtras50() {
        return horasExtras50;
    }

    // @brief: Define o total de horas extras com acréscimo de 50%
    // @param horasExtras50: Total de horas extras com acréscimo de 50%
    public void setHorasExtras50(double horasExtras50) {
        this.horasExtras50 = horasExtras50;
    }

    // @brief: Retorna o total de horas extras com acréscimo de 100%
    // @return double: Total de horas extras com acréscimo de 100%
    public double getHorasExtras100() {
        return horasExtras100;
    }

    // @brief: Define o total de horas extras com acréscimo de 100%
    // @param horasExtras100: Total de horas extras com acréscimo de 100%
    public void setHorasExtras100(double horasExtras100) {
        this.horasExtras100 = horasExtras100;
    }

    // @brief: Retorna o número de dias trabalhados
    // @return int: Número de dias trabalhados
    public int getDiasTrabalhados() {
        return diasTrabalhados;
    }

    // @brief: Define o número de dias trabalhados
    // @param diasTrabalhados: Número de dias trabalhados
    public void setDiasTrabalhados(int diasTrabalhados) {
        this.diasTrabalhados = diasTrabalhados;
    }

    // @brief: Retorna o número de dias úteis
    // @return int: Número de dias úteis
    public int getDiasUteis() {
        return diasUteis;
    }

    // @brief: Define o número de dias úteis
    // @param diasUteis: Número de dias úteis
    public void setDiasUteis(int diasUteis) {
        this.diasUteis = diasUteis;
    }
}
