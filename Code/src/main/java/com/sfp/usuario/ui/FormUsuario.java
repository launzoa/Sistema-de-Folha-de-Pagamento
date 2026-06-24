/**
 * @brief Classe responsável pela interface gráfica do formulário de usuário.
 */
package com.sfp.usuario.ui;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

import com.sfp.usuario.domain.Usuario;
import com.sfp.usuario.application.ControladorUsuario;

public class FormUsuario {
    @FXML
    private Label labelTitulo; // Label responsável por exibir o título do formulário.
    @FXML
    private TextField txtNome; // TextField responsável por exibir o nome do usuário.
    @FXML
    private PasswordField txtSenha; // PasswordField responsável por exibir a senha do usuário.
    @FXML
    private ComboBox<String> comboPerfil; // ComboBox responsável por exibir o perfil do usuário.
    @FXML
    private CheckBox checkAtivo; // CheckBox responsável por exibir o status do usuário.

    private ControladorUsuario controladorBD = new ControladorUsuario();
    private Usuario usuarioEdicao = null; // null = cadastro; objeto = edição.
    private boolean salvoComSucesso = false; // Flag para indicar se o usuário foi salvo com sucesso.

    /**
     * @brief Método responsável por inicializar o formulário.
     *        Responsável por preencher o ComboBox com os perfis de usuário.
     */
    @FXML
    public void initialize() {
        comboPerfil.getItems().addAll("Administrador", "Operador");
    }

    /**
     * @brief Método responsável por salvar o usuário.
     * @brief Verifica se todos os campos obrigatórios foram preenchidos.
     * @brief Cadastra um novo usuário ou atualiza um usuário existente.
     * @brief Registra a operação no log de auditoria.
     * @brief Fecha a janela do formulário.
     */
    @FXML
    public void salvar() {
        // Recebe os dados do formulário.
        String nome = txtNome.getText();
        String senha = txtSenha.getText();
        String perfilSelecionado = comboPerfil.getValue();

        // Validação dos dados.
        if (nome == null || nome.trim().isEmpty()) {
            exibirAlerta("Informe o nome.");
            return;
        }
        if (usuarioEdicao == null && (senha == null || senha.trim().isEmpty())) {
            exibirAlerta("Informe a senha.");
            return;
        }
        if (perfilSelecionado == null) {
            exibirAlerta("Selecione o perfil.");
            return;
        }
        // Transforma os dados para o formato do modelo (admin e status).
        boolean ehAdmin = "Administrador".equals(perfilSelecionado);
        boolean ativo = checkAtivo.isSelected();
        // Insere ou atualiza o registro.
        if (usuarioEdicao == null) { // Insere
            controladorBD.cadastrarUsuario(nome, senha, ehAdmin);
        } else { // Atualiza
            controladorBD.atualizarUsuario(usuarioEdicao.getId(), nome, senha, ativo);
        }

        salvoComSucesso = true;
        fecharJanela();
    }

    /**
     * @brief Método responsável por cancelar a operação.
     */
    @FXML
    public void cancelar() {
        fecharJanela();
    }

    /**
     * @brief Método responsável por preparar a edição do usuário.
     *        Preenche os campos do formulário com os dados do usuário.
     */
    public void prepararEdicao(Usuario user) {
        // Recebe o usuário que será editado.
        this.usuarioEdicao = user;
        labelTitulo.setText("Editar Usuário");
        // Preenche os inputs com os dados do usuário.
        txtNome.setText(user.getNome());
        txtSenha.setText(""); // Não carrega o hash na interface
        txtSenha.setPromptText("Deixe em branco para manter");
        comboPerfil.setValue(user.isPerfil() ? "Administrador" : "Operador");
        checkAtivo.setSelected(user.isStatus());
        // Impede a alteração do perfil.
        comboPerfil.setDisable(true);
    }

    /**
     * @brief Método responsável por verificar se o usuário foi salvo com sucesso.
     * @return true se o usuário foi salvo com sucesso, false caso contrário.
     */
    public boolean isSalvoComSucesso() {
        return salvoComSucesso;
    }

    /**
     * @brief Método responsável por fechar a janela do formulário.
     */
    private void fecharJanela() {
        ((Stage) txtNome.getScene().getWindow()).close();
    }

    /**
     * @brief Método responsável por exibir um alerta.
     * @param mensagem Mensagem a ser exibida no alerta.
     */
    private void exibirAlerta(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
