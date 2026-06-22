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
import com.sfp.folha.domain.Lancamento;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @brief Classe responsável por gerar o arquivo PDF físico do Holerite do funcionário.
 */
public class GeradorHoleritePDF {

    /**
     * @brief Gera o arquivo PDF de um holerite específico.
     * @param holerite O holerite processado do funcionário.
     * @param competencia Competência do holerite (Mês/Ano).
     * @param diretorioSaida Caminho onde o PDF será salvo na máquina.
     * @param mapaRubricas Dicionário de rubricas em memória para resolução rápida de nomes.
     */
    public void gerarPdf(Holerite holerite, String competencia, String diretorioSaida,
            Map<Integer, com.sfp.rubrica.domain.Rubrica> mapaRubricas) {
        String nomeArquivo = diretorioSaida + "/Holerite_" + holerite.getFuncionario().getNome().replaceAll(" ", "_")
                + "_" + competencia.replaceAll("/", "_") + ".pdf";

        Document document = new Document(PageSize.A4);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo));
            document.open();

            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA, 10);
            Font fontBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);

            // Cabeçalho
            Paragraph titulo = new Paragraph("RECIBO DE PAGAMENTO DE SALÁRIO", fontTitulo);
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

            PdfPCell cellEmpresa = new PdfPCell(
                    new Phrase("EMPRESA: " + nomeEmpresa + "\nCNPJ: " + cnpjEmpresa, fontNormal));
            cellEmpresa.setBorder(PdfPCell.NO_BORDER);
            tableCabecalho.addCell(cellEmpresa);

            PdfPCell cellMes = new PdfPCell(new Phrase("COMPETÊNCIA: " + competencia, fontBold));
            cellMes.setBorder(PdfPCell.NO_BORDER);
            cellMes.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableCabecalho.addCell(cellMes);

            document.add(tableCabecalho);
            document.add(new Paragraph("\n"));

            // Info Funcionário
            PdfPTable tableFunc = new PdfPTable(1);
            tableFunc.setWidthPercentage(100);
            PdfPCell cellFuncInfo = new PdfPCell(new Phrase(
                    "Funcionário: " + holerite.getFuncionario().getNome() + "\n" +
                            "CPF: " + holerite.getFuncionario().getCpf() + "\n" +
                            "Cargo: " + holerite.getFuncionario().getCargo(),
                    fontNormal));
            tableFunc.addCell(cellFuncInfo);
            document.add(tableFunc);
            document.add(new Paragraph("\n"));

            // Lançamentos e Eventos
            PdfPTable tableEventos = new PdfPTable(5);
            tableEventos.setWidthPercentage(100);
            tableEventos.setWidths(new float[] { 1.5f, 4f, 1.5f, 1.5f, 1.5f });

            // Títulos das colunas
            String[] headers = { "Cód", "Descrição", "Referência", "Vencimentos", "Descontos" };
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header, fontBold));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new java.awt.Color(230, 230, 230));
                tableEventos.addCell(cell);
            }

            // Lançamento Base (Salário Mês)
            adicionarLinhaTabela(tableEventos, "001", "Salário Base Mensal",
                    String.valueOf(holerite.getQuantidadeDiasUteis()),
                    "R$ " + holerite.getFuncionario().getSalarioBruto().toString(), "", fontNormal);

            // Variáveis / Outros Lançamentos
            if (holerite.getLancamentos() != null) {
                for (Lancamento l : holerite.getLancamentos()) {
                    com.sfp.rubrica.domain.Rubrica r = mapaRubricas.get(l.getCodigoRubrica());
                    String desc = r != null ? r.getDescricao() : "Rubrica " + l.getCodigoRubrica();
                    String ref = l.getQuantidade() > 0 ? l.getQuantidade() + " un" : "-";
                    String vencimento = "";
                    String desconto = "";

                    if (l.getValor() != null) {
                        if (r != null && "Desconto".equalsIgnoreCase(r.getNatureza())) {
                            desconto = "R$ " + l.getValor().toString();
                        } else {
                            vencimento = "R$ " + l.getValor().toString();
                        }
                    }

                    adicionarLinhaTabela(tableEventos, String.format("%03d", l.getCodigoRubrica()), desc, ref,
                            vencimento, desconto, fontNormal);
                }
            }

            // INSS e IRRF
            if (holerite.getDescontoINSS() != null && holerite.getDescontoINSS().compareTo(BigDecimal.ZERO) > 0) {
                adicionarLinhaTabela(tableEventos, "002", "Desconto INSS", "-", "",
                        "R$ " + holerite.getDescontoINSS().toString(), fontNormal);
            }
            if (holerite.getDescontoIRRF() != null && holerite.getDescontoIRRF().compareTo(BigDecimal.ZERO) > 0) {
                adicionarLinhaTabela(tableEventos, "003", "Desconto IRRF", "-", "",
                        "R$ " + holerite.getDescontoIRRF().toString(), fontNormal);
            }

            document.add(tableEventos);
            document.add(new Paragraph("\n"));

            // Totais
            PdfPTable tableTotais = new PdfPTable(3);
            tableTotais.setWidthPercentage(100);
            tableTotais.setWidths(new float[] { 6f, 2f, 2f });

            PdfPCell cBranca = new PdfPCell(new Phrase(" "));
            cBranca.setBorder(PdfPCell.NO_BORDER);
            tableTotais.addCell(cBranca);

            PdfPCell cTotVenc = new PdfPCell(
                    new Phrase("Total Vencimentos\nR$ " + holerite.getTotalProventos().toString(), fontBold));
            cTotVenc.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableTotais.addCell(cTotVenc);

            PdfPCell cTotDesc = new PdfPCell(
                    new Phrase("Total Descontos\nR$ " + holerite.getTotalDescontos().toString(), fontBold));
            cTotDesc.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableTotais.addCell(cTotDesc);

            document.add(tableTotais);
            document.add(new Paragraph("\n"));

            // Líquido e FGTS
            PdfPTable tableLiq = new PdfPTable(2);
            tableLiq.setWidthPercentage(100);
            tableLiq.setWidths(new float[] { 7f, 3f });

            PdfPCell cMsg = new PdfPCell(
                    new Phrase("Declaro ter recebido a importância líquida discriminada neste recibo.\n\nBase FGTS: R$ "
                            + holerite.getTotalProventos().toString() + "  |  FGTS do Mês: R$ "
                            + holerite.getValorFGTS().toString(), fontNormal));
            cMsg.setBorder(PdfPCell.NO_BORDER);
            tableLiq.addCell(cMsg);

            PdfPCell cValLiq = new PdfPCell(
                    new Phrase("LÍQUIDO A RECEBER\nR$ " + holerite.getSalarioLiquido().toString(), fontBold));
            cValLiq.setHorizontalAlignment(Element.ALIGN_CENTER);
            cValLiq.setBackgroundColor(new java.awt.Color(200, 230, 200));
            tableLiq.addCell(cValLiq);

            document.add(tableLiq);

        } catch (DocumentException e) {
            e.printStackTrace();
            System.err.println("Erro ao gerar PDF: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    private void adicionarLinhaTabela(PdfPTable tabela, String col1, String col2, String col3, String col4, String col5,
            Font font) {
        tabela.addCell(new PdfPCell(new Phrase(col1, font)));
        tabela.addCell(new PdfPCell(new Phrase(col2, font)));

        PdfPCell c3 = new PdfPCell(new Phrase(col3, font));
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabela.addCell(c3);

        PdfPCell c4 = new PdfPCell(new Phrase(col4, font));
        c4.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabela.addCell(c4);

        PdfPCell c5 = new PdfPCell(new Phrase(col5, font));
        c5.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabela.addCell(c5);
    }
}
