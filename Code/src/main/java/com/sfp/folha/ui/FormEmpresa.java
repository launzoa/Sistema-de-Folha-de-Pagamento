/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sfp.folha.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.sfp.core.domain.Empresa;
import com.sfp.empresa.application.ControladorEmpresa;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author maria
 */
public class FormEmpresa{
    @FXML private Label labelTitulo;
    @FXML private TextField txtCnpj;
    @FXML private TextField txtRazaoSocial;
    @FXML private TextField txtEmail;
    @FXML private TextField txtRespLegal;
    @FXML private TextField txtAliquotaFGTS;
    @FXML private TextField txtHorasMensais;
    @FXML private TextField txtValCestaBasic;
    @FXML private TextField txtPercHoraExtra50;
    @FXML private TextField txtPercHoraExtra100;

    private ControladorEmpresa controladorEmpresa = new ControladorEmpresa();
    private Empresa empresaEdicao = null;
    private boolean salvoComSucesso = false;  
 
    @FXML
    public void initialize() {
        txtAliquotaFGTS.setText("8.00");
        txtHorasMensais.setText("220");
        txtValCestaBasic.setText("0.00");
        txtPercHoraExtra50.setText("50.00");
        txtPercHoraExtra100.setText("100.00");
    }
    @FXML
    public void salvar() {
        String cnpj = txtCnpj.getText();
        String razaoSocial = txtRazaoSocial.getText();
        String email = txtEmail.getText();
        String respLegal = txtRespLegal.getText();

        if (cnpj == null || cnpj.trim().isEmpty()) 
        {
            exibirAlerta("Informe o CNPJ.");
            return;
        }

        if (razaoSocial == null || razaoSocial.trim().isEmpty()) 
        {
            exibirAlerta("Informe a razão social.");
            return;
        }

        if (respLegal == null || respLegal.trim().isEmpty()) 
        {
            exibirAlerta("Informe o responsável legal.");
            return;
        }

        try {
            double aliquotaFGTS = Double.parseDouble(txtAliquotaFGTS.getText().replace(",", "."));
            int horasMensais = Integer.parseInt(txtHorasMensais.getText());
            double valCestaBasic = Double.parseDouble(txtValCestaBasic.getText().replace(",", "."));
            double percHoraExtra50 = Double.parseDouble(txtPercHoraExtra50.getText().replace(",", "."));
            double percHoraExtra100 = Double.parseDouble(txtPercHoraExtra100.getText().replace(",", "."));

            Empresa empresa = new Empresa(
                    cnpj,
                    razaoSocial,
                    email,
                    respLegal,
                    aliquotaFGTS,
                    horasMensais,
                    valCestaBasic,
                    percHoraExtra50,
                    percHoraExtra100
            );

            if (empresaEdicao == null) 
            {
                controladorEmpresa.cadastrarEmpresa(empresa);
            } 
            else 
            {
                controladorEmpresa.atualizarEmpresa(empresa);
            }

            salvoComSucesso = true;
            fecharJanela();

        } 
        catch (NumberFormatException e) 
        {
            exibirAlerta("Verifique os campos numéricos.");
        }
    }

    @FXML
    public void cancelar() {
        fecharJanela();
    }

    public void prepararEdicao(Empresa empresa) {
        empresaEdicao = empresa;

        labelTitulo.setText("Editar Empresa");
        txtCnpj.setText(empresa.getCnpj());
        txtRazaoSocial.setText(empresa.getRazaoSocial());
        txtEmail.setText(empresa.getEmail());
        txtRespLegal.setText(empresa.getRespLegal());
        txtAliquotaFGTS.setText(String.valueOf(empresa.getAliquotaFGTS()));
        txtHorasMensais.setText(String.valueOf(empresa.getHorasMensais()));
        txtValCestaBasic.setText(String.valueOf(empresa.getValCestaBasic()));
        txtPercHoraExtra50.setText(String.valueOf(empresa.getPercHoraExtra50()));
        txtPercHoraExtra100.setText(String.valueOf(empresa.getPercHoraExtra100()));

        txtCnpj.setDisable(true);
    }

    public boolean isSalvoComSucesso() {
        return salvoComSucesso;
    }

    private void fecharJanela() {
        ((Stage) txtCnpj.getScene().getWindow()).close();
    }

    private void exibirAlerta(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }    
}
