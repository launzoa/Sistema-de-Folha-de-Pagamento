/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sfp.folha.ui;

import com.sfp.auditoria.application.ServicoAuditoria;
import com.sfp.core.domain.Empresa;
import com.sfp.core.domain.EnderecoEmpresa;
import com.sfp.empresa.application.ControladorEmpresa;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author manoe
 */
public class TelaEmpresa{
    @FXML private Label labelRazaoSocial;
    @FXML private Label labelCNPJ;
    @FXML private Label labelRespLegal;
    @FXML private Label labelEmail;
    @FXML private Button btnCadastrar; //COLOCAR OS ID NO FXML
    @FXML private Button btnEditar;
    
    @FXML private TableView<EnderecoEmpresa> tabelaEnderecos;
    @FXML private TableColumn<EnderecoEmpresa, String> colCep;
    @FXML private TableColumn<EnderecoEmpresa, String> colLogradouro;
    @FXML private TableColumn<EnderecoEmpresa, String> colBairro;
    @FXML private TableColumn<EnderecoEmpresa, String> colComplemento;

    private ControladorEmpresa controladorEmpresa = new ControladorEmpresa();
    private Empresa empresaAtual = null;
    
    @FXML
    public void initialize() {
        tabelaEnderecos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        colCep.setCellValueFactory(new PropertyValueFactory<>("cep"));
        colLogradouro.setCellValueFactory(new PropertyValueFactory<>("logradouro"));
        colBairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        colComplemento.setCellValueFactory(new PropertyValueFactory<>("complemento"));

        carregarEmpresa();

    }
    
    @FXML
    private void addEmpresa() 
    {
        exibirFormularioEmpresa(null);
    }
    
    @FXML
    private void editarEmpresa() 
    {
        exibirFormularioEmpresa(empresaAtual);
    }
    
    @FXML
    private void excluirEmpresa() 
    {
        if (empresaAtual == null) 
        {
            exibirAlerta("Não há empresa para ser excluida!");
            
            return;
        }

        controladorEmpresa.excluirEmpresa(empresaAtual.getCnpj());
        ServicoAuditoria.registrar("Exclusão", "Empresa", "CNPJ:" + empresaAtual.getCnpj());
        carregarEmpresa();
        limparDadosEmpresa();
    }
    
    @FXML
    private void addEndereco() 
    {
        if (empresaAtual == null) 
        {
            exibirAlerta("Crie uma empresa antes de adicionar endereço.");
            return;
        }

        exibirFormularioEndereco(null, empresaAtual.getCnpj());
    }   
    @FXML
    private void editarEndereco() 
    {
        EnderecoEmpresa endereco = tabelaEnderecos.getSelectionModel().getSelectedItem();

        if (endereco == null) 
        {
            exibirAlerta("Selecione um endereço para editar.");
            return;
        }

        exibirFormularioEndereco(endereco, endereco.getCnpjEmpresa());
    }

    @FXML
    private void excluirEndereco() 
    {
        EnderecoEmpresa endereco = tabelaEnderecos.getSelectionModel().getSelectedItem();

        if (endereco == null) {
            exibirAlerta("Selecione um endereço para excluir.");
            return;
        }

        controladorEmpresa.excluirEndereco(endereco.getId());
        ServicoAuditoria.registrar("Exclusão", "Endereço Empresa", "CEP:" + endereco.getCep());
        atualizarTabelaEnderecos();
    }
    private void limparDadosEmpresa() 
    {
        labelRazaoSocial.setText("-");
        labelCNPJ.setText("-");
        labelRespLegal.setText("-");
        labelEmail.setText("-");
        btnCadastrar.setVisible(true);
        btnEditar.setVisible(false);
        tabelaEnderecos.setItems(FXCollections.observableArrayList());
    }
    private void carregarEmpresa() 
    {
        empresaAtual = controladorEmpresa.buscarEmpresa();
        
        if (empresaAtual!= null) 
        {
            exibirEmpresaSelecionada();
        }
        else
        {
            limparDadosEmpresa();
        }
    }

    private void exibirEmpresaSelecionada() 
    {
        if (empresaAtual == null) {
            labelRazaoSocial.setText("-");
            labelCNPJ.setText("-");
            labelRespLegal.setText("-");
            labelEmail.setText("-");
            tabelaEnderecos.setItems(FXCollections.observableArrayList());
            return;
        }

        labelRazaoSocial.setText(empresaAtual.getRazaoSocial());
        labelCNPJ.setText(empresaAtual.getCnpj());
        labelRespLegal.setText(empresaAtual.getRespLegal());
        labelEmail.setText(empresaAtual.getEmail());
        btnCadastrar.setVisible(false);
        btnEditar.setVisible(true);
        atualizarTabelaEnderecos(); 
    }

    private void atualizarTabelaEnderecos() 
    {
        if (empresaAtual == null) 
        {
            tabelaEnderecos.setItems(FXCollections.observableArrayList());
            return;
        }

        ObservableList<EnderecoEmpresa> enderecos = FXCollections.observableArrayList(controladorEmpresa.listarEnderecos(empresaAtual.getCnpj()));
        tabelaEnderecos.setItems(enderecos);
    }
    
    private void exibirFormularioEmpresa(Empresa empresa) 
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormEmpresa.fxml"));
            Parent rootForm = loader.load();

            FormEmpresa formController = loader.getController();

            if (empresa != null) 
            {
                formController.prepararEdicao(empresa);
            }
            
            if (GerenciadorTema.modoEscuroAtivo) 
            {
                rootForm.getStyleClass().add("dark-mode");
            }
            Stage stage = new Stage();
            Scene scene = new Scene(rootForm, 600, 600);

            stage.setScene(scene);
            stage.setTitle(empresa == null ? "Nova Empresa" : "Editar Empresa");
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            stage.showAndWait();

            if (formController.isSalvoComSucesso()) 
            {
                carregarEmpresa();
            }

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    private void exibirFormularioEndereco(EnderecoEmpresa endereco, String cnpjEmpresa) {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormEndereco.fxml"));
            Parent rootForm = loader.load();

            FormEndereco formController = loader.getController();

            if (endereco == null) 
            {
                formController.prepararCadastro(cnpjEmpresa);
            } 
            else 
            {
                formController.prepararEdicao(endereco);
            }
            
            if (GerenciadorTema.modoEscuroAtivo) 
            {
                rootForm.getStyleClass().add("dark-mode");
            }
            Stage stage = new Stage();
            Scene scene = new Scene(rootForm, 500, 360);

            stage.setScene(scene);
            stage.setTitle(endereco == null ? "Novo Endereço" : "Editar Endereço");
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            stage.showAndWait();

            if (formController.isSalvoComSucesso()) {
                atualizarTabelaEnderecos();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
