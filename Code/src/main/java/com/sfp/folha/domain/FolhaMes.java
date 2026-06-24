package com.sfp.folha.domain;

import java.time.LocalDate;

/**
 * @brief Classe que representa uma folha de pagamento para um mês específico
 */
public class FolhaMes {
    private int id;
    private String competencia; // Ex: "06/2026"
    private int diasUteis;
    private String status; // "Aberta" ou "Fechada"
    private LocalDate dataInicio;
    private LocalDate dataFim;

    /**
     * @brief Constructor da classe FolhaMes
     * @param id          ID da folha
     * @param competencia Competência da folha
     * @param diasUteis   Número de dias úteis da folha
     * @param status      Status da folha
     */
    public FolhaMes(int id, String competencia, int diasUteis, String status, LocalDate dataInicio,
            LocalDate dataFim) {
        this.id = id;
        this.competencia = competencia;
        this.diasUteis = diasUteis;
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    /**
     * @brief Pega o ID da folha
     * @return int
     */
    public int getId() {
        return this.id;
    }

    /**
     * @brief Seta o ID da folha
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @brief Pega a competência da folha
     * @return String
     */
    public String getCompetencia() {
        return this.competencia;
    }

    /**
     * @brief Seta a competência da folha
     * @param competencia
     */
    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    /**
     * @brief Pega o número de dias úteis da folha
     * @return int
     */
    public int getDiasUteis() {
        return this.diasUteis;
    }

    /**
     * @brief Seta o número de dias úteis da folha
     * @param diasUteis
     */
    public void setDiasUteis(int diasUteis) {
        this.diasUteis = diasUteis;
    }

    /**
     * @brief Pega o status da folha
     * @return String
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * @brief Seta o status da folha
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @brief Pega a data de início da folha
     * @return LocalDate
     */
    public LocalDate getDataInicio() {
        return dataInicio;
    }

    /**
     * @brief Seta a data de início da folha
     * @param dataInicio
     */
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @brief Pega a data de início da folha
     * @return LocalDate
     */
    public LocalDate getDataFim() {
        return dataFim;
    }

    /**
     * @brief Seta a data de início da folha
     * @param dataFim
     */
    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}