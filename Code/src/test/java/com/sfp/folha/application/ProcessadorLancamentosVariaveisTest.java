package com.sfp.folha.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.sfp.folha.domain.Holerite;
import com.sfp.folha.domain.Lancamento;
import com.sfp.funcionario.domain.Funcionario;
import com.sfp.rubrica.domain.Rubrica;
import com.sfp.rubrica.domain.RubricaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProcessadorLancamentosVariaveisTest {

    // Fake Repository de Rubrica
    class FakeRubricaRepository implements RubricaRepository {
        private List<Rubrica> rubricas = new ArrayList<>();

        public void add(Rubrica r) { rubricas.add(r); }

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

    private ProcessadorDeFolha criarProcessador(FakeRubricaRepository repo) {
        ProcessadorDeFolha processador = new ProcessadorDeFolha(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        processador.setRubricaRepository(repo);
        return processador;
    }

    private Funcionario criarFuncionario(double salario) {
        return new Funcionario("123", "Func Test", "Dev", LocalDate.now(), new BigDecimal(salario), true, 1);
    }

    @Test
    @DisplayName("Deve calcular corretamente Hora Extra 50% em quantidade de horas")
    void testeHoraExtra50Porcento() {
        FakeRubricaRepository repo = new FakeRubricaRepository();
        repo.add(new Rubrica(10, "Hora Extra 50%", "Provento", "Variável", true, true, true, false, true));
        
        ProcessadorDeFolha processador = criarProcessador(repo);
        Funcionario func = criarFuncionario(2200.0); // 10 reais por hora
        
        List<Lancamento> lancamentos = new ArrayList<>();
        // 10 horas extras. Multiplicador 1.5. Rate = 10. Total = 10 * 10 * 1.5 = 150
        lancamentos.add(new Lancamento(1, 1, "123", 10, 10.0, LocalDate.now(), null, "Quantidade", null, null, null, null));
        
        Holerite holerite = processador.processar(func, lancamentos, 22);
        
        assertEquals(new BigDecimal("150.00"), holerite.getTotalProventos());
        assertEquals(new BigDecimal("150.00"), lancamentos.get(0).getValor());
    }

    @Test
    @DisplayName("Deve calcular corretamente Atraso de Horas (Desconto em horas)")
    void testeAtrasoHoras() {
        FakeRubricaRepository repo = new FakeRubricaRepository();
        repo.add(new Rubrica(11, "Atraso de horas", "Desconto", "Variável", true, true, true, false, true));
        
        ProcessadorDeFolha processador = criarProcessador(repo);
        Funcionario func = criarFuncionario(2200.0); // 10 reais por hora
        
        List<Lancamento> lancamentos = new ArrayList<>();
        // 5 horas de atraso. Total = 5 * 10 = 50 de desconto
        lancamentos.add(new Lancamento(2, 1, "123", 11, 5.0, LocalDate.now(), null, "Quantidade", null, null, null, null));
        
        Holerite holerite = processador.processar(func, lancamentos, 22);
        
        assertEquals(new BigDecimal("50.00"), holerite.getTotalDescontos());
        assertEquals(new BigDecimal("0"), holerite.getSalarioLiquido()); // Fica negativo mas o Math max zera
    }

    @Test
    @DisplayName("Deve calcular corretamente Faltas (Dias de falta)")
    void testeFaltasDias() {
        FakeRubricaRepository repo = new FakeRubricaRepository();
        repo.add(new Rubrica(12, "Falta injustificada", "Desconto", "Variável", true, true, true, false, true));
        
        ProcessadorDeFolha processador = criarProcessador(repo);
        Funcionario func = criarFuncionario(3000.0); // 100 reais por dia
        
        List<Lancamento> lancamentos = new ArrayList<>();
        // 2 dias de falta. Total = 2 * 100 = 200 de desconto
        lancamentos.add(new Lancamento(3, 1, "123", 12, 2.0, LocalDate.now(), null, "Quantidade", null, null, null, null));
        
        Holerite holerite = processador.processar(func, lancamentos, 22);
        
        assertEquals(new BigDecimal("200.00"), holerite.getTotalDescontos());
    }

    @Test
    @DisplayName("Deve calcular corretamente Porcentagem sobre Salário Bruto")
    void testePorcentagemSalarioBruto() {
        FakeRubricaRepository repo = new FakeRubricaRepository();
        repo.add(new Rubrica(13, "Adicional de Periculosidade", "Provento", "Fixo", true, true, true, false, true));
        
        ProcessadorDeFolha processador = criarProcessador(repo);
        Funcionario func = criarFuncionario(2000.0);
        
        List<Lancamento> lancamentos = new ArrayList<>();
        // 30% do Salário Bruto (2000) = 600
        lancamentos.add(new Lancamento(4, 1, "123", 13, 0.0, LocalDate.now(), new BigDecimal("30.00"), "Porcentagem", "Salário Bruto", null, null, null));
        
        Holerite holerite = processador.processar(func, lancamentos, 22);
        
        assertEquals(new BigDecimal("600.00"), holerite.getTotalProventos());
    }
}
