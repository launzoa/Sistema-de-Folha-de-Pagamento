package com.sfp.folha.ui;

import java.math.BigDecimal;

import com.sfp.core.domain.Funcionario;
import com.sfp.core.domain.FuncionarioRepository;
import com.sfp.infrastructure.persistence.MySQLFuncionarioRepository;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.time.LocalDate;

public class CadastroFuncionarioController {
    @FXML
    private TextField inputNome;

    @FXML
    private TextField inputCpf;

    @FXML
    private TextField inputCargo;

    @FXML
    private TextField inputSalario;

    @FXML
    private DatePicker inputDataAdmissao;

    @FXML
    private TextField inputNumeroDependentes;

    @FXML
    private Label labelMensagem;

    private FuncionarioRepository funcionarioRepository = new MySQLFuncionarioRepository();
    private boolean modoEdicao = false;

    // @brief: Salva um funcionário no banco de dados
    // @param event: Evento de clique do botão Salvar
    @FXML
    private void salvarFuncionario(ActionEvent event) {
        // Pega os dados dos campos
        String nome = inputNome.getText();
        String cpf = inputCpf.getText();
        String cargo = inputCargo.getText();
        String salarioStr = inputSalario.getText();
        LocalDate dataAdmissao = inputDataAdmissao.getValue();
        String numeroDependentesStr = inputNumeroDependentes.getText();

        // Verifica se todos os campos foram preenchidos
        if (nome.isEmpty() || cpf.isEmpty() || cargo.isEmpty() || salarioStr.isEmpty() || dataAdmissao == null) {
            labelMensagem.setText("Todos os campos devem ser preenchidos!");
            labelMensagem.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            BigDecimal salarioBruto = new BigDecimal(salarioStr.replace(',', '.'));
            // Cria um objeto Funcionario
            Funcionario funcionario = new Funcionario(nome, cpf, cargo, dataAdmissao, salarioBruto,
                    true, Integer.parseInt(numeroDependentesStr));

            if (modoEdicao) {
                funcionarioRepository.atualizar(funcionario);
                labelMensagem.setText("Funcionário atualizado com sucesso!");
            } else {
                funcionarioRepository.salvar(funcionario);
                labelMensagem.setText("Funcionário cadastrado com sucesso!");
            }
            labelMensagem.setStyle("-fx-text-fill: green;");
        } catch (NumberFormatException e) {
            // Lança erro se o salário for inválido
            labelMensagem.setText("Salário inválido!");
            labelMensagem.setStyle("-fx-text-fill: red;");
        } catch (Exception e) {
            // Lança erro se houver outro erro
            labelMensagem.setText("Erro: " + e.getMessage());
            labelMensagem.setStyle("-fx-text-fill: red;");
        }
    }

    /**
     * @brief Define o funcionário a ser editado.
     * @param funcionario Funcionário a ser editado.
     */
    public void setFuncionarioEdicao(Funcionario funcionario) {
        this.modoEdicao = true;
        inputNome.setText(funcionario.getNome());
        inputCpf.setText(funcionario.getCpf());
        inputCpf.setDisable(true); // CPF não pode ser alterado na edição
        inputCargo.setText(funcionario.getCargo());
        inputSalario.setText(funcionario.getSalarioBruto().toString());
        inputDataAdmissao.setValue(funcionario.getDataAdmissao());
        inputNumeroDependentes.setText(String.valueOf(funcionario.getNumeroDependentes()));
    }
}
