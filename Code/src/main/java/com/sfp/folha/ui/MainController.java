
package com.sfp.folha.ui;

/**
 *
 * @author manoe
 */
import com.sfp.core.domain.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainController {
    @FXML
    private AnchorPane painelConteudo;
    @FXML
    private Label labelTela;
    @FXML
    private Label labelUsuario;

    private Usuario userLogado;
    private boolean modoEscuroAtivo = false;
    
    @FXML
    public void initialize() {
        abrirDashboard();
    }

    public void setUsuario(Usuario usuario)
    {
        this.userLogado = usuario;
        if(usuario.isPerfil() == true)
        {
            labelUsuario.setText(usuario.getNome() + " | Administrador");
        }
        else
        {
            labelUsuario.setText(usuario.getNome() + " | Operador");
        }
    }

    @FXML
    public void abrirDashboard() {
        carregarTela("TelaDashboard.fxml");
        labelTela.setText("Dashboard");
    }

    @FXML
    public void abrirFuncionarios() {
        carregarTela("TelaFuncionario.fxml");
        labelTela.setText("Funcionários");
    }



    @FXML
    public void abrirRubrica() {
        carregarTela("TelaRubrica.fxml");
        labelTela.setText("Rubricas");
    }

    @FXML
    public void abrirLancamento() {
        carregarTela("TelaLancamento.fxml");
        labelTela.setText("Lançamento de Excessões");
    }

    @FXML
    public void abrirFolhaMes() {
        carregarTela("TelaFolhaMes.fxml");
        labelTela.setText("Folha do Mês");
    }

    @FXML
    public void abrirHolerite() {
        carregarTela("TelaHolerite.fxml");
        labelTela.setText("Holerites");
    }

    @FXML
    public void abrirHistorico() {
        carregarTela("TelaHistorico.fxml");
        labelTela.setText("Histórico de Folhas");
    }

    @FXML
    public void abrirUsuario() {
        carregarTela("TelaUsuario.fxml");
        labelTela.setText("Usuários");
    }

    @FXML
    public void abrirEmpresa() {
        carregarTela("TelaEmpresa.fxml");
        labelTela.setText("Empresas");
    }

    @FXML
    public void abrirParametro() {
        carregarTela("TelaParametro.fxml");
        labelTela.setText("Parâmetros Legais");
    }

    @FXML
    public void abrirLog() {
        carregarTela("TelaLog.fxml");
        labelTela.setText("Log de Auditoria"); 
    }

    private void carregarTela(String fxml) {
        try {
            Parent tela = FXMLLoader.load(getClass().getResource(fxml));
            painelConteudo.getChildren().setAll(tela);
            AnchorPane.setTopAnchor(tela, 0.0);
            AnchorPane.setBottomAnchor(tela, 0.0);
            AnchorPane.setLeftAnchor(tela, 0.0);
            AnchorPane.setRightAnchor(tela, 0.0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void sair() {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaLogin.fxml"));
            Parent rootLogin = loader.load();
            Stage stage = (Stage) painelConteudo.getScene().getWindow();
            Scene scene = new Scene(rootLogin, 1200, 700);
            if (GerenciadorTema.modoEscuroAtivo)
            {
                rootLogin.getStyleClass().add("dark-mode");
            }
            stage.setScene(scene);
            stage.setTitle("Sistema de Folha de Pagamento - SFP");
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }  
    }
    @FXML
    public void alterarModo()
    {
        try
        {
            GerenciadorTema.modoEscuroAtivo = !GerenciadorTema.modoEscuroAtivo;
            Parent rootNode = painelConteudo.getScene().getRoot();

            if (GerenciadorTema.modoEscuroAtivo)
            {
                if(!rootNode.getStyleClass().contains("dark-mode"))
                {
                    rootNode.getStyleClass().add("dark-mode");    
                }
            }
            else
            {
                rootNode.getStyleClass().removeAll("dark-mode");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }     
    }
    private void mostrarAlertaEmDesenvolvimento(String modulo) 
    {
        javafx.scene.control.Alert alerta = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alerta.setTitle("Módulo em Desenvolvimento");
        alerta.setHeaderText(null);
        alerta.setContentText("O módulo '" + modulo + "' está em fase de desenvolvimento e será disponibilizado nas próximas atualizações.");
        alerta.showAndWait();
    }
}
