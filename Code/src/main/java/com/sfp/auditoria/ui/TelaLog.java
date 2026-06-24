package com.sfp.auditoria.ui;

import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;

import com.sfp.auditoria.application.ControladorAuditoria;
import com.sfp.auditoria.domain.RegistroAuditoria;
import com.sfp.usuario.domain.Usuario;
import com.sfp.usuario.application.ControladorUsuario;

/**
 * @brief Classe responsável por controlar a interface de logs.
 */
public class TelaLog {
    @FXML
    private ComboBox<Usuario> comboUsuario; // ComboBox para selecionar o usuário
    @FXML
    private DatePicker dpFiltroData; // DatePicker para selecionar a data
    @FXML
    private TableView<RegistroAuditoria> tabelaLogs; // TableView para exibir os logs
    @FXML
    private TableColumn<RegistroAuditoria, String> colDataHora; // Coluna para exibir a data e hora do log
    @FXML
    private TableColumn<RegistroAuditoria, String> colUsuario; // Coluna para exibir o usuário que realizou a ação
    @FXML
    private TableColumn<RegistroAuditoria, String> colPerfil; // Coluna para exibir o perfil do usuário
    @FXML
    private TableColumn<RegistroAuditoria, String> colAcao; // Coluna para exibir a ação realizada
    @FXML
    private TableColumn<RegistroAuditoria, String> colEntidade; // Coluna para exibir a entidade afetada
    @FXML
    private TableColumn<RegistroAuditoria, String> colDetalhes; // Coluna para exibir detalhes do log

    private final ControladorAuditoria controladorAuditoria = new ControladorAuditoria();

    /**
     * @brief Inicializa a tela de logs.
     */
    @FXML
    public void initialize() {
        // Define a política de redimensionamento da tabela
        tabelaLogs.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Define o valor padrão das colunas
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colPerfil.setCellValueFactory(new PropertyValueFactory<>("perfil"));
        colAcao.setCellValueFactory(new PropertyValueFactory<>("acao"));
        colEntidade.setCellValueFactory(new PropertyValueFactory<>("entidade"));
        colDetalhes.setCellValueFactory(new PropertyValueFactory<>("detalhes"));

        // dataHora precisa de formatação especial
        colDataHora.setCellValueFactory(new PropertyValueFactory<>("dataHoraFormatada"));

        // Adiciona usuários ao ComboBox
        ControladorUsuario ctrlUser = new ControladorUsuario();
        List<Usuario> usuarios = ctrlUser.listarUsuarios();
        comboUsuario.getItems().addAll(usuarios);
        comboUsuario.setConverter(new javafx.util.StringConverter<Usuario>() {
            /**
             * @brief Converte um usuário para String.
             */
            public String toString(Usuario u) {
                return u == null ? "Todos" : u.getNome();
            }

            /**
             * @brief Converte uma String para usuário.
             */
            public Usuario fromString(String s) {
                return null;
            }
        });
        // Faz a primeira busca
        buscar();
    }

    /**
     * @brief Busca os logs.
     */
    @FXML
    private void buscar() {
        // Busca os logs no controlador
        Usuario usuario = comboUsuario.getValue();
        String usuarioNome = (usuario != null) ? usuario.getNome() : null;
        LocalDate data = dpFiltroData.getValue();

        // Monta a lista
        List<RegistroAuditoria> lista = controladorAuditoria.buscar(usuarioNome, data);

        // Define a lista na tabela
        ObservableList<RegistroAuditoria> obsList = FXCollections.observableArrayList(lista);
        tabelaLogs.setItems(obsList);
    }

    /**
     * @brief Limpa os filtros.
     */
    @FXML
    private void limparFiltros() {
        comboUsuario.setValue(null);
        dpFiltroData.setValue(null);
        buscar();
    }
}
