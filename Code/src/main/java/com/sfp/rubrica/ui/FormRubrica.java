/**
 * @brief classe responsavel por abrir o formulario de rubricas
 
 */
package com.sfp.rubrica.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import com.sfp.rubrica.domain.Rubrica;
import com.sfp.rubrica.application.ControladorRubrica;

public class FormRubrica {
    @FXML
    private TextField txtCodigo; // Texto para inserir o código da rubrica
    @FXML
    private TextField txtDescricao; // Texto para inserir a descrição da rubrica
    @FXML
    private ComboBox<String> cbNatureza; // ComboBox para selecionar a natureza da rubrica
    @FXML
    private ComboBox<String> cbTipo; // ComboBox para selecionar o tipo da rubrica
    @FXML
    private CheckBox chkINSS; // CheckBox para selecionar se a rubrica incide INSS
    @FXML
    private CheckBox chkFGTS; // CheckBox para selecionar se a rubrica incide FGTS
    @FXML
    private CheckBox chkIRRF; // CheckBox para selecionar se a rubrica incide IRRF
    @FXML
    private CheckBox chkAtivo; // CheckBox para selecionar se a rubrica está ativa

    private ControladorRubrica controlador;
    private Rubrica rubricaEmEdicao; // null = cadastro, não-null = edição

    /**
     * @brief Inicializa o formulario de rubricas
     */
    @FXML
    public void initialize() {
        cbNatureza.setItems(FXCollections.observableArrayList("Provento", "Desconto"));
        cbTipo.setItems(FXCollections.observableArrayList("Fixo", "Variável"));
    }

    /**
     * @brief Seta o controlador da rubrica
     * @param controlador Controlador da rubrica
     */
    public void setControlador(ControladorRubrica controlador) {
        this.controlador = controlador;
    }

    /**
     * @brief Seta a rubrica que será editada
     * @param rubrica Rubrica que será editada
     */
    public void setRubrica(Rubrica rubrica) {
        this.rubricaEmEdicao = rubrica;

        if (rubrica == null) {// modo cadastro -> campo código editável
            txtCodigo.setDisable(false);
            return;
        }
        // modo edição -> preenche e trava o código
        prepararEdicao(rubrica);
    }

    /**
     * @brief Prepara o formulario para edição de uma rubrica
     * @param rubrica Rubrica que será editada
     */
    public void prepararEdicao(Rubrica rubrica) {
        this.rubricaEmEdicao = rubrica; // Seta a rubrica que será editada
        // Seta os dados da rubrica nos campos do formulario
        txtCodigo.setText(String.valueOf(rubrica.getCodigo()));
        txtCodigo.setDisable(true); // Código não pode ser editado
        txtDescricao.setText(rubrica.getDescricao());
        cbNatureza.setValue(rubrica.getNatureza());
        cbTipo.setValue(rubrica.getTipo());
        chkINSS.setSelected(rubrica.isIncideINSS());
        chkFGTS.setSelected(rubrica.isIncideFGTS());
        chkIRRF.setSelected(rubrica.isIncideIRRF());
        chkAtivo.setSelected(rubrica.isAtivo());
    }

    /**
     * @brief Salva a rubrica
     */
    @FXML
    public void salvar() {
        // Valida os dados da rubrica
        if (!validar()) {
            return; // Se os dados forem inválidos, não salva
        }
        // Se os dados forem válidos, salva a rubrica
        if (rubricaEmEdicao == null) {
            Rubrica nova = new Rubrica(
                    Integer.parseInt(txtCodigo.getText().trim()),
                    txtDescricao.getText().trim(),
                    cbNatureza.getValue(),
                    cbTipo.getValue(),
                    chkINSS.isSelected(),
                    chkFGTS.isSelected(),
                    chkIRRF.isSelected(),
                    false, // padrao = false para rubricas do usuário
                    chkAtivo.isSelected() // ativo
            );
            // Cadastra a rubrica
            controlador.cadastrarRubrica(nova);
        } else { // Se não, edita a rubrica
            rubricaEmEdicao.setDescricao(txtDescricao.getText().trim());
            rubricaEmEdicao.setNatureza(cbNatureza.getValue());
            rubricaEmEdicao.setTipo(cbTipo.getValue());
            rubricaEmEdicao.setIncideINSS(chkINSS.isSelected());
            rubricaEmEdicao.setIncideFGTS(chkFGTS.isSelected());
            rubricaEmEdicao.setIncideIRRF(chkIRRF.isSelected());
            rubricaEmEdicao.setAtivo(chkAtivo.isSelected());
            // Edita a rubrica
            controlador.editarRubrica(rubricaEmEdicao);
        }
        fecharJanela();
    }

    /**
     * @brief Cancela a operação e fecha a janela
     */
    @FXML
    public void cancelar() {
        fecharJanela();
    }

    /**
     * @brief Valida os dados da rubrica
     * @return true se os dados forem válidos, false caso contrário
     */
    private boolean validar() {
        StringBuilder erros = new StringBuilder();
        // Se for novo cadastro
        if (rubricaEmEdicao == null) {
            String codStr = txtCodigo.getText().trim();
            if (codStr.isEmpty()) { // Se o código estiver vazio
                erros.append("Código é obrigatório.\n");
            } else { // Se o código não estiver vazio
                try { // Tenta converter o código para inteiro
                    int cod = Integer.parseInt(codStr);
                    if (cod <= 0) { // Se o código for menor ou igual a zero
                        erros.append("O código deve ser maior que zero.\n");
                    } else if (cod <= 5) { // Se o código for menor ou igual a 5
                        erros.append("Os códigos 001–005 são reservados para rubricas padrão.\n");
                    }
                } catch (NumberFormatException e) {// Se o código não for um número inteiro
                    erros.append("O código deve ser um número inteiro.\n");
                }
            }
        }

        if (txtDescricao.getText().trim().isEmpty()) { // Se a descrição estiver vazia
            erros.append("• Descrição é obrigatória.\n");
        }

        if (cbNatureza.getValue() == null) { // Se a natureza não estiver selecionada
            erros.append("• Selecione a Natureza.\n");
        }

        if (cbTipo.getValue() == null) { // Se o tipo não estiver selecionado
            erros.append("• Selecione o Tipo.\n");
        }

        if (erros.length() > 0) { // Se houver erros
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Dados inválidos");
            alert.setHeaderText("Corrija os seguintes erros:");
            alert.setContentText(erros.toString());
            alert.showAndWait();
            return false; // Retorna false para possíveis erros
        }
        // Se não houver erros
        return true;
    }

    /**
     * @brief Fecha a janela
     */
    private void fecharJanela() {
        ((Stage) txtCodigo.getScene().getWindow()).close();
    }
}
