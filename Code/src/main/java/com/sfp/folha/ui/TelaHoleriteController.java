/**
 * @brief Controller da tela principal de Processamento e Visualização Eletrônica de Holerites.
 * Executa o processamento dinâmico em RAM baseando-se nos lançamentos do banco.
 * Realiza a exportação final dos arquivos PDF.
 */
package com.sfp.folha.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
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

public class TelaHoleriteController {

    /** @brief Classe auxiliar para exibir na tabela de preview */
    public static class ItemHolerite {
        private int codigoRubrica;
        private String descricao;
        private String referencia;
        private BigDecimal provento;
        private BigDecimal desconto;

        /**
         * @brief Método construtor da classe ItemHolerite.
         * @param codigoRubrica Código da rubrica
         * @param descricao     Descrição da rubrica
         * @param referencia    Referência da rubrica
         * @param provento      Valor do provento
         * @param desconto      Valor do desconto
         */
        public ItemHolerite(int codigoRubrica, String descricao, String referencia, BigDecimal provento,
                BigDecimal desconto) {
            this.codigoRubrica = codigoRubrica;
            this.descricao = descricao;
            this.referencia = referencia;
            this.provento = provento;
            this.desconto = desconto;
        }

        /**
         * @brief Método getter para o código da rubrica
         * @return Código da rubrica
         */
        public int getCodigoRubrica() {
            return codigoRubrica;
        }

        /**
         * @brief Método getter para a descrição
         * @return Descrição da rubrica
         */
        public String getDescricao() {
            return descricao;
        }

        /**
         * @brief Método getter para a referência
         * @return Referência
         */
        public String getReferencia() {
            return referencia;
        }

        /**
         * @brief Método getter para o provento
         * @return Provento
         */
        public BigDecimal getProvento() {
            return provento;
        }

        /**
         * @brief Método getter para o desconto
         * @return Desconto
         */
        public BigDecimal getDesconto() {
            return desconto;
        }
    }

    // Controle da Folha
    @FXML
    private ComboBox<FolhaMes> comboCompetencia; // Dropdown com todas as folhas cadastradas no sistema
    @FXML
    private Label labelStatus; // Status da folha

    // Tabela Esquerda
    @FXML
    private TableView<Holerite> tabelaHolerites; // Tabela que lista os holerites
    @FXML
    private TableColumn<Holerite, String> colNome; // Coluna com o nome do funcionário
    @FXML
    private TableColumn<Holerite, String> colCargo; // Coluna com o cargo do funcionário
    @FXML
    private TableColumn<Holerite, BigDecimal> colLiquido; // Coluna com o salário líquido do funcionário

    // Pré-visualização Holerite (Direita)
    @FXML
    private VBox painelHoleritePreview; // Painel que exibe o holerite em pré-visualização
    @FXML
    private Label lblPreviewEmpresa; // Label com o nome da empresa
    @FXML
    private Label lblPreviewCnpj; // Label com o CNPJ da empresa
    @FXML
    private Label lblPreviewCompetencia; // Label com a competência
    @FXML
    private Label lblPreviewFuncionario; // Label com o nome do funcionário
    @FXML
    private Label lblPreviewCargo; // Label com o cargo do funcionário
    @FXML
    private TableView<ItemHolerite> tabelaRubricasPreview; // Tabela com as rubricas do holerite
    @FXML
    private TableColumn<ItemHolerite, Integer> colRubricaCod; // Coluna com o código da rubrica
    @FXML
    private TableColumn<ItemHolerite, String> colRubricaDesc; // Coluna com a descrição da rubrica
    @FXML
    private TableColumn<ItemHolerite, String> colRubricaRef; // Coluna com a referência da rubrica
    @FXML
    private TableColumn<ItemHolerite, BigDecimal> colRubricaProv; // Coluna com o valor do provento
    @FXML
    private TableColumn<ItemHolerite, BigDecimal> colRubricaDescValor; // Coluna com o valor do desconto
    @FXML
    private Label lblPreviewTotalProv; // Label com o total de proventos
    @FXML
    private Label lblPreviewTotalDesc; // Label com o total de descontos
    @FXML
    private Label lblPreviewLiquido; // Label com o salário líquido

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

