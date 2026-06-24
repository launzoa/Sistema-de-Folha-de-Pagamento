/**
 * @brief Classe responsavel por gerenciar o formulario de cadastro de empresas
 
 */
package com.sfp.empresa.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import com.sfp.empresa.domain.Empresa;
import com.sfp.empresa.application.ControladorEmpresa;

public class FormEmpresa {
    @FXML
    private Label labelTitulo; // Label para exibir o título do formulário
    @FXML
    private TextField txtCnpj; // TextField para exibir o CNPJ
    @FXML
    private TextField txtRazaoSocial; // TextField para exibir a razão social
    @FXML
    private TextField txtEmail; // TextField para exibir o email
    @FXML
    private TextField txtRespLegal; // TextField para exibir o responsável legal
    @FXML
    private ComboBox<Integer> cbDiaFechamentoPonto; // ComboBox para exibir o dia de fechamento do ponto

    // Atributos para gerenciar o estado do formulário
    private ControladorEmpresa controladorEmpresa = new ControladorEmpresa();
    private Empresa empresaEdicao = null; // Objeto para armazenar a empresa que está sendo editada
    private boolean salvoComSucesso = false; // Flag para indicar se o cadastro foi realizado com sucesso

    /**
     * @brief Inicializa o formulário de cadastro de empresas
     */
    @FXML
    public void initialize() {
        cbDiaFechamentoPonto.getItems().addAll(1, 15, 20, 25, 30); // Adiciona os dias de fechamento do ponto
        cbDiaFechamentoPonto.getSelectionModel().select(Integer.valueOf(30)); // Seleciona o dia de fechamento do ponto
    }

    /**
     * @brief Salva o formulário de cadastro de empresas
     */
    @FXML
    public void salvar() {
        // Obtém os dados do formulário
        String cnpj = txtCnpj.getText();
        String razaoSocial = txtRazaoSocial.getText();
        String email = txtEmail.getText();
        String respLegal = txtRespLegal.getText();
        Integer diaFechamento = cbDiaFechamentoPonto.getValue();

        // Valida os dados do formulário
        if (cnpj == null || cnpj.trim().isEmpty()) {
            exibirAlerta("Informe o CNPJ.");
            return;
        }
        if (email == null || email.trim().isEmpty()) {
            exibirAlerta("Informe o E-mail de contato.");
            return;
        }
        if (razaoSocial == null || razaoSocial.trim().isEmpty()) {
            exibirAlerta("Informe a razão social.");
            return;
        }
        if (respLegal == null || respLegal.trim().isEmpty()) {
            exibirAlerta("Informe o responsável legal.");
            return;
        }
        if (diaFechamento == null) {
            diaFechamento = 30;
        }

        // Tenta criar ou atualizar a empresa
            if (empresaEdicao == null) { // Se não tiver empresa para editar, cadastra nova
                Empresa empresa = new Empresa(
                        cnpj,
                        razaoSocial,
                        email,
                        respLegal,
                        diaFechamento);
                controladorEmpresa.cadastrarEmpresa(empresa);
            } else { // Se tiver empresa para editar, atualiza
                Empresa empresa = new Empresa(
                        cnpj,
                        razaoSocial,
                        email,
                        respLegal,
                        diaFechamento);
                controladorEmpresa.atualizarEmpresa(empresa);
            }

            salvoComSucesso = true;
            fecharJanela();
    }

    /**
     * @brief Cancela o cadastro de empresas
     */
    @FXML
    public void cancelar() {
        fecharJanela();
    }

    /**
     * @brief Prepara o formulário para edição de empresas
     * @param empresa Empresa que será editada
     */
    public void prepararEdicao(Empresa empresa) {
        empresaEdicao = empresa;
        labelTitulo.setText("Editar Empresa"); // Altera o título do formulário
        // Preenche os campos com os dados da empresa
        txtCnpj.setText(empresa.getCnpj());
        txtRazaoSocial.setText(empresa.getRazaoSocial());
        txtEmail.setText(empresa.getEmail());
        txtRespLegal.setText(empresa.getRespLegal());
        cbDiaFechamentoPonto.getSelectionModel().select(Integer.valueOf(empresa.getDiaFechamentoPonto()));
        txtCnpj.setDisable(true); // Impede a edição do CNPJ
    }

    /**
     * @brief Retorna se o cadastro foi realizado com sucesso
     * @return Boolean
     */
    public boolean isSalvoComSucesso() {
        return salvoComSucesso;
    }

    /**
     * @brief Fecha a janela
     */
    private void fecharJanela() {
        ((Stage) txtCnpj.getScene().getWindow()).close();
    }

    /**
     * @brief Exibe um alerta
     * @param mensagem Mensagem do alerta
     */
    private void exibirAlerta(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
