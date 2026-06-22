package com.sfp.folha.application.calculadoras;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

import com.sfp.funcionario.domain.Funcionario;
import com.sfp.core.domain.FaixaINSS;
import com.sfp.folha.domain.Holerite;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class CalculadoraINSSTest {

        private CalculadoraINSS calculadora;

        @BeforeEach
        public void setup() {
                // Arrange: Nossa Tabela de INSS Fictícia (baseada em 2024/2025 com Parcela a
                // Deduzir)
                // Faixa 1: Até 1.412,00 (7,5% - Parcela 0,00)
                // Faixa 2: 1.412,01 a 2.666,68 (9% - Parcela 21,18)
                // Faixa 3: 2.666,69 a 4.000,03 (12% - Parcela 101,18)
                // Faixa 4: 4.000,04 a 7.786,02 (14% - Parcela 181,18)
                // Teto de desconto máximo (calculado): R$ 908,86

                List<FaixaINSS> tabelaVigente = Arrays.asList(
                                new FaixaINSS(new BigDecimal("0.00"), new BigDecimal("1412.00"),
                                                new BigDecimal("7.5"),
                                                new BigDecimal("0.00")),
                                new FaixaINSS(new BigDecimal("1412.01"), new BigDecimal("2666.68"),
                                                new BigDecimal("9.0"),
                                                new BigDecimal("21.18")),
                                new FaixaINSS(new BigDecimal("2666.69"), new BigDecimal("4000.03"),
                                                new BigDecimal("12.0"),
                                                new BigDecimal("101.18")),
                                new FaixaINSS(new BigDecimal("4000.04"), new BigDecimal("7786.02"),
                                                new BigDecimal("14.0"),
                                                new BigDecimal("181.18")));

                BigDecimal tetoMaximo = new BigDecimal("908.86");
                this.calculadora = new CalculadoraINSS(tabelaVigente, tetoMaximo);
        }

        @ParameterizedTest
        @CsvSource({
                        // Salário, Resultado INSS Esperado
                        "1412.00,  105.90", // 1ª Faixa cravada
                        "2000.00,  158.82", // 2ª Faixa (2000 * 9% - 21.18)
                        "3000.00,  258.82", // 3ª Faixa (3000 * 12% - 101.18)
                        "10000.00, 908.86" // Acima do teto (limita ao máximo legal)
        })
        @DisplayName("Deve calcular INSS progressivo aplicando a parcela a deduzir e o teto máximo")
        public void testCalcularINSSCorretamente(String salarioBrutoStr, String descontoEsperadoStr) {
                BigDecimal salarioBruto = new BigDecimal(salarioBrutoStr);
                Funcionario funcionario = new Funcionario("Teste", "000", "Cargo", LocalDate.now(), salarioBruto, true, 1);
                Holerite holerite = new Holerite(funcionario, new ArrayList<>(), salarioBruto, BigDecimal.ZERO, BigDecimal.ZERO);
                BigDecimal desconto = calculadora.calcular(holerite);
                BigDecimal descontoEsperado = new BigDecimal(descontoEsperadoStr);

                assertEquals(0, descontoEsperado.compareTo(desconto), "O desconto esperado era " + descontoEsperado + " mas foi " + desconto);

        }
}
