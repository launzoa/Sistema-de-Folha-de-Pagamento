/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sfp.folha.ui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.sfp.autenticacao.domain.CatalogoUsuario;
import com.sfp.core.domain.Usuario;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

public class TelaUsuario {

    @FXML private TableView<Usuario> tabelaUsuario;
    @FXML private TableColumn<Usuario, String> colLogin;
    @FXML private TableColumn<Usuario, String> colNome;
    @FXML private TableColumn<Usuario, Boolean> colPerfil;
    @FXML private TableColumn<Usuario, Boolean> colStatus;
    
    private CatalogoUsuario catalogo = new CatalogoUsuario();

    @FXML
    public void initialize() {
        tabelaUsuario.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("nome")); // Using nome as login for now
        colPerfil.setCellValueFactory(new PropertyValueFactory<>("perfil"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        carregarUsuarios();
    } 
    
    private void carregarUsuarios() {
        List<Usuario> lista = catalogo.buscarTodos();
        ObservableList<Usuario> obsList = FXCollections.observableArrayList(lista);
        tabelaUsuario.setItems(obsList);
    }
    
    @FXML
    private void AddUsuario() {
        exibirDialogoUsuario(null);
    }
    
    @FXML
    private void editarUsuario() {
        Usuario user = tabelaUsuario.getSelectionModel().getSelectedItem();
        if (user != null) {
            exibirDialogoUsuario(user);
        } else {
            mostrarAlerta("Aviso", "Selecione um usuário para editar.");
        }
    }
    
    @FXML
    private void excluirUsuario() {
        Usuario user = tabelaUsuario.getSelectionModel().getSelectedItem();
        if (user != null) {
            catalogo.excluir(user.getId());
            carregarUsuarios();
        } else {
            mostrarAlerta("Aviso", "Selecione um usuário para excluir.");
        }
    }
    
    private void exibirDialogoUsuario(Usuario usuarioEdicao) {
        Dialog<Usuario> dialog = new Dialog<>();
        dialog.setTitle(usuarioEdicao == null ? "Novo Usuário" : "Editar Usuário");
        
        ButtonType btnSalvar = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btnSalvar, ButtonType.CANCEL);
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        TextField txtNome = new TextField();
        PasswordField txtSenha = new PasswordField();
        CheckBox chkAdmin = new CheckBox("Administrador?");
        CheckBox chkStatus = new CheckBox("Ativo?");
        chkStatus.setSelected(true);
        
        if (usuarioEdicao != null) {
            txtNome.setText(usuarioEdicao.getNome());
            txtSenha.setText(usuarioEdicao.getSenha());
            chkAdmin.setSelected(usuarioEdicao.isPerfil());
            chkStatus.setSelected(usuarioEdicao.isStatus());
        }
        
        grid.add(new Label("Nome (Login):"), 0, 0);
        grid.add(txtNome, 1, 0);
        grid.add(new Label("Senha:"), 0, 1);
        grid.add(txtSenha, 1, 1);
        grid.add(new Label("Perfil Administrador:"), 0, 2);
        grid.add(chkAdmin, 1, 2);
        grid.add(new Label("Status (Ativo):"), 0, 3);
        grid.add(chkStatus, 1, 3);
        
        dialog.getDialogPane().setContent(grid);
        
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == btnSalvar) {
                Usuario user = new Usuario();
                if (usuarioEdicao != null) user.setId(usuarioEdicao.getId());
                user.setNome(txtNome.getText());
                user.setSenha(txtSenha.getText());
                user.setPerfil(chkAdmin.isSelected());
                user.setStatus(chkStatus.isSelected());
                return user;
            }
            return null;
        });
        
        dialog.showAndWait().ifPresent(resultado -> {
            if (usuarioEdicao == null) {
                catalogo.salvar(resultado);
            } else {
                catalogo.atualizar(resultado);
            }
            carregarUsuarios();
        });
    }

    private void mostrarAlerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
