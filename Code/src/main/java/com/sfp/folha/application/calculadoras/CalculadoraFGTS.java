package com.sfp.folha.application.calculadoras;

import com.sfp.folha.domain.Holerite;
import com.sfp.folha.domain.RegraDeCalculo;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @brief Implementação da regra de cálculo para o FGTS.
 *        Calcula 8% sobre a base de proventos do funcionário.
 *        O FGTS é uma regra informativa (não desconta do salário líquido).
 */
public class CalculadoraFGTS implements RegraDeCalculo {

    private static final BigDecimal ALIQUOTA_FGTS = new BigDecimal("0.08"); // 8%

    /**
     * @brief Executa o cálculo de FGTS.
     * @param holerite Holerite contendo os proventos processados.
     * @return BigDecimal O valor de FGTS a ser recolhido pela empresa.
     */
    @Override
    public BigDecimal calcular(Holerite holerite) {
        // Base do FGTS geralmente é a soma dos proventos (Salário Base + Horas Extras,
        // etc.)
        BigDecimal baseCalculo = holerite.getTotalProventos();

        // Calcula 8% sobre a base
        BigDecimal fgts = baseCalculo.multiply(ALIQUOTA_FGTS).setScale(2, RoundingMode.HALF_UP);

        // Atualiza o holerite para armazenar a informação
        holerite.setValorFGTS(fgts);

        return fgts;
    }
}
