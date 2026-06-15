/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sfp.folha.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author manoe
 */
public class TelaLog{
    
    
    @FXML private TextField txtFiltroUsuario;
    @FXML private DatePicker dpFiltroData;
    
    @FXML private TableView tabelaLogs;
    @FXML private TableColumn colDataHora;
    @FXML private TableColumn colUsuario;
    @FXML private TableColumn colPerfil;
    @FXML private TableColumn colAcao;
    @FXML private TableColumn colEntidade;
    @FXML private TableColumn colDetalhes;

    @FXML
    public void initialize() {
        tabelaLogs.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    } 
 
    
}
