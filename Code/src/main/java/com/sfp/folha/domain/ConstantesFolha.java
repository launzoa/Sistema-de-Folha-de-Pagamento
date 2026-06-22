/**
 * @brief Classe responsável por armazenar as constantes da folha de pagamento.
 */
package com.sfp.folha.domain;

import java.math.BigDecimal;

public class ConstantesFolha {
    // Teto de desconto legal para o INSS
    public static final BigDecimal TETO_INSS = new BigDecimal("908.85");
    // Teto de desconto legal para o IRRF
    public static final BigDecimal TETO_IRRF = new BigDecimal("999999.99");

}
