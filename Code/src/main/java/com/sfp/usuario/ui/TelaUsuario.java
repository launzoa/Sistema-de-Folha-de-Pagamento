package com.sfp.usuario.ui;

import java.util.List;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import com.sfp.core.ui.GerenciadorTema;
import com.sfp.usuario.domain.Usuario;
import com.sfp.usuario.application.ControladorUsuario;

/**
 * @brief Classe responsável por exibir a tela de gerenciamento de usuários.
 */
public class TelaUsuario {
    @FXML
    private TableView<Usuario> tabelaUsuario; // Tabela que exibe os usuários
    @FXML
    private TableColumn<?, ?> colNome; // Coluna que exibe o nome do usuário
    @FXML
    private TableColumn<Usuario, Boolean> colPerfil; // Coluna que exibe o perfil do usuário
    @FXML
    private TableColumn<Usuario, Boolean> colStatus; // Coluna que exibe o status do usuário
    @FXML
    private ComboBox<Usuario> comboUsuario; // ComboBox que exibe os usuários

    private ControladorUsuario controladorUsuario = new ControladorUsuario();

    /**
     * @brief Inicializa a tela de gerenciamento de usuários.
     */
    @FXML
    public void initialize() {
        // Redimensiona as colunas da tabela
        tabelaUsuario.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Define o valor padrão para as colunas
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colPerfil.setCellValueFactory(new PropertyValueFactory<>("perfil"));
        // Configura a coluna de perfil
        colPerfil.setCellFactory(col -> new TableCell<Usuario, Boolean>() {
            /**
             * @brief Atualiza a coluna de perfil.
             */
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item ? "Administrador" : "Operador");
            }
        });

        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStatus.setCellFactory(col -> new TableCell<Usuario, Boolean>() {
            /**
             * @brief Atualiza a coluna de status.
             */
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item ? "Ativo" : "Inativo");
            }
        });

        // Adiciona os usuários ao ComboBox
        List<Usuario> usuarios = controladorUsuario.listarUsuarios();
        comboUsuario.getItems().addAll(usuarios);
        comboUsuario.setConverter(new StringConverter<Usuario>() {
            /**
             * @brief Converte um usuário para String.
             */
            public String toString(Usuario usuario) {
                return usuario == null ? "Todos" : usuario.getNome();
            }

            /**
             * @brief Converte uma String para usuário.
             */
            public Usuario fromString(String str) {
                return null;
            }
        });

        // Atualiza a tabela
        atualizarTabela();
    }

    /**
     * @brief Adiciona um novo usuário.
     */
    @FXML
    private void AddUsuario() {
        exibirFormulario(null);
    }

    /**
     * @brief Edita um usuário.
     */
    @FXML
    private void editarUsuario() {
        Usuario selecionado = tabelaUsuario.getSelectionModel().getSelectedItem();
        if (selecionado != null) { // Se o usuário não for nulo, exibe o formulário
            exibirFormulario(selecionado);
        } else { // Caso contrário, exibe um alerta
            mostrarAlerta("Aviso", "Selecione um usuário para editar.");
        }
    }

    /**
     * @brief Busca um usuário.
     */
    @FXML
    public void buscar() {
        Usuario usuario = comboUsuario.getValue();
        if (usuario == null) { // Se o usuário não for nulo, atualiza a tabela
            atualizarTabela();
            return;
        }
        carregarTabela(List.of(usuario));
    }

    /**
     * @brief Limpa os filtros.
     */
    @FXML
    public void limparFiltros() {
        comboUsuario.setValue(null);
        atualizarTabela();
    }

    /**
     * @brief Exibe o formulário de usuário.
     * @param usuario O usuário a ser editado.
     */
    private void exibirFormulario(Usuario usuario) {
        try {
            // Tenta carregar o formulário de usuário
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormUsuario.fxml"));
            Parent rootForm = loader.load();

            // Pega o controlador do formulário
            FormUsuario formController = loader.getController();

            // Se passado usuário avisa que é modo EDIÇÃO
            if (usuario != null) {
                formController.prepararEdicao(usuario);
            }

            // Cria pop-up
            Stage stage = new Stage();
            Scene scene = new Scene(rootForm, 500, 400);

            // Sincroniza o tema do Pop-up!
            if (GerenciadorTema.modoEscuroAtivo) {
                rootForm.getStyleClass().add("dark-mode");
            }

            // Seta a cena e o título
            stage.setScene(scene);
            stage.setTitle(usuario == null ? "Novo Usuário" : "Editar Usuário");
            // Modal evita cliques na tela de trás enquanto o pop-up está aberto
            stage.initModality(Modality.APPLICATION_MODAL);
            // Trava a execução até fechar o pop-up
            stage.showAndWait();

            // Se salvou no banco, recarrega a tabela principal atualizada
            if (formController.isSalvoComSucesso()) {
                atualizarTabela();
            }

        } catch (Exception e) { // Caso ocorra algum erro, exibe uma mensagem
            Logger.getGlobal().severe(e.getMessage());
        }
    }

    /**
     * @brief Carrega a tabela com os usuários.
     * @param lista A lista de usuários.
     */
    private void carregarTabela(List<Usuario> lista) {
        tabelaUsuario.setItems(FXCollections.observableArrayList(lista));
    }

    /**
     * @brief Limpa e atualiza a tabela com todos os usuários.
     */
    private void atualizarTabela() {
        ObservableList<Usuario> lista = FXCollections.observableArrayList(controladorUsuario.listarUsuarios());
        tabelaUsuario.setItems(lista);
    }

    /**
     * @brief Mostra um alerta ao usuário.
     * @param titulo O título do alerta.
     * @param msg    A mensagem do alerta.
     */
    private void mostrarAlerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
