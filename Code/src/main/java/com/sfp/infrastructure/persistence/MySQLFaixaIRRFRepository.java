/**
 * @brief Implementação da interface FaixaIRRFRepository utilizando MySQL
 */
package com.sfp.infrastructure.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sfp.core.database.ConexaoBD;
import com.sfp.core.domain.FaixaIRRF;
import com.sfp.core.domain.FaixaIRRFRepository;

public class MySQLFaixaIRRFRepository implements FaixaIRRFRepository {

    /**
     * @brief Busca todas as faixas de IRRF do banco de dados.
     * @return List<FaixaIRRF> Lista de faixas encontradas.
     * @throws RuntimeException em caso de erro de banco de dados.
     */
    @Override
    public List<FaixaIRRF> buscarTodas() {
        // Script SQL para buscar todas as faixas de IRRF
        String sql = "SELECT * FROM faixa_irrf";
        List<FaixaIRRF> faixas = new ArrayList<>();

        // Conexão com banco de dados e preparação do statement
        try (Connection conn = ConexaoBD.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Execução da query
            ResultSet rs = pstmt.executeQuery();
            // Obtenção dos resultados
            while (rs.next()) { // Para cada resultado, cria um objeto FaixaIRRF
                FaixaIRRF f = new FaixaIRRF(
                        rs.getBigDecimal("piso"),
                        rs.getBigDecimal("teto"),
                        rs.getBigDecimal("aliquota"),
                        rs.getBigDecimal("parcela_a_deduzir"));
                faixas.add(f);
            }
        } catch (Exception e) { // Em caso de erro, lança uma exceção
            throw new RuntimeException("Erro ao buscar faixas de IRRF no Banco de Dados", e);
        }
        return faixas; // Retorna a lista de faixas
    }

    /**
     * @brief Atualiza os dados de uma FaixaIRRF existente.
     * @param antiga A faixa de IRRF a ser substituída.
     * @param nova   A nova faixa de IRRF com os dados atualizados.
     */
    public void atualizar(FaixaIRRF antiga, FaixaIRRF nova) {
        // Script SQL para atualizar uma faixa de IRRF
        String sql = "UPDATE faixa_irrf SET piso = ?, teto = ?, aliquota = ?, parcela_deduzir = ? WHERE piso = ? AND teto = ?";
        // Conexão com banco de dados e preparação do statement
        try (Connection conn = com.sfp.core.database.ConexaoBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Setando os valores
            pstmt.setBigDecimal(1, nova.getPiso());
            pstmt.setBigDecimal(2, nova.getTeto());
            pstmt.setBigDecimal(3, nova.getAliquota());
            pstmt.setBigDecimal(4, nova.getParcelaADeduzir());
            pstmt.setBigDecimal(5, antiga.getPiso());
            pstmt.setBigDecimal(6, antiga.getTeto());
            // Execução da query
            pstmt.executeUpdate();
        } catch (Exception e) { // Em caso de erro, lança uma exceção
            throw new RuntimeException("Erro ao atualizar Faixa IRRF", e);
        }
    }
}
