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
public class TelaRubrica{
    
    @FXML private TableView tabelaRubrica;
    @FXML private TableColumn colDescr;
    @FXML private TableColumn colNat;
    @FXML private TableColumn colTipo;
    @FXML private TableColumn colIncINSS;
    @FXML private TableColumn colIncFGTS;
    @FXML private TableColumn colIncIRRF;
    @FXML private TableColumn colAcoes;

    @FXML
    public void initialize() {
        tabelaRubrica.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }  

    
}
