/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sfp.core.domain;

/**
 *
 * @author maria
 */
public class Empresa {
   private String cnpj;
    private String razaoSocial;
    private String email;
    private String respLegal;
    private double aliquotaFGTS;
    private int horasMensais;
    private double valCestaBasic;
    private double percHoraExtra50;
    private double percHoraExtra100;

    public Empresa() {}

    public Empresa(String cnpj, String razaoSocial, String email, String respLegal, double aliquotaFGTS, int horasMensais, double valCestaBasic, double percHoraExtra50, double percHoraExtra100) {
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.email = email;
        this.respLegal = respLegal;
        this.aliquotaFGTS = aliquotaFGTS;
        this.horasMensais = horasMensais;
        this.valCestaBasic = valCestaBasic;
        this.percHoraExtra50 = percHoraExtra50;
        this.percHoraExtra100 = percHoraExtra100;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRespLegal() {
        return respLegal;
    }

    public void setRespLegal(String respLegal) {
        this.respLegal = respLegal;
    }

    public double getAliquotaFGTS() {
        return aliquotaFGTS;
    }

    public void setAliquotaFGTS(double aliquotaFGTS) {
        this.aliquotaFGTS = aliquotaFGTS;
    }

    public int getHorasMensais() {
        return horasMensais;
    }

    public void setHorasMensais(int horasMensais) {
        this.horasMensais = horasMensais;
    }

    public double getValCestaBasic() {
        return valCestaBasic;
    }

    public void setValCestaBasic(double valCestaBasic) {
        this.valCestaBasic = valCestaBasic;
    }

    public double getPercHoraExtra50() {
        return percHoraExtra50;
    }

    public void setPercHoraExtra50(double percHoraExtra50) {
        this.percHoraExtra50 = percHoraExtra50;
    }

    public double getPercHoraExtra100() {
        return percHoraExtra100;
    }

    public void setPercHoraExtra100(double percHoraExtra100) {
        this.percHoraExtra100 = percHoraExtra100;
    }

    @Override
    public String toString() {
        return razaoSocial;
    }
    
}
