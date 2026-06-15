/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sfp.folha.ui;

import com.sfp.core.domain.Usuario;
import com.sfp.usuario.application.ControladorUsuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author manoe
 */
public class TelaUsuario{
    //TODO:
    //VER SE VAI TER A OPÇÃO DE EXCLUIR USUÁRIO OU SÓ DESATIVA PELO ATRIBUTO STATUS
    @FXML private TableView<Usuario> tabelaUsuario;
    //@FXML private TableColumn colLogin;
    @FXML private TableColumn colNome;
    @FXML private TableColumn colPerfil;
    @FXML private TableColumn colStatus;
   // @FXML private TableColumn colUltimoAcesso;
    
    private ControladorUsuario controladorUsuario = new ControladorUsuario();
    
    @FXML
    public void initialize()
    {
        tabelaUsuario.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colPerfil.setCellValueFactory(new PropertyValueFactory<>("perfil"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        atualizarTabela();
    } 
    
    @FXML
    private void AddUsuario()
    {
        exibirFormulario(null);
    }
    
    @FXML
    private void editarUsuario()
    {
        Usuario selecionado = tabelaUsuario.getSelectionModel().getSelectedItem();
        if (selecionado != null)
        {
            exibirFormulario(selecionado); // Passa o selecionado para o formulário carregar os dados
        } 
        else
        {
        // Alerta avisando para selecionar alguém na tabela
        }
    }
    
    @FXML
    private void excluirUsuario()
    {
    }
    
    
    private void exibirFormulario(Usuario usuario)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormUsuario.fxml"));
            Parent rootForm = loader.load();

            FormUsuario formController = loader.getController();

            //Se passado usuário avisa que é modo EDIÇÃO
            if(usuario != null)
            {
                formController.prepararEdicao(usuario);
            }

            //Cria pop-up
            Stage stage = new Stage();
            Scene scene = new Scene(rootForm,500,400);

            // >>> SUPREMO CUIDADO COM O MODO ESCURO GLOBLAL: Sincroniza a cor do Pop-up!
            if(GerenciadorTema.modoEscuroAtivo)
            {
                rootForm.getStyleClass().add("dark-mode");
            }

            stage.setScene(scene);
            stage.setTitle(usuario == null ? "Novo Usuário" : "Editar Usuário");
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL); //Bloqueia a tela de trás enquanto digita
            stage.showAndWait(); // Trava a execução até fechar o pop-up

            //Se salvou no banco, recarrega a tabela principal atualizada
            if (formController.isSalvoComSucesso()) {
                atualizarTabela(); 
            }

        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void atualizarTabela()
    {
        ObservableList<Usuario> lista = FXCollections.observableArrayList(controladorUsuario.listarUsuarios());
        tabelaUsuario.setItems(lista);
    }
}
