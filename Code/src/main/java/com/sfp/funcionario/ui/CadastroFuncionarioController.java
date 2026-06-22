/**
 * @brief: Controller para a janela de cadastro de funcionários.
 * É responsável por interagir com a view e com o repositório de funcionários.
 */

package com.sfp.funcionario.ui;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.sfp.funcionario.domain.Funcionario;
import com.sfp.funcionario.application.ControladorFuncionario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;

public class CadastroFuncionarioController {
    @FXML
    private TextField inputNome; // Campo de texto para o nome do funcionário
    @FXML
    private TextField inputCpf; // Campo de texto para o cpf do funcionário
    @FXML
    private TextField inputCargo; // Campo de texto para o cargo do funcionário
    @FXML
    private TextField inputSalario; // Campo de texto para o salário do funcionário
    @FXML
    private DatePicker inputDataAdmissao; // Campo de texto para a data de admissão do funcionário
    @FXML
    private Label labelMensagem; // Label para exibir mensagens ao usuário
    @FXML
    private CheckBox checkStatus; // Checkbox para status do funcionário
    @FXML
    private HBox boxStatus; // Container do checkbox de status

    private ControladorFuncionario controladorFuncionario = new ControladorFuncionario();
    private boolean modoEdicao = false; // Flag para indicar se o funcionário será editado

    /**
     * @brief: Define o funcionário que será editado
     * @param funcionario: Funcionário a ser editado
     */
    public void setFuncionarioEdicao(Funcionario funcionario) {
        this.modoEdicao = true; // Ativa o modo de edição
        // Preenche os campos com os dados do funcionário
        inputNome.setText(funcionario.getNome());
        inputCpf.setText(funcionario.getCpf());
        inputCpf.setDisable(true); // CPF não pode ser alterado na edição
        inputCargo.setText(funcionario.getCargo());
        inputSalario.setText(funcionario.getSalarioBruto().toString());
        inputDataAdmissao.setValue(funcionario.getDataAdmissao());
        boxStatus.setVisible(true);
        boxStatus.setManaged(true);
        checkStatus.setSelected(funcionario.getStatus());
    }

    /**
     * @brief: Salva um funcionário no banco de dados
     * @param event: Evento de clique do botão Salvar
     */
    @FXML
    private void salvarFuncionario(ActionEvent event) {
        // Pega os dados dos campos
        String nome = inputNome.getText();
        String cpf = inputCpf.getText();
        String cargo = inputCargo.getText();
        String salarioStr = inputSalario.getText();
        LocalDate dataAdmissao = inputDataAdmissao.getValue();

        // Verifica se todos os campos foram preenchidos
        if (nome.isEmpty() || cpf.isEmpty() || cargo.isEmpty() || salarioStr.isEmpty() || dataAdmissao == null) {
            labelMensagem.setText("Todos os campos devem ser preenchidos!");
            labelMensagem.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            // Remove a vírgula e converte para BigDecimal
            BigDecimal salarioBruto = new BigDecimal(salarioStr.replace(',', '.'));
            // Cria um objeto Funcionario
            boolean ativo = modoEdicao ? checkStatus.isSelected() : true;
            Funcionario funcionario = new Funcionario(nome, cpf, cargo, dataAdmissao, salarioBruto, ativo, 0);

            if (modoEdicao) { // Se estiver em modo de edição, atualiza o funcionário
                controladorFuncionario.atualizarFuncionario(funcionario);
                labelMensagem.setText("Funcionário atualizado com sucesso!");
            } else { // Se não estiver em modo de edição, cadastra o funcionário
                controladorFuncionario.cadastrarFuncionario(funcionario);
                labelMensagem.setText("Funcionário cadastrado com sucesso!");
            }

            labelMensagem.setStyle("-fx-text-fill: green;");

        } catch (NumberFormatException e) { // Lança erro se o salário for inválido
            labelMensagem.setText("Salário inválido!");
            labelMensagem.setStyle("-fx-text-fill: red;");
        } catch (Exception e) { // Lança erro se houver outro erro
            labelMensagem.setText("Erro: " + e.getMessage());
            labelMensagem.setStyle("-fx-text-fill: red;");
        }
    }
}
