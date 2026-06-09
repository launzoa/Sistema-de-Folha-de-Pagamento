package com.sfp.folha.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.math.BigDecimal;

import com.sfp.folha.application.ProcessadorDeFolha;
import com.sfp.folha.application.ProcessadorDeFolha;
import com.sfp.folha.application.calculadoras.CalculadoraHoraExtra;
import com.sfp.folha.application.calculadoras.CalculadoraSalarioProporcional;
import com.sfp.folha.application.calculadoras.CalculadoraINSS;

import com.sfp.folha.domain.FaixaINSS;
import com.sfp.folha.domain.Holerite;

import com.sfp.core.domain.Funcionario;

import java.util.Arrays;
import java.util.List;

public class MainController {

        // A anotacao @FXML liga a variavel Java ao componente do arquivo FXML pelo
        // "fx:id"
        @FXML
        private TextField inputSalario;

        @FXML
        private Label labelProventos;

        @FXML
        private Label labelDescontos;

        @FXML
        private Label labelLiquido;

        @FXML
        private TextField inputHoras50;

        @FXML
        private TextField inputHoras100;

        // Metodo que sera disparado ao clicar no botao (onAction no FXML)
        // @brief: Calcula a folha de pagamento
        // @return void
        @FXML
        public void calcularFolha() {
                String salarioStr = inputSalario.getText();
                BigDecimal salarioBase = new BigDecimal(salarioStr);

                String horas50Str = inputHoras50.getText();
                if (horas50Str.isEmpty()) {
                        horas50Str = "0";
                }

                String horas100Str = inputHoras100.getText();
                if (horas100Str.isEmpty()) {
                        horas100Str = "0";
                }

                Funcionario funcionario = new Funcionario(salarioBase);

                funcionario.getPonto().setHorasExtras50(Double.parseDouble(horas50Str));
                funcionario.getPonto().setHorasExtras100(Double.parseDouble(horas100Str));

                FaixaINSS faixa1 = new FaixaINSS(new BigDecimal("0.00"), new BigDecimal("1621.00"),
                                new BigDecimal("0.075"),
                                new BigDecimal("0.00"));
                FaixaINSS faixa2 = new FaixaINSS(new BigDecimal("1621.01"), new BigDecimal("2902.84"),
                                new BigDecimal("0.09"),
                                new BigDecimal("24.32"));
                FaixaINSS faixa3 = new FaixaINSS(new BigDecimal("2902.85"), new BigDecimal("4354.27"),
                                new BigDecimal("0.12"),
                                new BigDecimal("111.40"));
                FaixaINSS faixa4 = new FaixaINSS(new BigDecimal("4354.28"), new BigDecimal("8475.55"),
                                new BigDecimal("0.14"),
                                new BigDecimal("198.49"));
                BigDecimal tetoMaximo = new BigDecimal("988.09");
                CalculadoraINSS calcINSS = new CalculadoraINSS(Arrays.asList(faixa1, faixa2, faixa3, faixa4),
                                tetoMaximo);

                BigDecimal percentual = new BigDecimal("0.5");
                BigDecimal percentual100 = new BigDecimal("1.0");
                BigDecimal divisor = new BigDecimal("220"); // CLT padrao e 220
                CalculadoraHoraExtra calcHoraExtra50 = new CalculadoraHoraExtra(percentual, divisor, false);
                CalculadoraHoraExtra calcHoraExtra100 = new CalculadoraHoraExtra(percentual100, divisor, true);

                int diasUteis = 22;
                int diasTrabalhados = 22;
                CalculadoraSalarioProporcional calcSalarioProporcional = new CalculadoraSalarioProporcional();

                ProcessadorDeFolha processador = new ProcessadorDeFolha(
                                Arrays.asList(calcHoraExtra50, calcHoraExtra100, calcSalarioProporcional),
                                Arrays.asList(calcINSS));

                Holerite holerite = processador.processar(funcionario, diasUteis, diasTrabalhados);
                System.out.println("Salario liquido calculado: " + holerite);

                labelProventos.setText("R$ " + holerite.getTotalProventos());
                labelDescontos.setText("R$ " + holerite.getTotalDescontos());
                labelLiquido.setText("R$ " + holerite.getSalarioLiquido());
        }
}
