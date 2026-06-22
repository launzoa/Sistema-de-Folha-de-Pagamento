/** 
 * @brief Classe controladora da tela de empresas
 */
package com.sfp.empresa.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import com.sfp.core.ui.GerenciadorTema;
import com.sfp.auditoria.application.ServicoAuditoria;
import com.sfp.empresa.application.ControladorEmpresa;
import com.sfp.empresa.domain.Empresa;
import com.sfp.empresa.domain.EnderecoEmpresa;

public class TelaEmpresa {
    @FXML
    private Label labelRazaoSocial; // Label da Razao Social da Empresa
    @FXML
    private Label labelCNPJ; // Label do CNPJ da Empresa
    @FXML
    private Label labelRespLegal; // Label do Responsável Legal da Empresa
    @FXML
    private Label labelEmail; // Label do Email da Empresa
    @FXML
    private Label labelNomeTopoEmpresa; // Label do Nome da Empresa no topo
    @FXML
    private Button btnCadastrar; // Botão Cadastrar Empresa
    @FXML
    private Button btnEditar; // Botão Editar Empresa
    @FXML
    private TableView<EnderecoEmpresa> tabelaEnderecos; // Tabela de Endereços
    @FXML
    private TableColumn<EnderecoEmpresa, String> colCep; // Coluna CEP
    @FXML
    private TableColumn<EnderecoEmpresa, String> colLogradouro; // Coluna Logradouro
    @FXML
    private TableColumn<EnderecoEmpresa, String> colBairro; // Coluna Bairro
    @FXML
    private TableColumn<EnderecoEmpresa, String> colComplemento;

    private ControladorEmpresa controladorEmpresa = new ControladorEmpresa();
    private Empresa empresaAtual = null;

    /**
     * @brief Metodo que inicializa a tela de empresas
     */
    @FXML
    public void initialize() {
        // Ajusta o redimensionamento das colunas da tabela
        tabelaEnderecos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Define as colunas da tabela
        colCep.setCellValueFactory(new PropertyValueFactory<>("cep"));
        colLogradouro.setCellValueFactory(new PropertyValueFactory<>("logradouro"));
        colBairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        colComplemento.setCellValueFactory(new PropertyValueFactory<>("complemento"));

        carregarEmpresa();
    }

    /**
     * @brief Metodo que adiciona uma empresa
     */
    @FXML
    private void addEmpresa() {
        exibirFormularioEmpresa(null);
    }

    /**
     * @brief Metodo que edita uma empresa
     */
    @FXML
    private void editarEmpresa() {
        exibirFormularioEmpresa(empresaAtual);
    }

    /**
     * @brief Metodo que exclui uma empresa
     */
    @FXML
    private void excluirEmpresa() {
        if (empresaAtual == null) { // Caso não haja empresa cadastrada
            exibirAlerta("Não há empresa para ser excluida!");
            return;
        }
        // Exclui a empresa
        controladorEmpresa.excluirEmpresa(empresaAtual.getCnpj());
        ServicoAuditoria.registrar("Exclusão", "Empresa", "CNPJ:" + empresaAtual.getCnpj());
        carregarEmpresa();
        limparDadosEmpresa();
    }

    /**
     * @brief Metodo que adiciona um endereço
     */
    @FXML
    private void addEndereco() {
        if (empresaAtual == null) { // Caso não haja empresa cadastrada
            exibirAlerta("Crie uma empresa antes de adicionar endereço.");
            return;
        }
        // Exibe o formulário de endereço
        exibirFormularioEndereco(null, empresaAtual.getCnpj());
    }

    /**
     * @brief Metodo que edita um endereço
     */
    @FXML
    private void editarEndereco() {
        // Obtem o endereço selecionado
        EnderecoEmpresa endereco = tabelaEnderecos.getSelectionModel().getSelectedItem();
        if (endereco == null) { // Caso não haja endereço selecionado
            exibirAlerta("Selecione um endereço para editar.");
            return;
        }
        // Exibe o formulário de endereço
        exibirFormularioEndereco(endereco, endereco.getCnpjEmpresa());
    }

    /**
     * @brief Metodo que exclui um endereço
     */
    @FXML
    private void excluirEndereco() {
        // Obtem o endereço selecionado
        EnderecoEmpresa endereco = tabelaEnderecos.getSelectionModel().getSelectedItem();
        if (endereco == null) { // Caso não haja endereço selecionado
            exibirAlerta("Selecione um endereço para excluir.");
            return;
        }

        controladorEmpresa.excluirEndereco(endereco.getId());
        ServicoAuditoria.registrar("Exclusão", "Endereço Empresa", "CEP:" + endereco.getCep());
        atualizarTabelaEnderecos();
    }

    /**
     * @brief Metodo que limpa os dados da empresa
     */
    private void limparDadosEmpresa() {
        labelRazaoSocial.setText("-");
        labelCNPJ.setText("-");
        labelRespLegal.setText("-");
        labelEmail.setText("-");
        btnCadastrar.setVisible(true);
        btnEditar.setVisible(false);
        tabelaEnderecos.setItems(FXCollections.observableArrayList());
    }

