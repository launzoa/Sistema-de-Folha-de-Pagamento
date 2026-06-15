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
public class TelaEmpresa{
    @FXML private ComboBox comboEmpresas;
    @FXML private Label labelRazaoSocial;
    @FXML private Label labelCNPJ;
    @FXML private Label labelRespLegal;
    @FXML private Label labelEmail;
    
    @FXML private TableView tabelaEnderecos;
    @FXML private TableColumn colCep;
    @FXML private TableColumn colLogradouro;
    @FXML private TableColumn colBairro;
    @FXML private TableColumn colComplemento;

    @FXML
    public void initialize() {
        tabelaEnderecos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    } 

}
