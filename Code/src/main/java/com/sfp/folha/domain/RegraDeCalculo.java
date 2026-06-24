package com.sfp.folha.domain;

import java.math.BigDecimal;

/**
 * @brief Interface que representa uma regra de cálculo
 */

public interface RegraDeCalculo {
    /**
     * @brief Calcula o valor da rubrica
     * @param holerite Objeto do tipo Holerite
     * @return BigDecimal Valor calculado
     */
    BigDecimal calcular(Holerite holerite);
}
