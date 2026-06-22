package com.sfp.folha.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.ComboBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.File;

import com.sfp.folha.application.ProcessadorDeFolha;
import com.sfp.folha.application.GeradorHoleritePDF;
import com.sfp.folha.application.GeradorRelatorioGeralPDF;
import com.sfp.folha.application.ServicoCicloFolha;
import com.sfp.folha.domain.ConstantesFolha;
import com.sfp.folha.application.calculadoras.*;
import com.sfp.folha.domain.FolhaMes;
import com.sfp.folha.domain.Holerite;
import com.sfp.folha.domain.Lancamento;
import com.sfp.folha.domain.FolhaMesRepository;
import com.sfp.folha.domain.LancamentoRepository;
import com.sfp.folha.infrastructure.persistence.*;
import com.sfp.funcionario.domain.Funcionario;
import com.sfp.funcionario.domain.FuncionarioRepository;
import com.sfp.funcionario.infrastructure.persistence.MySQLFuncionarioRepository;
import com.sfp.core.domain.FaixaINSSRepository;
import com.sfp.core.domain.FaixaIRRFRepository;
import com.sfp.infrastructure.persistence.MySQLFaixaINSSRepository;
import com.sfp.infrastructure.persistence.MySQLFaixaIRRFRepository;
import com.sfp.rubrica.domain.Rubrica;
import com.sfp.rubrica.domain.RubricaRepository;
import com.sfp.rubrica.infrastructure.persistence.MySQLRubricaRepository;
import com.sfp.empresa.domain.Empresa;
import com.sfp.empresa.application.ControladorEmpresa;
/**
 * @brief Controller da tela principal de Processamento e Visualização Eletrônica de Holerites.
 * Executa o processamento dinâmico em RAM baseando-se nos lançamentos do banco.
 * Realiza a exportação final dos arquivos PDF.
 */
public class TelaHoleriteController {

    // Classe auxiliar para exibir na tabela de preview
    public static class ItemHolerite {
        private int codigoRubrica;
        private String descricao;
        private String referencia;
        private BigDecimal provento;
        private BigDecimal desconto;

        public ItemHolerite(int codigoRubrica, String descricao, String referencia, BigDecimal provento, BigDecimal desconto) {
            this.codigoRubrica = codigoRubrica;
            this.descricao = descricao;
            this.referencia = referencia;
            this.provento = provento;
            this.desconto = desconto;
        }

        public int getCodigoRubrica() { return codigoRubrica; }
        public String getDescricao() { return descricao; }
        public String getReferencia() { return referencia; }
        public BigDecimal getProvento() { return provento; }
        public BigDecimal getDesconto() { return desconto; }
    }

    // Controle da Folha
    @FXML private ComboBox<FolhaMes> comboCompetencia;
    @FXML private Label labelStatus;
    @FXML private HBox boxControlesFolha;

    // Totais Gerais Removidos

    // Tabela Esquerda
    @FXML private TableView<Holerite> tabelaHolerites;
    @FXML private TableColumn<Holerite, String> colNome;
    @FXML private TableColumn<Holerite, String> colCargo;
    @FXML private TableColumn<Holerite, BigDecimal> colLiquido;

    // Pré-visualização Holerite (Direita)
    @FXML private VBox painelHoleritePreview;
    @FXML private Label lblPreviewEmpresa;
    @FXML private Label lblPreviewCnpj;
    @FXML private Label lblPreviewCompetencia;
    @FXML private Label lblPreviewFuncionario;
    @FXML private Label lblPreviewCargo;
    @FXML private TableView<ItemHolerite> tabelaRubricasPreview;
    @FXML private TableColumn<ItemHolerite, Integer> colRubricaCod;
    @FXML private TableColumn<ItemHolerite, String> colRubricaDesc;
    @FXML private TableColumn<ItemHolerite, String> colRubricaRef;
    @FXML private TableColumn<ItemHolerite, BigDecimal> colRubricaProv;
    @FXML private TableColumn<ItemHolerite, BigDecimal> colRubricaDescValor;
    @FXML private Label lblPreviewTotalProv;
    @FXML private Label lblPreviewTotalDesc;
    @FXML private Label lblPreviewLiquido;

