package com.sfp.folha.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.util.List;

import com.sfp.folha.application.ProcessadorDeFolha;
import com.sfp.folha.application.calculadoras.CalculadoraHoraExtra;
import com.sfp.folha.application.calculadoras.CalculadoraSalarioProporcional;
import com.sfp.folha.application.calculadoras.CalculadoraINSS;
import com.sfp.folha.domain.Holerite;
import com.sfp.folha.domain.RegraDeCalculo;

import com.sfp.core.domain.Funcionario;
import com.sfp.core.domain.FuncionarioRepository;
import com.sfp.core.domain.FaixaINSSRepository;

import com.sfp.infrastructure.persistence.MySQLFuncionarioRepository;
import com.sfp.infrastructure.persistence.MySQLFaixaINSSRepository;

// @brief: Classe responsável pela tela de folha de pagamento.
public class TelaFolhaPagamento {

    @FXML
    private Label labelCompetencia;
    @FXML
    private Label labelStatus;
    @FXML
    private TableView<Holerite> tabelafolha;
    @FXML
    private TableColumn<Holerite, String> colFunc;
    @FXML
    private TableColumn<Holerite, String> colCargo;
    @FXML
    private TableColumn<Holerite, BigDecimal> colSalBase;
    @FXML
    private TableColumn<Holerite, BigDecimal> colTotProv;
    @FXML
    private TableColumn<Holerite, BigDecimal> colTotDesc;
    @FXML
    private TableColumn<Holerite, BigDecimal> colSalLiq;

    private FuncionarioRepository funcionarioRepository = new MySQLFuncionarioRepository();
    private FaixaINSSRepository inssRepository = new MySQLFaixaINSSRepository();
    private ProcessadorDeFolha processador;

    /**
     * @brief: Método inicializador da tela.
     */
    @FXML
    public void initialize() {
        try {
            tabelafolha.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            // Pega os valores do funcionário
            colFunc.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                    cellData.getValue().getFuncionario().getNome()));

            colCargo.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                    cellData.getValue().getFuncionario().getCargo()));

            colSalBase.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(
                    cellData.getValue().getFuncionario().getSalarioBruto()));

            // Cria as colunas de proventos, descontos e salário líquido
            colTotProv.setCellValueFactory(new PropertyValueFactory<>("totalProventos"));
            colTotDesc.setCellValueFactory(new PropertyValueFactory<>("totalDescontos"));
            colSalLiq.setCellValueFactory(new PropertyValueFactory<>("salarioLiquido"));

            // Configura a competência e o status
            labelCompetencia.setText(
                    java.time.LocalDate.now().getMonth().toString() + "/" + java.time.LocalDate.now().getYear());
            labelStatus.setText("Aguardando Processamento");

            // Configurar o Motor
            CalculadoraSalarioProporcional calcProp = new CalculadoraSalarioProporcional();
            CalculadoraINSS calcINSS = new CalculadoraINSS(inssRepository.buscarTodas(), new BigDecimal("908.85"));

            // Inicializa as listas de regras de provento e desconto
            List<RegraDeCalculo> proventos = java.util.Arrays.asList(calcProp);
            List<RegraDeCalculo> descontos = java.util.Arrays.asList(calcINSS);

            // Configura o processador de folha
            this.processador = new ProcessadorDeFolha(proventos, descontos);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERRO CRÍTICO NO INITIALIZE: " + e.getMessage());
        }
    }

    // @brief: Método responsável por calcular a folha.
    @FXML
    public void calcularFolha() {
        labelStatus.setText("Processando...");

        // Busca todos os funcionários
        List<Funcionario> funcionarios = funcionarioRepository.buscarTodos();

        // Cria a lista de holerites
        ObservableList<Holerite> holerites = FXCollections.observableArrayList();

        // Dias úteis e dias trabalhadoe (podem ser configurados em outro lugar)
        int diasUteis = 22;
        int diasTrabalhados = 22;

        // Calcula a folha de todos os funcionários (pode ser mudado para individual)
        for (Funcionario funcionario : funcionarios) {
            Holerite holerite = processador.processar(funcionario);

            // Se o holerite não for nulo, adiciona à lista
            if (holerite != null) {
                holerites.add(holerite);
            }
        }

        // Adiciona os holerites à tabela
        tabelafolha.setItems(holerites);
        labelStatus.setText("Calculada com Sucesso!");
    }

    @FXML
    public void fecharFolha() {
        labelStatus.setText("Folha Fechada!");
        // O fechamento no banco pode ser implementado no futuro
    }
}
