/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sfp.folha.ui;

import com.sfp.core.domain.Usuario;
import com.sfp.usuario.application.ControladorUsuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author manoe
 */
public class FormUsuario{
    @FXML private Label labelTitulo;
    @FXML private TextField txtNome;
    @FXML private PasswordField txtSenha;
    
    @FXML private ComboBox<String> comboPerfil;
    @FXML private CheckBox checkAtivo;
    
    private ControladorUsuario controladorBD = new ControladorUsuario();
    private Usuario usuarioEdicao = null; // Se manter null, é CADASTRO. Se receber objeto, é EDIÇÃO.
    private boolean salvoComSucesso = false;
    
    @FXML
    public void initialize()
    {
        comboPerfil.getItems().addAll("Administrador", "Operador");
    }   
    
    @FXML
    public void salvar()
    {
        String nome = txtNome.getText();
        String senha = txtSenha.getText();
        String perfilSelecionado = comboPerfil.getValue();
        
        if (nome == null || nome.trim().isEmpty())
        {
            exibirAlerta("Informe o nome.");
            return;
        }

        if (senha == null || senha.trim().isEmpty())
        {
            exibirAlerta("Informe a senha.");
            return;
        }

        if (perfilSelecionado == null)
        {
            exibirAlerta("Selecione o perfil.");
            return;
        }
        
        boolean ehAdmin = "Administrador".equals(perfilSelecionado);
        boolean ativo = checkAtivo.isSelected();

        if (usuarioEdicao == null)
        {
            controladorBD.cadastrarUsuario(nome, senha, ehAdmin);
        } 
        else
        {
            controladorBD.atualizarUsuario(usuarioEdicao.getId(), nome, senha, ativo);
        }

        salvoComSucesso = true;
        fecharJanela();        
    }
    
    @FXML
    public void cancelar()
    {
        fecharJanela();    
    }
    
    public void prepararEdicao(Usuario user)
    {
        this.usuarioEdicao = user;
        labelTitulo.setText("Editar Usuário");
        
        //Preenche os inputs
        txtNome.setText(user.getNome());
        txtSenha.setText(user.getSenha());
        comboPerfil.setValue(user.isPerfil() ? "Administrador" : "Operador");
        checkAtivo.setSelected(user.isStatus());
        
        comboPerfil.setDisable(true);
    }
    public boolean isSalvoComSucesso()
    { 
        return salvoComSucesso; 
    }
    
    private void fecharJanela()
    {
        ((Stage) txtNome.getScene().getWindow()).close();
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
