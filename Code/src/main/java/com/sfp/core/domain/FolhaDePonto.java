package com.sfp.core.domain;

public class FolhaDePonto {
    private double horasExtras50;
    private double horasExtras100;

    public FolhaDePonto() {
        this.horasExtras50 = 0.0;
        this.horasExtras100 = 0.0;
    }

    public double getHorasExtras50() {
        return horasExtras50;
    }

    public void setHorasExtras50(double horasExtras50) {
        this.horasExtras50 = horasExtras50;
    }

    public double getHorasExtras100() {
        return horasExtras100;
    }

    public void setHorasExtras100(double horasExtras100) {
        this.horasExtras100 = horasExtras100;
    }
}
