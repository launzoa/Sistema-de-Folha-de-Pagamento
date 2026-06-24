/**
 * @brief Classe responsável por gerenciar a interface da tela de rubricas.
 */

package com.sfp.rubrica.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.sfp.core.ui.GerenciadorTema;
import com.sfp.rubrica.domain.Rubrica;
import com.sfp.rubrica.application.ControladorRubrica;
import com.sfp.core.domain.FaixaINSS;
import com.sfp.core.domain.FaixaINSSRepository;
import com.sfp.infrastructure.persistence.MySQLFaixaINSSRepository;
import com.sfp.core.domain.FaixaIRRF;
import com.sfp.core.domain.FaixaIRRFRepository;
import com.sfp.infrastructure.persistence.MySQLFaixaIRRFRepository;

public class TelaRubrica {
    // ABA 1: RUBRICAS
    @FXML
    private ComboBox<Rubrica> comboRubrica; // Campo para buscar rubricas
    @FXML
    private TableView<Rubrica> tabelaRubrica; // Tabela das rubricas
    @FXML
    private TableColumn<Rubrica, Integer> colCod; // Coluna do código
    @FXML
    private TableColumn<Rubrica, String> colDescr; // Coluna da descrição
    @FXML
    private TableColumn<Rubrica, String> colNat; // Coluna da natureza
    @FXML
    private TableColumn<Rubrica, String> colTipo; // Coluna do tipo
    @FXML
    private TableColumn<Rubrica, String> colIncINSS; // Coluna da incidência de INSS
    @FXML
    private TableColumn<Rubrica, String> colIncFGTS; // Coluna da incidência de FGTS
    @FXML
    private TableColumn<Rubrica, String> colIncIRRF; // Coluna da incidência de IRRF
    @FXML
    private TableColumn<Rubrica, String> colPadrao; // Coluna do padrão
    @FXML
    private TableColumn<Rubrica, String> colAtivo; // Coluna do ativo
    // ABA 2: PARÂMETROS LEGAIS - INSS
    @FXML
    private TableView<FaixaINSS> tabelaINSS; // Tabela das faixas de INSS
    @FXML
    private TableColumn<FaixaINSS, BigDecimal> colInssPiso; // Coluna do piso
    @FXML
    private TableColumn<FaixaINSS, BigDecimal> colInssTeto; // Coluna do teto
    @FXML
    private TableColumn<FaixaINSS, BigDecimal> colInssAliquota; // Coluna da alíquota
    // ABA 2: PARÂMETROS LEGAIS - IRRF
    @FXML
    private TableView<FaixaIRRF> tabelaIRRF; // Tabela das faixas de IRRF
    @FXML
    private TableColumn<FaixaIRRF, BigDecimal> colIrrfPiso; // Coluna do piso
    @FXML
    private TableColumn<FaixaIRRF, BigDecimal> colIrrfTeto; // Coluna do teto
    @FXML
    private TableColumn<FaixaIRRF, BigDecimal> colIrrfAliquota; // Coluna da alíquota
    @FXML
    private TableColumn<FaixaIRRF, BigDecimal> colIrrfParcela; // Coluna da parcela

    private final ControladorRubrica controlador = new ControladorRubrica();
    private final FaixaINSSRepository inssRepo = new MySQLFaixaINSSRepository();
    private final FaixaIRRFRepository irrfRepo = new MySQLFaixaIRRFRepository();

    /**
     * @brief Inicializa a tela de rubricas.
     */
    @FXML
    public void initialize() {
        // Configurações das tabelas de Rubricas
        tabelaRubrica.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        configurarColunasRubricas();
        // ComboBox de todas as Rubricas
        List<Rubrica> todas = controlador.listarTodasRubricas();
        comboRubrica.getItems().addAll(todas);
        comboRubrica.setConverter(new StringConverter<Rubrica>() {
            /**
             * @brief Converte uma Rubrica para String.
             */
            public String toString(Rubrica r) {
                return r == null ? "Todas" : r.getCodigo() + " - " + r.getDescricao();
            }

            /**
             * @brief Converte uma String para Rubrica.
             */
            public Rubrica fromString(String s) {
                return null;
            }
        });

        // Carregar todas as rubricas na tabela
        carregarTabelaRubricas(todas);

        // Configurações das tabelas de Parâmetros Legais
        tabelaINSS.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabelaIRRF.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        configurarColunasParametros();
        carregarTabelasParametros();
    }

