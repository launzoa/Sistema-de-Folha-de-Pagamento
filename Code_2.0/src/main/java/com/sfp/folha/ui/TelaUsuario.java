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
public class TelaUsuario{

    @FXML private TableView tabelaUsuario;
    @FXML private TableColumn colLogin;
    @FXML private TableColumn colNome;
    @FXML private TableColumn colPerfil;
    @FXML private TableColumn colStatus;
    @FXML private TableColumn colUltimoAcesso;
    
    
    @FXML
    public void initialize()
    {
        tabelaUsuario.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    } 
    
    @FXML
    private void AddUsuario()
    {
    }
    
    @FXML
    private void editarUsuario()
    {
    }
    
    @FXML
    private void excluirUsuario()
    {
    }
}
