package com.sfp.folha.ui;

import javafx.util.StringConverter;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.util.stream.Collectors;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import com.sfp.folha.domain.Lancamento;
import com.sfp.folha.domain.LancamentoRepository;
import com.sfp.folha.infrastructure.persistence.MySQLLancamentoRepository;
import com.sfp.folha.domain.FolhaMes;
import com.sfp.folha.domain.FolhaMesRepository;
import com.sfp.folha.infrastructure.persistence.MySQLFolhaMesRepository;
import com.sfp.funcionario.domain.Funcionario;
import com.sfp.funcionario.domain.FuncionarioRepository;
import com.sfp.funcionario.infrastructure.persistence.MySQLFuncionarioRepository;
import com.sfp.rubrica.domain.Rubrica;
import com.sfp.rubrica.domain.RubricaRepository;
import com.sfp.rubrica.infrastructure.persistence.MySQLRubricaRepository;

/**
 * @brief Controller do Modal (Formulário) de criação e edição de Lançamentos.
 *        Responsável pela lógica dinâmica de exibição de campos dependendo da
 *        Modalidade
 *        escolhida (Valor Absoluto, Porcentagem ou Quantidade).
 */
public class FormLancamentoController {
    @FXML
    private ComboBox<Funcionario> comboFuncionario; // Box para seleção do funcionário
    @FXML
    private ComboBox<Rubrica> comboRubrica; // Box para seleção da rubrica
    @FXML
    private ComboBox<String> cbModalidade; // Box para seleção da modalidade
    @FXML
    private TextField txtValorOuQtd; // Box para digitação do valor ou quantidade
    @FXML
    private ComboBox<String> cbBaseCalculo; // Box para seleção da base de cálculo
    @FXML
    private javafx.scene.layout.VBox boxBaseCalculo; // Box para exibição da base de cálculo
    @FXML
    private TextField txtBaseLivre; // Box para digitação da base de cálculo livre
    @FXML
    private CheckBox chkDescontoDSR; // Checkbox para desconto de DSR
    @FXML
    private DatePicker dpDataClt; // DatePicker para data da CLT

    private LancamentoRepository lancamentoRepo = new MySQLLancamentoRepository();
    private FolhaMesRepository folhaRepo = new MySQLFolhaMesRepository();
    private FuncionarioRepository funcRepo = new MySQLFuncionarioRepository();
    private RubricaRepository rubricaRepo = new MySQLRubricaRepository();

    private BigDecimal valorFinalCalculado = BigDecimal.ZERO;
    private Lancamento lancamentoEditado = null;
    private FolhaMes folhaSelecionada = null;

    /**
     * @brief Define a folha selecionada pelo usuário
     * @param folha A folha selecionada pelo usuário
     */
    public void setFolhaSelecionada(FolhaMes folha) {
        this.folhaSelecionada = folha;
    }

    /**
     * @brief Define o lançamento para edição
     * @param lancamento O lançamento a ser editado
     */
    public void setLancamentoParaEdicao(Lancamento lancamento) {
        this.lancamentoEditado = lancamento; // Armazena o lançamento que será editado
        if (lancamento != null) { // Se o lançamento não for nulo, preenche os campos com os dados do lançamento
            // Seleciona o funcionário
            comboFuncionario.getItems().stream()
                    .filter(f -> f.getCpf().equals(lancamento.getCpfFuncionario()))
                    .findFirst().ifPresent(f -> comboFuncionario.setValue(f));
            // Seleciona a rubrica
            comboRubrica.getItems().stream()
                    .filter(r -> r.getCodigo() == lancamento.getCodigoRubrica())
                    .findFirst().ifPresent(r -> comboRubrica.setValue(r));
            // Seleciona a modalidade
            cbModalidade.setValue(lancamento.getModalidade());
            // Preenche o valor ou quantidade
            if ("Quantidade".equals(lancamento.getModalidade())) {
                txtValorOuQtd.setText(String.valueOf(lancamento.getQuantidade()));
            } else {
                txtValorOuQtd.setText(lancamento.getValor() != null ? lancamento.getValor().toString() : "");
            }
            // Preenche a base de cálculo
            if (lancamento.getBaseCalculo() != null &&
                    !lancamento.getBaseCalculo().equals("Salário Bruto") &&
                    !lancamento.getBaseCalculo().equals("Salário Líquido")) {
                cbBaseCalculo.setValue("Livre"); // Se a base de cálculo não for Salário Bruto ou Salário Líquido,
                                                 // seleciona Livre
                if (txtBaseLivre != null) { // Se o campo txtBaseLivre não for nulo, preenche com o valor da base de
                                            // cálculo
                    txtBaseLivre.setText(lancamento.getBaseCalculo());
                    txtBaseLivre.setVisible(true);
                    txtBaseLivre.setManaged(true);
                }
            } else { // Se a base de cálculo for Salário Bruto ou Salário Líquido, seleciona a base
                     // de cálculo
                cbBaseCalculo.setValue(lancamento.getBaseCalculo());
            }
            // Preenche a data de CLT
            dpDataClt.setValue(lancamento.getDataClt());
        }
    }