    private FuncionarioRepository funcionarioRepository = new MySQLFuncionarioRepository();
    private FaixaINSSRepository inssRepository = new MySQLFaixaINSSRepository();
    private FaixaIRRFRepository irrfRepository = new MySQLFaixaIRRFRepository();
    private ServicoCicloFolha servicoCicloFolha = new ServicoCicloFolha();
    private ControladorEmpresa controladorEmpresa = new ControladorEmpresa();
    private RubricaRepository rubricaRepository = new MySQLRubricaRepository();
    private LancamentoRepository lancamentoRepository = new MySQLLancamentoRepository();
    
    private ProcessadorDeFolha processador;
    private FolhaMes folhaAtual;
    private ObservableList<Holerite> holeritesData = FXCollections.observableArrayList();
    private Empresa empresaConfigurada;
    private Map<Integer, Rubrica> mapaRubricas;

    @FXML
    public void initialize() {
        try {
            colNome.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFuncionario().getNome()));
            colCargo.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFuncionario().getCargo()));
            colLiquido.setCellValueFactory(new PropertyValueFactory<>("salarioLiquido"));

            colRubricaCod.setCellValueFactory(new PropertyValueFactory<>("codigoRubrica"));
            colRubricaDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            colRubricaRef.setCellValueFactory(new PropertyValueFactory<>("referencia"));
            colRubricaProv.setCellValueFactory(new PropertyValueFactory<>("provento"));
            colRubricaDescValor.setCellValueFactory(new PropertyValueFactory<>("desconto"));

            configurarMotor();

            tabelaHolerites.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> exibirPreview(newValue)
            );

            empresaConfigurada = controladorEmpresa.buscarEmpresa();
            
            List<Rubrica> rubricas = rubricaRepository.buscarTodas();
            mapaRubricas = rubricas.stream().collect(Collectors.toMap(Rubrica::getCodigo, r -> r));

            abrirFolhaAutomatica();

            comboCompetencia.setConverter(new javafx.util.StringConverter<FolhaMes>() {
                public String toString(FolhaMes f) { return f == null ? "" : f.getCompetencia() + " - " + f.getStatus(); }
                public FolhaMes fromString(String s) { return null; }
            });
            
            carregarComboFolhas();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configurarMotor() {
        CalculadoraSalarioProporcional calcProp = new CalculadoraSalarioProporcional();
        CalculadoraINSS calcINSS = new CalculadoraINSS(inssRepository.buscarTodas(), ConstantesFolha.TETO_INSS);
        CalculadoraIRRF calcIRRF = new CalculadoraIRRF(irrfRepository.buscarTodas(), ConstantesFolha.TETO_IRRF);
        CalculadoraFGTS calcFGTS = new CalculadoraFGTS();

        this.processador = new ProcessadorDeFolha(
            java.util.Arrays.asList(calcProp),
            java.util.Arrays.asList(calcINSS, calcIRRF),
            java.util.Arrays.asList(calcFGTS)
        );
    }

    private void carregarComboFolhas() {
        List<FolhaMes> todas = servicoCicloFolha.buscarTodasFolhas();
        comboCompetencia.getItems().setAll(todas);
        
        folhaAtual = servicoCicloFolha.buscarFolhaAberta();
        if (folhaAtual == null && !todas.isEmpty()) {
            folhaAtual = todas.get(todas.size() - 1);
        }
        
        comboCompetencia.setValue(folhaAtual);
        carregarDados();
    }
    
    @FXML
    public void mudarCompetencia() {
        FolhaMes f = comboCompetencia.getValue();
        if (f != null && !f.equals(folhaAtual)) {
            folhaAtual = f;
            carregarDados();
        }
    }

    private void carregarDados() {
        if (folhaAtual == null) {
            painelHoleritePreview.setVisible(false);
            atualizarStatusTela();
            return;
        }

        atualizarStatusTela();

        List<Funcionario> funcionarios = funcionarioRepository.buscarTodos();
        holeritesData.clear();

        BigDecimal somaBase = BigDecimal.ZERO;
        BigDecimal somaProv = BigDecimal.ZERO;
        BigDecimal somaDesc = BigDecimal.ZERO;
        BigDecimal somaLiq = BigDecimal.ZERO;
        BigDecimal somaFGTS = BigDecimal.ZERO;

        for (Funcionario f : funcionarios) {
            if (!f.getStatus() && "Aberta".equals(folhaAtual.getStatus())) {
                continue;
            }

            List<Lancamento> lancamentos = lancamentoRepository.buscarPorFolhaEFuncionario(folhaAtual.getId(), f.getCpf());
            Holerite h = processador.processar(f, lancamentos, folhaAtual.getDiasUteis());
            if (h != null) {
                holeritesData.add(h);

                somaBase = somaBase.add(f.getSalarioBruto());
                somaProv = somaProv.add(h.getTotalProventos());
                somaDesc = somaDesc.add(h.getTotalDescontos());
                somaLiq = somaLiq.add(h.getSalarioLiquido());
                if (h.getValorFGTS() != null) somaFGTS = somaFGTS.add(h.getValorFGTS());
            }
        }

        tabelaHolerites.setItems(holeritesData);
        // labels removidas

        if (!holeritesData.isEmpty()) {
            tabelaHolerites.getSelectionModel().selectFirst();
        } else {
            painelHoleritePreview.setVisible(false);
        }
    }

    private void exibirPreview(Holerite holerite) {
        if (holerite == null) {
            painelHoleritePreview.setVisible(false);
            return;
        }
        painelHoleritePreview.setVisible(true);

        if (empresaConfigurada != null && empresaConfigurada.getRazaoSocial() != null && !empresaConfigurada.getRazaoSocial().trim().isEmpty()) {
            lblPreviewEmpresa.setText(empresaConfigurada.getRazaoSocial());
            lblPreviewCnpj.setText("CNPJ: " + empresaConfigurada.getCnpj());
        } else {
            lblPreviewEmpresa.setText("Empresa Não Configurada");
            lblPreviewCnpj.setText("Verifique o menu Empresa");
        }

        lblPreviewCompetencia.setText("Competência: " + (folhaAtual != null ? folhaAtual.getCompetencia() : ""));
        lblPreviewFuncionario.setText(holerite.getFuncionario().getNome());
        lblPreviewCargo.setText(holerite.getFuncionario().getCargo());

        ObservableList<ItemHolerite> itens = FXCollections.observableArrayList();

        // Adiciona Salário Base
        itens.add(new ItemHolerite(1, "Salário Base Mensal", String.valueOf(holerite.getQuantidadeDiasUteis()), holerite.getFuncionario().getSalarioBruto(), null));
        
        // Mapear lançamentos e impostos para ItemHolerite
        if (holerite.getLancamentos() != null) {
            for (Lancamento l : holerite.getLancamentos()) {
                Rubrica r = mapaRubricas.get(l.getCodigoRubrica());
                if (r != null) {
                    boolean isProv = "Provento".equalsIgnoreCase(r.getNatureza());
                    itens.add(new ItemHolerite(
                        r.getCodigo(),
                        r.getDescricao(),
                        String.valueOf(l.getQuantidade()),
                        isProv ? l.getValor() : null,
                        !isProv ? l.getValor() : null
                    ));
                }
            }
        }

        // Impostos
        if (holerite.getDescontoINSS() != null && holerite.getDescontoINSS().compareTo(BigDecimal.ZERO) > 0) {
            itens.add(new ItemHolerite(998, "Desconto INSS", "-", null, holerite.getDescontoINSS()));
        }
        if (holerite.getDescontoIRRF() != null && holerite.getDescontoIRRF().compareTo(BigDecimal.ZERO) > 0) {
            itens.add(new ItemHolerite(999, "Desconto IRRF", "-", null, holerite.getDescontoIRRF()));
        }

        tabelaRubricasPreview.setItems(itens);

        lblPreviewTotalProv.setText(String.format("R$ %.2f", holerite.getTotalProventos()));
        lblPreviewTotalDesc.setText(String.format("R$ %.2f", holerite.getTotalDescontos()));
        lblPreviewLiquido.setText(String.format("R$ %.2f", holerite.getSalarioLiquido()));
    }

    @FXML
    public void exportarHoleriteSelecionado() {
        Holerite h = tabelaHolerites.getSelectionModel().getSelectedItem();
        if (h == null) {
            mostrarAlerta("Aviso", "Selecione um funcionário na tabela primeiro.");
            return;
        }

        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Salvar PDF em...");
        Stage stage = (Stage) tabelaHolerites.getScene().getWindow();
        File dir = dc.showDialog(stage);

        if (dir != null) {
            GeradorHoleritePDF gerador = new GeradorHoleritePDF();
            gerador.gerarPdf(h, folhaAtual.getCompetencia(), dir.getAbsolutePath(), mapaRubricas);
            mostrarMensagem("Sucesso", "Holerite de " + h.getFuncionario().getNome() + " exportado com sucesso!");
        }
    }

    @FXML
    public void exportarTodosHolerites() {
        if (holeritesData.isEmpty()) {
            mostrarAlerta("Aviso", "Nenhum holerite processado para exportar.");
            return;
        }

        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Salvar PDFs em...");
        Stage stage = (Stage) tabelaHolerites.getScene().getWindow();
        File dir = dc.showDialog(stage);

        if (dir != null) {
            GeradorHoleritePDF gerador = new GeradorHoleritePDF();
            for (Holerite h : holeritesData) {
                gerador.gerarPdf(h, folhaAtual.getCompetencia(), dir.getAbsolutePath(), mapaRubricas);
            }
            mostrarMensagem("Sucesso", "Todos os " + holeritesData.size() + " holerites exportados com sucesso!");
        }
    }

    @FXML
    public void exportarRelatorioGeral() {
        if (holeritesData.isEmpty()) {
            mostrarAlerta("Aviso", "Nenhum dado processado para exportar o relatório geral.");
            return;
        }

        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Salvar Relatório Geral em...");
        Stage stage = (Stage) tabelaHolerites.getScene().getWindow();
        File dir = dc.showDialog(stage);

        if (dir != null) {
            GeradorRelatorioGeralPDF gerador = new GeradorRelatorioGeralPDF();
            gerador.gerarPdf(holeritesData, folhaAtual.getCompetencia(), dir.getAbsolutePath(), "", "", "", "", "");
            mostrarMensagem("Sucesso", "Relatório Geral exportado com sucesso!");
        }
    }
    
    private void mostrarAlerta(String titulo, String msg) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    private void mostrarMensagem(String titulo, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
    public void atualizarStatusTela() {
        if (folhaAtual != null) {
            labelStatus.setText(folhaAtual.getStatus());
            boolean isFechada = "Fechada".equals(folhaAtual.getStatus());
            boxControlesFolha.setVisible(!isFechada);
            boxControlesFolha.setManaged(!isFechada);
        } else {
            labelStatus.setText("Nenhuma folha disponível");
            boxControlesFolha.setVisible(false);
            boxControlesFolha.setManaged(false);
        }
    }

    private void abrirFolhaAutomatica() {
        servicoCicloFolha.abrirFolhaAutomatica();
    }

    @FXML
    public void fecharFolha() {
        if (folhaAtual != null && !folhaAtual.getStatus().equals("Fechada")) {
            servicoCicloFolha.fecharFolha(folhaAtual);
            carregarComboFolhas();
        }
    }

    @FXML
    public void resetarFolhas() {
        try {
            servicoCicloFolha.resetarFolhas();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Todas as folhas e lançamentos foram zerados no banco de dados.");
            alert.showAndWait();
            
            folhaAtual = null; // Zera a referência
            carregarComboFolhas();
        } catch (Exception e) {
            mostrarAlerta("Erro", "Falha ao resetar folhas: " + e.getMessage());
        }
    }
}
