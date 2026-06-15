package com.sfp.folha.ui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import com.sfp.infrastructure.persistence.MySQLFuncionarioRepository;
import com.sfp.core.domain.FuncionarioRepository;
import java.time.LocalDate;

public class TelaDashboard{
    
    @FXML private Label labelCompetencia;
    @FXML private Label labelFuncionarios;
    @FXML private Label labelStatus;
    @FXML private TableView tabelaFolha;
    @FXML private TableView tabelaProvisoes;

    @FXML
    public void initialize() {
        try {
            FuncionarioRepository repo = new MySQLFuncionarioRepository();
            int totalFuncionarios = repo.buscarTodos().size();

            labelCompetencia.setText(String.format("%02d / %d", LocalDate.now().getMonthValue(), LocalDate.now().getYear()));
            labelFuncionarios.setText(String.valueOf(totalFuncionarios));
            labelStatus.setText("Aberta");
        } catch (Exception e) {
            e.printStackTrace();
            labelFuncionarios.setText("Erro ao carregar");
        }

        tabelaFolha.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabelaProvisoes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
}