    /**
     * @brief Inicializa o formulário com os dados do lançamento
     */
    @FXML
    public void initialize() {
        // Adiciona os funcionários ativos ao comboFuncionario
        comboFuncionario.getItems().addAll(funcRepo.buscarTodos().stream()
                .filter(Funcionario::getStatus)
                .collect(Collectors.toList()));
        // Define o conversor do comboFuncionario
        comboFuncionario.setConverter(new StringConverter<Funcionario>() {
            /**
             * @brief Converte um Funcionario para String
             * @param f O Funcionario a ser convertido
             * @return A String convertida
             */
            public String toString(Funcionario f) {
                return f == null ? "" : f.getNome();
            }

            /**
             * @brief Converte uma String para Funcionario
             * @param s A String a ser convertida
             * @return O Funcionario convertido
             */
            public Funcionario fromString(String s) {
                return null;
            }
        });
        // Busca as rubricas ativas, mas OMITINDO as puramente estruturais (1=Salário,
        // 100=INSS, 101, 102=IRRF, 103=DSR automático)
        List<Integer> estruturais = java.util.Arrays.asList(1, 100, 101, 102, 103);
        List<Rubrica> rubricas = rubricaRepo.buscarTodas().stream()
                .filter(r -> !estruturais.contains(r.getCodigo()) && r.isAtivo())
                .collect(Collectors.toList());

        // Adiciona as rubricas ao comboRubrica
        comboRubrica.getItems().addAll(rubricas);
        // Define o conversor do comboRubrica
        comboRubrica.setConverter(new StringConverter<Rubrica>() {
            /**
             * @brief Converte uma Rubrica para String
             * @param r A Rubrica a ser convertida
             * @return A String convertida
             */
            public String toString(Rubrica r) {
                return r == null ? "" : r.getCodigo() + " - " + r.getDescricao();
            }

            /**
             * @brief Converte uma String para Rubrica
             * @param s A String a ser convertida
             * @return A Rubrica convertida
             */
            public Rubrica fromString(String s) {
                return null;
            }
        });
        // Adiciona as modalidades ao comboModalidade (o listener abaixo irá ajustar
        // isso dinamicamente)
        cbModalidade.getItems().addAll("Valor", "Porcentagem", "Quantidade");
        // Adiciona as bases de cálculo ao comboBaseCalculo (Removido o Salário Líquido
        // a pedido)
        cbBaseCalculo.getItems().addAll("Salário Bruto", "Livre");
        // Listener para alterar a visibilidade do campo txtBaseLivre
        cbBaseCalculo.valueProperty().addListener((obs, oldVal, newVal) -> {
            // Verifica se a base de cálculo é Livre
            boolean isLivre = "Livre".equals(newVal);
            if (txtBaseLivre != null) { // Se o campo txtBaseLivre não for nulo, altera a visibilidade e o gerenciamento
                txtBaseLivre.setVisible(isLivre);
                txtBaseLivre.setManaged(isLivre);
            }
        });
        // Listener para alterar a visibilidade do campo boxBaseCalculo
        cbModalidade.valueProperty().addListener((obs, oldVal, newVal) -> {
            // Verifica se a modalidade é Porcentagem
            boolean isPorcentagem = "Porcentagem".equals(newVal);
            if (boxBaseCalculo != null) { // Se o campo boxBaseCalculo não for nulo, altera a visibilidade e o
                                          // gerenciamento
                boxBaseCalculo.setVisible(isPorcentagem);
                boxBaseCalculo.setManaged(isPorcentagem);
            }
        });
        cbModalidade.setValue("Valor"); // Valor default
        // Oculta e gerencia o checkbox descontoDSR
        chkDescontoDSR.setVisible(false);
        chkDescontoDSR.setManaged(false);
        // Listener para alterar a visibilidade do checkbox descontoDSR
        comboRubrica.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            // Verifica se a rubrica contém "falta" ou "atraso"
            if (newVal != null && (newVal.getDescricao().toLowerCase().contains("falta") || newVal.getDescricao().toLowerCase().contains("atraso"))) {
                chkDescontoDSR.setVisible(true);
                chkDescontoDSR.setManaged(true);
            } else { // Se a rubrica não contém "falta" nem "atraso", oculta e gerencia o checkbox descontoDSR
                chkDescontoDSR.setVisible(false);
                chkDescontoDSR.setManaged(false);
                chkDescontoDSR.setSelected(false);
            }

            // Regra: APENAS as rubricas pré-cadastradas 2, 3, 4 e 5 podem ter a opção
            // "Quantidade" e DEVE ser a ÚNICA opção
            if (newVal != null) {
                int cod = newVal.getCodigo();
                if (cod == 2 || cod == 3 || cod == 4 || cod == 5) {
                    cbModalidade.getItems().setAll("Quantidade");
                    cbModalidade.setValue("Quantidade");
                } else {
                    cbModalidade.getItems().setAll("Valor", "Porcentagem");
                    if ("Quantidade".equals(cbModalidade.getValue()) || cbModalidade.getValue() == null) {
                        cbModalidade.setValue("Valor");
                    }
                }
            }
        });
    }

    /**
     * @brief Salva o lançamento
     */
    @FXML
    private void salvar() {
        // Pega os dados do formulário
        Funcionario f = comboFuncionario.getValue();
        Rubrica r = comboRubrica.getValue();
        String valorQtdStr = txtValorOuQtd.getText();
        String modalidade = cbModalidade.getValue();
        LocalDate data = dpDataClt.getValue();
        // Verifica se os campos obrigatórios estão preenchidos
        if (f == null || r == null || valorQtdStr == null || valorQtdStr.isEmpty() || modalidade == null) {
            // Mostra alerta de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Campos Obrigatórios");
            alert.setContentText("Preencha Funcionário, Rubrica, Modalidade e Valor/Quantidade!");
            alert.showAndWait();
            return;
        }
        // Se a modalidade for Porcentagem, verifica se a base de cálculo está
        // preenchida
        if ("Porcentagem".equals(modalidade) && cbBaseCalculo.getValue() == null) {
            // Mostra alerta de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Campos Obrigatórios");
            alert.setContentText("Selecione a Base de Cálculo para a Porcentagem!");
            alert.showAndWait();
            return;
        }
        // Pega a folha selecionada
        FolhaMes folhaAtual = folhaSelecionada != null ? folhaSelecionada : folhaRepo.buscarFolhaAberta();
        if (folhaAtual == null || "Fechada".equals(folhaAtual.getStatus())) {
            // Mostra alerta de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Operação Inválida");
            alert.setContentText("Não há folha selecionada, ou a folha selecionada já está fechada.");
            alert.showAndWait();
            return;
        }

        try {
            // Tenta converter o valor inserido para double
            double numInserido = Double.parseDouble(valorQtdStr.replace(",", "."));
            double quantidade = 0.0;
            BigDecimal valor = null;
            String baseCalculo = null;
            // Se a modalidade for Quantidade
            if ("Quantidade".equals(modalidade)) {
                quantidade = numInserido;
                // A rubrica 103 (DSR) será lançada separadamente no final, se o checkbox
                // estiver marcado.
            } else if ("Porcentagem".equals(modalidade)) {
                // Se a modalidade for Porcentagem, converte o valor inserido para BigDecimal
                valor = BigDecimal.valueOf(numInserido).setScale(2, RoundingMode.HALF_UP);
                // Se a base de cálculo for Livre
                if ("Livre".equals(cbBaseCalculo.getValue())) {
                    String valLivre = txtBaseLivre.getText();
                    // Se a base de cálculo livre não estiver preenchida, mostra alerta de erro
                    if (valLivre == null || valLivre.trim().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erro");
                        alert.setHeaderText("Campos Obrigatórios");
                        alert.setContentText("Preencha o valor da Base Livre!");
                        alert.showAndWait();
                        return;
                    }
                    // Se a base de cálculo livre estiver preenchida, converte para BigDecimal e
                    // define como base de cálculo
                    baseCalculo = valLivre.replace(",", ".");
                } else {
                    // Se a base de cálculo não for Livre, define como base de cálculo
                    baseCalculo = cbBaseCalculo.getValue();
                }
            } else {
                // Se a modalidade não for Porcentagem nem Quantidade, converte o valor inserido
                // para BigDecimal
                valor = BigDecimal.valueOf(numInserido).setScale(2, RoundingMode.HALF_UP);
            }

            // Validação de teto para VT e PAT
            if (r.getCodigo() == 901 || r.getCodigo() == 902) {
                BigDecimal limiteMax;
                String nomeBen;
                if (r.getCodigo() == 901) {
                    limiteMax = f.getSalarioBruto().multiply(new BigDecimal("0.06")).setScale(2, RoundingMode.HALF_UP);
                    nomeBen = "Vale Transporte (6%)";
                } else {
                    limiteMax = f.getSalarioBruto().multiply(new BigDecimal("0.20")).setScale(2, RoundingMode.HALF_UP);
                    nomeBen = "Vale Alimentação (20%)";
                }

                BigDecimal valorSimulado = BigDecimal.ZERO;
                if ("Valor".equals(modalidade) && valor != null) {
                    valorSimulado = valor;
                } else if ("Porcentagem".equals(modalidade) && valor != null) {
                    BigDecimal baseCalcVal = f.getSalarioBruto();
                    if ("Livre".equals(cbBaseCalculo.getValue()) && baseCalculo != null) {
                         try { baseCalcVal = new BigDecimal(baseCalculo); } catch(Exception ignored){}
                    }
                    valorSimulado = baseCalcVal.multiply(valor).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
                }

                if (valorSimulado.compareTo(limiteMax) > 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Teto Ultrapassado");
                    alert.setHeaderText("Limite Legal Excedido");
                    alert.setContentText("O valor projetado de desconto (R$ " + valorSimulado + ") ultrapassa o teto legal permitido para " + nomeBen + " que é de R$ " + limiteMax + " para este funcionário.");
                    alert.showAndWait();
                    return;
                }
            }

            // Se o lançamento não estiver editado
            if (lancamentoEditado == null) {
                // Cria um novo lançamento
                Lancamento l = new Lancamento(0, folhaAtual.getId(), f.getCpf(), r.getCodigo(), quantidade, data, valor,
                        modalidade, baseCalculo, null, null);
                lancamentoRepo.salvar(l);
            } else { // Se não, o lançamento está editado
                // Atualiza o lançamento
                lancamentoEditado.setCpfFuncionario(f.getCpf());
                lancamentoEditado.setCodigoRubrica(r.getCodigo());
                lancamentoEditado.setModalidade(modalidade);
                lancamentoEditado.setQuantidade(quantidade);
                lancamentoEditado.setValor(valor);
                lancamentoEditado.setBaseCalculo(baseCalculo);
                lancamentoEditado.setDataClt(data);
                lancamentoRepo.atualizar(lancamentoEditado);
            }

            // Verifica se o desconto DSR deve ser lançado como rubrica independente (103)
            if (chkDescontoDSR.isSelected() && (r.getDescricao().toLowerCase().contains("falta") || r.getDescricao().toLowerCase().contains("atraso"))) {
                // Cria um lançamento separado para a rubrica 103 (DSR)
                Lancamento lDsr = new Lancamento(0, folhaAtual.getId(), f.getCpf(), 103, 1.0, data, null,
                        "Quantidade", "Salário Bruto", null, null);
                lancamentoRepo.salvar(lDsr);
            }
            fecharJanela();

        } catch (NumberFormatException e) { // Captura erro se o valor inserido não for um
                                            // número
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Valor Inválido");
            alert.setContentText("O valor/quantidade deve ser um número válido!");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao salvar");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * @brief Cancela o lançamento e fecha a janela
     */
    @FXML
    private void cancelar() {
        fecharJanela();
    }

    /**
     * @brief Fecha a janela
     */
    private void fecharJanela() {
        Stage stage = (Stage) comboFuncionario.getScene().getWindow();
        stage.close();
    }
}