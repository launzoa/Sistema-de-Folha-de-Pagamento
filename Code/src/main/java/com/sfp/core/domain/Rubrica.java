/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sfp.core.domain;

/**
 *
 * @author manoe
 */
public class Rubrica {
    private int codigo;
    private String descricao;
    private String natureza;   // "Provento" ou "Desconto"
    private String tipo;       // "Fixo" ou "Variável"
    private boolean incideINSS;
    private boolean incideFGTS;
    private boolean incideIRRF;
    private boolean padrao;    // true = não editável (001-005)
    private boolean ativo; 

    public Rubrica() {}

    public Rubrica(int codigo, String descricao, String natureza, String tipo, boolean incideINSS, boolean incideFGTS, boolean incideIRRF, boolean padrao, boolean ativo) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.natureza = natureza;
        this.tipo = tipo;
        this.incideINSS = incideINSS;
        this.incideFGTS = incideFGTS;
        this.incideIRRF = incideIRRF;
        this.padrao = padrao;
        this.ativo = ativo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(String natureza) {
        this.natureza = natureza;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isIncideINSS() {
        return incideINSS;
    }

    public void setIncideINSS(boolean incideINSS) {
        this.incideINSS = incideINSS;
    }

    public boolean isIncideFGTS() {
        return incideFGTS;
    }

    public void setIncideFGTS(boolean incideFGTS) {
        this.incideFGTS = incideFGTS;
    }

    public boolean isIncideIRRF() {
        return incideIRRF;
    }

    public void setIncideIRRF(boolean incideIRRF) {
        this.incideIRRF = incideIRRF;
    }

    public boolean isPadrao() {
        return padrao;
    }

    public void setPadrao(boolean padrao) {
        this.padrao = padrao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public String getIncideINSSStr() 
    {
        return incideINSS ? "Sim" : "Não"; 
    }
    public String getIncideFGTSStr() 
    { 
        return incideFGTS ? "Sim" : "Não"; 
    }
    public String getIncideIRRFStr() 
    { 
        return incideIRRF ? "Sim" : "Não"; 
    }
    public String getTipoLabel()     
    { 
        return padrao ? "Padrão" : "Personalizada"; 
    }    
}
