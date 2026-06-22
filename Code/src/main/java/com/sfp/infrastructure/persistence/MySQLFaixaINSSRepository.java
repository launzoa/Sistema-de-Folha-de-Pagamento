/**
 * @brief Classe que implementa a interface FaixaINSSRepository.
 */

package com.sfp.infrastructure.persistence;

import com.sfp.core.domain.FaixaINSS;
import com.sfp.core.domain.FaixaINSSRepository;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySQLFaixaINSSRepository implements FaixaINSSRepository {

    /**
     * @brief Insere uma nova faixa de aliquota do INSS
     * @param FaixaINSS faixaINSS: objeto FaixaINSS que será inserido no BD
     */
    @Override
    public void salvar(FaixaINSS faixaINSS) {
        // Script sql para inserir uma nova faixa de aliquota do INSS
        String sql = "INSERT INTO faixa_inss (piso, teto, aliquota, parcela_deduzir) VALUES (?, ?, ?, ?)";

        // Conexão com banco de dados mysql
        try (Connection conn = com.sfp.core.database.ConexaoBD.getConnection()) {
            // Prepara statement para inserir dados
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Setando os valores para o statement
            pstmt.setBigDecimal(1, faixaINSS.getPiso());
            pstmt.setBigDecimal(2, faixaINSS.getTeto());
            pstmt.setBigDecimal(3, faixaINSS.getAliquota());
            pstmt.setBigDecimal(4, faixaINSS.getParcelaADeduzir());

            // Executa o statement
            int linhasAfetadas = pstmt.executeUpdate();

            // Verifica se o statement foi executado com sucesso
            if (linhasAfetadas == 1) {
                System.out.println("Faixa de aliquota do INSS cadastrada com sucesso!");
            } else {
                System.out.println("Erro ao cadastrar faixa de aliquota do INSS!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao cadastrar Faixa INSS no Banco de Dados", e);
        }
    }

    /**
     * @brief Busca todas as faixas de aliquota do INSS
     * @return List<FaixaINSS>: lista de faixas de aliquota do INSS
     */
    @Override
    public List<FaixaINSS> buscarTodas() {
        List<FaixaINSS> faixas = new ArrayList<>();
        // Script sql para buscar todas as faixas do INSS
        String sql = "SELECT * FROM faixa_inss";

        // Conexão com banco de dados mysql
        try (Connection conn = com.sfp.core.database.ConexaoBD.getConnection()) {
            // Prepara statement para buscar dados
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Executa o statement
            ResultSet rs = pstmt.executeQuery();
            // Percorre o resultset
            while (rs.next()) { // Para cada linha do resultset
                // Cria um objeto FaixaINSS com os dados da linha
                FaixaINSS faixa = new FaixaINSS(rs.getBigDecimal("piso"), rs.getBigDecimal("teto"),
                        rs.getBigDecimal("aliquota"), rs.getBigDecimal("parcela_deduzir"));
                // Adiciona o objeto FaixaINSS na lista
                faixas.add(faixa);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar faixas de INSS no Banco de Dados", e);
        }
        return faixas;
    }

    /**
     * @brief Atualiza uma faixa de aliquota do INSS
     * @param FaixaINSS antiga: faixa de aliquota do INSS antiga
     * @param FaixaINSS nova: faixa de aliquota do INSS nova
     */
    @Override
    public void atualizar(FaixaINSS antiga, FaixaINSS nova) {
        // Script sql para atualizar uma faixa de aliquota do INSS
        String sql = "UPDATE faixa_inss SET piso = ?, teto = ?, aliquota = ?, parcela_deduzir = ? WHERE piso = ? AND teto = ?";
        // Conexão com banco de dados mysql
        try (Connection conn = com.sfp.core.database.ConexaoBD.getConnection()) {
            // Prepara statement para atualizar dados
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Setando os valores para o statement
            pstmt.setBigDecimal(1, nova.getPiso());
            pstmt.setBigDecimal(2, nova.getTeto());
            pstmt.setBigDecimal(3, nova.getAliquota());
            pstmt.setBigDecimal(4, nova.getParcelaADeduzir());
            pstmt.setBigDecimal(5, antiga.getPiso());
            pstmt.setBigDecimal(6, antiga.getTeto());
            // Executa o statement
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar Faixa INSS", e);
        }
    }
}
