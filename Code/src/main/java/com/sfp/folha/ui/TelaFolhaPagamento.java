package com.sfp.folha.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.util.List;

import com.sfp.folha.application.ProcessadorDeFolha;
import com.sfp.folha.application.calculadoras.CalculadoraHoraExtra;
import com.sfp.folha.application.calculadoras.CalculadoraSalarioProporcional;
import com.sfp.folha.application.calculadoras.CalculadoraINSS;
import com.sfp.folha.domain.Holerite;
import com.sfp.core.domain.Funcionario;
import com.sfp.core.domain.FuncionarioRepository;
import com.sfp.core.domain.FaixaINSSRepository;
import com.sfp.infrastructure.persistence.MySQLFuncionarioRepository;
import com.sfp.infrastructure.persistence.MySQLFaixaINSSRepository;

public class TelaFolhaPagamento {

    @FXML private Label labelCompetencia;
    @FXML private Label labelStatus;
    
    @FXML private TableView<Holerite> tabelafolha;
    @FXML private TableColumn<Holerite, String> colFunc;
    @FXML private TableColumn<Holerite, String> colCargo;
    @FXML private TableColumn<Holerite, BigDecimal> colSalBase;
    @FXML private TableColumn<Holerite, BigDecimal> colTotProv;
    @FXML private TableColumn<Holerite, BigDecimal> colTotDesc;
    @FXML private TableColumn<Holerite, BigDecimal> colSalLiq;

    private FuncionarioRepository funcionarioRepository = new MySQLFuncionarioRepository();
    private FaixaINSSRepository inssRepository = new MySQLFaixaINSSRepository();
    private ProcessadorDeFolha processador;

    @FXML
    public void initialize() {
        try {
            tabelafolha.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            colFunc.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFuncionario().getNome()));
            colCargo.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFuncionario().getCargo()));
            colSalBase.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getFuncionario().getSalarioBruto()));
            colTotProv.setCellValueFactory(new PropertyValueFactory<>("totalProventos"));
            colTotDesc.setCellValueFactory(new PropertyValueFactory<>("totalDescontos"));
            colSalLiq.setCellValueFactory(new PropertyValueFactory<>("salarioLiquido"));

            labelCompetencia.setText(java.time.LocalDate.now().getMonth().toString() + "/" + java.time.LocalDate.now().getYear());
            labelStatus.setText("Aguardando Processamento");

            // Configurar o Motor
            CalculadoraSalarioProporcional calcProp = new CalculadoraSalarioProporcional();
            CalculadoraINSS calcINSS = new CalculadoraINSS(inssRepository.buscarTodas(), new BigDecimal("908.85"));
            
            List<com.sfp.folha.domain.RegraDeCalculo> proventos = java.util.Arrays.asList(calcProp);
            List<com.sfp.folha.domain.RegraDeCalculo> descontos = java.util.Arrays.asList(calcINSS);
            
            this.processador = new ProcessadorDeFolha(proventos, descontos);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERRO CRÍTICO NO INITIALIZE: " + e.getMessage());
        }
    }

    @FXML
    public void calcularFolha() {
        labelStatus.setText("Processando...");
        List<Funcionario> funcionarios = funcionarioRepository.buscarTodos();
        ObservableList<Holerite> holerites = FXCollections.observableArrayList();

        int diasUteis = 22;
        int diasTrabalhados = 22;

        for(Funcionario f : funcionarios) {
            Holerite h = processador.processar(f, diasUteis, diasTrabalhados);
            if (h != null) {
                holerites.add(h);
            }
        }

        tabelafolha.setItems(holerites);
        labelStatus.setText("Calculada com Sucesso!");
    }

    @FXML
    public void fecharFolha() {
        labelStatus.setText("Folha Fechada!");
        // O fechamento no banco pode ser implementado no futuro
    }
}
