/**
 * @brief Classe que gerencia a tela de funcionários.
 * É responsável pela interação entre a View e a lógica de negócio referente a funcionários.
 * O Controller manipula os dados exibidos na View e responde às ações do usuário.
 */

package com.sfp.funcionario.ui;

import com.sfp.core.ui.GerenciadorTema;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.sfp.funcionario.domain.Funcionario;
import com.sfp.funcionario.domain.FuncionarioRepository;
import com.sfp.funcionario.infrastructure.persistence.MySQLFuncionarioRepository;

import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;

public class TelaFuncionario {
    @FXML
    private TableView<Funcionario> tabelaFuncionarios; // Tabela que exibe os funcionários
    @FXML
    private TableColumn<Funcionario, String> colNome; // Coluna que exibe o nome
    @FXML
    private TableColumn<Funcionario, String> colCpf; // Coluna que exibe o cpf
    @FXML
    private TableColumn<Funcionario, String> colCargo; // Coluna que exibe o cargo
    @FXML
    private TableColumn<Funcionario, java.time.LocalDate> colAdmissao; // Coluna que exibe a data de admissão
    @FXML
    private TableColumn<Funcionario, java.math.BigDecimal> colSalario; // Coluna que exibe o salário
    @FXML
    private TableColumn<Funcionario, Boolean> colStatus; // Coluna que exibe o status

    private FuncionarioRepository funcionarioRepository = new MySQLFuncionarioRepository();

    /**
     * @brief Método para inicializar a tela de funcionários (obrigatório do JavaFX)
     */
    @FXML
    public void initialize() {
        // Redimensiona as colunas da tabela para se ajustarem ao tamanho da janela
        tabelaFuncionarios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Mapeia as colunas com as propriedades exatas da classe Funcionario
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        colAdmissao.setCellValueFactory(new PropertyValueFactory<>("dataAdmissao"));
        colSalario.setCellValueFactory(new PropertyValueFactory<>("salarioBruto"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStatus.setCellFactory(col -> new TableCell<Funcionario, Boolean>() {
            /**
             * @brief Método para atualizar a célula da coluna de status
             * @param item  Item da célula
             * @param empty Se a célula está vazia
             */
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty); // Atualiza a célula
                setText(empty || item == null ? null : item ? "Ativo" : "Inativo");
            }
        });
        // Carrega os dados do banco
        carregarDadosBanco();
    }

    /**
     * @brief Método para carregar os dados do banco
     */
    private void carregarDadosBanco() {
        try {
            // Busca todos os funcionários do banco
            List<Funcionario> lista = funcionarioRepository.buscarTodos();
            // Transforma a lista em uma lista observável para que a tabela possa exibi-la
            ObservableList<Funcionario> obsList = FXCollections.observableArrayList(lista);
            tabelaFuncionarios.setItems(obsList);
        } catch (Exception e) { // Captura erro de execução ao carregar dados
            System.err.println("Erro ao carregar tabela: " + e.getMessage());
        }
    }

    /**
     * @brief Método para abrir a tela de cadastro de funcionários
     */
    @FXML
    public void abrirTelaCadastro() {
        try {
            // Cria um FXMLLoader para carregar a tela de cadastro
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("CadastroFuncionarioView.fxml"));
            Parent root = loader.load();
            // Verifica se o modo escuro está ativo e aplica o estilo
            if (GerenciadorTema.modoEscuroAtivo) {
                root.getStyleClass().add("dark-mode");
            }
            // Cria uma nova janela (Stage) para o cadastro
            Stage stage = new Stage();
            stage.setTitle("Cadastrar Novo Funcionário");
            stage.setScene(new Scene(root));
            stage.show(); // Mostra a tela de cadastro
        } catch (Exception e) { // Captura possíveis erros ao abrir a tela de cadastro
            e.printStackTrace();
        }
    }

    /**
     * @brief Método para abrir a tela de edição de funcionários
     */
    @FXML
    public void editarFuncionario() {
        // Seleciona o funcionário da tabela
        Funcionario funcionario = tabelaFuncionarios.getSelectionModel().getSelectedItem();
        // Verifica se algum funcionário foi selecionado
        if (funcionario == null) {
            // Cria um alerta para informar que nenhum funcionário foi selecionado
            Alert alert = new Alert(Alert.AlertType.WARNING);
            // Configura os campos do alerta
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Selecione um funcionário na tabela para editar.");
            alert.showAndWait();
            return;
        }
        try {
            // Cria um FXMLLoader para carregar a tela de edição
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("CadastroFuncionarioView.fxml"));
            Parent root = loader.load();
            // Verifica se o modo escuro está ativo e aplica o estilo
            if (GerenciadorTema.modoEscuroAtivo) {
                root.getStyleClass().add("dark-mode");
            }
            // Pega o controller da tela de cadastro
            CadastroFuncionarioController controller = loader.getController();
            // Passa o funcionário selecionado para a tela de edição
            controller.setFuncionarioEdicao(funcionario);
            // Cria uma nova janela (Stage) para a edição
            Stage stage = new Stage();
            // Configura os campos da nova janela
            stage.setTitle("Editar Funcionário");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) { // Captura possíveis erros ao abrir a tela de edição
            e.printStackTrace();
        }
    }

}
