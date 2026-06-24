package com.sfp.core.domain;

import java.util.List;

/**
 * @brief Interface responsável por definir as operações de banco de dados para
 *        a
 *        classe FaixaINSS.
 */

public interface FaixaINSSRepository {
    /**
     * @brief: Salva uma faixa de INSS
     * @param: FaixaINSS faixa
     */
    void salvar(FaixaINSS faixa);

    /**
     * @brief: Busca todas as faixas de INSS
     * @return: List<FaixaINSS> - Lista de faixas de INSS
     */
    List<FaixaINSS> buscarTodas();

    /**
     * @brief: Atualiza uma faixa de INSS
     * @param: FaixaINSS antiga - Faixa de INSS antiga
     * @param: FaixaINSS nova - Faixa de INSS nova
     */
    void atualizar(FaixaINSS antiga, FaixaINSS nova);
}