    /**
     * @brief Método responsável por inicializar a tela de holerites. Responsável
     *        por
     *        carregar os dados da folha de pagamento, processar os holerites e
     *        exibir na tabela de holerites. Responsável por inicializar a tabela de
     *        rubricas e exibir os dados da rubrica. Responsável por inicializar a
     *        tabela de descontos e exibir os dados dos descontos.
     */
    @FXML
    public void initialize() {
        try {
            // Configuração das colunas da tabela de holerites
            colNome.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                    cellData.getValue().getFuncionario().getNome()));
            colCargo.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                    cellData.getValue().getFuncionario().getCargo()));
            colLiquido.setCellValueFactory(new PropertyValueFactory<>("salarioLiquido"));
            // Configuração das colunas da tabela de rubricas
            colRubricaCod.setCellValueFactory(new PropertyValueFactory<>("codigoRubrica"));
            colRubricaDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            colRubricaRef.setCellValueFactory(new PropertyValueFactory<>("referencia"));
            colRubricaProv.setCellValueFactory(new PropertyValueFactory<>("provento"));
            colRubricaDescValor.setCellValueFactory(new PropertyValueFactory<>("desconto"));
            // Configuração dos eventos
            configurarMotor();
            // Evento de seleção de holerite
            tabelaHolerites.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> exibirPreview(newValue));
            // Busca empresa configurada
            empresaConfigurada = controladorEmpresa.buscarEmpresa();
            // Busca todas as rubricas
            List<Rubrica> rubricas = rubricaRepository.buscarTodas();
            mapaRubricas = rubricas.stream().collect(Collectors.toMap(Rubrica::getCodigo, r -> r));
            // Gerencia o ciclo das folhas
            gerenciarCicloFolhas();
            // Converter comboCompetencia
            comboCompetencia.setConverter(new StringConverter<FolhaMes>() {
                /**
                 * @brief Converte FolhaMes para String
                 * @param f FolhaMes a ser convertida
                 * @return String com a representação da FolhaMes
                 */
                public String toString(FolhaMes f) {
                    return f == null ? "" : f.getCompetencia() + " - " + f.getStatus();
                }

                /**
                 * @brief Converte String para FolhaMes
                 * @param s String a ser convertida
                 * @return FolhaMes com a representação da String
                 */
                public FolhaMes fromString(String s) {
                    return null;
                }
            });
            // Carrega comboCompetencia
            carregarComboFolhas();

        } catch (Exception e) { // Captura exceções e imprime o stack trace
            e.printStackTrace();
        }
    }

    /**
     * @brief Configura o processador de folha
     */
    private void configurarMotor() {
        // Inicializa as calculadoras
        CalculadoraSalarioProporcional calcProp = new CalculadoraSalarioProporcional();
        CalculadoraINSS calcINSS = new CalculadoraINSS(inssRepository.buscarTodas(), ConstantesFolha.TETO_INSS);
        CalculadoraIRRF calcIRRF = new CalculadoraIRRF(irrfRepository.buscarTodas(), ConstantesFolha.TETO_IRRF);
        CalculadoraFGTS calcFGTS = new CalculadoraFGTS();
        // Inicializa o processador
        this.processador = new ProcessadorDeFolha(
                java.util.Arrays.asList(calcProp),
                java.util.Arrays.asList(calcINSS, calcIRRF),
                java.util.Arrays.asList(calcFGTS));
    }

    /**
     * @brief Carrega as folhas no comboCompetencia
     */
    private void carregarComboFolhas() {
        // Busca todas as folhas
        List<FolhaMes> todas = servicoCicloFolha.buscarTodasFolhas();
        // Adiciona as folhas no comboCompetencia
        comboCompetencia.getItems().setAll(todas);

        // Busca a folha atual
        folhaAtual = servicoCicloFolha.buscarFolhaAberta();
        // Se não houver folha atual, define a última folha
        if (folhaAtual == null && !todas.isEmpty()) {
            folhaAtual = todas.get(todas.size() - 1);
        }
        // Define o valor do comboCompetencia
        comboCompetencia.setValue(folhaAtual);
        // Carrega os dados
        carregarDados();
    }

    /**
     * @brief Método responsável por mudar a competência da folha
     */
    @FXML
    public void mudarCompetencia() {
        // Busca a competência selecionada
        FolhaMes f = comboCompetencia.getValue();
        // Verifica se a competência é diferente da atual
        if (f != null && !f.equals(folhaAtual)) {
            // Atualiza a competência atual
            folhaAtual = f;
            // Recarrega os dados
            carregarDados();
        }
    }

    /**
     * @brief Carrega os dados da folha
     */
    private void carregarDados() {
        // Verifica se a folha atual é nula
        if (folhaAtual == null) {
            // Oculta o painel de holerite
            painelHoleritePreview.setVisible(false);
            // Atualiza o status da tela
            atualizarStatusTela();
            return;
        }
        // Atualiza o status da tela
        atualizarStatusTela();
        // Busca todos os funcionários
        List<Funcionario> funcionarios = funcionarioRepository.buscarTodos();
        // Limpa os dados da tabela
        holeritesData.clear();
        // Zera os acumuladores
        BigDecimal somaBase = BigDecimal.ZERO;
        BigDecimal somaProv = BigDecimal.ZERO;
        BigDecimal somaDesc = BigDecimal.ZERO;
        BigDecimal somaLiq = BigDecimal.ZERO;
        BigDecimal somaFGTS = BigDecimal.ZERO;

        // Itera sobre todos os funcionários
        for (Funcionario f : funcionarios) {
            // Verifica se o funcionário está ativo e se a folha está aberta
            if (!f.getStatus() && "Aberta".equals(folhaAtual.getStatus())) {
                // Pula o funcionário se estiver inativo e a folha estiver aberta
                continue;
            }
            // Busca os lançamentos do funcionário
            List<Lancamento> lancamentos = lancamentoRepository.buscarPorFolhaEFuncionario(folhaAtual.getId(),
                    f.getCpf());
            // Processa o holerite
            Holerite h = processador.processar(f, lancamentos, folhaAtual.getDiasUteis());
            // Adiciona o holerite na tabela
            if (h != null) {
                holeritesData.add(h);
                // Adiciona o salário bruto na soma base
                somaBase = somaBase.add(f.getSalarioBruto());
                // Adiciona o total de proventos na soma de proventos
                somaProv = somaProv.add(h.getTotalProventos());
                // Adiciona o total de descontos na soma de descontos
                somaDesc = somaDesc.add(h.getTotalDescontos());
                // Adiciona o salário líquido na soma líquida
                somaLiq = somaLiq.add(h.getSalarioLiquido());
                // Verifica se o valor do FGTS é diferente de nulo
                if (h.getValorFGTS() != null)
                    // Adiciona o valor do FGTS na soma do FGTS
                    somaFGTS = somaFGTS.add(h.getValorFGTS());
            }
        }

        tabelaHolerites.setItems(holeritesData); // Define os dados da tabela
        // Seleciona o primeiro holerite
        if (!holeritesData.isEmpty()) {
            tabelaHolerites.getSelectionModel().selectFirst();
        } else {
            painelHoleritePreview.setVisible(false);
        }
    }

    /**
     * @brief Exibe o preview do holerite
     * @param holerite Holerite a ser exibido
     */
    private void exibirPreview(Holerite holerite) {
        // Verifica se o holerite é nulo
        if (holerite == null) {
            painelHoleritePreview.setVisible(false);
            return;
        }
        // Torna o painel visível
        painelHoleritePreview.setVisible(true);

        // Verifica se a empresa está configurada
        if (empresaConfigurada != null && empresaConfigurada.getRazaoSocial() != null
                && !empresaConfigurada.getRazaoSocial().trim().isEmpty()) {
            // Define a razão social
            lblPreviewEmpresa.setText(empresaConfigurada.getRazaoSocial());
            lblPreviewCnpj.setText("CNPJ: " + empresaConfigurada.getCnpj());
        } else { // Caso a empresa não esteja configurada
            lblPreviewEmpresa.setText("Empresa Não Configurada");
            lblPreviewCnpj.setText("Verifique o menu Empresa");
        }
        // Define a competência
        lblPreviewCompetencia.setText("Competência: " + (folhaAtual != null ? folhaAtual.getCompetencia() : ""));
        // Define o funcionário
        lblPreviewFuncionario.setText(holerite.getFuncionario().getNome());
        // Define o cargo
        lblPreviewCargo.setText(holerite.getFuncionario().getCargo());
        // Cria a lista de itens
        ObservableList<ItemHolerite> itens = FXCollections.observableArrayList();
        // Adiciona Salário Base
        itens.add(new ItemHolerite(1, "Salário Base Mensal", String.valueOf(holerite.getQuantidadeDiasUteis()),
                holerite.getFuncionario().getSalarioBruto(), null));

        // Mapear lançamentos e impostos para ItemHolerite
        if (holerite.getLancamentos() != null) {
            // Itera sobre todos os lançamentos
            for (Lancamento l : holerite.getLancamentos()) {
                // Busca a rubrica
                Rubrica r = mapaRubricas.get(l.getCodigoRubrica());
                // Verifica se a rubrica é diferente de nula
                if (r != null) {
                    // Verifica se a rubrica é provento
                    boolean isProv = "Provento".equalsIgnoreCase(r.getNatureza());
                    // Adiciona o item
                    itens.add(new ItemHolerite(
                            r.getCodigo(),
                            r.getDescricao(),
                            String.valueOf(l.getQuantidade()),
                            isProv ? l.getValor() : null,
                            !isProv ? l.getValor() : null));
                }
            }
        }

        // Verifica se o desconto de INSS é maior que zero
        if (holerite.getDescontoINSS() != null && holerite.getDescontoINSS().compareTo(BigDecimal.ZERO) > 0) {
            itens.add(new ItemHolerite(998, "Desconto INSS", "-", null, holerite.getDescontoINSS()));
        }
        // Verifica se o desconto de IRRF é maior que zero
        if (holerite.getDescontoIRRF() != null && holerite.getDescontoIRRF().compareTo(BigDecimal.ZERO) > 0) {
            itens.add(new ItemHolerite(999, "Desconto IRRF", "-", null, holerite.getDescontoIRRF()));
        }

        tabelaRubricasPreview.setItems(itens);

        // Formata os valores
        lblPreviewTotalProv.setText(String.format("R$ %.2f", holerite.getTotalProventos()));
        lblPreviewTotalDesc.setText(String.format("R$ %.2f", holerite.getTotalDescontos()));
        lblPreviewLiquido.setText(String.format("R$ %.2f", holerite.getSalarioLiquido()));
    }

    /**
     * @brief Exporta o holerite selecionado
     */
    @FXML
    public void exportarHoleriteSelecionado() {
        // Seleciona o holerite
        Holerite h = tabelaHolerites.getSelectionModel().getSelectedItem();
        // Verifica se o holerite é nulo
        if (h == null) {
            mostrarAlerta("Aviso", "Selecione um funcionário na tabela primeiro.");
            return;
        }
        // Cria o chooser de diretório
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Salvar PDF em...");
        // Pega a janela atual
        Stage stage = (Stage) tabelaHolerites.getScene().getWindow();
        // Mostra o chooser
        File dir = dc.showDialog(stage);
        // Verifica se o diretório não é nulo
        if (dir != null) {
            // Cria o gerador de PDF
            GeradorHoleritePDF gerador = new GeradorHoleritePDF();
            // Gera o PDF
            gerador.gerarPdf(h, folhaAtual.getCompetencia(), dir.getAbsolutePath(), mapaRubricas);
            // Mostra a mensagem
            mostrarMensagem("Sucesso", "Holerite de " + h.getFuncionario().getNome() + " exportado com sucesso!");
        }
    }

    /**
     * @brief Exporta todos os holerites
     */
    @FXML
    public void exportarTodosHolerites() {
        if (holeritesData.isEmpty()) { // Verifica se a lista de holerites está vazia
            mostrarAlerta("Aviso", "Nenhum holerite processado para exportar.");
            return;
        }
        // Cria o chooser de diretório
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Salvar PDFs em...");
        // Pega a janela atual
        Stage stage = (Stage) tabelaHolerites.getScene().getWindow();
        // Mostra o chooser
        File dir = dc.showDialog(stage);

        if (dir != null) { // Verifica se o diretório não é nulo
            // Cria o gerador de PDF
            GeradorHoleritePDF gerador = new GeradorHoleritePDF();
            // Itera sobre todos os holerites
            for (Holerite h : holeritesData) {
                // Gera o PDF
                gerador.gerarPdf(h, folhaAtual.getCompetencia(), dir.getAbsolutePath(), mapaRubricas);
            }
            // Mostra a mensagem
            mostrarMensagem("Sucesso", "Todos os " + holeritesData.size() + " holerites exportados com sucesso!");
        }
    }

    /**
     * @brief Exporta o relatório geral
     */
    @FXML
    public void exportarRelatorioGeral() {
        if (holeritesData.isEmpty()) { // Verifica se a lista de holerites está vazia
            mostrarAlerta("Aviso", "Nenhum dado processado para exportar o relatório geral.");
            return;
        }
        // Cria o chooser de diretório
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Salvar Relatório Geral em...");
        // Pega a janela atual
        Stage stage = (Stage) tabelaHolerites.getScene().getWindow();
        // Mostra o chooser
        File dir = dc.showDialog(stage);

        if (dir != null) { // Verifica se o diretório não é nulo
            // Cria o gerador de PDF
            GeradorRelatorioGeralPDF gerador = new GeradorRelatorioGeralPDF();
            // Gera o PDF
            gerador.gerarPdf(holeritesData, folhaAtual.getCompetencia(), dir.getAbsolutePath(), "", "", "", "", "");
            // Mostra a mensagem
            mostrarMensagem("Sucesso", "Relatório Geral exportado com sucesso!");
        }
    }

    /**
     * @brief Mostra um alerta
     * @param titulo Título do alerta
     * @param msg    Mensagem do alerta
     */
    private void mostrarAlerta(String titulo, String msg) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    /**
     * @brief Mostra uma mensagem
     * @param titulo Título da mensagem
     * @param msg    Mensagem da mensagem
     */
    private void mostrarMensagem(String titulo, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    /**
     * @brief Atualiza o status da tela
     */
    public void atualizarStatusTela() {
        if (folhaAtual != null) { // Verifica se a folha atual não é nula
            labelStatus.setText(folhaAtual.getStatus());
        } else {
            labelStatus.setText("Nenhuma folha disponível");
        }
    }

    /**
     * @brief Gerencia o ciclo das folhas
     */
    private void gerenciarCicloFolhas() {
        servicoCicloFolha.gerenciarCicloFolhas();
    }
}
