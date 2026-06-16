package com.sfp.folha.domain;

import com.sfp.folha.domain.Holerite;

import java.math.BigDecimal;

public interface RegraDeCalculo {
    BigDecimal calcular(Holerite holerite);
}