    /**
     * @brief Metodo que carrega a empresa
     */
    private void carregarEmpresa() {
        empresaAtual = controladorEmpresa.buscarEmpresa();
        if (empresaAtual != null) { // Caso haja empresa cadastrada
            exibirEmpresaSelecionada();
        } else {
            limparDadosEmpresa();
        }
    }

    /**
     * @brief Metodo que exibe a empresa selecionada
     */
    private void exibirEmpresaSelecionada() {
        if (empresaAtual == null) { // Caso não haja empresa cadastrada
            labelRazaoSocial.setText("-");
            labelCNPJ.setText("-");
            labelRespLegal.setText("-");
            labelEmail.setText("-");
            if (labelNomeTopoEmpresa != null) // Caso o nome da empresa não seja nulo
                labelNomeTopoEmpresa.setText("Nenhuma Empresa Cadastrada");
            tabelaEnderecos.setItems(FXCollections.observableArrayList());
            return;
        }

        // Exibe os dados da empresa
        labelRazaoSocial.setText(empresaAtual.getRazaoSocial());
        labelCNPJ.setText(empresaAtual.getCnpj());
        labelRespLegal.setText(empresaAtual.getRespLegal());
        labelEmail.setText(empresaAtual.getEmail());
        if (labelNomeTopoEmpresa != null) // Caso o nome da empresa não seja nulo
            labelNomeTopoEmpresa.setText(empresaAtual.getRazaoSocial());
        btnCadastrar.setVisible(false);
        btnEditar.setVisible(true);
        atualizarTabelaEnderecos();
    }

    /**
     * @brief Metodo que atualiza a tabela de endereços
     */
    private void atualizarTabelaEnderecos() {
        if (empresaAtual == null) { // Caso não haja empresa cadastrada
            tabelaEnderecos.setItems(FXCollections.observableArrayList());
            return;
        }

        // Atualiza a tabela de endereços
        ObservableList<EnderecoEmpresa> enderecos = FXCollections
                .observableArrayList(controladorEmpresa.listarEnderecos(empresaAtual.getCnpj()));
        tabelaEnderecos.setItems(enderecos);
    }

    /**
     * @brief Metodo que exibe o formulario de empresa
     * @param empresa Empresa a ser editada
     */
    private void exibirFormularioEmpresa(Empresa empresa) {
        try {
            // Carrega o formulario de empresa
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormEmpresa.fxml"));
            Parent rootForm = loader.load();
            FormEmpresa formController = loader.getController();

            if (empresa != null) { // Caso haja empresa cadastrada
                formController.prepararEdicao(empresa); // Prepara a edição
            }
            if (GerenciadorTema.modoEscuroAtivo) { // Caso o modo escuro esteja ativo
                rootForm.getStyleClass().add("dark-mode");
            }
            // Cria o novo "palco" e cria a cena
            Stage stage = new Stage();
            Scene scene = new Scene(rootForm, 600, 600);
            // Define a cena no palco e configura o título e a modalidade
            stage.setScene(scene);
            stage.setTitle(empresa == null ? "Nova Empresa" : "Editar Empresa");
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            // Mostra o formulario e espera até que ele seja fechado
            stage.showAndWait();
            // Verifica se o formulario foi salvo com sucesso
            if (formController.isSalvoComSucesso()) {
                carregarEmpresa();
            }

        } catch (Exception e) { // Captura exceções e as imprime
            e.printStackTrace();
        }
    }

    /**
     * @brief Metodo que exibe o formulario de endereço
     * @param endereco    Endereço a ser editado
     * @param cnpjEmpresa CNPJ da empresa
     */
    private void exibirFormularioEndereco(EnderecoEmpresa endereco, String cnpjEmpresa) {
        try {
            // Carrega o formulario de endereço
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormEndereco.fxml"));
            Parent rootForm = loader.load();
            FormEndereco formController = loader.getController();

            // Prepara o formulario de endereço
            if (endereco == null) { // Caso não haja endereço cadastrado
                formController.prepararCadastro(cnpjEmpresa); // Prepara o cadastro
            } else { // Caso haja endereço cadastrado
                formController.prepararEdicao(endereco); // Prepara a edição
            }
            if (GerenciadorTema.modoEscuroAtivo) { // Caso o modo escuro esteja ativo
                rootForm.getStyleClass().add("dark-mode");
            }
            Stage stage = new Stage(); // Cria o novo "palco"
            Scene scene = new Scene(rootForm, 500, 360); // Cria a cena

            // Define a cena no palco e configura o título e a modalidade
            stage.setScene(scene);
            stage.setTitle(endereco == null ? "Novo Endereço" : "Editar Endereço");
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            // Mostra o formulario e espera até que ele seja fechado
            stage.showAndWait();

            // Verifica se o formulario foi salvo com sucesso
            if (formController.isSalvoComSucesso()) {
                atualizarTabelaEnderecos();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Metodo que exibe o alerta
     * @param mensagem Mensagem a ser exibida
     */
    private void exibirAlerta(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
