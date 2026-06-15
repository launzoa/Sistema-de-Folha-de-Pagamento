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
import com.sfp.core.domain.Empresa;
import com.sfp.core.domain.EnderecoEmpresa;
import com.sfp.core.domain.EmpresaRepository;
import com.sfp.infrastructure.persistence.MySQLEmpresaRepository;

public class TelaEmpresa {

    @FXML private ComboBox<Empresa> comboEmpresas;
    @FXML private Label labelRazaoSocial;
    @FXML private Label labelCNPJ;
    @FXML private Label labelRespLegal;
    @FXML private Label labelEmail;

    @FXML private TableView<EnderecoEmpresa> tabelaEnderecos;
    @FXML private TableColumn<EnderecoEmpresa, String> colCep;
    @FXML private TableColumn<EnderecoEmpresa, String> colLogradouro;
    @FXML private TableColumn<EnderecoEmpresa, String> colBairro;
    @FXML private TableColumn<EnderecoEmpresa, String> colComplemento;

    private EmpresaRepository repo = new MySQLEmpresaRepository();

    @FXML
    public void initialize() {
        tabelaEnderecos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        colCep.setCellValueFactory(new PropertyValueFactory<>("cep"));
        colLogradouro.setCellValueFactory(new PropertyValueFactory<>("logradouro"));
        colBairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        colComplemento.setCellValueFactory(new PropertyValueFactory<>("complemento"));

        carregarEmpresas();

        comboEmpresas.setOnAction(event -> mostrarDetalhesEmpresa());
    }

    private void carregarEmpresas() {
        List<Empresa> empresas = repo.buscarTodas();
        comboEmpresas.getItems().setAll(empresas);
        if (!empresas.isEmpty()) {
            comboEmpresas.getSelectionModel().selectFirst();
            mostrarDetalhesEmpresa();
        }
    }

    private void mostrarDetalhesEmpresa() {
        Empresa emp = comboEmpresas.getValue();
        if (emp != null) {
            labelRazaoSocial.setText(emp.getRazaoSocial());
            labelCNPJ.setText(emp.getCnpj());
            labelRespLegal.setText(emp.getRespLegal());
            labelEmail.setText(emp.getEmail());

            ObservableList<EnderecoEmpresa> obsEnderecos = FXCollections.observableArrayList(emp.getEnderecos());
            tabelaEnderecos.setItems(obsEnderecos);
        } else {
            labelRazaoSocial.setText("—");
            labelCNPJ.setText("—");
            labelRespLegal.setText("—");
            labelEmail.setText("—");
            tabelaEnderecos.setItems(FXCollections.observableArrayList());
        }
    }

    @FXML
    private void novaEmpresa() {
        exibirDialogoEmpresa(null);
    }

    @FXML
    private void editarEmpresa() {
        Empresa emp = comboEmpresas.getValue();
        if (emp != null) {
            exibirDialogoEmpresa(emp);
        } else {
            mostrarAlerta("Aviso", "Selecione uma empresa para editar.");
        }
    }

    @FXML
    private void excluirEmpresa() {
        Empresa emp = comboEmpresas.getValue();
        if (emp != null) {
            repo.excluir(emp.getCnpj());
            carregarEmpresas();
        } else {
            mostrarAlerta("Aviso", "Selecione uma empresa para excluir.");
        }
    }

    private void exibirDialogoEmpresa(Empresa empresaEdicao) {
        Dialog<Empresa> dialog = new Dialog<>();
        dialog.setTitle(empresaEdicao == null ? "Nova Empresa" : "Editar Empresa");
        
        ButtonType btnSalvar = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btnSalvar, ButtonType.CANCEL);
        
        javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));
        
        TextField txtCnpj = new TextField();
        TextField txtRazao = new TextField();
        TextField txtEmail = new TextField();
        TextField txtResp = new TextField();
        
        if (empresaEdicao != null) {
            txtCnpj.setText(empresaEdicao.getCnpj());
            txtCnpj.setDisable(true); // CNPJ is Primary Key
            txtRazao.setText(empresaEdicao.getRazaoSocial());
            txtEmail.setText(empresaEdicao.getEmail());
            txtResp.setText(empresaEdicao.getRespLegal());
        }
        
        grid.add(new Label("CNPJ:"), 0, 0);
        grid.add(txtCnpj, 1, 0);
        grid.add(new Label("Razão Social:"), 0, 1);
        grid.add(txtRazao, 1, 1);
        grid.add(new Label("Email:"), 0, 2);
        grid.add(txtEmail, 1, 2);
        grid.add(new Label("Responsável Legal:"), 0, 3);
        grid.add(txtResp, 1, 3);
        
        dialog.getDialogPane().setContent(grid);
        
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == btnSalvar) {
                Empresa emp = new Empresa();
                emp.setCnpj(txtCnpj.getText());
                emp.setRazaoSocial(txtRazao.getText());
                emp.setEmail(txtEmail.getText());
                emp.setRespLegal(txtResp.getText());
                
                // Keep existing advanced configs if editing, else defaults
                if (empresaEdicao != null) {
                    emp.setAliquotaFgts(empresaEdicao.getAliquotaFgts());
                    emp.setHorasMensais(empresaEdicao.getHorasMensais());
                    emp.setValCestaBasic(empresaEdicao.getValCestaBasic());
                    emp.setPercHoraExtra50(empresaEdicao.getPercHoraExtra50());
                    emp.setPercHoraExtra100(empresaEdicao.getPercHoraExtra100());
                } else {
                    emp.setAliquotaFgts(8.0);
                    emp.setHorasMensais(220);
                    emp.setPercHoraExtra50(50);
                    emp.setPercHoraExtra100(100);
                }
                
                return emp;
            }
            return null;
        });
        
        dialog.showAndWait().ifPresent(resultado -> {
            if (empresaEdicao == null) {
                repo.salvar(resultado);
            } else {
                repo.atualizar(resultado);
            }
            carregarEmpresas();
        });
    }

    @FXML
    private void novoEndereco() {
        Empresa emp = comboEmpresas.getValue();
        if (emp != null) {
            exibirDialogoEndereco(null, emp.getCnpj());
        } else {
            mostrarAlerta("Aviso", "Selecione uma empresa antes de adicionar endereço.");
        }
    }

    @FXML
    private void editarEndereco() {
        EnderecoEmpresa end = tabelaEnderecos.getSelectionModel().getSelectedItem();
        if (end != null) {
            mostrarAlerta("Aviso", "Para editar, por favor exclua e recrie o endereço (em desenvolvimento).");
        } else {
            mostrarAlerta("Aviso", "Selecione um endereço para editar.");
        }
    }

    @FXML
    private void excluirEndereco() {
        EnderecoEmpresa end = tabelaEnderecos.getSelectionModel().getSelectedItem();
        if (end != null) {
            repo.excluirEndereco(end.getId());
            mostrarDetalhesEmpresa(); // Refresh ends without DB call just for speed? 
            carregarEmpresas(); // Reloads from DB
        } else {
            mostrarAlerta("Aviso", "Selecione um endereço para excluir.");
        }
    }

    private void exibirDialogoEndereco(EnderecoEmpresa enderecoEdicao, String cnpjEmpresa) {
        Dialog<EnderecoEmpresa> dialog = new Dialog<>();
        dialog.setTitle("Novo Endereço");
        
        ButtonType btnSalvar = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btnSalvar, ButtonType.CANCEL);
        
        javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));
        
        TextField txtCep = new TextField();
        TextField txtLogra = new TextField();
        TextField txtBairro = new TextField();
        TextField txtComp = new TextField();
        
        grid.add(new Label("CEP:"), 0, 0);
        grid.add(txtCep, 1, 0);
        grid.add(new Label("Logradouro:"), 0, 1);
        grid.add(txtLogra, 1, 1);
        grid.add(new Label("Bairro:"), 0, 2);
        grid.add(txtBairro, 1, 2);
        grid.add(new Label("Complemento:"), 0, 3);
        grid.add(txtComp, 1, 3);
        
        dialog.getDialogPane().setContent(grid);
        
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == btnSalvar) {
                EnderecoEmpresa end = new EnderecoEmpresa();
                end.setCnpjEmpresa(cnpjEmpresa);
                end.setCep(txtCep.getText());
                end.setLogradouro(txtLogra.getText());
                end.setBairro(txtBairro.getText());
                end.setComplemento(txtComp.getText());
                return end;
            }
            return null;
        });
        
        dialog.showAndWait().ifPresent(resultado -> {
            repo.salvarEndereco(resultado);
            carregarEmpresas();
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
