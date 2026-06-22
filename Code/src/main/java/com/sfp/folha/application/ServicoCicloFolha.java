package com.sfp.folha.application;

import com.sfp.folha.domain.FolhaMes;
import com.sfp.folha.domain.FolhaMesRepository;
import com.sfp.folha.domain.Lancamento;
import com.sfp.folha.domain.LancamentoRepository;
import com.sfp.folha.infrastructure.persistence.MySQLFolhaMesRepository;
import com.sfp.folha.infrastructure.persistence.MySQLLancamentoRepository;
import com.sfp.funcionario.domain.Funcionario;
import com.sfp.funcionario.domain.FuncionarioRepository;
import com.sfp.funcionario.infrastructure.persistence.MySQLFuncionarioRepository;
import com.sfp.empresa.domain.Empresa;
import com.sfp.empresa.application.ControladorEmpresa;
import com.sfp.auditoria.application.ServicoAuditoria;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class ServicoCicloFolha {

    private FolhaMesRepository folhaMesRepository = new MySQLFolhaMesRepository();
    private LancamentoRepository lancamentoRepository = new MySQLLancamentoRepository();
    private FuncionarioRepository funcionarioRepository = new MySQLFuncionarioRepository();
    private ControladorEmpresa controladorEmpresa = new ControladorEmpresa();

    /**
     * @brief Cria automaticamente uma nova folha de pagamento para o mês atual se não houver nenhuma aberta
     */
    public void abrirFolhaAutomatica() {
        try {
            FolhaMes folhaAberta = folhaMesRepository.buscarFolhaAberta();
            if (folhaAberta != null) return; // Já existe folha aberta

            Empresa empresa = controladorEmpresa.buscarEmpresa();
            int diaFechamento = (empresa != null && empresa.getDiaFechamentoPonto() > 0) ? empresa.getDiaFechamentoPonto() : 30;

            LocalDate hoje = LocalDate.now();
            YearMonth mesAtual = YearMonth.now();
            
            LocalDate dataInicioCalculada;
            LocalDate dataFimCalculada;
            
            if (diaFechamento == 30 || diaFechamento == 31) {
                dataInicioCalculada = mesAtual.atDay(1);
                dataFimCalculada = mesAtual.atEndOfMonth();
            } else {
                YearMonth mesAnterior = mesAtual.minusMonths(1);
                dataInicioCalculada = mesAnterior.atDay(diaFechamento + 1);
                dataFimCalculada = mesAtual.atDay(diaFechamento);
            }

            String competenciaCalculada = String.format("%02d/%d", mesAtual.getMonthValue(), mesAtual.getYear());
            
            FolhaMes folhaDuplicada = folhaMesRepository.buscarPorCompetencia(competenciaCalculada);
            if (folhaDuplicada != null) return; // Folha desse mês já foi criada e fechada

            int diasUteis = 22; // Valor padrão
            FolhaMes novaFolha = new FolhaMes(0, competenciaCalculada, diasUteis, "Aberta", dataInicioCalculada, dataFimCalculada);
            folhaMesRepository.salvar(novaFolha);
            ServicoAuditoria.registrar("Cadastro", "Folha de Pagamento", "Competência: " + competenciaCalculada);

            FolhaMes folhaCriada = folhaMesRepository.buscarFolhaAberta();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao abrir folha automática", e);
        }
    }

    /**
     * @brief Fecha a folha de pagamento atual
     * @param folhaAtual A folha de pagamento a ser fechada
     */
    public void fecharFolha(FolhaMes folhaAtual) {
        if (folhaAtual != null && !folhaAtual.getStatus().equals("Fechada")) {
            folhaMesRepository.atualizarStatus(folhaAtual.getId(), "Fechada");
            folhaAtual.setStatus("Fechada");
            ServicoAuditoria.registrar("Edição", "Folha de Pagamento", "Status: Fechada, Competência: " + folhaAtual.getCompetencia());
        }
    }

    /**
     * @brief Exclui todas as folhas e os lançamentos para zerar o banco de dados
     */
    public void resetarFolhas() {
        try {
            folhaMesRepository.excluirTodasFolhas();
            ServicoAuditoria.registrar("Exclusão", "Folha de Pagamento", "Zerar Banco de Dados de Folhas");
            abrirFolhaAutomatica();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao resetar folhas: " + e.getMessage(), e);
        }
    }

    /**
     * @brief Obtém as folhas cadastradas e a atual
     * @return Retorna a lista de todas as folhas, garantindo que a ordem está correta
     */
    public List<FolhaMes> buscarTodasFolhas() {
        return folhaMesRepository.buscarTodas();
    }

    /**
     * @brief Busca a folha aberta
     * @return Folha aberta
     */
    public FolhaMes buscarFolhaAberta() {
        return folhaMesRepository.buscarFolhaAberta();
    }
}
