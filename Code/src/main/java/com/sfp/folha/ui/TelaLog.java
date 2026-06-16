/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sfp.folha.ui;

import com.sfp.auditoria.application.ControladorAuditoria;
import com.sfp.core.domain.RegistroAuditoria;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author manoe
 */
public class TelaLog{
    
    
    @FXML private TextField txtFiltroUsuario;
    @FXML private DatePicker dpFiltroData;
    
    @FXML private TableView<RegistroAuditoria> tabelaLogs;
    @FXML private TableColumn<RegistroAuditoria, String> colDataHora;
    @FXML private TableColumn<RegistroAuditoria, String> colUsuario;
    @FXML private TableColumn<RegistroAuditoria, String> colPerfil;
    @FXML private TableColumn<RegistroAuditoria, String> colAcao;
    @FXML private TableColumn<RegistroAuditoria, String> colEntidade;
    @FXML private TableColumn<RegistroAuditoria, String> colDetalhes;
    
    private final ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    @FXML
    public void initialize() {
        tabelaLogs.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        colUsuario  .setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colPerfil   .setCellValueFactory(new PropertyValueFactory<>("perfil"));
        colAcao     .setCellValueFactory(new PropertyValueFactory<>("acao"));
        colEntidade .setCellValueFactory(new PropertyValueFactory<>("entidade"));
        colDetalhes .setCellValueFactory(new PropertyValueFactory<>("detalhes"));

        // dataHora precisa de formatação especial
        colDataHora.setCellValueFactory(new PropertyValueFactory<>("dataHoraFormatada"));

        buscar();
    } 
 
    @FXML
    private void buscar() {
        String usuario = txtFiltroUsuario.getText();
        LocalDate data = dpFiltroData.getValue();

        List<RegistroAuditoria> lista = controladorAuditoria.buscar(
            (usuario == null || usuario.isBlank()) ? null : usuario,data);

        ObservableList<RegistroAuditoria> obsList = FXCollections.observableArrayList(lista);
        tabelaLogs.setItems(obsList);
    }

    @FXML
    private void limparFiltros() {
        txtFiltroUsuario.clear();
        dpFiltroData.setValue(null);
        buscar();
    }    
}
