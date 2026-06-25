package com.sfp.folha.application;

import com.sfp.folha.domain.FolhaMes;
import com.sfp.folha.domain.FolhaMesRepository;
import com.sfp.folha.infrastructure.persistence.MySQLFolhaMesRepository;
import com.sfp.empresa.domain.Empresa;
import com.sfp.empresa.application.ControladorEmpresa;
import com.sfp.auditoria.application.ServicoAuditoria;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

/**
 * @brief Classe responsável por gerenciar o ciclo da folha de pagamento,
 *        incluindo abertura, fechamento e exclusão.
 */
public class ServicoCicloFolha {

    private FolhaMesRepository folhaMesRepository = new MySQLFolhaMesRepository();
    private ControladorEmpresa controladorEmpresa = new ControladorEmpresa();

    /**
     * @brief Construtor vazio
     */
    public ServicoCicloFolha() {
    }

    /**
     * @brief Construtor que recebe o repositório de folhas e a empresa.
     * @param folhaMesRepository Repositório de folhas.
     * @param controladorEmpresa Controlador de empresa.
     */
    public ServicoCicloFolha(FolhaMesRepository folhaMesRepository, ControladorEmpresa controladorEmpresa) {
        this.folhaMesRepository = folhaMesRepository;
        this.controladorEmpresa = controladorEmpresa;
    }

    /**
     * @brief Gerencia o ciclo de folhas, fechando folhas em transição expiradas,
     *        colocando em transição folhas abertas vencidas e criando novas folhas
     *        se necessário.
     */
    public void gerenciarCicloFolhas() {
        try {
            // Data atual
            LocalDate hoje = LocalDate.now();
            // Busca folhas ativas
            List<FolhaMes> folhasAtivas = folhaMesRepository.buscarFolhasAtivas();
            Empresa empresa = controladorEmpresa.buscarEmpresa();
            int diaFechamento = (empresa != null && empresa.getDiaFechamentoPonto() > 0)
                    ? empresa.getDiaFechamentoPonto()
                    : 30;

            // Verifica se tem alguma folha aberta
            boolean temAberta = false;
            // Percorre as folhas ativas
            for (FolhaMes folha : folhasAtivas) {
                try {
                    // Formata a competência (mês/ano)
                    String[] compParts = folha.getCompetencia().split("/");
                    int month = Integer.parseInt(compParts[0]);
                    int year = Integer.parseInt(compParts[1]);
                    YearMonth mesFolha = YearMonth.of(year, month);
                    // Atualiza datas de início e fim de acordo com o dia de fechamento
                    LocalDate novaDataInicio;
                    LocalDate novaDataFim;
                    // Verifica se o dia de fechamento é 30 ou 31
                    if (diaFechamento == 30 || diaFechamento == 31) {
                        // Se for 30 ou 31, a data de início é o primeiro dia do mês e a data de fim é o
                        // último dia do mês
                        novaDataInicio = mesFolha.atDay(1);
                        novaDataFim = mesFolha.atEndOfMonth();
                    } else {
                        // Caso contrário, a data de início é o dia seguinte ao dia de fechamento do mês
                        // anterior e a data de fim é o dia de fechamento do mês atual
                        YearMonth mesAnterior = mesFolha.minusMonths(1);
                        novaDataInicio = mesAnterior.atDay(diaFechamento + 1);
                        novaDataFim = mesFolha.atDay(diaFechamento);
                    }
                    // Atualiza as datas se necessário
                    if (folha.getDataFim() == null || !folha.getDataFim().equals(novaDataFim)) {
                        folha.setDataInicio(novaDataInicio);
                        folha.setDataFim(novaDataFim);
                        folhaMesRepository.atualizarDatas(folha.getId(), novaDataInicio, novaDataFim);
                    }
                } catch (Exception ex) { // Em caso de erro, lança exceção
                    throw new IllegalStateException("Erro ao recalcular datas da folha: " + folha.getCompetencia(), ex);
                }
                // Verifica se a folha está em transição
                if ("Em Transição".equals(folha.getStatus())) {
                    // Verifica se já passaram 5 dias do fim da folha
                    if (folha.getDataFim() != null && hoje.isAfter(folha.getDataFim().plusDays(5))) {
                        folhaMesRepository.atualizarStatus(folha.getId(), "Fechada");
                        folha.setStatus("Fechada");
                        ServicoAuditoria.registrar("Edição", "Folha de Pagamento",
                                "Fechamento automático (5 dias após prazo): " + folha.getCompetencia());
                    }
                } else if ("Aberta".equals(folha.getStatus())) { // Verifica se a folha está aberta
                    // Verifica se a folha venceu
                    if (folha.getDataFim() != null && hoje.isAfter(folha.getDataFim())) {
                        folhaMesRepository.atualizarStatus(folha.getId(), "Em Transição");
                        folha.setStatus("Em Transição");
                        ServicoAuditoria.registrar("Edição", "Folha de Pagamento",
                                "Status Em Transição (Vencida): " + folha.getCompetencia());
                    } else {
                        temAberta = true; // Tem uma folha aberta e dentro do prazo
                    }
                }
            }

            // Se depois da verificação não há nenhuma folha "Aberta", criamos uma nova
            if (!temAberta) {
                abrirFolhaAutomatica();
            }

        } catch (Exception e) { // Em caso de erro, lança uma exceção
            throw new IllegalStateException("Erro ao gerenciar ciclo de folhas", e);
        }
    }

