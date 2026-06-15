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
    private Label labelMensagem;

    private FuncionarioRepository funcionarioRepository = new MySQLFuncionarioRepository();
    private boolean modoEdicao = false;

    public void setFuncionarioEdicao(Funcionario func) {
        this.modoEdicao = true;
        inputNome.setText(func.getNome());
        inputCpf.setText(func.getCpf());
        inputCpf.setDisable(true); // CPF não pode ser alterado na edição
        inputCargo.setText(func.getCargo());
        inputSalario.setText(func.getSalarioBruto().toString());
        inputDataAdmissao.setValue(func.getDataAdmissao());
    }

    @FXML
    // @brief: Salva um funcionário no banco de dados
    // @param event: Evento de clique do botão Salvar
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
            BigDecimal salarioBruto = new BigDecimal(salarioStr.replace(',', '.'));
            // Cria um objeto Funcionario
            Funcionario funcionario = new Funcionario(nome, cpf, cargo, dataAdmissao, salarioBruto, true);
            
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
}
