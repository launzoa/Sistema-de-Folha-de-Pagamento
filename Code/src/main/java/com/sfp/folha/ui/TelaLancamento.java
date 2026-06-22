/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sfp.folha.ui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import com.sfp.folha.domain.Lancamento;
import com.sfp.folha.infrastructure.persistence.MySQLLancamentoRepository;
import com.sfp.funcionario.domain.Funcionario;
import com.sfp.funcionario.infrastructure.persistence.MySQLFuncionarioRepository;
import com.sfp.rubrica.domain.Rubrica;
import com.sfp.rubrica.infrastructure.persistence.MySQLRubricaRepository;
import com.sfp.folha.application.ServicoCicloFolha;
import com.sfp.folha.domain.FolhaMes;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.Optional;

/**
 * @brief Controller da tela principal de gestão de Lançamentos da folha de
 *        pagamento.
 *        Permite visualizar, pesquisar e gerenciar os lançamentos dinâmicos
 *        associados
 *        aos funcionários na competência (folha) atualmente aberta.
 */
public class TelaLancamento {

    @FXML
    private Label labelCompetencia;
    @FXML
    private Label labelStatus;
    @FXML
    private ComboBox<Funcionario> comboBuscaFuncionario;

    @FXML
    private TableView<LancamentoRow> tabelaLancamento;
    @FXML
    private TableColumn<LancamentoRow, String> colFunc;
    @FXML
    private TableColumn<LancamentoRow, String> colTipo;
    @FXML
    private TableColumn<LancamentoRow, String> colVal;
    @FXML
    private TableColumn<LancamentoRow, String> colData;
    @FXML
    private TableColumn<LancamentoRow, String> colAcoes;
    @FXML
    private TableColumn<LancamentoRow, String> colObs;

    private final MySQLLancamentoRepository lancamentoRepo = new MySQLLancamentoRepository();
    private final MySQLFuncionarioRepository funcionarioRepo = new MySQLFuncionarioRepository();
    private final MySQLRubricaRepository rubricaRepo = new MySQLRubricaRepository();
    private final ServicoCicloFolha servicoCicloFolha = new ServicoCicloFolha();

    // DTO Visual
    public static class LancamentoRow {
        private int id;
        private String funcionario;
        private String tipo;
        private String valor;
        private String data;
        private String observacao;

        public LancamentoRow(int id, String funcionario, String tipo, String valor, String data, String observacao) {
            this.id = id;
            this.funcionario = funcionario;
            this.tipo = tipo;
            this.valor = valor;
            this.data = data;
            this.observacao = observacao;
        }

        public int getId() {
            return id;
        }

        public String getFuncionario() {
            return funcionario;
        }

        public String getTipo() {
            return tipo;
        }

        public String getValor() {
            return valor;
        }

        public String getData() {
            return data;
        }

        public String getObservacao() {
            return observacao;
        }
    }

