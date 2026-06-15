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
public class TelaHistorico{
    
    @FXML private TextField txtBuscaNome;
    @FXML private DatePicker dpBuscaData;
    
    @FXML private TableView tabelaHistorico;
    @FXML private TableColumn colFuncionario;
    @FXML private TableColumn colCompetencia;
    @FXML private TableColumn colDtFechamento;
    @FXML private TableColumn colTotalBruto;
    @FXML private TableColumn colTotalLiquido;
    @FXML private TableColumn colTotalFgts;

    @FXML
    public void initialize() {
        tabelaHistorico.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    } 
    @FXML
    private void filtrarHistorico()
    {
    }
    @FXML
    private void limparFiltros()
    {
        txtBuscaNome.clear();
        dpBuscaData.setValue(null);
        // Reseta a tabela
    }
}
