/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sfp.folha.ui;

import com.sfp.auditoria.application.ServicoAuditoria;
import com.sfp.core.domain.Rubrica;
import com.sfp.rubrica.application.ControladorRubrica;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author manoe
 */
public class TelaRubrica{
    
    @FXML private TextField txtCodigo;
    
    @FXML private TableView<Rubrica> tabelaRubrica;
    @FXML private TableColumn<Rubrica, Integer> colCod;
    @FXML private TableColumn<Rubrica, String> colDescr;
    @FXML private TableColumn<Rubrica, String> colNat;
    @FXML private TableColumn<Rubrica, String> colTipo;
    @FXML private TableColumn<Rubrica, String> colIncINSS;
    @FXML private TableColumn<Rubrica, String> colIncFGTS;
    @FXML private TableColumn<Rubrica, String> colIncIRRF;
    @FXML private TableColumn<Rubrica, String> colPadrao;
    @FXML private TableColumn<Rubrica, String> colAtivo;
    
    private final ControladorRubrica controlador = new ControladorRubrica();
    
    @FXML
    public void initialize() {
        tabelaRubrica.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        configurarColunas();
        carregarTabela(controlador.listarTodasRubricas());
    }  
    private void configurarColunas() {
        colCod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colDescr.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colNat.setCellValueFactory(new PropertyValueFactory<>("natureza"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colIncINSS.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIncideINSSStr()));
        colIncFGTS.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIncideFGTSStr()));
        colIncIRRF.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getIncideIRRFStr()));
        colPadrao.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTipoLabel()));
        colAtivo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().isAtivo() ? "Ativo" : "Inativo"));

        //Estiliza a linha inteira de rubricas inativas em cinza
        tabelaRubrica.setRowFactory(tv -> new javafx.scene.control.TableRow<>() {
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
    
    @FXML
    public void buscar()
    {
        String texto = txtCodigo.getText().trim();
        if (texto.isEmpty()) 
        {
            carregarTabela(controlador.listarTodasRubricas());
            return;
        }
        try 
        {
            int codigo = Integer.parseInt(texto);
            Rubrica r = controlador.buscarRubricaCod(codigo);
            if (r != null) 
            {
                carregarTabela(List.of(r));
            } 
            else 
            {
                carregarTabela(List.of());
                mostrarAviso("Nenhuma rubrica encontrada para o código " + codigo);
            }
        } catch (NumberFormatException ex) {
            mostrarAviso("O código deve ser um número inteiro.");
        }       
    }
    @FXML 
    public void limparFiltros()
    {
        txtCodigo.clear();
        carregarTabela(controlador.listarTodasRubricas());       
    }
    
    @FXML
    public void addRubrica()
    {
        abrirFormulario(null);   
    }
    
    @FXML 
    public void editarRubrica()
    {
        Rubrica selecionada = tabelaRubrica.getSelectionModel().getSelectedItem();
        if(selecionada == null) 
        {
            mostrarAviso("Selecione uma rubrica na tabela para editar.");
            return;
        }
        if(!controlador.podeEditar(selecionada.getCodigo())) 
        {
            mostrarAviso("Rubricas padrão (001–005) não podem ser editadas.");
            return;
        }
        if (!selecionada.isAtivo()) {
            mostrarAviso("Não é possível editar uma rubrica inativa.");
            return;
        }
        abrirFormulario(selecionada);      
    }
      
    private void abrirFormulario(Rubrica rubrica) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormRubrica.fxml"));
            Parent root = loader.load();
            
            if(GerenciadorTema.modoEscuroAtivo)
            {
                root.getStyleClass().add("dark-mode");
            }
            FormRubrica formController = loader.getController();
            formController.setControlador(controlador);
            formController.setRubrica(rubrica);

            Stage stage = new Stage();
            stage.setTitle(rubrica == null ? "Nova Rubrica" : "Editar Rubrica");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();

            carregarTabela(controlador.listarTodasRubricas());

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    private void carregarTabela(List<Rubrica> lista) 
    {
        tabelaRubrica.setItems(FXCollections.observableArrayList(lista));
    }   
    private void mostrarAviso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
