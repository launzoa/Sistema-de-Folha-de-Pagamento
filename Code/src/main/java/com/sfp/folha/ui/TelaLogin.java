/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sfp.folha.ui;

/**
 *
 * @author igor.nogueira_unesp
 */

import com.sfp.autenticacao.application.ControladorAutenticacao;
import com.sfp.core.domain.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class TelaLogin {
    @FXML
    private TextField inputUsuario;
    @FXML
    private PasswordField inputSenha;
    @FXML
    private Label labelErro;

    private ControladorAutenticacao controlador = new ControladorAutenticacao();

    @FXML
    public void fazerLogin() {
        String nome = inputUsuario.getText();
        String senha = inputSenha.getText();

        Usuario user = controlador.autenticar(nome, senha);

        if (user != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sfp/folha/ui/MainView.fxml"));
                Stage stage = (Stage) inputUsuario.getScene().getWindow();
                Scene scene = new Scene(loader.load(), 1200, 700);

                MainController mainController = loader.getController();
                mainController.setUsuario(user);

                stage.setScene(scene);
                stage.setTitle("SPF - Sistema de Folha de Pagamento");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            labelErro.setText("Usario ou senha inválidos. ");
        }

    }
}
