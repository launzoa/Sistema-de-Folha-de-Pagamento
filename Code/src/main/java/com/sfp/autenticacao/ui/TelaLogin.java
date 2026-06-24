package com.sfp.autenticacao.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import com.sfp.core.database.ServicoDatabase;
import com.sfp.core.ui.MainController;
import com.sfp.core.ui.GerenciadorTema;
import com.sfp.autenticacao.application.ControladorAutenticacao;
import com.sfp.usuario.domain.Usuario;

/**
 * @brief Controla a tela de login.
 */
public class TelaLogin {
    @FXML
    private TextField inputUsuario; // Campo de texto para inserir o usuário.
    @FXML
    private PasswordField inputSenha; // Campo de texto para inserir a senha.
    @FXML
    private Label labelErro; // Label para exibir mensagens de erro.

    private ControladorAutenticacao controlador = new ControladorAutenticacao();

    /**
     * @brief Método que faz o login do usuário.
     */
    @FXML
    public void fazerLogin() {
        // Pega o usuário e a senha dos campos de texto.
        String nome = inputUsuario.getText();
        String senha = inputSenha.getText();
        // Tenta autenticar o usuário.
        Usuario user = controlador.autenticar(nome, senha);

        if (user != null) { // Se o usuário for válido.
            try {
                // Carrega a tela principal.
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sfp/core/ui/MainView.fxml"));
                Parent rootLogin = loader.load();
                // Obtém a janela atual.
                Stage stage = (Stage) inputUsuario.getScene().getWindow();
                // Cria a cena com a tela principal.
                Scene scene = new Scene(rootLogin, 1200, 700);
                // Verifica se o modo escuro está ativo e aplica o estilo.
                if (GerenciadorTema.modoEscuroAtivo) {
                    rootLogin.getStyleClass().add("dark-mode");
                }
                // Obtém o controlador da tela principal.
                MainController mainController = loader.getController();
                // Seta o usuário na tela principal.
                mainController.setUsuario(user);

                // Seta a cena na janela.
                stage.setScene(scene);
                stage.setTitle("SPF - Sistema de Folha de Pagamento");
            } catch (Exception e) { // Se ocorrer um erro, imprime o stack trace.
                e.printStackTrace();
            }
        } else { // Se o usuário for inválido.
            labelErro.setText("Usario ou senha inválidos.");
        }
    }

    /**
     * @brief Método que zera o banco de dados.
     */
    @FXML
    public void acaoZerarDados() {
        // Mostra um alerta de confirmação.
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Tem certeza que deseja APAGAR TODOS OS DADOS do sistema?\nUm usuário admin/admin será recriado.",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) { // Se o usuário confirmar.
            try {
                // Cria um novo serviço de banco de dados.
                ServicoDatabase servico = new ServicoDatabase();
                // Zera o banco de dados.
                servico.zerarBancoDeDados();
                // Mostra um alerta de informação.
                Alert info = new Alert(Alert.AlertType.INFORMATION,
                        "Banco de dados apagado com sucesso. Entre com admin/admin.");
                info.showAndWait();
            } catch (Exception e) { // Se ocorrer um erro, imprime o stack trace.
                Alert erro = new Alert(Alert.AlertType.ERROR, "Erro ao zerar: " + e.getMessage());
                erro.showAndWait();
            }
        }
    }

    /**
     * @brief Método que gera o teste de mesa.
     */
    @FXML
    public void acaoGerarTesteMesa() {
        try {
            // Cria um novo serviço de banco de dados.
            ServicoDatabase servico = new ServicoDatabase();
            // Gera o teste de mesa.
            servico.gerarTesteDeMesa();
            // Mostra um alerta de informação.
            Alert info = new Alert(Alert.AlertType.INFORMATION,
                    "Teste de mesa (Unesp) gerado com sucesso!\nEntre com admin/admin.");
            info.showAndWait();
        } catch (Exception e) { // Se ocorrer um erro, imprime o stack trace.
            Alert erro = new Alert(Alert.AlertType.ERROR, "Erro ao gerar teste: " + e.getMessage());
            erro.showAndWait();
        }
    }
}
