/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
public class TelaFuncionario{
    
    @FXML private TableView tabelaFuncionarios;
    @FXML private TableColumn colNome;
    @FXML private TableColumn colCpf;
    @FXML private TableColumn colAdmissao;
    @FXML private TableColumn colSalario;
    @FXML private TableColumn colStatus;
    @FXML private TableColumn colAcoes;

    @FXML
    public void initialize() {
        tabelaFuncionarios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }  
    
}
