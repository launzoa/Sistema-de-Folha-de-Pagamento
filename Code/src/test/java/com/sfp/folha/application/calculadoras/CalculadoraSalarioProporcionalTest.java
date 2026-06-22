package com.sfp.folha.application.calculadoras;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.sfp.funcionario.domain.Funcionario;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.DisplayName;

public class CalculadoraSalarioProporcionalTest {

        @ParameterizedTest
        @CsvSource({
                        "1518.00, 22, 22, 1518.00", // Cenário 1: Trabalhou todos os dias
                        "1518.00, 22, 10, 690.00", // Cenário 2: Trabalhou 10 dias
                        "1518.00, 22, 0, 0.00", // Cenário 3: Não trabalhou nenhum dia
                        "3000.00, 20, 5,  750.00" // Cenário 4: Outro salário, outros dias
        })
        @DisplayName("Deve retornar salário proporcional quando trabalhou 'n' dias úteis.")
        public void testCalcularSalarioProporcionalCorretamente(String salarioBaseStr, int diasUteis,
                        int diasTrabalhados,
                        String resultadoEsperado) {
                CalculadoraSalarioProporcional calculadora = new CalculadoraSalarioProporcional();
                Funcionario funcionario = new Funcionario(new BigDecimal(salarioBaseStr));

                BigDecimal resultado = calculadora.calcular(funcionario, diasUteis, diasTrabalhados);
                assertEquals(new BigDecimal(resultadoEsperado), resultado,
                                "O salário proporcional do funcionário deveria ser " + resultadoEsperado);
        }
}
