/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sfp.folha.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author manoe
 */
public class TelaLancamento{
    
    @FXML private Label labelCompetencia;
    @FXML private Label labelStatus;
    @FXML private ComboBox comboFuncionario;
    @FXML private Label labelInfoFuncionario;
    
    @FXML private TableView tabelaLancamento;
    @FXML private TableColumn colFunc;
    @FXML private TableColumn colTipo;
    @FXML private TableColumn colVal;
    @FXML private TableColumn colData;
    @FXML private TableColumn colAcoes;
    @FXML private TableColumn colObs;

    @FXML
    public void initialize() {
        tabelaLancamento.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    } 
    
    @FXML
    public void registrar() {
        //registrar lançamento
    }
    @FXML
    public void editarLancamento()
    {
        
    }
    @FXML
    public void cancelar() {
        //limpar campos
    }
    
    @FXML 
    public void excluirLancamento()
    {
        
    }
}
