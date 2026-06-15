package com.sfp.folha.domain;

import com.sfp.core.domain.Funcionario;
import java.math.BigDecimal;

public interface RegraDeCalculo {
    BigDecimal calcular(Funcionario funcionario, int diasUteis, int diasTrabalhados);
}
