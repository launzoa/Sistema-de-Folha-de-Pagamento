package com.sfp.core.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.logging.Logger;

import com.sfp.auditoria.application.ServicoAuditoria;
import com.sfp.usuario.domain.Usuario;

/**
 * @brief Arquivo responsável pelo controller da tela principal.
 *        Ele é o controlador do arquivo FXML da tela principal (MainView.fxml).
 *        Contém os métodos que abrem as telas de acordo com o menu.
 *        Ele opera no estilo Single Page Application (SPA). Em vez de abrir
 *        novas janelas (Stages),
 *        ele carrega as telas dentro do painel de conteúdo central
 *        (AnchorPane).
 */

public class MainController {
    @FXML
    private AnchorPane painelConteudo; // Area da tela principal onde será carregado os menus
    @FXML
    private Label labelTela; // Label que mostra o nome do menu atual
    @FXML
    private Label labelUsuario; // Label que mostra o usuario logado
    @FXML
    private Label labelAdmin; // Label que mostra se o usuario é administrador
    @FXML
    private VBox vboxAdmin; // VBox que contém os elementos da administração

    /**
     * @brief Método que inicializa a tela principal.
     *        É o primeiro método a ser chamado quando a tela é carregada
     *        (obrigatório para telas JavaFX).
     */
    @FXML
    public void initialize() {
        abrirDashboard(); // Abre o dashboard por padrão ao iniciar a tela
    }

    /**
     * @brief Método que define o usuário logado.
     * @param usuario Usuário logado
     */
    public void setUsuario(Usuario usuario) {
        // Define o perfil do usuário
        if (usuario.isPerfil() == true) {
            labelUsuario.setText(usuario.getNome() + " | Administrador");
            labelAdmin.setVisible(true);
            labelAdmin.setManaged(true);
            vboxAdmin.setVisible(true);
            vboxAdmin.setManaged(true);
        } else {
            labelUsuario.setText(usuario.getNome() + " | Operador");
            labelAdmin.setVisible(false);
            labelAdmin.setManaged(false);
            vboxAdmin.setVisible(false);
            vboxAdmin.setManaged(false);
        }
    }

    /**
     * @brief Método que abre o dashboard.
     */
    @FXML
    public void abrirDashboard() {
        navegarPara("Dashboard", "/com/sfp/core/ui/TelaDashboard.fxml");
    }

    /**
     * @brief Método que abre a tela de funcionários.
     */
    @FXML
    public void abrirFuncionarios() {
        navegarPara("Funcionários", "/com/sfp/funcionario/ui/TelaFuncionario.fxml");
    }

    /**
     * @brief Método que abre a tela de rubricas.
     */
    @FXML
    public void abrirRubrica() {
        navegarPara("Rubricas", "/com/sfp/rubrica/ui/TelaRubrica.fxml");
    }

    /**
     * @brief Método que abre a tela de lançamento.
     */
    @FXML
    public void abrirLancamento() {
        navegarPara("Lançamento de Excessões", "/com/sfp/folha/ui/TelaLancamento.fxml");
    }

    /**
     * @brief Método que abre a tela de holerite.
     */
    @FXML
    public void abrirHolerite() {
        navegarPara("Folha de pagamento", "/com/sfp/folha/ui/TelaHolerite.fxml");
    }

    /**
     * @brief Método que abre a tela de usuário.
     */
    @FXML
    public void abrirUsuario() {
        navegarPara("Usuários", "/com/sfp/usuario/ui/TelaUsuario.fxml");
    }

    /**
     * @brief Método que abre a tela de empresa.
     */
    @FXML
    public void abrirEmpresa() {
        navegarPara("Empresas", "/com/sfp/empresa/ui/TelaEmpresa.fxml");
    }

    /**
     * @brief Método que abre a tela de log.
     */
    @FXML
    public void abrirLog() {
        navegarPara("Log de Auditoria", "/com/sfp/auditoria/ui/TelaLog.fxml");
    }

    /**
     * @brief Método que navega para a tela desejada.
     * @param titulo Título da tela
     * @param fxml   FXML da tela
     */
    private void navegarPara(String titulo, String fxml) {
        labelTela.setText(titulo);
        carregarTela(fxml);
    }

    /**
     * @brief Método que carrega a tela.
     * @param fxml Caminho do arquivo FXML (tela a ser carregada)
     */
    private void carregarTela(String fxml) {
        try {
            // Carrega o FXML e define a tela em questão, como o conteúdo principal
            Parent tela = FXMLLoader.load(getClass().getResource(fxml));
            painelConteudo.getChildren().setAll(tela);
            // Define a posição da tela
            AnchorPane.setTopAnchor(tela, 0.0);
            AnchorPane.setBottomAnchor(tela, 0.0);
            AnchorPane.setLeftAnchor(tela, 0.0);
            AnchorPane.setRightAnchor(tela, 0.0);
        } catch (Exception e) { // Em caso de erro
            Logger.getGlobal().severe(e.getMessage());
        }

    }

    /**
     * @brief Método que sai do sistema.
     */
    @FXML
    public void sair() {
        try { // Tenta sair do sistema
            ServicoAuditoria.registrar("Logout", "Sistema", null); // Registra o logout
            // Carrega a tela de login e o FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sfp/autenticacao/ui/TelaLogin.fxml"));
            Parent rootLogin = loader.load();
            // Pega a tela atual e cria a cena de login
            Stage stage = (Stage) painelConteudo.getScene().getWindow();
            Scene scene = new Scene(rootLogin, 1200, 700);
            // Se o modo escuro estiver ativo
            if (GerenciadorTema.modoEscuroAtivo) {
                rootLogin.getStyleClass().add("dark-mode");
            }
            // Define a cena e o título da tela
            stage.setScene(scene);
            stage.setTitle("Sistema de Folha de Pagamento - SFP");
        } catch (Exception e) { // Em caso de erro...
            Logger.getGlobal().severe(e.getMessage());
        }
    }

    /**
     * @brief Método que altera o modo da tela (Claro ou Escuro).
     */
    @FXML
    public void alterarModo() {
        try {
            // Altera o modo atual (se escuro, vira claro e vice-versa)
            GerenciadorTema.modoEscuroAtivo = !GerenciadorTema.modoEscuroAtivo;
            // Pega a tela atual
            Parent rootNode = painelConteudo.getScene().getRoot();

            // Se o modo escuro estiver ativo
            if (GerenciadorTema.modoEscuroAtivo) {
                if (!rootNode.getStyleClass().contains("dark-mode")) {
                    rootNode.getStyleClass().add("dark-mode");
                }
            } else { // Se o modo claro estiver ativo
                rootNode.getStyleClass().removeAll("dark-mode");
            }
        } catch (Exception e) { // Em caso de erro...
            Logger.getGlobal().severe(e.getMessage());
        }
    }
}
