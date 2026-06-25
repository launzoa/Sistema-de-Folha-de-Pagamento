package com.sfp.folha.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import com.sfp.funcionario.domain.Funcionario;
import com.sfp.empresa.domain.Empresa;
import com.sfp.rubrica.domain.Rubrica;
import com.sfp.rubrica.domain.RubricaRepository;
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

class ProcessadorDeFolhaTest {

    // Mock/Fake Repository para isolar as dependências do banco
    class FakeRubricaRepository implements RubricaRepository {
        private List<Rubrica> rubricas = new ArrayList<>();

        void add(Rubrica r) { rubricas.add(r); }

        @Override public void cadastrarRubrica(Rubrica r) {}
        @Override public void editarRubrica(Rubrica r) {}
        @Override public void excluirRubrica(int codigo) {}
        @Override public List<Rubrica> listarAtivas() { return rubricas; }
        @Override public List<Rubrica> buscarTodas() { return rubricas; }

        @Override
        public Rubrica buscarPorCodigo(int codigo) {
            return rubricas.stream().filter(r -> r.getCodigo() == codigo).findFirst().orElse(null);
        }
    }

    private ProcessadorDeFolha criarProcessadorComRepo(FakeRubricaRepository repo) {
        RegraDeCalculo proporcao = new CalculadoraSalarioProporcional();
        List<FaixaINSS> tabelaVigente = Arrays.asList(
                new FaixaINSS(new BigDecimal("0.00"), new BigDecimal("1412.00"), new BigDecimal("7.5"), new BigDecimal("0.00")),
                new FaixaINSS(new BigDecimal("1412.01"), new BigDecimal("2666.68"), new BigDecimal("9.0"), new BigDecimal("21.18")));
        RegraDeCalculo inss = new CalculadoraINSS(tabelaVigente, new BigDecimal("908.86"));

        ProcessadorDeFolha processador = new ProcessadorDeFolha(Arrays.asList(proporcao), Arrays.asList(inss), new ArrayList<>());
        processador.setRubricaRepository(repo);
        return processador;
    }

    private Funcionario criarFuncionario(double salario) {
        return new Funcionario("João Silva", "111.111.111-11", "Dev", LocalDate.now(), new BigDecimal(salario), true, 1);
    }

    private ProcessadorDeFolha processadorPadrao;

    @BeforeEach
    void setup() {
        this.processadorPadrao = criarProcessadorComRepo(new FakeRubricaRepository());
    }

    @Test
    @DisplayName("Deve orquestrar o cálculo da folha processando Proventos e Descontos e gerando o Holerite")
    void testProcessarFolhaCorretamente() {
        Funcionario funcionario = criarFuncionario(2000.00);
        List<Lancamento> lancamentos = new ArrayList<>();
        Empresa empresa = new Empresa("00.000.000/0001-00", "Test", "test@test.com", "Admin", 30);
        
        Holerite holerite = this.processadorPadrao.processar(empresa, funcionario, lancamentos, 22);

        assertEquals(0, new BigDecimal("2000.00").compareTo(holerite.getTotalProventos()), "Esperado: 2000.00, Recebido: " + holerite.getTotalProventos());
        assertEquals(0, new BigDecimal("158.82").compareTo(holerite.getTotalDescontos()), "Esperado: 158.82, Recebido: " + holerite.getTotalDescontos());
        assertEquals(0, new BigDecimal("1841.18").compareTo(holerite.getTotalProventos().subtract(holerite.getTotalDescontos())));
    }