    // -------------------------------------------------------------------------
    // ABA 1: RUBRICAS
    /**
     * @brief Configura as colunas da tabela de Rubricas.
     */
    private void configurarColunasRubricas() {
        // Configurações das colunas
        colCod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colDescr.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colNat.setCellValueFactory(new PropertyValueFactory<>("natureza"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colIncINSS.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIncideINSSStr()));
        colIncFGTS.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIncideFGTSStr()));
        colIncIRRF.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIncideIRRFStr()));
        colPadrao.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTipoLabel()));
        colAtivo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().isAtivo() ? "Ativo" : "Inativo"));

        // Formatação das linhas (coloração de rubricas inativas)
        tabelaRubrica.setRowFactory(tv -> new TableRow<>() {
            /**
             * @brief Atualiza o item da linha.
             */
            @Override
            protected void updateItem(Rubrica r, boolean empty) {
                super.updateItem(r, empty);
                if (r == null || empty) {
                    setStyle("");
                } else {
                    setStyle(r.isAtivo() ? "" : "-fx-text-fill: grey; -fx-opacity: 0.6;");
                }
            }
        });
    }

    /**
     * @brief Busca rubricas por código ou descrição.
     */
    @FXML
    public void buscar() {
        Rubrica r = comboRubrica.getValue();
        if (r == null) {
            carregarTabelaRubricas(controlador.listarTodasRubricas());
        } else {
            carregarTabelaRubricas(List.of(r));
        }
    }

    /**
     * @brief Limpa os filtros da tabela de Rubricas.
     */
    @FXML
    public void limparFiltros() {
        comboRubrica.setValue(null);
        carregarTabelaRubricas(controlador.listarTodasRubricas());
    }

    /**
     * @brief Adiciona uma nova rubrica.
     */
    @FXML
    public void addRubrica() {
        abrirFormularioRubrica(null);
    }

    /**
     * @brief Edita uma rubrica.
     */
    @FXML
    public void editarRubrica() {
        Rubrica selecionada = tabelaRubrica.getSelectionModel().getSelectedItem();
        // Verificação de rubrica selecionada
        if (selecionada == null) {
            mostrarAviso("Selecione uma rubrica na tabela para editar.");
            return;
        }
        // Verificação de rubrica padrão
        if (!controlador.podeEditar(selecionada.getCodigo())) {
            mostrarAviso("Rubricas Constitucionais e Legais (001–005 e 100-102) são blindadas e inalteráveis.");
            return;
        }
        // Verificação de rubrica inativa
        if (!selecionada.isAtivo()) {
            mostrarAviso("Não é possível editar uma rubrica inativa.");
            return;
        }
        abrirFormularioRubrica(selecionada);
    }

    /**
     * @brief Abre o formulário de rubrica.
     * @param rubrica A rubrica a ser editada (null para nova).
     */
    private void abrirFormularioRubrica(Rubrica rubrica) {
        try {
            // Carrega o formulário
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormRubrica.fxml"));
            Parent root = loader.load();
            // Configura o modo escuro
            if (GerenciadorTema.modoEscuroAtivo) {
                root.getStyleClass().add("dark-mode");
            }
            // Prepara e configura o formulário
            FormRubrica formController = loader.getController();
            formController.setControlador(controlador);
            formController.setRubrica(rubrica);
            // Cria e configura a janela
            Stage stage = new Stage();
            stage.setTitle(rubrica == null ? "Nova Rubrica" : "Editar Rubrica");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
            // Atualiza a tabela
            carregarTabelaRubricas(controlador.listarTodasRubricas());
        } catch (IOException e) { // Captura erro de IO
            e.printStackTrace();
        }
    }

    /**
     * @brief Carrega a tabela de rubricas.
     * @param lista A lista de rubricas.
     */
    private void carregarTabelaRubricas(List<Rubrica> lista) {
        tabelaRubrica.setItems(FXCollections.observableArrayList(lista));
    }

    // ----------------------------------------------------------------------------
    // METODOS DA ABA 2: PARÂMETROS LEGAIS
    /**
     * @brief Configura as colunas da tabela de Parâmetros Legais.
     */
    private void configurarColunasParametros() {
        tabelaINSS.setEditable(true);
        tabelaIRRF.setEditable(true);

        BigDecimalStringConverter converter = new BigDecimalStringConverter();

        // Configurações das colunas da tabela de INSS
        colInssPiso.setCellValueFactory(new PropertyValueFactory<>("piso"));
        colInssPiso.setCellFactory(TextFieldTableCell.forTableColumn(converter));
        colInssPiso.setOnEditCommit(event -> {
            FaixaINSS f = event.getRowValue();
            FaixaINSS old = new FaixaINSS(f.getPiso(), f.getTeto(), f.getAliquota(), f.getParcelaADeduzir());
            f.setPiso(event.getNewValue());
            inssRepo.atualizar(old, f);
        });

        colInssTeto.setCellValueFactory(new PropertyValueFactory<>("teto"));
        colInssTeto.setCellFactory(TextFieldTableCell.forTableColumn(converter));
        colInssTeto.setOnEditCommit(event -> {
            FaixaINSS f = event.getRowValue();
            FaixaINSS old = new FaixaINSS(f.getPiso(), f.getTeto(), f.getAliquota(), f.getParcelaADeduzir());
            f.setTeto(event.getNewValue());
            inssRepo.atualizar(old, f);
        });

        colInssAliquota.setCellValueFactory(new PropertyValueFactory<>("aliquota"));
        colInssAliquota.setCellFactory(TextFieldTableCell.forTableColumn(converter));
        colInssAliquota.setOnEditCommit(event -> {
            FaixaINSS f = event.getRowValue();
            FaixaINSS old = new FaixaINSS(f.getPiso(), f.getTeto(), f.getAliquota(), f.getParcelaADeduzir());
            f.setAliquota(event.getNewValue());
            inssRepo.atualizar(old, f);
        });

        // Configurações das colunas da tabela de IRRF
        colIrrfPiso.setCellValueFactory(new PropertyValueFactory<>("piso"));
        colIrrfPiso.setCellFactory(TextFieldTableCell.forTableColumn(converter));
        colIrrfPiso.setOnEditCommit(event -> {
            FaixaIRRF f = event.getRowValue();
            FaixaIRRF old = new FaixaIRRF(f.getPiso(), f.getTeto(), f.getAliquota(), f.getParcelaADeduzir());
            f.setPiso(event.getNewValue());
            irrfRepo.atualizar(old, f);
        });

        colIrrfTeto.setCellValueFactory(new PropertyValueFactory<>("teto"));
        colIrrfTeto.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<BigDecimal>() {
            @Override
            public String toString(BigDecimal object) {
                if (object == null || object.compareTo(new BigDecimal("999999.00")) >= 0) return "Ilimitado";
                return object.toString();
            }
            @Override
            public BigDecimal fromString(String string) {
                if (string == null || string.equalsIgnoreCase("Ilimitado") || string.isEmpty()) return new BigDecimal("9999999.99");
                return new BigDecimal(string.replace(",", "."));
            }
        }));
        colIrrfTeto.setOnEditCommit(event -> {
            FaixaIRRF f = event.getRowValue();
            FaixaIRRF old = new FaixaIRRF(f.getPiso(), f.getTeto(), f.getAliquota(), f.getParcelaADeduzir());
            f.setTeto(event.getNewValue());
            irrfRepo.atualizar(old, f);
        });

        colIrrfAliquota.setCellValueFactory(new PropertyValueFactory<>("aliquota"));
        colIrrfAliquota.setCellFactory(TextFieldTableCell.forTableColumn(converter));
        colIrrfAliquota.setOnEditCommit(event -> {
            FaixaIRRF f = event.getRowValue();
            FaixaIRRF old = new FaixaIRRF(f.getPiso(), f.getTeto(), f.getAliquota(), f.getParcelaADeduzir());
            f.setAliquota(event.getNewValue());
            irrfRepo.atualizar(old, f);
        });

        colIrrfParcela.setCellValueFactory(new PropertyValueFactory<>("parcelaDeduzir"));
        colIrrfParcela.setCellFactory(TextFieldTableCell.forTableColumn(converter));
        colIrrfParcela.setOnEditCommit(event -> {
            FaixaIRRF f = event.getRowValue();
            FaixaIRRF old = new FaixaIRRF(f.getPiso(), f.getTeto(), f.getAliquota(), f.getParcelaADeduzir());
            f.setParcelaADeduzir(event.getNewValue());
            irrfRepo.atualizar(old, f);
        });
    }

    /**
     * @brief Carrega as tabelas de Parâmetros Legais.
     */
    private void carregarTabelasParametros() {
        tabelaINSS.setItems(FXCollections.observableArrayList(inssRepo.buscarTodas()));
        tabelaIRRF.setItems(FXCollections.observableArrayList(irrfRepo.buscarTodas()));
    }


    // UTILITÁRIOS
    /**
     * @brief Mostra um aviso na tela.
     * @param mensagem A mensagem a ser exibida.
     */
    private void mostrarAviso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
