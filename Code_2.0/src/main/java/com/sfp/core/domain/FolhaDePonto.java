package com.sfp.core.domain;

// @brief: Classe que representa a folha de ponto
public class FolhaDePonto {
    private double horasExtras50;
    private double horasExtras100;

    // @brief: Construtor da classe
    public FolhaDePonto() {
        this.horasExtras50 = 0.0;
        this.horasExtras100 = 0.0;
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
}
