package com.sfp.folha.application.calculadoras;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.sfp.funcionario.domain.Funcionario;
import com.sfp.folha.domain.Holerite;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import com.sfp.empresa.domain.Empresa;

public class CalculadoraFGTSTest {

    @Test
    @DisplayName("Deve calcular o FGTS como 8% sobre a base de cálculo de proventos")
    public void testCalcularFGTSCorretamente() {
        CalculadoraFGTS calculadora = new CalculadoraFGTS();
        Funcionario funcionario = new Funcionario("Fulano", "000", "Cargo", LocalDate.now(), new BigDecimal("3000.00"), true, 1);
        
        // Simula um holerite que tem 3000.00 de proventos (que é a base do FGTS)
        Empresa empresa = new Empresa("00.000.000/0001-00", "Test", "test@test.com", "Admin", 30);
        Holerite holerite = new Holerite(empresa, funcionario, new ArrayList<>(), new BigDecimal("3000.00"), BigDecimal.ZERO, BigDecimal.ZERO);
        
        BigDecimal fgts = calculadora.calcular(holerite);
        
        // 8% de 3000 = 240
        assertEquals(new BigDecimal("240.00"), fgts);
    }
}
