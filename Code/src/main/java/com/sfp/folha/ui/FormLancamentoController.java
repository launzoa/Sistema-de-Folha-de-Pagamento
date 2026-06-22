package com.sfp.folha.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.stream.Collectors;

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
    private ComboBox<Funcionario> comboFuncionario;
    @FXML
    private ComboBox<Rubrica> comboRubrica;

    @FXML
    private ComboBox<String> cbModalidade;
    @FXML
    private TextField txtValorOuQtd;
    @FXML
    private ComboBox<String> cbBaseCalculo;
    @FXML
    private javafx.scene.layout.VBox boxBaseCalculo;

    @FXML
    private CheckBox chkDescontoDSR;
    @FXML
    private DatePicker dpDataClt;

    private LancamentoRepository lancamentoRepo = new MySQLLancamentoRepository();
    private FolhaMesRepository folhaRepo = new MySQLFolhaMesRepository();
    private FuncionarioRepository funcRepo = new MySQLFuncionarioRepository();
    private RubricaRepository rubricaRepo = new MySQLRubricaRepository();

    private java.math.BigDecimal valorFinalCalculado = java.math.BigDecimal.ZERO;
    private Lancamento lancamentoEditado = null;

    public void setLancamentoParaEdicao(Lancamento lancamento) {
        this.lancamentoEditado = lancamento;
        if (lancamento != null) {
            comboFuncionario.getItems().stream()
                    .filter(f -> f.getCpf().equals(lancamento.getCpfFuncionario()))
                    .findFirst().ifPresent(f -> comboFuncionario.setValue(f));
            comboRubrica.getItems().stream()
                    .filter(r -> r.getCodigo() == lancamento.getCodigoRubrica())
                    .findFirst().ifPresent(r -> comboRubrica.setValue(r));
            cbModalidade.setValue(lancamento.getModalidade());
            if ("Quantidade".equals(lancamento.getModalidade())) {
                txtValorOuQtd.setText(String.valueOf(lancamento.getQuantidade()));
            } else {
                txtValorOuQtd.setText(lancamento.getValor() != null ? lancamento.getValor().toString() : "");
            }
            cbBaseCalculo.setValue(lancamento.getBaseCalculo());
            dpDataClt.setValue(lancamento.getDataClt());
        }
    }

    @FXML
    public void initialize() {
        comboFuncionario.getItems().addAll(funcRepo.buscarTodos().stream()
                .filter(Funcionario::getStatus)
                .collect(Collectors.toList()));
        comboFuncionario.setConverter(new javafx.util.StringConverter<Funcionario>() {
            public String toString(Funcionario f) {
                return f == null ? "" : f.getNome();
            }

            public Funcionario fromString(String s) {
                return null;
            }
        });

        java.util.List<Rubrica> rubricas = rubricaRepo.buscarTodas();
        boolean temAdicionalNoturno = rubricas.stream().anyMatch(r -> r.getDescricao().contains("Adicional Noturno"));
        if (!temAdicionalNoturno) {
            int maxCodigo = rubricas.stream().mapToInt(Rubrica::getCodigo).max().orElse(0);
            com.sfp.rubrica.application.ControladorRubrica controlador = new com.sfp.rubrica.application.ControladorRubrica();
            controlador.cadastrarRubrica(
                    new Rubrica(maxCodigo + 1, "Adicional Noturno 20%", "Provento", "Variável", true, true, true, true, true));
            rubricas = rubricaRepo.buscarTodas();
        }

        comboRubrica.getItems().addAll(rubricas);
        comboRubrica.setConverter(new javafx.util.StringConverter<Rubrica>() {
            public String toString(Rubrica r) {
                return r == null ? "" : r.getCodigo() + " - " + r.getDescricao();
            }

            public Rubrica fromString(String s) {
                return null;
            }
        });

        cbModalidade.getItems().addAll("Valor", "Porcentagem", "Quantidade");
        cbBaseCalculo.getItems().addAll("Salário Bruto", "Salário Líquido", "Livre");

        cbModalidade.valueProperty().addListener((obs, oldVal, newVal) -> {
            boolean isPorcentagem = "Porcentagem".equals(newVal);
            if (boxBaseCalculo != null) {
                boxBaseCalculo.setVisible(isPorcentagem);
                boxBaseCalculo.setManaged(isPorcentagem);
            }
        });
        cbModalidade.setValue("Valor"); // Valor default

        chkDescontoDSR.setVisible(false);
        chkDescontoDSR.setManaged(false);

        comboRubrica.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && newVal.getDescricao().toLowerCase().contains("falta")) {
                chkDescontoDSR.setVisible(true);
                chkDescontoDSR.setManaged(true);
            } else {
                chkDescontoDSR.setVisible(false);
                chkDescontoDSR.setManaged(false);
                chkDescontoDSR.setSelected(false);
            }
        });
    }

    @FXML
    private void salvar() {
        Funcionario f = comboFuncionario.getValue();
        Rubrica r = comboRubrica.getValue();
        String valorQtdStr = txtValorOuQtd.getText();
        String modalidade = cbModalidade.getValue();
        LocalDate data = dpDataClt.getValue();

        if (f == null || r == null || valorQtdStr == null || valorQtdStr.isEmpty() || modalidade == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Campos Obrigatórios");
            alert.setContentText("Preencha Funcionário, Rubrica, Modalidade e Valor/Quantidade!");
            alert.showAndWait();
            return;
        }

        if ("Porcentagem".equals(modalidade) && cbBaseCalculo.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Campos Obrigatórios");
            alert.setContentText("Selecione a Base de Cálculo para a Porcentagem!");
            alert.showAndWait();
            return;
        }

        FolhaMes folhaAtual = folhaRepo.buscarFolhaAberta();
        if (folhaAtual == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhuma folha aberta");
            alert.setContentText("O sistema não identificou a folha do mês aberta.");
            alert.showAndWait();
            return;
        }

        try {
            double numInserido = Double.parseDouble(valorQtdStr.replace(",", "."));

            double quantidade = 0.0;
            java.math.BigDecimal valor = null;
            String baseCalculo = null;

            if ("Quantidade".equals(modalidade)) {
                quantidade = numInserido;
                if (chkDescontoDSR.isSelected() && r.getDescricao().toLowerCase().contains("falta")) {
                    quantidade += 1.0;
                }
            } else if ("Porcentagem".equals(modalidade)) {
                valor = new java.math.BigDecimal(numInserido).setScale(2, java.math.RoundingMode.HALF_UP);
                baseCalculo = cbBaseCalculo.getValue();
            } else {
                valor = new java.math.BigDecimal(numInserido).setScale(2, java.math.RoundingMode.HALF_UP);
            }

            if (lancamentoEditado == null) {
                Lancamento l = new Lancamento(0, folhaAtual.getId(), f.getCpf(), r.getCodigo(), quantidade, data, valor,
                        modalidade, baseCalculo, null, null, null);
                lancamentoRepo.salvar(l);
            } else {
                lancamentoEditado.setCpfFuncionario(f.getCpf());
                lancamentoEditado.setCodigoRubrica(r.getCodigo());
                lancamentoEditado.setModalidade(modalidade);
                lancamentoEditado.setQuantidade(quantidade);
                lancamentoEditado.setValor(valor);
                lancamentoEditado.setBaseCalculo(baseCalculo);
                lancamentoEditado.setDataClt(data);
                lancamentoRepo.atualizar(lancamentoEditado);
            }

            fecharJanela();

        } catch (NumberFormatException e) {
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

    @FXML
    private void cancelar() {
        fecharJanela();
    }

    private void fecharJanela() {
        Stage stage = (Stage) comboFuncionario.getScene().getWindow();
        stage.close();
    }
}