package com.sfp.folha.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // O JavaFX procura o arquivo fxml dentro de src/main/resources respeitando o
        // caminho do pacote
        URL fxmlLocation = getClass().getResource("TelaLogin.fxml");

        if (fxmlLocation == null) {
            throw new IllegalStateException(
                    "Nao foi possivel encontrar o arquivo TelaLogin.fxml. Verifique a pasta resources.");
        }

        Parent root = FXMLLoader.load(fxmlLocation);
        primaryStage.setTitle("SFP - Login");

        // Define o icone da janela (Barra de tarefas)
        URL iconLocation = getClass().getResource("gato.png");
        if (iconLocation != null) {
            primaryStage.getIcons().add(new javafx.scene.image.Image(iconLocation.toString()));
        }

        // Define o tamanho inicial da tela
        primaryStage.setScene(new Scene(root, 650, 600));
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);
        primaryStage.setResizable(true); // para o Hyprland poder esticar a janela
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Gatilho inicial da aplicacao visual
        launch(args);
    }
}
