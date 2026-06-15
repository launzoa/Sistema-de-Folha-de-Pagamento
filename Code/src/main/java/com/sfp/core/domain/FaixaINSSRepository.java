package com.sfp.core.domain;

import com.sfp.folha.domain.FaixaINSS;

import java.util.List;

public interface FaixaINSSRepository {
    // @brief: Salva uma faixa de INSS
    // @param: FaixaINSS faixa
    void salvar(FaixaINSS faixa);

    // @brief: Busca todas as faixas de INSS
    // @return: List<FaixaINSS> - Lista de faixas de INSS
    List<FaixaINSS> buscarTodas();

}
