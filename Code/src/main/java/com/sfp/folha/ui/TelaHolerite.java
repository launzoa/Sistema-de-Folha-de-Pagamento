/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sfp.folha.ui;

import java.net.URL;
import java.util.Locale;
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
public class TelaHolerite{
    @FXML private ComboBox comboFuncionario;
    @FXML private ComboBox comboCompetencia;

    @FXML private Label labelEmpresa;
    @FXML private Label labelEmpresaInfo;
    @FXML private Label labelCNPJEmp;
    @FXML private Label labelEndEMp;
    
    @FXML private Label labelNome;
    @FXML private Label labelCpf;
    @FXML private Label labelCargo;
    
    @FXML private Label labelAdmissao;
    @FXML private Label labelBanco;
    @FXML private Label labelDependentes;

    @FXML private TableView  tabelaProventos;
    @FXML private TableColumn colProvDescricao;
    @FXML private TableColumn colProvRef;
    @FXML private TableColumn colProvValor;

    @FXML private TableView  tabelaDescontos;
    @FXML private TableColumn colDescDescricao;
    @FXML private TableColumn colDescRef;
    @FXML private TableColumn colDescValor;

    @FXML private Label labelTotalProventos;
    @FXML private Label labelTotalDescontos;
    @FXML private Label labelLiquido;

    @FXML
    public void initialize() {
        tabelaProventos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabelaDescontos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }  
    
    
    @FXML
    private void visualizar()
    {
        
    }
    @FXML private void imprimir()
    {
        
    }
}
