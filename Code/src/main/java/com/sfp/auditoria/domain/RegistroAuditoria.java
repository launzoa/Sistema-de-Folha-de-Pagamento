/**
 * @brief Classe que representa um registro de auditoria.
 */
package com.sfp.auditoria.domain;

import java.time.LocalDateTime;

public class RegistroAuditoria {
    private int id;
    private LocalDateTime data_hora;
    private String usuario;
    private String perfil;
    private String acao;
    private String entidade;
    private String detalhes;

    /**
     * @brief Construtor padrão.
     */
    public RegistroAuditoria() {
    }

    /**
     * @brief Obtém o ID do registro.
     * @return O ID do registro.
     */
    public int getId() {
        return id;
    }

    /**
     * @brief Define o ID do registro.
     * @param id O ID do registro.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @brief Obtém a data e hora do registro.
     * @return A data e hora do registro.
     */
    public LocalDateTime getDataHora() {
        return data_hora;
    }

    /**
     * @brief Obtém a data e hora do registro formatada.
     * @return A data e hora do registro formatada.
     */
    public String getDataHoraFormatada() {
        if (data_hora == null)
            return "";
        return data_hora.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    /**
     * @brief Define a data e hora do registro.
     * @param dataHora A data e hora do registro.
     */
    public void setDataHora(LocalDateTime dataHora) {
        this.data_hora = dataHora;
    }

    /**
     * @brief Obtém o usuário que realizou a ação.
     * @return O usuário que realizou a ação.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @brief Define o usuário que realizou a ação.
     * @param usuario O usuário que realizou a ação.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @brief Obtém o perfil do usuário que realizou a ação.
     * @return O perfil do usuário que realizou a ação.
     */
    public String getPerfil() {
        return perfil;
    }

    /**
     * @brief Define o perfil do usuário que realizou a ação.
     * @param perfil O perfil do usuário que realizou a ação.
     */
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    /**
     * @brief Obtém a ação realizada.
     * @return A ação realizada.
     */
    public String getAcao() {
        return acao;
    }

    /**
     * @brief Define a ação realizada.
     * @param acao A ação realizada.
     */
    public void setAcao(String acao) {
        this.acao = acao;
    }

    /**
     * @brief Obtém a entidade afetada.
     * @return A entidade afetada.
     */
    public String getEntidade() {
        return entidade;
    }

    /**
     * @brief Define a entidade afetada.
     * @param entidade A entidade afetada.
     */
    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    /**
     * @brief Obtém os detalhes da ação.
     * @return Os detalhes da ação.
     */
    public String getDetalhes() {
        return detalhes;
    }

    /**
     * @brief Define os detalhes da ação.
     * @param detalhes Os detalhes da ação.
     */
    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

}
