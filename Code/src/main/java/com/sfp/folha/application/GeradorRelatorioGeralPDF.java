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

import com.sfp.folha.domain.Holerite;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @brief Classe responsável por gerar o arquivo PDF físico do Relatório Geral da Folha.
 */
public class GeradorRelatorioGeralPDF {

    /**
     * @brief Gera o arquivo PDF de fechamento mensal consolidado.
     * @param holerites Lista de holerites processados no mês.
     * @param competencia Competência do relatório (Mês/Ano).
     * @param diretorioSaida Caminho onde o PDF será salvo.
     * @param totalBase Total geral de Salários Base.
     * @param totalProv Total geral de Proventos.
     * @param totalDesc Total geral de Descontos.
     * @param totalLiq Total geral Líquido a pagar.
     * @param totalFgts Total geral de FGTS a recolher.
     */
    public void gerarPdf(List<Holerite> holerites, String competencia, String diretorioSaida, 
                         String totalBase, String totalProv, String totalDesc, String totalLiq, String totalFgts) {
                         
        String nomeArquivo = diretorioSaida + "/Relatorio_Geral_Empresa_" + competencia.replaceAll("/", "_") + ".pdf";

        Document document = new Document(PageSize.A4);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo));
            document.open();

            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font fontSubTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 10);
            Font fontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);

            // Cabeçalho
            Paragraph titulo = new Paragraph("RELATÓRIO GERAL DA FOLHA DE PAGAMENTO", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(new Paragraph("\n"));

            // Info Empresa e Mês
            PdfPTable tableCabecalho = new PdfPTable(2);
            tableCabecalho.setWidthPercentage(100);
            
            // Busca os dados da empresa do BD
            com.sfp.empresa.domain.EmpresaRepository empresaRepo = new com.sfp.empresa.infrastructure.persistence.MySQLEmpresaRepository();
            com.sfp.empresa.domain.Empresa emp = empresaRepo.buscarEmpresaUnica();
            String nomeEmpresa = (emp != null) ? emp.getRazaoSocial() : "SFP Corporation LTDA";
            String cnpjEmpresa = (emp != null) ? emp.getCnpj() : "00.000.000/0001-00";

            PdfPCell cellEmpresa = new PdfPCell(new Phrase("EMPRESA: " + nomeEmpresa + "\nCNPJ: " + cnpjEmpresa, fontNormal));
            cellEmpresa.setBorder(PdfPCell.NO_BORDER);
            tableCabecalho.addCell(cellEmpresa);
            
            PdfPCell cellMes = new PdfPCell(new Phrase("COMPETÊNCIA: " + competencia, fontBold));
            cellMes.setBorder(PdfPCell.NO_BORDER);
            cellMes.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableCabecalho.addCell(cellMes);
            
            document.add(tableCabecalho);
            document.add(new Paragraph("\n"));

            // Resumo Financeiro da Empresa
            PdfPTable tableResumo = new PdfPTable(2);
            tableResumo.setWidthPercentage(100);
            tableResumo.setWidths(new float[]{6f, 4f});

            PdfPCell cTit = new PdfPCell(new Phrase("RESUMO CONSOLIDADO", fontSubTitulo));
            cTit.setColspan(2);
            cTit.setHorizontalAlignment(Element.ALIGN_CENTER);
            cTit.setBackgroundColor(new java.awt.Color(230, 230, 230));
            tableResumo.addCell(cTit);

            adicionarLinhaResumo(tableResumo, "Total Salário Base:", totalBase, fontNormal);
            adicionarLinhaResumo(tableResumo, "Total Proventos:", totalProv, fontNormal);
            adicionarLinhaResumo(tableResumo, "Total Descontos (INSS, IRRF, Faltas, etc):", totalDesc, fontNormal);
            adicionarLinhaResumo(tableResumo, "Total FGTS a Recolher:", totalFgts, fontNormal);
            
            PdfPCell cLiq = new PdfPCell(new Phrase("Total Líquido a Pagar:", fontBold));
            PdfPCell cLiqVal = new PdfPCell(new Phrase(totalLiq, fontBold));
            cLiqVal.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableResumo.addCell(cLiq);
            tableResumo.addCell(cLiqVal);

            document.add(tableResumo);
            document.add(new Paragraph("\n"));

            // Lista de Funcionários Processados
            Paragraph subTitulo = new Paragraph("FUNCIONÁRIOS PROCESSADOS (" + holerites.size() + ")", fontSubTitulo);
            document.add(subTitulo);
            document.add(new Paragraph("\n"));

            PdfPTable tableFuncs = new PdfPTable(3);
            tableFuncs.setWidthPercentage(100);
            tableFuncs.setWidths(new float[]{5f, 2.5f, 2.5f});

            tableFuncs.addCell(new PdfPCell(new Phrase("Nome", fontBold)));
            tableFuncs.addCell(new PdfPCell(new Phrase("Cargo", fontBold)));
            tableFuncs.addCell(new PdfPCell(new Phrase("Líquido", fontBold)));

            for(Holerite h : holerites) {
                tableFuncs.addCell(new PdfPCell(new Phrase(h.getFuncionario().getNome(), fontNormal)));
                tableFuncs.addCell(new PdfPCell(new Phrase(h.getFuncionario().getCargo(), fontNormal)));
                tableFuncs.addCell(new PdfPCell(new Phrase("R$ " + h.getSalarioLiquido().toString(), fontNormal)));
            }

            document.add(tableFuncs);

        } catch (DocumentException e) {
            e.printStackTrace();
            System.err.println("Erro ao gerar PDF Geral: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    private void adicionarLinhaResumo(PdfPTable tabela, String rotulo, String valor, Font font) {
        tabela.addCell(new PdfPCell(new Phrase(rotulo, font)));
        PdfPCell cVal = new PdfPCell(new Phrase(valor, font));
        cVal.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabela.addCell(cVal);
    }
}
