/**
 * @brief Classe responsável por processar os dados do dashboard.
 */
package com.sfp.core.application;

import java.math.BigDecimal;
import java.util.List;

import com.sfp.funcionario.domain.Funcionario;
import com.sfp.funcionario.domain.FuncionarioRepository;
import com.sfp.funcionario.infrastructure.persistence.MySQLFuncionarioRepository;
import com.sfp.folha.domain.FolhaMes;
import com.sfp.folha.domain.FolhaMesRepository;
import com.sfp.folha.infrastructure.persistence.MySQLFolhaMesRepository;
import com.sfp.folha.domain.Lancamento;
import com.sfp.folha.domain.LancamentoRepository;
import com.sfp.folha.infrastructure.persistence.MySQLLancamentoRepository;
import com.sfp.folha.domain.Holerite;
import com.sfp.folha.application.ProcessadorDeFolha;
import com.sfp.folha.application.calculadoras.*;
import com.sfp.folha.domain.ConstantesFolha;
import com.sfp.core.domain.FaixaINSSRepository;
import com.sfp.infrastructure.persistence.MySQLFaixaINSSRepository;
import com.sfp.core.domain.FaixaIRRFRepository;
import com.sfp.infrastructure.persistence.MySQLFaixaIRRFRepository;

public class ServicoDashboard {
    private FuncionarioRepository funcRepo = new MySQLFuncionarioRepository();
    private FolhaMesRepository folhaRepo = new MySQLFolhaMesRepository();
    private LancamentoRepository lancamentoRepo = new MySQLLancamentoRepository();
    private FaixaINSSRepository inssRepo = new MySQLFaixaINSSRepository();
    private FaixaIRRFRepository irrfRepo = new MySQLFaixaIRRFRepository();

    /**
     * @brief Método responsável por processar os dados do dashboard.
     */
    public DashboardDados processarDadosDashboard() {
        DashboardDados dados = new DashboardDados();
        // Lista todos os funcionários pega a quantidade cadastrada.
        List<Funcionario> funcionarios = funcRepo.buscarTodos();
        dados.setTotalFuncionarios(funcionarios.size());
        // Busca a folha atual.
        FolhaMes folhaAtual = folhaRepo.buscarFolhaAberta();
        // Se não existir folha atual, busca a última folha cadastrada.
        if (folhaAtual == null) {
            List<FolhaMes> todas = folhaRepo.buscarTodas();
            if (todas != null && !todas.isEmpty()) {
                folhaAtual = todas.get(todas.size() - 1);
            }
        }
        // Seta a folha atual no dashboard.
        dados.setFolhaAtual(folhaAtual);
        // Calcula o financeiro do dashboard.
        if (folhaAtual != null) {
            calcularFinanceiro(dados, folhaAtual, funcionarios);
        }
        // Retorna os dados do dashboard.
        return dados;
    }

    /**
     * @brief Método responsável por calcular o financeiro do dashboard.
     * @param dados        - Dados do dashboard.
     * @param folha        - Folha de pagamento.
     * @param funcionarios - Lista de funcionários.
     */
    private void calcularFinanceiro(DashboardDados dados, FolhaMes folha, List<Funcionario> funcionarios) {
        // Processador de folha.
        ProcessadorDeFolha processador = new ProcessadorDeFolha(
                java.util.Arrays.asList(new CalculadoraSalarioProporcional()),
                java.util.Arrays.asList(
                        new CalculadoraINSS(inssRepo.buscarTodas(), ConstantesFolha.TETO_INSS),
                        new CalculadoraIRRF(irrfRepo.buscarTodas(), ConstantesFolha.TETO_IRRF)),
                java.util.Arrays.asList(new CalculadoraFGTS()));

        // Variáveis para calcular o financeiro do dashboard.
        BigDecimal totalBruto = BigDecimal.ZERO;
        BigDecimal totalLiquido = BigDecimal.ZERO;
        BigDecimal totalImpostos = BigDecimal.ZERO; // INSS + IRRF
        BigDecimal totalFGTS = BigDecimal.ZERO;
        BigDecimal totalDescontosDiversos = BigDecimal.ZERO; // Faltas, VT, PAT, etc.

        // Itera sobre todos os funcionários e calcula o financeiro do dashboard.
        for (Funcionario f : funcionarios) {
            // Pega os lançamentos do funcionário na folha atual.
            List<Lancamento> lancamentos = lancamentoRepo.buscarPorFolhaEFuncionario(folha.getId(), f.getCpf());
            // Processa a folha do funcionário.
            Holerite h = processador.processar(f, lancamentos, folha.getDiasUteis());
            // Se a folha do funcionário foi processada com sucesso.
            if (h != null) {
                // Adiciona o total de proventos do funcionário ao total de proventos do
                // dashboard.
                totalBruto = totalBruto.add(h.getTotalProventos());
                // Adiciona o salário líquido do funcionário ao salário líquido do dashboard.
                totalLiquido = totalLiquido.add(h.getSalarioLiquido());
                // Pega os descontos do funcionário (INSS, IRRF) e o FGTS.
                BigDecimal inss = h.getDescontoINSS() != null ? h.getDescontoINSS() : BigDecimal.ZERO;
                BigDecimal irrf = h.getDescontoIRRF() != null ? h.getDescontoIRRF() : BigDecimal.ZERO;
                BigDecimal fgts = h.getValorFGTS() != null ? h.getValorFGTS() : BigDecimal.ZERO;
                // Adiciona os descontos ao total de descontos do dashboard.
                totalImpostos = totalImpostos.add(inss).add(irrf);
                totalFGTS = totalFGTS.add(fgts);
                // Pega os descontos diversos do funcionário.
                BigDecimal descontosTotais = h.getTotalDescontos();
                BigDecimal diversos = descontosTotais.subtract(inss).subtract(irrf);
                // Se os descontos diversos forem maiores que zero, adiciona ao total de
                // descontos diversos do dashboard.
                if (diversos.compareTo(BigDecimal.ZERO) > 0) {
                    totalDescontosDiversos = totalDescontosDiversos.add(diversos);
                }
            }
        }
        // Seta os valores calculados no dashboard.
        dados.setTotalLiquido(totalLiquido);
        dados.setTotalImpostos(totalImpostos);
        dados.setTotalDescontosDiversos(totalDescontosDiversos);
        dados.setCustoEmpresa(totalBruto.add(totalFGTS));
    }
}
