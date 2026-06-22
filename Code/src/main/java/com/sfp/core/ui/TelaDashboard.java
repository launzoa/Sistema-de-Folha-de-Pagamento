/**
 * @brief Classe responsável por gerenciar a tela de dashboard
 */
package com.sfp.core.ui;

import java.math.BigDecimal;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.sfp.core.application.ServicoDashboard;
import com.sfp.core.application.DashboardDados;

public class TelaDashboard {
    @FXML
    private Label labelCompetencia; // Label que mostra a competência da folha atual
    @FXML
    private Label labelFuncionarios; // Label que mostra o total de funcionários
    @FXML
    private Label labelStatus; // Label que mostra o status da folha atual
    @FXML
    private PieChart pieChartDistribuicao; // Gráfico de pizza que mostra a distribuição financeira
    @FXML
    private BarChart<String, Number> barChartComparativo; // Gráfico de barras que mostra o comparativo

    private ServicoDashboard servicoDashboard = new ServicoDashboard();

    /**
     * @brief Método que inicializa a tela de dashboard
     *        É o primeiro método a ser chamado quando a tela é carregada
     */
    @FXML
    public void initialize() {
        carregarDados();
    }

    /**
     * @brief Método que carrega os dados do dashboard
     */
    private void carregarDados() {
        try {
            // Processa os dados do dashboard
            DashboardDados dados = servicoDashboard.processarDadosDashboard();
            // Seta o total de funcionários
            labelFuncionarios.setText(String.valueOf(dados.getTotalFuncionarios()));
            // Seta a competência da folha atual
            if (dados.getFolhaAtual() != null) { // Caso tenha folha atual
                labelCompetencia.setText(dados.getFolhaAtual().getCompetencia());
                // Seta o status da folha atual
                gerarGraficos(dados);
            } else { // Caso não tenha folha atual
                labelCompetencia.setText("Nenhuma");
                labelStatus.setText("Nenhuma Folha");
            }
        } catch (Exception e) { // Caso ocorra um erro
            e.printStackTrace();
            labelFuncionarios.setText("Erro");
        }
    }

    /**
     * @brief Método que gera os gráficos do dashboard
     * @param dados Objeto que contém os dados do dashboard
     */
    @SuppressWarnings("unchecked")
    private void gerarGraficos(DashboardDados dados) {
        // --- PieChart: Distribuição Financeira ---
        double tLiq = dados.getTotalLiquido().doubleValue();
        double tImp = dados.getTotalImpostos().doubleValue();
        double tDesc = dados.getTotalDescontosDiversos().doubleValue();
        double totalGlobal = tLiq + tImp + tDesc;

        String lblLiq = String.format("Líquido (%.1f%%)", totalGlobal > 0 ? (tLiq / totalGlobal * 100) : 0);
        String lblImp = String.format("Impostos (%.1f%%)", totalGlobal > 0 ? (tImp / totalGlobal * 100) : 0);
        String lblDesc = String.format("Descontos Diversos (%.1f%%)",
                totalGlobal > 0 ? (tDesc / totalGlobal * 100) : 0);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data(lblLiq, tLiq),
                new PieChart.Data(lblImp, tImp),
                new PieChart.Data(lblDesc, tDesc));
        pieChartDistribuicao.setData(pieChartData);
        pieChartDistribuicao.setLegendVisible(true);
        pieChartDistribuicao.setLabelsVisible(true);

        // --- BarChart: Comparativo ---
        barChartComparativo.getData().clear();

        XYChart.Series<String, Number> seriesCusto = new XYChart.Series<>();
        seriesCusto.setName("Custo Empresa");
        // Custo Empresa = Total Bruto + FGTS (simplificado)
        BigDecimal custoEmpresa = dados.getCustoEmpresa();
        seriesCusto.getData().add(new XYChart.Data<>("Totais", custoEmpresa.doubleValue()));

        XYChart.Series<String, Number> seriesLiquido = new XYChart.Series<>();
        seriesLiquido.setName("Líquido Pago");
        seriesLiquido.getData().add(new XYChart.Data<>("Totais", dados.getTotalLiquido().doubleValue()));

        barChartComparativo.getData().addAll(seriesCusto, seriesLiquido);
    }
}
