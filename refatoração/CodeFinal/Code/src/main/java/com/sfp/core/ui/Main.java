package com.sfp.core.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.logging.Logger;

/**
 * @brief Classe responsável por iniciar a aplicação.
 *        Ele lê o fxml da tela de login e inicia o sistema.
 */
public class Main extends Application {

    /**
     * @brief Método responsável por iniciar a aplicação
     * @param primaryStage Janela principal da aplicação
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // O JavaFX procura o arquivo fxml dentro de 'src/main/resources'
        URL fxmlLocation = getClass().getResource("/com/sfp/autenticacao/ui/TelaLogin.fxml");

        if (fxmlLocation == null) { // Lança um erro se o arquivo não for encontrado
            throw new IllegalStateException(
                    "Nao foi possivel encontrar o arquivo TelaLogin.fxml. Verifique a pasta resources.");
        }

        Parent root = FXMLLoader.load(fxmlLocation); // Carrega a tela de login
        primaryStage.setTitle("SFP - Login");

        // Define o icone da janela (Barra de tarefas)
        URL iconLocation = getClass().getResource("/com/sfp/core/ui/gato.png");
        if (iconLocation != null) { // Adiciona o icone à janela
            primaryStage.getIcons().add(new Image(iconLocation.toExternalForm()));
        } else { // Se o icone não for encontrado, lança um aviso
            Logger.getGlobal().severe("Aviso: Icone gato.png nao encontrado no pacote core/ui!");
        }

        // Define o tamanho inicial da tela
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);
        primaryStage.setResizable(true); // Específico para o Hyprland — archlinux traz alguns problemas...
        primaryStage.show();
    }

    /**
     * @brief Gatilho inicial para o carregamento da aplicação
     * @param args
     */
    public static void main(String[] args) {
        launch(args); // Inicia o sistema
    }
}
