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
public class TelaParametro{
    @FXML private ComboBox comboEmpresasParam;
    @FXML private Label labelAliquota;
    @FXML private Label labelHorasMensais;
    @FXML private Label labelCestaBasica;
    @FXML private Label labelSalarioMinimo;
    
    @FXML private TableView tabelaParametro;
    @FXML private TableColumn colAnoVigencia;
    @FXML private TableColumn colAliquotaTab;
    @FXML private TableColumn colHorasTab;
    @FXML private TableColumn colCestaTab;
    @FXML private TableColumn colSalarioTab;
    
    @FXML
    public void initialize() {
        tabelaParametro.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    } 
    
}