    /**
     * @brief Cria automaticamente uma nova folha de pagamento para o mês atual
     */
    private void abrirFolhaAutomatica() {
        try {

            Empresa empresa = controladorEmpresa.buscarEmpresa(); // Busca a empresa cadastrada
            // Busca o dia de fechamento da folha
            int diaFechamento = (empresa != null && empresa.getDiaFechamentoPonto() > 0)
                    ? empresa.getDiaFechamentoPonto()
                    : 30;

            YearMonth mesAtual = YearMonth.now(); // Busca o mês atual

            // Avança o mês caso já exista uma folha para a competência atual
            String competenciaCalculada = null;
            int tentativas = 0;
            while (tentativas < 24) {
                competenciaCalculada = String.format("%02d/%d", mesAtual.getMonthValue(), mesAtual.getYear());
                FolhaMes folhaExistente = folhaMesRepository.buscarPorCompetencia(competenciaCalculada);
                if (folhaExistente == null) {
                    break;
                }
                mesAtual = mesAtual.plusMonths(1);
                tentativas++;
            }
            if (tentativas >= 24) {
                throw new IllegalStateException("Limite de tentativas para abrir folha automática excedido.");
            }

            LocalDate dataInicioCalculada; // Data de início da folha
            LocalDate dataFimCalculada; // Data de fim da folha

            // Calcula a data de início e fim da folha
            if (diaFechamento == 30 || diaFechamento == 31) { // Se o fechamento da folha for no final do mês
                dataInicioCalculada = mesAtual.atDay(1); // Data de início da folha
                dataFimCalculada = mesAtual.atEndOfMonth(); // Data de fim da folha
            } else { // Caso o fechamento da folha seja no meio do mês
                YearMonth mesAnterior = mesAtual.minusMonths(1); // Pega o mês anterior
                dataInicioCalculada = mesAnterior.atDay(diaFechamento + 1); // Data de início da folha
                dataFimCalculada = mesAtual.atDay(diaFechamento); // Data de fim da folha
            }

            int diasUteis = 22; // Valor padrão
            // Cria a nova folha
            FolhaMes novaFolha = new FolhaMes(0, competenciaCalculada, diasUteis, "Aberta", dataInicioCalculada,
                    dataFimCalculada);
            // Salva a nova folha
            folhaMesRepository.salvar(novaFolha);
            ServicoAuditoria.registrar("Cadastro", "Folha de Pagamento", "Competência: " + competenciaCalculada);
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao abrir folha automática", e);
        }
    }

    /**
     * @brief Fecha a folha de pagamento atual
     * @param folhaAtual A folha de pagamento a ser fechada
     */
    public void fecharFolha(FolhaMes folhaAtual) {
        // Verifica se a folha não é nula e não está fechada
        if (folhaAtual != null && !folhaAtual.getStatus().equals("Fechada")) {
            // Atualiza o status da folha para "Fechada"
            folhaMesRepository.atualizarStatus(folhaAtual.getId(), "Fechada");
            folhaAtual.setStatus("Fechada");
            // Registra a ação no log de auditoria
            ServicoAuditoria.registrar("Edição", "Folha de Pagamento",
                    "Status: Fechada, Competência: " + folhaAtual.getCompetencia());
        }
    }

    /**
     * @brief Exclui todas as folhas e os lançamentos para zerar o banco de dados
     */
    public void resetarFolhas() {
        try {
            // Exclui todas as folhas
            folhaMesRepository.excluirTodasFolhas();
            // Registra a ação no log de auditoria
            ServicoAuditoria.registrar("Exclusão", "Folha de Pagamento", "Zerar Banco de Dados de Folhas");
            // Gerencia o ciclo de folhas
            gerenciarCicloFolhas();
        } catch (Exception e) { // Em caso de erro, imprime o erro e lança uma exceção
            throw new IllegalStateException("Erro ao resetar folhas: " + e.getMessage(), e);
        }
    }

    /**
     * @brief Obtém as folhas cadastradas e a atual
     * @return Retorna a lista de todas as folhas, garantindo que a ordem está
     *         correta
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
