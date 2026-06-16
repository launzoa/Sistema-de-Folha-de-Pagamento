/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sfp.folha.ui;

import com.sfp.auditoria.application.ServicoAuditoria;
import com.sfp.core.domain.Rubrica;
import com.sfp.rubrica.application.ControladorRubrica;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author manoe
 */
public class FormRubrica{
    
    @FXML private TextField txtCodigo;
    @FXML private TextField txtDescricao;
    @FXML private ComboBox<String> cbNatureza;
    @FXML private ComboBox<String> cbTipo;
    @FXML private CheckBox chkINSS;
    @FXML private CheckBox chkFGTS;
    @FXML private CheckBox chkIRRF;

    private ControladorRubrica controlador;
    private Rubrica rubricaEmEdicao; //null = cadastro, não-null = edição 
    
    @FXML
    public void initialize() 
    {
        cbNatureza.setItems(FXCollections.observableArrayList("Provento", "Desconto"));
        cbTipo.setItems(FXCollections.observableArrayList("Fixo", "Variável"));
    }
    
    public void setControlador(ControladorRubrica controlador) 
    {
        this.controlador = controlador;
    }
    
    public void setRubrica(Rubrica rubrica) 
    {
        this.rubricaEmEdicao = rubrica;

        if(rubrica == null) 
        {
            //modo cadastro: campo código editável
            txtCodigo.setDisable(false);
            return;
        }
        //modo edição: preenche e trava o código
        prepararEdicao(rubrica);

    }
    
    public void prepararEdicao(Rubrica rubrica) {
        this.rubricaEmEdicao = rubrica;
        txtCodigo.setText(String.valueOf(rubrica.getCodigo()));
        txtCodigo.setDisable(true);
        txtDescricao.setText(rubrica.getDescricao());
        cbNatureza.setValue(rubrica.getNatureza());
        cbTipo.setValue(rubrica.getTipo());
        chkINSS.setSelected(rubrica.isIncideINSS());
        chkFGTS.setSelected(rubrica.isIncideFGTS());
        chkIRRF.setSelected(rubrica.isIncideIRRF());
    }

    @FXML
    public void salvar() {
        if (!validar()) 
        {
            return;
        }

        if (rubricaEmEdicao == null) 
        {
            Rubrica nova = new Rubrica(
                Integer.parseInt(txtCodigo.getText().trim()),
                txtDescricao.getText().trim(),
                cbNatureza.getValue(),
                cbTipo.getValue(),
                chkINSS.isSelected(),
                chkFGTS.isSelected(),
                chkIRRF.isSelected(),
                false, // padrao = false para rubricas do usuário
                true   // ativo = true por padrão
            );
            controlador.cadastrarRubrica(nova);
            ServicoAuditoria.registrar("Cadastrar", "Rubrica", "Código: "+ Integer.parseInt(txtCodigo.getText().trim()));
        } 
        else 
        {
            rubricaEmEdicao.setDescricao(txtDescricao.getText().trim());
            rubricaEmEdicao.setNatureza(cbNatureza.getValue());
            rubricaEmEdicao.setTipo(cbTipo.getValue());
            rubricaEmEdicao.setIncideINSS(chkINSS.isSelected());
            rubricaEmEdicao.setIncideFGTS(chkFGTS.isSelected());
            rubricaEmEdicao.setIncideIRRF(chkIRRF.isSelected());
            
            controlador.editarRubrica(rubricaEmEdicao);
            ServicoAuditoria.registrar("Edição", "Rubrica", "Código: "+ Integer.parseInt(txtCodigo.getText().trim()));
        }

        fecharJanela();
    }
 
    @FXML
    public void cancelar() 
    {
        fecharJanela();
    }

    private boolean validar() 
    {
        StringBuilder erros = new StringBuilder();

        if(rubricaEmEdicao == null) 
        {
            String codStr = txtCodigo.getText().trim();
            if(codStr.isEmpty()) 
            {
                erros.append("Código é obrigatório.\n");
            } 
            else 
            {
                try 
                {
                    int cod = Integer.parseInt(codStr);
                    if(cod <= 0)
                    {
                        erros.append("O código deve ser maior que zero.\n");
                    }
                    else if(cod <= 5)
                    {
                        erros.append("Os códigos 001–005 são reservados para rubricas padrão.\n");
                    }
                } 
                catch (NumberFormatException e) 
                {
                    erros.append("O código deve ser um número inteiro.\n");
                }
            }
        }

        if(txtDescricao.getText().trim().isEmpty())
        {
            erros.append("• Descrição é obrigatória.\n");
        }

        if(cbNatureza.getValue() == null)
        {
            erros.append("• Selecione a Natureza.\n");
        }

        if(cbTipo.getValue() == null)
        {
            erros.append("• Selecione o Tipo.\n");
        }

        if(erros.length() > 0) 
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Dados inválidos");
            alert.setHeaderText("Corrija os seguintes erros:");
            alert.setContentText(erros.toString());
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private void fecharJanela() 
    {
        ((Stage) txtCodigo.getScene().getWindow()).close();
    }
}
