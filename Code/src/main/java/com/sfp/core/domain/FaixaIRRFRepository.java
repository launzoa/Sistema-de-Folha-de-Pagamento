/**
 * @brief Interface que representa o repositório de faixas de IRRF
 */

package com.sfp.core.domain;

import java.util.List;

public interface FaixaIRRFRepository {
    /**
     * @brief Método que busca todas as faixas de IRRF
     * @return List<FaixaIRRF>: lista de faixas de IRRF
     */
    List<FaixaIRRF> buscarTodas();

    /**
     * @brief Método que atualiza uma faixa de IRRF
     * @param antiga Faixa de IRRF antiga
     * @param nova   Faixa de IRRF nova
     */
    void atualizar(FaixaIRRF antiga, FaixaIRRF nova);
}
