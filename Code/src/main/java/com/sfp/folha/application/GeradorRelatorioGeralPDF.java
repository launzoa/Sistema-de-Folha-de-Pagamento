
package com.sfp.folha.application;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Rectangle;

import java.awt.Color;
import java.util.List;
import java.io.FileOutputStream;
import java.util.logging.Logger;

import com.sfp.empresa.domain.Empresa;
import com.sfp.folha.domain.Holerite;

/**
 * @brief Classe responsável por gerar o arquivo PDF físico do Relatório Geral
 *        da Folha.
 */
public class GeradorRelatorioGeralPDF {

    /**
     * @brief Gera o arquivo PDF de fechamento mensal consolidado.
     * @param holerites      Lista de holerites processados no mês.
     * @param competencia    Competência do relatório (Mês/Ano).
     * @param diretorioSaida Caminho onde o PDF será salvo.
     * @param totalBase      Total geral de Salários Base.
     * @param totalProv      Total geral de Proventos.
     * @param totalDesc      Total geral de Descontos.
     * @param totalLiq       Total geral Líquido a pagar.
     * @param totalFgts      Total geral de FGTS a recolher.
     */
    public void gerarPdf(List<Holerite> holerites, String competencia, String diretorioSaida,
            String totalBase, String totalProv, String totalDesc, String totalLiq, String totalFgts,
            Empresa empresaConfigurada) {
        // Nome do arquivo PDF com a competência.
        String nomeArquivo = diretorioSaida + "/Relatorio_Geral_Empresa_" + competencia.replaceAll("/", "_") + ".pdf";
        // Cria o documento.
        Document document = new Document(PageSize.A4);

        try {
            // Cria o arquivo PDF
            PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo));
            // Abre o documento
            document.open();
            // Define as fontes
            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font fontSubTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 10);
            Font fontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
            // Cabeçalho do documento
            Paragraph titulo = new Paragraph("RELATÓRIO GERAL DA FOLHA DE PAGAMENTO", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(new Paragraph("\n"));
            // Informações da Empresa e Mês
            PdfPTable tableCabecalho = new PdfPTable(2);
            tableCabecalho.setWidthPercentage(100);
            // Busca os dados da empresa (Injetado em vez de pesquisar no BD)
            String nomeEmpresa = (empresaConfigurada != null && empresaConfigurada.getRazaoSocial() != null)
                    ? empresaConfigurada.getRazaoSocial()
                    : "SFP Corporation LTDA";
            String cnpjEmpresa = (empresaConfigurada != null && empresaConfigurada.getCnpj() != null)
                    ? empresaConfigurada.getCnpj()
                    : "00.000.000/0001-00";
            // Cria a célula com os dados da empresa
            PdfPCell cellEmpresa = new PdfPCell(
                    new Phrase("EMPRESA: " + nomeEmpresa + "\nCNPJ: " + cnpjEmpresa, fontNormal));
            cellEmpresa.setBorder(Rectangle.NO_BORDER);
            tableCabecalho.addCell(cellEmpresa);
            // Cria a célula com os dados da competência
            PdfPCell cellMes = new PdfPCell(new Phrase("COMPETÊNCIA: " + competencia, fontBold));
            cellMes.setBorder(Rectangle.NO_BORDER);
            cellMes.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableCabecalho.addCell(cellMes);
            // Adiciona a tabela de cabeçalho ao documento
            document.add(tableCabecalho);
            document.add(new Paragraph("\n"));
            // Resumo Financeiro da Empresa
            PdfPTable tableResumo = new PdfPTable(2);
            tableResumo.setWidthPercentage(100);
            tableResumo.setWidths(new float[] { 6f, 4f });
            // Título do resumo
            PdfPCell cTit = new PdfPCell(new Phrase("RESUMO CONSOLIDADO", fontSubTitulo));
            cTit.setColspan(2);
            cTit.setHorizontalAlignment(Element.ALIGN_CENTER);
            cTit.setBackgroundColor(new Color(230, 230, 230));
            tableResumo.addCell(cTit);
            // Método auxiliar para adicionar linhas ao resumo
            adicionarLinhaResumo(tableResumo, "Total Salário Base:", totalBase, fontNormal);
            adicionarLinhaResumo(tableResumo, "Total Proventos:", totalProv, fontNormal);
            adicionarLinhaResumo(tableResumo, "Total Descontos (INSS, IRRF, Faltas, etc):", totalDesc, fontNormal);
            adicionarLinhaResumo(tableResumo, "Total FGTS a Recolher:", totalFgts, fontNormal);
            // Total Líquido a Pagar
            PdfPCell cLiq = new PdfPCell(new Phrase("Total Líquido a Pagar:", fontBold));
            PdfPCell cLiqVal = new PdfPCell(new Phrase(totalLiq, fontBold));
            cLiqVal.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableResumo.addCell(cLiq);
            tableResumo.addCell(cLiqVal);
            // Adiciona a tabela de resumo ao documento
            document.add(tableResumo);
            document.add(new Paragraph("\n"));
            // Lista de Funcionários Processados
            Paragraph subTitulo = new Paragraph("FUNCIONÁRIOS PROCESSADOS (" + holerites.size() + ")", fontSubTitulo);
            document.add(subTitulo);
            document.add(new Paragraph("\n"));
            // Tabela de Funcionários
            PdfPTable tableFuncs = new PdfPTable(3);
            tableFuncs.setWidthPercentage(100);
            tableFuncs.setWidths(new float[] { 5f, 2.5f, 2.5f });
            // Cabeçalho da tabela de funcionários
            tableFuncs.addCell(new PdfPCell(new Phrase("Nome", fontBold)));
            tableFuncs.addCell(new PdfPCell(new Phrase("Cargo", fontBold)));
            tableFuncs.addCell(new PdfPCell(new Phrase("Líquido", fontBold)));
            // Adiciona os funcionários à tabela
            for (Holerite h : holerites) {
                tableFuncs.addCell(new PdfPCell(new Phrase(h.getFuncionario().getNome(), fontNormal)));
                tableFuncs.addCell(new PdfPCell(new Phrase(h.getFuncionario().getCargo(), fontNormal)));
                tableFuncs.addCell(new PdfPCell(new Phrase("R$ " + h.getSalarioLiquido().toString(), fontNormal)));
            }
            // Adiciona a tabela de funcionários ao documento
            document.add(tableFuncs);

        } catch (DocumentException e) { // Exceções relacionadas ao PDF
            Logger.getGlobal().severe(e.getMessage());
            Logger.getGlobal().severe("Erro ao gerar PDF Geral: " + e.getMessage());
        } catch (Exception e) { // Exceções gerais
            Logger.getGlobal().severe(e.getMessage());
        } finally { // Fecha o documento
            document.close();
        }
    }

    /**
     * @brief Método auxiliar para adicionar linhas ao resumo.
     * @param tabela O resumo
     * @param rotulo O rótulo da linha
     * @param valor  O valor da linha
     * @param font   A fonte a ser usada
     */
    private void adicionarLinhaResumo(PdfPTable tabela, String rotulo, String valor, Font font) {
        // Adiciona o rótulo
        tabela.addCell(new PdfPCell(new Phrase(rotulo, font)));
        // Cria a célula com o valor
        PdfPCell cVal = new PdfPCell(new Phrase(valor, font));
        // Alinha o valor à direita
        cVal.setHorizontalAlignment(Element.ALIGN_RIGHT);
        // Adiciona a célula ao resumo
        tabela.addCell(cVal);
    }
}
