/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sfp.folha.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.sfp.core.domain.EnderecoEmpresa;
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
public class FormEndereco{
    @FXML private Label labelTitulo;
    @FXML private TextField txtCep;
    @FXML private TextField txtLogradouro;
    @FXML private TextField txtBairro;
    @FXML private TextField txtComplemento;

    private ControladorEmpresa controladorEmpresa = new ControladorEmpresa();
    private EnderecoEmpresa enderecoEdicao = null;
    private String cnpjEmpresa;
    private boolean salvoComSucesso = false; 
    
    @FXML
    public void salvar() 
    {
        String cep = txtCep.getText();
        String logradouro = txtLogradouro.getText();
        String bairro = txtBairro.getText();
        String complemento = txtComplemento.getText();

        if (cnpjEmpresa == null || cnpjEmpresa.trim().isEmpty()) 
        {
            exibirAlerta("Selecione uma empresa antes de cadastrar endereço.");
            return;
        }

        if (cep == null || cep.trim().isEmpty()) 
        {
            exibirAlerta("Informe o CEP.");
            return;
        }

        if (logradouro == null || logradouro.trim().isEmpty()) 
        {
            exibirAlerta("Informe o logradouro.");
            return;
        }

        if (enderecoEdicao == null) 
        {
            EnderecoEmpresa endereco = new EnderecoEmpresa(0, cnpjEmpresa, cep, logradouro, bairro, complemento);
            controladorEmpresa.cadastrarEndereco(endereco);
        } 
        else 
        {
            EnderecoEmpresa endereco = new EnderecoEmpresa(enderecoEdicao.getId(), cnpjEmpresa, cep, logradouro, bairro, complemento);
            controladorEmpresa.atualizarEndereco(endereco);
        }

        salvoComSucesso = true;
        fecharJanela();
    }

    @FXML
    public void cancelar() 
    {
        fecharJanela();
    }

    public void prepararCadastro(String cnpjEmpresa) 
    {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public void prepararEdicao(EnderecoEmpresa endereco) 
    {
        this.enderecoEdicao = endereco;
        this.cnpjEmpresa = endereco.getCnpjEmpresa();

        labelTitulo.setText("Editar Endereço");
        txtCep.setText(endereco.getCep());
        txtLogradouro.setText(endereco.getLogradouro());
        txtBairro.setText(endereco.getBairro());
        txtComplemento.setText(endereco.getComplemento());
    }

    public boolean isSalvoComSucesso() 
    {
        return salvoComSucesso;
    }

    private void fecharJanela() 
    {
        ((Stage) txtCep.getScene().getWindow()).close();
    }

    private void exibirAlerta(String mensagem) 
    {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }    
}
