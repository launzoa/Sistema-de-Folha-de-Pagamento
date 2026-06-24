package com.sfp.folha.application.calculadoras;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.sfp.funcionario.domain.Funcionario;
import com.sfp.empresa.domain.Empresa;
import com.sfp.folha.domain.Holerite;
import com.sfp.folha.domain.Lancamento;
import com.sfp.rubrica.domain.Rubrica;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import com.sfp.folha.domain.FolhaMes;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.DisplayName;

public class CalculadoraSalarioProporcionalTest {

        @ParameterizedTest
        @CsvSource({
                        "1518.00, 22, 22, 1518.00", // Cenário 1: Trabalhou todos os dias
                        "1518.00, 22, 10, 1518.00", // Cenário 2: Retorna integral pois o abate é no Processador
                        "1518.00, 22, 0, 1518.00", // Cenário 3: Não trabalhou nenhum dia
                        "3000.00, 20, 5,  3000.00" // Cenário 4: Outro salário, outros dias
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
                    lancamentos.add(new Lancamento(1, 1, funcionario.getCpf(), 102, faltas, null, BigDecimal.ZERO, "Quantidade", null, null, null));
                }
                Empresa empresa = new Empresa("00.000.000/0001-00", "Test", "test@test.com", "Admin", 30);
                Holerite holerite = new Holerite(empresa, funcionario, lancamentos, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
                holerite.setQuantidadeDiasUteis(diasUteis);

                BigDecimal resultado = calculadora.calcular(holerite);
                assertEquals(0, new BigDecimal(resultadoEsperado).compareTo(resultado),
                                "O salário proporcional do funcionário deveria ser " + resultadoEsperado);
        }

        @Test
        @DisplayName("Deve retornar salário pro-rata se admitido no meio do mês")
        public void testCalcularSalarioProporcionalMeioDoMes() {
                CalculadoraSalarioProporcional calculadora = new CalculadoraSalarioProporcional();
                // Admitido no dia 16/06/2026
                LocalDate admissao = LocalDate.of(2026, 6, 16);
                Funcionario funcionario = new Funcionario("Teste", "000", "Cargo", admissao, new BigDecimal("3000.00"), true, 1);
                
                Empresa empresa = new Empresa("00", "Test", "test@test.com", "Admin", 30);
                Holerite holerite = new Holerite(empresa, funcionario, new ArrayList<>(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
                
                // Folha começa em 01/06/2026 e vai até 30/06/2026
                FolhaMes folha = new FolhaMes(1, "06/2026", 22, "Aberta", LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 30));
                holerite.setFolhaAtual(folha);

                // Dias trabalhados: 30 - 16 + 1 = 15 dias.
                // 3000 / 30 = 100 por dia. 100 * 15 = 1500.00
                BigDecimal resultado = calculadora.calcular(holerite);
                assertEquals(0, new BigDecimal("1500.00").compareTo(resultado),
                                "O salário pro-rata deveria ser 1500.00");
        }
}
