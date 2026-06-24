package com.sfp.folha.domain;

import java.util.List;

/**
 * @brief Interface que define as operações de persistência para FolhaMes
 */
public interface FolhaMesRepository {
    /**
     * @brief Salva uma folha de pagamento
     * @param folha Folha de pagamento a ser salva
     */
    void salvar(FolhaMes folha);

    /**
     * @brief Busca uma folha de pagamento por competência
     * @param competencia Competência da folha
     * @return FolhaMes
     */
    FolhaMes buscarPorCompetencia(String competencia);

    /**
     * @brief Busca todas as folhas de pagamento
     * @return List<FolhaMes>
     */
    List<FolhaMes> buscarTodas();

    /**
     * @brief Busca a folha de pagamento que está com status 'Aberta'
     * @return FolhaMes ou null se não houver nenhuma
     */
    FolhaMes buscarFolhaAberta();

    /**
     * @brief Busca as folhas que estão com status 'Aberta' ou 'Em Transição'
     * @return List<FolhaMes> ativas
     */
    List<FolhaMes> buscarFolhasAtivas();

    /**
     * @brief Atualiza o status de uma folha de pagamento
     * @param id         ID da folha de pagamento
     * @param novoStatus Novo status da folha de pagamento
     */
    void atualizarStatus(int id, String novoStatus);

    /**
     * @brief Atualiza as datas de início e fim da folha
     * @param id     ID da folha
     * @param inicio Nova data de início
     * @param fim    Nova data de fim
     */
    void atualizarDatas(int id, java.time.LocalDate inicio, java.time.LocalDate fim);

    /**
     * @brief Exclui todas as folhas de pagamento
     */
    void excluirTodasFolhas();
}