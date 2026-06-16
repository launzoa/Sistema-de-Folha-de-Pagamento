/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sfp.folha.ui;

import com.sfp.auditoria.application.ServicoAuditoria;
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


    private ControladorEmpresa controladorEmpresa = new ControladorEmpresa();
    private Empresa empresaEdicao = null;
    private boolean salvoComSucesso = false;  
 
    @FXML
    public void initialize() {

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
            Empresa empresa = new Empresa(
                    cnpj,
                    razaoSocial,
                    email,
                    respLegal
            );

            if (empresaEdicao == null) 
            {
                controladorEmpresa.cadastrarEmpresa(empresa);
                ServicoAuditoria.registrar("Cadastro", "Empresa", "CNPJ: "+empresa.getCnpj());
            } 
            else 
            {
                controladorEmpresa.atualizarEmpresa(empresa);
                ServicoAuditoria.registrar("Edição", "Empresa", "CNPJ: "+empresa.getCnpj());
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
