/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sfp.folha.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author manoe
 */
public class TelaFolhaMes{

    @FXML private Label labelCompetencia;
    @FXML private Label labelStatus;
    
    @FXML private TableView tabelafolha;
    @FXML private TableColumn colFunc;
    @FXML private TableColumn colCargo;
    @FXML private TableColumn colSalBase;
    @FXML private TableColumn colTotProv;
    @FXML private TableColumn colTotDesc;
    @FXML private TableColumn colSalLiq;

    @FXML
    public void initialize() {
        tabelafolha.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    } 
    @FXML
    public void calcularFolha() {
        //limpar campos
    }

    @FXML
    public void fecharFolha() {
        //registrar lançamento
    }
}
