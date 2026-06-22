package com.sfp.folha.application.calculadoras;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.sfp.funcionario.domain.Funcionario;
import com.sfp.folha.domain.Holerite;
import com.sfp.folha.domain.Lancamento;
import com.sfp.rubrica.domain.Rubrica;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

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
                Funcionario funcionario = new Funcionario("Teste", "000", "Cargo", LocalDate.now(), new BigDecimal(salarioBaseStr), true, 1);
                
                double faltas = diasUteis - diasTrabalhados;
                List<Lancamento> lancamentos = new ArrayList<>();
                if (faltas > 0) {
                    lancamentos.add(new Lancamento(1, 1, funcionario.getCpf(), 102, faltas, null, BigDecimal.ZERO, "Quantidade", null, null, null, null));
                }
                Holerite holerite = new Holerite(funcionario, lancamentos, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
                holerite.setQuantidadeDiasUteis(diasUteis);

                BigDecimal resultado = calculadora.calcular(holerite);
                assertEquals(0, new BigDecimal(resultadoEsperado).compareTo(resultado),
                                "O salário proporcional do funcionário deveria ser " + resultadoEsperado);
        }
}
