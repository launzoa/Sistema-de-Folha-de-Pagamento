package com.sfp.empresa.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import com.sfp.auditoria.application.ServicoAuditoria;
import com.sfp.empresa.domain.EnderecoEmpresa;
import com.sfp.empresa.application.ControladorEmpresa;

/**
 * @brief Classe que representa o formulario de endereço de empresa
 */
public class FormEndereco {
    @FXML
    private Label labelTitulo; // Título do formulario
    @FXML
    private TextField txtCep; // Campo de CEP
    @FXML
    private TextField txtLogradouro; // Campo de logradouro
    @FXML
    private TextField txtBairro; // Campo de bairro
    @FXML
    private TextField txtComplemento; // Campo de complemento

    private ControladorEmpresa controladorEmpresa = new ControladorEmpresa();
    private EnderecoEmpresa enderecoEdicao = null;
    private String cnpjEmpresa;
    private boolean salvoComSucesso = false; // Flag de sucesso

    /**
     * @brief Método responsável por salvar o endereço
     */
    @FXML
    public void salvar() {
        // Pega os dados do formulario
        String cep = txtCep.getText();
        String logradouro = txtLogradouro.getText();
        String bairro = txtBairro.getText();
        String complemento = txtComplemento.getText();

        // Validação dos dados
        if (cnpjEmpresa == null || cnpjEmpresa.trim().isEmpty()) {
            exibirAlerta("Selecione uma empresa antes de cadastrar endereço.");
            return;
        }
        if (cep == null || cep.trim().isEmpty()) {
            exibirAlerta("Informe o CEP.");
            return;
        }
        if (logradouro == null || logradouro.trim().isEmpty()) {
            exibirAlerta("Informe o logradouro.");
            return;
        }

        if (enderecoEdicao == null) { // Se for novo, cadastra
            EnderecoEmpresa endereco = new EnderecoEmpresa(0, cnpjEmpresa, cep, logradouro, bairro, complemento);
            controladorEmpresa.cadastrarEndereco(endereco);
            ServicoAuditoria.registrar("Cadastro", "Endereço Empresa", "ID:" + endereco.getId());
        } else { // Se for edição, atualiza
            EnderecoEmpresa endereco = new EnderecoEmpresa(enderecoEdicao.getId(), cnpjEmpresa, cep, logradouro, bairro,
                    complemento);
            controladorEmpresa.atualizarEndereco(endereco);
            ServicoAuditoria.registrar("Edição", "Endereço Empresa", "ID:" + endereco.getId());
        }
        salvoComSucesso = true;
        fecharJanela();
    }

    /**
     * @brief Método responsável por cancelar o cadastro
     */
    @FXML
    public void cancelar() {
        fecharJanela();
    }

    /**
     * @brief Método responsável por preparar o cadastro
     * @param cnpjEmpresa CNPJ da empresa
     */
    public void prepararCadastro(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    /**
     * @brief Método responsável por preparar a edição
     * @param endereco Endereço a ser editado
     */
    public void prepararEdicao(EnderecoEmpresa endereco) {
        // Coloca os dados do endereço nos campos
        this.enderecoEdicao = endereco;
        this.cnpjEmpresa = endereco.getCnpjEmpresa();
        labelTitulo.setText("Editar Endereço");
        // Preenche os campos
        txtCep.setText(endereco.getCep());
        txtLogradouro.setText(endereco.getLogradouro());
        txtBairro.setText(endereco.getBairro());
        txtComplemento.setText(endereco.getComplemento());
    }

    /**
     * @brief Método responsável por verificar se foi salvo com sucesso
     * @return True se foi salvo com sucesso, false caso contrário
     */
    public boolean isSalvoComSucesso() {
        return salvoComSucesso;
    }

    /**
     * @brief Método responsável por fechar a janela
     */
    private void fecharJanela() {
        ((Stage) txtCep.getScene().getWindow()).close();
    }

    /**
     * @brief Método responsável por exibir um alerta
     */
    private void exibirAlerta(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
