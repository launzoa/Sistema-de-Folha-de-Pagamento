package com.sfp.folha.application.calculadoras;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.sfp.funcionario.domain.FolhaDePonto;
import com.sfp.funcionario.domain.Funcionario;
import java.math.BigDecimal;

public class CalculadoraHoraExtraTest {

    private CalculadoraHoraExtra calculadora50;
    private CalculadoraHoraExtra calculadora100;

    @BeforeEach
    public void setup() {
        BigDecimal divisor = new BigDecimal("220");
        this.calculadora50 = new CalculadoraHoraExtra(new BigDecimal("0.50"), divisor, false);
        this.calculadora100 = new CalculadoraHoraExtra(new BigDecimal("1.00"), divisor, true);
    }

    @Test
    @DisplayName("Deve calcular horas extras a 50% corretamente")
    public void testCalcularHorasExtras50() {
        Funcionario funcionario = new Funcionario(new BigDecimal("2200.00"));
        FolhaDePonto ponto = funcionario.getPonto();
        ponto.setHorasExtras50(10);

        BigDecimal totalHorasExtras = this.calculadora50.calcular(funcionario, 22, 22);

        assertEquals(new BigDecimal("150.00"), totalHorasExtras, "O total esperado de horas extras era 150.00");
    }

    @Test
    @DisplayName("Deve retornar ZERO se o funcionário não tiver horas extras daquele tipo apontadas")
    public void testSemHorasExtras() {
        Funcionario funcionario = new Funcionario(new BigDecimal("2200.00"));
        FolhaDePonto ponto = funcionario.getPonto();
        ponto.setHorasExtras50(0);

        BigDecimal totalHorasExtras = this.calculadora50.calcular(funcionario, 22, 22);
        assertEquals(new BigDecimal("0.00"), totalHorasExtras, "O total esperado de horas extras era 0.00");
    }
}
