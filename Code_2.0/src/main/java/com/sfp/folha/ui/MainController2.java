/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

public class MainController2 {
    @FXML private AnchorPane painelConteudo;
    @FXML private Label labelTela;
    @FXML private Label labelUsuario;
    
     private Usuario userLogado;
     
    @FXML
    public void initialize()
    {
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
    public void abrirDashboard()
    {
        carregarTela("TelaDashboard.fxml");
        labelTela.setText("Dashboard");
    }
    
    @FXML
    public void abrirFuncionarios()
    {
        carregarTela("TelaFuncionario.fxml");
        labelTela.setText("Funcionários");
    }
    
    
    @FXML
    public void abrirRubrica()
    {
        carregarTela("TelaRubrica.fxml");
        labelTela.setText("Rubricas");
    }
    
    @FXML
    public void abrirLancamento()
    {
        carregarTela("TelaLancamento.fxml");
        labelTela.setText("Lançamento de Excessões");
    }
    
    @FXML
    public void abrirFolhaMes()
    {
        carregarTela("TelaFolhaMes.fxml");
        labelTela.setText("Folha do Mês");
    }
    
    @FXML
    public void abrirHolerite()
    {
        carregarTela("TelaHolerite.fxml");
        labelTela.setText("Holerites");
    }
    
    @FXML
    public void abrirHistorico()
    {
        carregarTela("TelaHistorico.fxml");
        labelTela.setText("Histórico de Folhas");
    }
    
    @FXML
    public void abrirUsuario()
    {
        carregarTela("TelaUsuario.fxml");
        labelTela.setText("Usuários");
    }
    
    @FXML
    public void abrirEmpresa()
    {
        carregarTela("TelaEmpresa.fxml");
        labelTela.setText("Empresas");
    }
    @FXML
    public void abrirParametro()
    {
        carregarTela("TelaParametro.fxml");
        labelTela.setText("Parâmetros Legais");        
    }
    
    @FXML
    public void abrirLog()
    {
        carregarTela("TelaLog.fxml");
        labelTela.setText("Log de Auditoria");        
    }
    private void carregarTela(String fxml)
    {
        try
        {
            Parent tela = FXMLLoader.load(getClass().getResource(fxml));
            painelConteudo.getChildren().setAll(tela);
            AnchorPane.setTopAnchor(tela, 0.0);
            AnchorPane.setBottomAnchor(tela, 0.0);
            AnchorPane.setLeftAnchor(tela, 0.0);
            AnchorPane.setRightAnchor(tela, 0.0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @FXML
    public void sair()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaLogin.fxml"));
            Stage stage = (Stage) painelConteudo.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1200, 700);
            stage.setScene(scene);
            stage.setTitle("Sistema de Folha de Pagamento - SFP");
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }        
    }
}
