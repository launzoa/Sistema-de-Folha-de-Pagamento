/**
 * @brief Classe que representa uma rubrica
 */
package com.sfp.rubrica.domain;

public class Rubrica {
    private int codigo;
    private String descricao;
    private String natureza; // "Provento" ou "Desconto"
    private String tipo; // "Fixo" ou "Variável"
    private boolean incideINSS;
    private boolean incideFGTS;
    private boolean incideIRRF;
    private boolean padrao; // true = não editável (001-005)
    private boolean ativo;

    /**
     * @brief Construtor padrão
     */
    public Rubrica() {
    }

    /**
     * @brief Construtor completo
     * @param codigo     Código da rubrica
     * @param descricao  Descrição da rubrica
     * @param natureza   Natureza da rubrica
     * @param tipo       Tipo da rubrica
     * @param incideINSS Se incide INSS
     * @param incideFGTS Se incide FGTS
     * @param incideIRRF Se incide IRRF
     * @param padrao     Se é padrão
     * @param ativo      Se está ativa
     */
    public Rubrica(int codigo, String descricao, String natureza, String tipo, boolean incideINSS, boolean incideFGTS,
            boolean incideIRRF, boolean padrao, boolean ativo) {
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

    /**
     * @brief Getter para o código
     * @return Código da rubrica
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @brief Setter para o código
     * @param codigo Código da rubrica
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @brief Getter para a descrição
     * @return Descrição da rubrica
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @brief Setter para a descrição
     * @param descricao Descrição da rubrica
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @brief Getter para a natureza
     * @return Natureza da rubrica
     */
    public String getNatureza() {
        return natureza;
    }

    /**
     * @brief Setter para a natureza
     * @param natureza Natureza da rubrica
     */
    public void setNatureza(String natureza) {
        this.natureza = natureza;
    }

    /**
     * @brief Getter para o tipo
     * @return Tipo da rubrica
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @brief Setter para o tipo
     * @param tipo Tipo da rubrica
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @brief Getter para incide INSS
     * @return Se incide INSS
     */
    public boolean isIncideINSS() {
        return incideINSS;
    }

    /**
     * @brief Setter para incide INSS
     * @param incideINSS Se incide INSS
     */
    public void setIncideINSS(boolean incideINSS) {
        this.incideINSS = incideINSS;
    }

    /**
     * @brief Getter para incide FGTS
     * @return Se incide FGTS
     */
    public boolean isIncideFGTS() {
        return incideFGTS;
    }

    /**
     * @brief Setter para incide FGTS
     * @param incideFGTS Se incide FGTS
     */
    public void setIncideFGTS(boolean incideFGTS) {
        this.incideFGTS = incideFGTS;
    }

    /**
     * @brief Getter para incide IRRF
     * @return Se incide IRRF
     */
    public boolean isIncideIRRF() {
        return incideIRRF;
    }

    /**
     * @brief Setter para incide IRRF
     * @param incideIRRF Se incide IRRF
     */
    public void setIncideIRRF(boolean incideIRRF) {
        this.incideIRRF = incideIRRF;
    }

    /**
     * @brief Getter para padrão
     * @return Se é padrão
     */
    public boolean isPadrao() {
        return padrao;
    }

    /**
     * @brief Setter para padrão
     * @param padrao Se é padrão
     */
    public void setPadrao(boolean padrao) {
        this.padrao = padrao;
    }

    /**
     * @brief Getter para ativo
     * @return Se está ativa
     */
    public boolean isAtivo() {
        return ativo;
    }

    /**
     * @brief Setter para ativo
     * @param ativo Se está ativa
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    /**
     * @brief Getter para incide INSS em String
     * @return "Sim" se incide INSS, "Não" caso contrário
     */
    public String getIncideINSSStr() {
        return incideINSS ? "Sim" : "Não";
    }

    /**
     * @brief Getter para incide FGTS em String
     * @return "Sim" se incide FGTS, "Não" caso contrário
     */
    public String getIncideFGTSStr() {
        return incideFGTS ? "Sim" : "Não";
    }

    /**
     * @brief Getter para incide IRRF em String
     * @return "Sim" se incide IRRF, "Não" caso contrário
     */
    public String getIncideIRRFStr() {
        return incideIRRF ? "Sim" : "Não";
    }

    /**
     * @brief Getter para o tipo em String
     * @return "Padrão" se for padrão, "Personalizada" caso contrário
     */
    public String getTipoLabel() {
        return padrao ? "Padrão" : "Personalizada";
    }

    /**
     * @brief Método toString
     * @return String formatada com o código e a descrição
     */
    @Override
    public String toString() {
        return codigo + " - " + descricao;
    }
}
