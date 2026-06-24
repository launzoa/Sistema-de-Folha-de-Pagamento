/**
 * @brief: Controller para a janela de cadastro de funcionários.
 * É responsável por interagir com a view e com o repositório de funcionários.
 */

package com.sfp.funcionario.ui;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.sfp.funcionario.domain.Funcionario;
import com.sfp.funcionario.application.ControladorFuncionario;
import com.sfp.folha.domain.ConstantesFolha;
import java.util.InputMismatchException;

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
    private TextField inputDependentes; // Campo de texto para o número de dependentes
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
        inputDependentes.setText(String.valueOf(funcionario.getNumeroDependentes()));
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
        String dependentesStr = inputDependentes.getText();
        LocalDate dataAdmissao = inputDataAdmissao.getValue();

        // Verifica se todos os campos foram preenchidos
        if (nome.isEmpty() || cpf.isEmpty() || cargo.isEmpty() || salarioStr.isEmpty() || dataAdmissao == null) {
            labelMensagem.setText("Todos os campos devem ser preenchidos!");
            labelMensagem.setStyle("-fx-text-fill: red;");
            return;
        }

        // Validação Algorítmica do CPF (Requisito 3.1.3.2)
        if (!isCpfValido(cpf)) {
            labelMensagem.setText("O CPF informado é matematicamente inválido!");
            labelMensagem.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            // Remove a vírgula e converte para BigDecimal
            BigDecimal salarioBruto = new BigDecimal(salarioStr.replace(',', '.'));

            // Validação de Salário Mínimo (2026)
            if (salarioBruto.compareTo(ConstantesFolha.SALARIO_MINIMO) < 0) {
                labelMensagem.setText(String.format("Operação Bloqueada: Salário inferior ao Piso Vigente (R$ %.2f).", ConstantesFolha.SALARIO_MINIMO));
                labelMensagem.setStyle("-fx-text-fill: red;");
                return;
            }

            int numeroDependentes = 0;
            if (dependentesStr != null && !dependentesStr.trim().isEmpty()) {
                numeroDependentes = Integer.parseInt(dependentesStr.trim());
            }

            // Cria um objeto Funcionario
            boolean ativo = modoEdicao ? checkStatus.isSelected() : true;
            Funcionario funcionario = new Funcionario(nome, cpf, cargo, dataAdmissao, salarioBruto, ativo, numeroDependentes);

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
            String msgErro = e.getMessage();
            // Verifica se a causa raiz do erro foi a trava UNIQUE do banco (Requisito
            // 3.1.3.3)
            if (e.getCause() != null && e.getCause().getMessage().toLowerCase().contains("duplicate entry")) {
                msgErro = "Clonagem Funcional (CPF Duplicado). Inserção Abortada!";
            }
            labelMensagem.setText("Erro: " + msgErro);
            labelMensagem.setStyle("-fx-text-fill: red;");
        }
    }

    /**
     * @brief Valida o algoritmo de um CPF (Módulo 11)
     * @param cpf CPF a ser validado
     * @return true se o CPF for válido, false caso contrário
     */
    private boolean isCpfValido(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return false;
        }

        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("\\D", "");

        // Verifica se tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais (ex: 11111111111), o que é inválido apesar de passar no algoritmo
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            // Cálculo do 1º Dígito Verificador
            int sm = 0;
            int peso = 10;
            for (int i = 0; i < 9; i++) {
                int num = (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            int r = 11 - (sm % 11);
            char dig10 = (r == 10 || r == 11) ? '0' : (char) (r + 48);

            // Cálculo do 2º Dígito Verificador
            sm = 0;
            peso = 11;
            for (int i = 0; i < 10; i++) {
                int num = (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            char dig11 = (r == 10 || r == 11) ? '0' : (char) (r + 48);

            // Verifica se os dígitos calculados conferem com os passados
            return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));
        } catch (InputMismatchException e) {
            return false;
        }
    }
}

