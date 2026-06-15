package com.sfp.core.domain;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String cnpj;
    private String razaoSocial;
    private String email;
    private String respLegal;
    private double aliquotaFgts;
    private int horasMensais;
    private double valCestaBasic;
    private double percHoraExtra50;
    private double percHoraExtra100;
    
    private List<EnderecoEmpresa> enderecos = new ArrayList<>();

    public Empresa() {}

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRespLegal() { return respLegal; }
    public void setRespLegal(String respLegal) { this.respLegal = respLegal; }
    public double getAliquotaFgts() { return aliquotaFgts; }
    public void setAliquotaFgts(double aliquotaFgts) { this.aliquotaFgts = aliquotaFgts; }
    public int getHorasMensais() { return horasMensais; }
    public void setHorasMensais(int horasMensais) { this.horasMensais = horasMensais; }
    public double getValCestaBasic() { return valCestaBasic; }
    public void setValCestaBasic(double valCestaBasic) { this.valCestaBasic = valCestaBasic; }
    public double getPercHoraExtra50() { return percHoraExtra50; }
    public void setPercHoraExtra50(double percHoraExtra50) { this.percHoraExtra50 = percHoraExtra50; }
    public double getPercHoraExtra100() { return percHoraExtra100; }
    public void setPercHoraExtra100(double percHoraExtra100) { this.percHoraExtra100 = percHoraExtra100; }

    public List<EnderecoEmpresa> getEnderecos() { return enderecos; }
    public void setEnderecos(List<EnderecoEmpresa> enderecos) { this.enderecos = enderecos; }

    @Override
    public String toString() {
        return this.razaoSocial + " (" + this.cnpj + ")";
    }
}
