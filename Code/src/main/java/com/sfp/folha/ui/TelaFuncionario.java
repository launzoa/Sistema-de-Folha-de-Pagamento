package com.sfp.folha.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author manoe
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import com.sfp.core.domain.Funcionario;
import com.sfp.core.domain.FuncionarioRepository;
import com.sfp.infrastructure.persistence.MySQLFuncionarioRepository;

import java.util.List;

public class TelaFuncionario {

    @FXML
    private TableView<Funcionario> tabelaFuncionarios;
    @FXML
    private TableColumn<Funcionario, String> colNome;
    @FXML
    private TableColumn<Funcionario, String> colCpf;
    @FXML
    private TableColumn<Funcionario, String> colCargo;
    @FXML
    private TableColumn<Funcionario, java.time.LocalDate> colAdmissao;
    @FXML
    private TableColumn<Funcionario, java.math.BigDecimal> colSalario;
    @FXML
    private TableColumn<Funcionario, Boolean> colStatus;

    // Repositório de funcionários com implementação MySQL
    private FuncionarioRepository funcionarioRepository = new MySQLFuncionarioRepository();

    // @brief: Inicializa a tela de funcionários
    @FXML
    public void initialize() {
        // Redimensiona as colunas da tabela para se ajustarem ao tamanho da janela
        tabelaFuncionarios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Mapear as colunas com as propriedades exatas da classe Funcionario
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        colAdmissao.setCellValueFactory(new PropertyValueFactory<>("dataAdmissao"));
        colSalario.setCellValueFactory(new PropertyValueFactory<>("salarioBruto"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Carrega os dados do banco
        carregarDadosBanco();
    }

    // @brief: Carrega os dados do banco
    private void carregarDadosBanco() {
        try {
            // Busca todos os funcionários do banco
            List<Funcionario> lista = funcionarioRepository.buscarTodos();
            // Transforma a lista em uma lista observável
            ObservableList<Funcionario> obsList = FXCollections.observableArrayList(lista);
            // Define os dados da tabela
            tabelaFuncionarios.setItems(obsList);
        } catch (Exception e) { // Captura erro de execução ao carregar dados
            System.err.println("Erro ao carregar tabela: " + e.getMessage());
        }
    }

    @FXML
    public void abrirTelaCadastro() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                    getClass().getResource("CadastroFuncionarioView.fxml"));
            javafx.scene.Parent root = loader.load();
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setTitle("Cadastrar Novo Funcionário");
            stage.setScene(new javafx.scene.Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editarFuncionario() {
        Funcionario func = tabelaFuncionarios.getSelectionModel().getSelectedItem();
        if (func == null) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Selecione um funcionário na tabela para editar.");
            alert.showAndWait();
            return;
        }
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("CadastroFuncionarioView.fxml"));
            javafx.scene.Parent root = loader.load();
            
            if(GerenciadorTema.modoEscuroAtivo)
            {
                    root.getStyleClass().add("dark-mode");
            }            
            
            CadastroFuncionarioController controller = loader.getController();
            controller.setFuncionarioEdicao(func);
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setTitle("Editar Funcionário");
            stage.setScene(new javafx.scene.Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
