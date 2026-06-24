/**
 * @brief Classe responsável por gerenciar a tela de lançamentos da folha de pagamento
 *        Permite visualizar, pesquisar e gerenciar os lançamentos dinâmicos
 *        associados aos funcionários na competência (folha) atualmente aberta.
 */
package com.sfp.folha.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Optional;

import com.sfp.folha.domain.Lancamento;
import com.sfp.folha.infrastructure.persistence.MySQLFolhaMesRepository;
import com.sfp.folha.infrastructure.persistence.MySQLLancamentoRepository;
import com.sfp.funcionario.domain.Funcionario;
import com.sfp.funcionario.infrastructure.persistence.MySQLFuncionarioRepository;
import com.sfp.rubrica.domain.Rubrica;
import com.sfp.rubrica.infrastructure.persistence.MySQLRubricaRepository;
import com.sfp.folha.application.ServicoCicloFolha;
import com.sfp.folha.domain.FolhaMes;

public class TelaLancamento {
    @FXML
    private Label labelCompetencia; // Label para exibir a competência atual
    @FXML
    private Label labelStatus; // Label para exibir o status da folha
    @FXML
    private ComboBox<Funcionario> comboBuscaFuncionario; // ComboBox para buscar funcionários
    @FXML
    private ComboBox<FolhaMes> comboFolha; // ComboBox para buscar folhas
    @FXML
    private TableView<LancamentoRow> tabelaLancamento; // Tabela para exibir lançamentos
    @FXML
    private TableColumn<LancamentoRow, String> colFunc; // Coluna para exibir funcionários
    @FXML
    private TableColumn<LancamentoRow, String> colTipo; // Coluna para exibir tipo
    @FXML
    private TableColumn<LancamentoRow, String> colVal; // Coluna para exibir valor
    @FXML
    private TableColumn<LancamentoRow, String> colData; // Coluna para exibir data

    // Repositórios
    private final MySQLLancamentoRepository lancamentoRepo = new MySQLLancamentoRepository();
    private final MySQLFuncionarioRepository funcionarioRepo = new MySQLFuncionarioRepository();
    private final MySQLRubricaRepository rubricaRepo = new MySQLRubricaRepository();
    private final ServicoCicloFolha servicoCicloFolha = new ServicoCicloFolha();

    /**
     * @brief Classe interna que representa uma linha da tabela de lançamentos e que
     *        armazena os dados necessários para exibir na tabela
     */
    public static class LancamentoRow {
        private int id;
        private String funcionario;
        private String tipo;
        private String valor;
        private String data;

        /**
         * @brief Construtor da classe LancamentoRow
         * @param id          O identificador do lançamento
         * @param funcionario O nome do funcionário
         * @param tipo        O tipo do lançamento
         * @param valor       O valor do lançamento
         * @param data        A data do lançamento
         */
        public LancamentoRow(int id, String funcionario, String tipo, String valor, String data) {
            this.id = id;
            this.funcionario = funcionario;
            this.tipo = tipo;
            this.valor = valor;
            this.data = data;
        }

        /**
         * @brief Método para obter o identificador do lançamento
         * @return O identificador do lançamento
         */
        public int getId() {
            return id;
        }

        /**
         * @brief Método para obter o nome do funcionário
         * @return O nome do funcionário
         */
        public String getFuncionario() {
            return funcionario;
        }

        /**
         * @brief Método para obter o tipo do lançamento
         * @return O tipo do lançamento
         */
        public String getTipo() {
            return tipo;
        }

        /**
         * @brief Método para obter o valor do lançamento
         * @return O valor do lançamento
         */
        public String getValor() {
            return valor;
        }

        /**
         * @brief Método para obter a data do lançamento
         * @return A data do lançamento
         */
        public String getData() {
            return data;
        }
    }