    @FXML
    public void initialize() {
        tabelaLancamento.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colFunc.setCellValueFactory(new PropertyValueFactory<>("funcionario"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colVal.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colObs.setCellValueFactory(new PropertyValueFactory<>("observacao"));

        List<Funcionario> funcionarios = funcionarioRepo.buscarTodos();
        comboBuscaFuncionario.getItems().addAll(funcionarios);
        comboBuscaFuncionario.setConverter(new javafx.util.StringConverter<Funcionario>() {
            public String toString(Funcionario f) {
                return f == null ? "Todos" : f.getNome();
            }

            public Funcionario fromString(String s) {
                return null;
            }
        });

        FolhaMes folhaAberta = servicoCicloFolha.buscarFolhaAberta();
        if (folhaAberta != null) {
            labelCompetencia.setText(folhaAberta.getCompetencia());
            labelStatus.setText(folhaAberta.getStatus());
        } else {
            labelCompetencia.setText("N/A");
            labelStatus.setText("Nenhuma folha aberta");
        }

        atualizarTabela(null);
    }

    @FXML
    public void buscar() {
        Funcionario f = comboBuscaFuncionario.getValue();
        atualizarTabela(f);
    }

    @FXML
    public void limparFiltros() {
        comboBuscaFuncionario.setValue(null);
        atualizarTabela(null);
    }

    @FXML
    public void registrar() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                    getClass().getResource("/com/sfp/folha/ui/FormLancamento.fxml"));
            javafx.scene.Parent root = loader.load();
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setTitle("Registrar Lançamento");
            stage.setScene(new javafx.scene.Scene(root));
            stage.showAndWait();

            atualizarTabela(comboBuscaFuncionario.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void atualizarTabela(Funcionario funcFiltro) {
        try {
            List<Lancamento> lancamentos = lancamentoRepo.buscarTodos();
            List<Funcionario> funcionarios = funcionarioRepo.buscarTodos();
            List<Rubrica> rubricas = rubricaRepo.buscarTodas();

            // Mapeia para buscas rápidas sem O(N^2)
            Map<String, String> mapFuncionarios = funcionarios.stream()
                    .collect(Collectors.toMap(Funcionario::getCpf, Funcionario::getNome));

            Map<Integer, String> mapRubricas = rubricas.stream()
                    .collect(Collectors.toMap(Rubrica::getCodigo, Rubrica::getDescricao));

            ObservableList<LancamentoRow> rows = FXCollections.observableArrayList();

            for (Lancamento l : lancamentos) {
                String nomeFunc = mapFuncionarios.getOrDefault(l.getCpfFuncionario(), l.getCpfFuncionario());
                String descRubrica = mapRubricas.getOrDefault(l.getCodigoRubrica(),
                        String.valueOf(l.getCodigoRubrica()));

                String valorFormatado = "";
                if (l.getValor() != null) {
                    valorFormatado = "R$ " + l.getValor().toString();
                } else if (l.getQuantidade() > 0) {
                    valorFormatado = l.getQuantidade() + " un";
                }

                String dataStr = "";
                if (l.getDataInicio() != null) {
                    dataStr = l.getDataInicio().toString();
                    if (l.getDataFim() != null && !l.getDataInicio().equals(l.getDataFim())) {
                        dataStr += " até " + l.getDataFim().toString();
                    }
                } else if (l.getDataClt() != null) {
                    dataStr = l.getDataClt().toString();
                }

                String obs = (l.getPathPdf() != null && !l.getPathPdf().isEmpty()) ? "Tem anexo PDF" : "-";

                if (funcFiltro == null || funcFiltro.getCpf().equals(l.getCpfFuncionario())) {
                    rows.add(new LancamentoRow(l.getId(), nomeFunc, descRubrica, valorFormatado, dataStr, obs));
                }
            }

            tabelaLancamento.setItems(rows);

        } catch (Exception e) {
            System.err.println("Erro ao atualizar tabela de Lançamentos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void editarLancamento() {
        LancamentoRow row = tabelaLancamento.getSelectionModel().getSelectedItem();
        if (row == null) {
            mostrarAlerta("Selecione um lançamento na tabela para editar.");
            return;
        }
        Lancamento lancamento = lancamentoRepo.buscarPorId(row.getId());
        if (lancamento != null) {
            abrirFormularioLancamento(lancamento);
        }
    }

    @FXML
    public void excluirLancamento() {
        LancamentoRow row = tabelaLancamento.getSelectionModel().getSelectedItem();
        if (row == null) {
            mostrarAlerta("Selecione um lançamento na tabela para excluir.");
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar Exclusão");
        confirm.setHeaderText("Você tem certeza que deseja excluir este lançamento?");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            lancamentoRepo.deletar(row.getId());
            atualizarTabela(comboBuscaFuncionario.getValue());
        }
    }

    private void abrirFormularioLancamento(Lancamento lancamento) {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                    getClass().getResource("/com/sfp/folha/ui/FormLancamento.fxml"));
            javafx.scene.Parent root = loader.load();
            FormLancamentoController controller = loader.getController();
            if (lancamento != null) {
                controller.setLancamentoParaEdicao(lancamento);
            }
            Stage stage = new Stage();
            stage.setTitle(lancamento == null ? "Registrar Lançamento" : "Editar Lançamento");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            atualizarTabela(comboBuscaFuncionario.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String msg) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle("Aviso");
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