    @Test
    @DisplayName("Deve calcular corretamente Hora Extra 50% em quantidade de horas")
    void testeHoraExtra50Porcento() {
        FakeRubricaRepository repo = new FakeRubricaRepository();
        repo.add(new Rubrica(10, "Hora Extra 50%", "Provento", "Variável", true, true, true, false, true));
        ProcessadorDeFolha proc = criarProcessadorComRepo(repo);
        Funcionario func = criarFuncionario(2200.0); // 10 reais por hora

        List<Lancamento> lancamentos = new ArrayList<>();
        // 10 horas extras. Multiplicador 1.5. Rate = 10. Total = 10 * 10 * 1.5 = 150
        lancamentos.add(new Lancamento(1, 1, "123", 10, 10.0, LocalDate.now(), null, "Quantidade", null, null, null));

        Holerite holerite = proc.processar(new Empresa("00.000.000/0001-00", "Test", "t@t.com", "Admin", 30), func, lancamentos, 22);

        // Base hour rate = 2200 / 176 = 12.50
        // Hora Extra 50% = 12.50 * 1.5 = 18.75. 10 hours = 187.50
        // Total Proventos = 2200 + 187.50 = 2387.50
        assertEquals(0, new BigDecimal("2387.50").compareTo(holerite.getTotalProventos()));
        assertEquals(0, new BigDecimal("187.50").compareTo(lancamentos.get(0).getValor()));
    }

    @Test
    @DisplayName("Deve calcular corretamente Atraso de Horas (Desconto em horas)")
    void testeAtrasoHoras() {
        FakeRubricaRepository repo = new FakeRubricaRepository();
        repo.add(new Rubrica(11, "Atraso de horas", "Desconto", "Variável", true, true, true, false, true));
        ProcessadorDeFolha proc = criarProcessadorComRepo(repo);
        Funcionario func = criarFuncionario(2200.0); // 10 reais por hora

        List<Lancamento> lancamentos = new ArrayList<>();
        // 5 horas de atraso. Total = 5 * 10 = 50 de desconto
        lancamentos.add(new Lancamento(2, 1, "123", 11, 5.0, LocalDate.now(), null, "Quantidade", null, null, null));

        Holerite holerite = proc.processar(new Empresa("00.000.000/0001-00", "Test", "t@t.com", "Admin", 30), func, lancamentos, 22);

        // Desconto esperado = INSS de 2200 (176.82) + Atraso (5 * 12.50 = 62.50) = 239.32
        assertEquals(0, new BigDecimal("239.32").compareTo(holerite.getTotalDescontos()));
    }

    @Test
    @DisplayName("Deve limitar o desconto do Vale Transporte a 6% do salário base")
    void testeValeTransporteTeto() {
        FakeRubricaRepository repo = new FakeRubricaRepository();
        repo.add(new Rubrica(901, "Vale Transporte (VT)", "Desconto", "Fixo", false, false, false, true, true));
        ProcessadorDeFolha proc = criarProcessadorComRepo(repo);
        Funcionario func = criarFuncionario(3000.0); // 6% = 180.00

        List<Lancamento> lancamentos = new ArrayList<>();
        // Tentando descontar um valor absoluto absurdo de R$ 500,00
        lancamentos.add(new Lancamento(1, 1, "123", 901, 0.0, LocalDate.now(), new BigDecimal("500.00"), "Valor", null, null, null));

        proc.processar(new Empresa("00.000.000/0001-00", "Test", "t@t.com", "Admin", 30), func, lancamentos, 22);

        // O valor salvo no lançamento deve ter sido capado em R$ 180,00
        assertEquals(0, new BigDecimal("180.00").compareTo(lancamentos.get(0).getValor()));
    }

    @Test
    @DisplayName("Deve limitar o desconto do PAT a 20% do salário base")
    void testeValeAlimentacaoTeto() {
        FakeRubricaRepository repo = new FakeRubricaRepository();
        repo.add(new Rubrica(902, "Vale Alimentação (PAT)", "Desconto", "Fixo", false, false, false, true, true));
        ProcessadorDeFolha proc = criarProcessadorComRepo(repo);
        Funcionario func = criarFuncionario(2000.0); // 20% = 400.00

        List<Lancamento> lancamentos = new ArrayList<>();
        // Tentando descontar 50% do salário base via porcentagem (R$ 1000)
        lancamentos.add(new Lancamento(1, 1, "123", 902, 0.0, LocalDate.now(), new BigDecimal("50.00"), "Porcentagem", "Salário Bruto", null, null));

        proc.processar(new Empresa("00.000.000/0001-00", "Test", "t@t.com", "Admin", 30), func, lancamentos, 22);

        // O valor salvo no lançamento deve ter sido capado em R$ 400,00
        assertEquals(0, new BigDecimal("400.00").compareTo(lancamentos.get(0).getValor()));
    }
}
