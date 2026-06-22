package com.sfp.folha.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import com.sfp.funcionario.domain.Funcionario;
import com.sfp.folha.domain.Holerite;
import com.sfp.folha.domain.RegraDeCalculo;
import com.sfp.folha.application.calculadoras.CalculadoraSalarioProporcional;
import com.sfp.folha.application.calculadoras.CalculadoraINSS;
import com.sfp.core.domain.FaixaINSS;
import com.sfp.folha.domain.Lancamento;
import java.util.ArrayList;
import java.time.LocalDate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ProcessadorDeFolhaTest {

    private ProcessadorDeFolha processador;

    @BeforeEach
    public void setup() {
        RegraDeCalculo proporcao = new CalculadoraSalarioProporcional();

        List<FaixaINSS> tabelaVigente = Arrays.asList(
                new FaixaINSS(new BigDecimal("0.00"), new BigDecimal("1412.00"), new BigDecimal("7.5"),
                        new BigDecimal("0.00")),
                new FaixaINSS(new BigDecimal("1412.01"), new BigDecimal("2666.68"), new BigDecimal("9.0"),
                        new BigDecimal("21.18")));
        RegraDeCalculo inss = new CalculadoraINSS(tabelaVigente, new BigDecimal("908.86"));

        List<RegraDeCalculo> proventos = Arrays.asList(proporcao);
        List<RegraDeCalculo> descontos = Arrays.asList(inss);
        List<RegraDeCalculo> informativas = new ArrayList<>();

        this.processador = new ProcessadorDeFolha(proventos, descontos, informativas);
    }

    @Test
    @DisplayName("Deve orquestrar o cálculo da folha processando Proventos e Descontos e gerando o Holerite")
    public void testProcessarFolhaCorretamente() {
        Funcionario funcionario = new Funcionario("João Silva", "111.111.111-11", "Dev", LocalDate.now(), new BigDecimal("2000.00"), true, 1);
        List<Lancamento> lancamentos = new ArrayList<>();
        Holerite holerite = this.processador.processar(funcionario, lancamentos, 22);

        BigDecimal proventos = holerite.getTotalProventos();
        BigDecimal proventosEsperados = new BigDecimal("2000.02");

        assertEquals(0, proventosEsperados.compareTo(proventos), "Os proventos esperados eram " + proventosEsperados);

        BigDecimal descontos = holerite.getTotalDescontos();
        BigDecimal descontosEsperados = new BigDecimal("158.82");

        assertEquals(0, descontosEsperados.compareTo(descontos), "Os descontos esperados eram " + descontosEsperados);

        BigDecimal salarioLiquido = proventos.subtract(descontos);
        BigDecimal salarioLiquidoEsperado = new BigDecimal("1841.20");

        assertEquals(0, salarioLiquidoEsperado.compareTo(salarioLiquido),
                "O salário líquido esperado era " + salarioLiquidoEsperado);
    }
}
