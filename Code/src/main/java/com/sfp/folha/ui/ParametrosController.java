package com.sfp.folha.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.math.BigDecimal;

import com.sfp.folha.domain.FaixaINSS;
import com.sfp.core.domain.FaixaINSSRepository;
import com.sfp.infrastructure.persistence.MySQLFaixaINSSRepository;

public class ParametrosController {
    @FXML
    private TextField inputPiso;

    @FXML
    private TextField inputTeto;

    @FXML
    private TextField inputAliquota;

    @FXML
    private TextField inputParcelaDeduzir;

    @FXML
    private Label labelMensagem;

    private FaixaINSSRepository repositorio = new MySQLFaixaINSSRepository();

    // @brief: Salva a faixa do INSS no banco de dados
    public void salvarFaixa() {
        try {
            // Lê os valores dos campos
            BigDecimal piso = new BigDecimal(inputPiso.getText());
            BigDecimal teto = new BigDecimal(inputTeto.getText());
            BigDecimal aliquota = new BigDecimal(inputAliquota.getText());
            BigDecimal parcela = new BigDecimal(inputParcelaDeduzir.getText());
            // Cria um objeto FaixaINSS
            FaixaINSS novaFaixa = new FaixaINSS(piso, teto, aliquota, parcela);

            // Verifica se já existe um registro
            var faixas = repositorio.buscarTodas();
            if (!faixas.isEmpty()) {
                for (FaixaINSS faixaExistente : faixas) {
                    if (faixaExistente.equals(novaFaixa)) {
                        labelMensagem.setText("Já existe uma faixa INSS com esses valores!");
                        labelMensagem.setStyle("-fx-text-fill: red;");
                        return;
                    }
                }
            }

            repositorio.salvar(novaFaixa); // Salva a faixa de INSS no BD
            labelMensagem.setText("Faixa INSS salva com sucesso!");
            labelMensagem.setStyle("-fx-text-fill: green;");

        } catch (Exception e) { // Captura qualquer exceção de erro e exibe na tela
            labelMensagem.setText("Erro ao salvar: " + e.getMessage());
            labelMensagem.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }
}