    /**
     * @brief Método chamado automaticamente pelo JavaFX quando a tela é carregada
     */
    @FXML
    public void initialize() {
        // Configura a política de redimensionamento da tabela
        tabelaLancamento.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Configura as colunas da tabela
        colFunc.setCellValueFactory(new PropertyValueFactory<>("funcionario"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colVal.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        // Carrega os funcionários no ComboBox de busca
        List<Funcionario> funcionarios = funcionarioRepo.buscarTodos();
        comboBuscaFuncionario.getItems().addAll(funcionarios);
        // Configura o conversor do ComboBox de busca
        comboBuscaFuncionario.setConverter(new StringConverter<Funcionario>() {
            /**
             * @brief Método para converter um Funcionario em String
             * @param f O Funcionario a ser convertido
             * @return A String resultante da conversão
             */
            public String toString(Funcionario f) {
                return f == null ? "Todos" : f.getNome();
            }

            /**
             * @brief Método para converter uma String em Funcionario
             * @param s A String a ser convertida
             * @return O Funcionario resultante da conversão
             */
            public Funcionario fromString(String s) {
                return null;
            }
        });
        // Carrega as folhas no ComboBox de folhas
        List<FolhaMes> todasFolhas = new MySQLFolhaMesRepository().buscarTodas();
        comboFolha.getItems().addAll(todasFolhas);
        // Configura o conversor do ComboBox de folhas
        comboFolha.setConverter(new StringConverter<FolhaMes>() {
            /**
             * @brief Método para converter uma FolhaMes em String
             * @param f A FolhaMes a ser convertida
             * @return A String resultante da conversão
             */
            public String toString(FolhaMes f) {
                return f == null ? "Nenhuma" : f.getCompetencia() + " - " + f.getStatus();
            }

            /**
             * @brief Método para converter uma String em FolhaMes
             * @param s A String a ser convertida
             * @return A FolhaMes resultante da conversão
             */
            public FolhaMes fromString(String s) {
                return null;
            }
        });
        // Busca a folha aberta
        FolhaMes folhaAberta = servicoCicloFolha.buscarFolhaAberta();
        // Se houver folha aberta, define o ComboBox de folhas para a folha aberta
        if (folhaAberta != null) {
            comboFolha.setValue(folhaAberta);
            labelCompetencia.setText(folhaAberta.getCompetencia());
            labelStatus.setText(folhaAberta.getStatus());
        } else if (!todasFolhas.isEmpty()) { // Se não houver folha aberta, define o ComboBox de folhas para a última
            // folha
            comboFolha.setValue(todasFolhas.get(todasFolhas.size() - 1));
            labelCompetencia.setText(comboFolha.getValue().getCompetencia());
            labelStatus.setText(comboFolha.getValue().getStatus());
        } else { // Se não houver folha aberta e não houver folhas, define o ComboBox de
                 // folhas para null
            labelCompetencia.setText("N/A");
            labelStatus.setText("Nenhuma folha aberta");
        }
        // Atualiza a tabela
        atualizarTabela(null);
    }

    /**
     * @brief Método chamado quando o ComboBox de folhas é alterado
     */
    @FXML
    public void mudarFolha() {
        // Busca a folha selecionada
        FolhaMes f = comboFolha.getValue();
        // Se houver folha selecionada, atualiza a tabela
        if (f != null) {
            labelCompetencia.setText(f.getCompetencia());
            labelStatus.setText(f.getStatus());
            atualizarTabela(comboBuscaFuncionario.getValue());
        }
    }

    /**
     * @brief Método chamado quando o ComboBox de busca de funcionários é alterado
     */
    @FXML
    public void buscar() {
        // Busca o funcionário selecionado
        Funcionario f = comboBuscaFuncionario.getValue();
        // Atualiza a tabela com o funcionário selecionado
        atualizarTabela(f);
    }

    /**
     * @brief Método chamado quando o ComboBox de busca de funcionários é limpo
     */
    @FXML
    public void limparFiltros() {
        // Limpa o ComboBox de busca de funcionários
        comboBuscaFuncionario.setValue(null);
        // Atualiza a tabela
        atualizarTabela(null);
    }

    /**
     * @brief Método chamado quando o botão "Registrar" é pressionado
     */
    @FXML
    public void registrar() {
        // Abre o formulário de lançamento
        abrirFormularioLancamento(null);
    }

    /**
     * @brief Método chamado quando o botão "Editar" é pressionado
     * @param funcFiltro O funcionário a ser filtrado
     */
    private void atualizarTabela(Funcionario funcFiltro) {
        try {
            // Busca a folha selecionada
            FolhaMes folhaSelecionada = comboFolha.getValue();
            // Busca os lançamentos
            List<Lancamento> lancamentos;
            if (folhaSelecionada != null) { // Se houver folha selecionada, busca os lançamentos da folha
                lancamentos = lancamentoRepo.buscarPorFolha(folhaSelecionada.getId());
            } else { // Se não houver folha selecionada, busca todos os lançamentos
                lancamentos = lancamentoRepo.buscarTodos();
            }
            // Busca os funcionários
            List<Funcionario> funcionarios = funcionarioRepo.buscarTodos();
            // Busca as rubricas
            List<Rubrica> rubricas = rubricaRepo.buscarTodas();
            // Mapeia os funcionários
            Map<String, String> mapFuncionarios = funcionarios.stream()
                    .collect(Collectors.toMap(Funcionario::getCpf, Funcionario::getNome));
            // Mapeia as rubricas
            Map<Integer, String> mapRubricas = rubricas.stream()
                    .collect(Collectors.toMap(Rubrica::getCodigo, Rubrica::getDescricao));
            // Cria a lista de lançamentos
            ObservableList<LancamentoRow> rows = FXCollections.observableArrayList();
            // Itera sobre os lançamentos
            for (Lancamento l : lancamentos) {
                // Busca o nome do funcionário
                String nomeFunc = mapFuncionarios.getOrDefault(l.getCpfFuncionario(), l.getCpfFuncionario());
                // Busca a descrição da rubrica
                String descRubrica = mapRubricas.getOrDefault(l.getCodigoRubrica(),
                        String.valueOf(l.getCodigoRubrica()));
                // Formata o valor
                String valorFormatado = "";
                if (l.getValor() != null) {
                    if ("Porcentagem".equals(l.getModalidade())) {
                        valorFormatado = l.getValor().toString() + "%";
                    } else {
                        valorFormatado = "R$ " + l.getValor().toString();
                    }
                } else if (l.getQuantidade() > 0) {
                    valorFormatado = l.getQuantidade() + " un";
                }
                // Formata a data
                String dataStr = "";
                if (l.getDataInicio() != null) { // Data de início
                    dataStr = l.getDataInicio().toString();
                    if (l.getDataFim() != null && !l.getDataInicio().equals(l.getDataFim())) {
                        dataStr += " até " + l.getDataFim().toString();
                    }
                } else if (l.getDataClt() != null) { // Data CLT
                    dataStr = l.getDataClt().toString();
                }
                // Se houver filtro de funcionário, verifica se o lançamento é do funcionário
                if (funcFiltro == null || funcFiltro.getCpf().equals(l.getCpfFuncionario())) {
                    rows.add(new LancamentoRow(l.getId(), nomeFunc, descRubrica, valorFormatado, dataStr));
                }
            }
            // Atualiza a tabela
            tabelaLancamento.setItems(rows);

        } catch (Exception e) { // Caso ocorra um erro ao atualizar a tabela
            System.err.println("Erro ao atualizar tabela de Lançamentos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @brief Método chamado quando o botão "Editar" é pressionado
     */
    @FXML
    public void editarLancamento() {
        // Busca o lançamento selecionado
        LancamentoRow row = tabelaLancamento.getSelectionModel().getSelectedItem();
        // Verifica se há um lançamento selecionado
        if (row == null) {
            mostrarAlerta("Selecione um lançamento na tabela para editar.");
            return;
        }
        // Busca o lançamento pelo ID
        Lancamento lancamento = lancamentoRepo.buscarPorId(row.getId());
        // Se houver um lançamento, abre o formulário
        if (lancamento != null) {
            abrirFormularioLancamento(lancamento);
        }
    }

    /**
     * @brief Método chamado quando o botão "Excluir" é pressionado
     */
    @FXML
    public void excluirLancamento() {
        // Busca o lançamento selecionado
        LancamentoRow row = tabelaLancamento.getSelectionModel().getSelectedItem();
        // Verifica se há um lançamento selecionado
        if (row == null) {
            mostrarAlerta("Selecione um lançamento na tabela para excluir.");
            return;
        }
        // Cria um alerta de confirmação
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar Exclusão");
        confirm.setHeaderText("Você tem certeza que deseja excluir este lançamento?");
        // Aguarda a resposta do usuário
        Optional<ButtonType> result = confirm.showAndWait();
        // Se o usuário confirmar, deleta o lançamento e atualiza a tabela
        if (result.isPresent() && result.get() == ButtonType.OK) {
            lancamentoRepo.deletar(row.getId());
            atualizarTabela(comboBuscaFuncionario.getValue());
        }
    }

    /**
     * @brief Método chamado para abrir o formulário de lançamento
     * @param lancamento O lançamento a ser editado (null para novo lançamento)
     */
    private void abrirFormularioLancamento(Lancamento lancamento) {
        try {
            // Carrega o formulário de lançamento
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/sfp/folha/ui/FormLancamento.fxml"));
            // Carrega o root
            Parent root = loader.load();
            // Obtém o controller
            FormLancamentoController controller = loader.getController();
            // Define a folha selecionada
            controller.setFolhaSelecionada(comboFolha.getValue());
            // Se houver um lançamento, define para edição
            if (lancamento != null) {
                controller.setLancamentoParaEdicao(lancamento);
            }
            // Cria o stage
            Stage stage = new Stage();
            // Define os parâmetros do stage
            stage.setTitle(lancamento == null ? "Registrar Lançamento" : "Editar Lançamento");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            // Mostra o stage
            stage.showAndWait();
            // Atualiza a tabela com os novos dados
            atualizarTabela(comboBuscaFuncionario.getValue());
        } catch (Exception e) { // Caso ocorra um erro ao abrir o formulário
            e.printStackTrace();
        }
    }

    /**
     * @brief Método chamado para mostrar um alerta
     * @param msg A mensagem a ser exibida no alerta
     */
    private void mostrarAlerta(String msg) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle("Aviso");
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
