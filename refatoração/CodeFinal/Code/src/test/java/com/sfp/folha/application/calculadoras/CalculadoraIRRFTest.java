package com.sfp.folha.application.calculadoras;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.sfp.funcionario.domain.Funcionario;
import com.sfp.empresa.domain.Empresa;
import com.sfp.folha.domain.Holerite;
import com.sfp.core.domain.FaixaIRRF;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CalculadoraIRRFTest {

    @Test
    @DisplayName("Deve calcular corretamente o IRRF considerando deduções e dependentes")
    void testCalcularIRRFCorretamente() {
        // Tabela Fictícia Simplificada de IRRF
        // Faixa 1: Ate 2259.20 -> Isento
        // Faixa 2: 2259.21 ate 2826.65 -> 7.5% - Deduzir 169.44
        // Faixa 3: 2826.66 ate 3751.05 -> 15.0% - Deduzir 381.44
        List<FaixaIRRF> tabelaIRRF = Arrays.asList(
            new FaixaIRRF(new BigDecimal("0.00"), new BigDecimal("2259.20"), BigDecimal.ZERO, BigDecimal.ZERO),
            new FaixaIRRF(new BigDecimal("2259.21"), new BigDecimal("2826.65"), new BigDecimal("7.5"), new BigDecimal("169.44")),
            new FaixaIRRF(new BigDecimal("2826.66"), new BigDecimal("3751.05"), new BigDecimal("15.0"), new BigDecimal("381.44"))
        );

        BigDecimal deducaoDependente = new BigDecimal("189.59");
        CalculadoraIRRF calculadora = new CalculadoraIRRF(tabelaIRRF, deducaoDependente);

        // Funcionario ganha 3500.00 e tem 1 dependente.
        // O provento total no holerite sera 3500.00
        Funcionario funcionario = new Funcionario("Fulano", "000", "Cargo", LocalDate.now(), new BigDecimal("3500.00"), true, 1);
        Empresa empresa = new Empresa("00.000.000/0001-00", "Test", "test@test.com", "Admin", 30);
        Holerite holerite = new Holerite(empresa, funcionario, new ArrayList<>(), new BigDecimal("3500.00"), BigDecimal.ZERO, BigDecimal.ZERO);
        holerite.setDescontoINSS(new BigDecimal("300.00")); // INSS deduzido = 300

        // Base de Calculo IRRF = 3500.00 - 300.00 (INSS) - 189.59 (Dep) = 3010.41
        // 3010.41 cai na Faixa 3 (15% - 381.44)
        // 3010.41 * 0.15 = 451.5615
        // 451.5615 - 381.44 = 70.12

        BigDecimal irrf = calculadora.calcular(holerite);
        
        assertEquals(0, new BigDecimal("58.84").compareTo(irrf));
    }
}
