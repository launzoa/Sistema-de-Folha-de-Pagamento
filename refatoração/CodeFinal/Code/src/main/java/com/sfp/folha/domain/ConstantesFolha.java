package com.sfp.folha.domain;

import java.math.BigDecimal;

/**
 * @brief Classe responsável por armazenar as constantes da folha de pagamento.
 */
public class ConstantesFolha {
    private ConstantesFolha() {
    }

    // Teto de desconto legal para o INSS
    public static final BigDecimal TETO_INSS = new BigDecimal("908.85");
    // Teto de desconto legal para o IRRF
    public static final BigDecimal TETO_IRRF = new BigDecimal("999999.99");
    // Piso do Salário Mínimo Vigente (2026)
    public static final BigDecimal SALARIO_MINIMO = new BigDecimal("1621.00");

    // Constantes parametrizáveis padrão da CLT
    public static final BigDecimal ALIQUOTA_FGTS = new BigDecimal("0.08");
    public static final double TETO_VR = 500.00;
    public static final int DIAS_UTEIS_PADRAO = 22;
    public static final int HORAS_MES_PADRAO = 176;
}
