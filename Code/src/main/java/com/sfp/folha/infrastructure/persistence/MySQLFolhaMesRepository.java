/**
 * @brief Classe que implementa a interface FolhaMesRepository para MySQL
 */
package com.sfp.folha.infrastructure.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sfp.folha.domain.FolhaMes;
import com.sfp.core.database.ConexaoBD;
import com.sfp.folha.domain.FolhaMesRepository;

public class MySQLFolhaMesRepository implements FolhaMesRepository {

    /**
     * @brief Salva uma folha de pagamento
     * @param folha Folha de pagamento a ser salva
     */
    @Override
    public void salvar(FolhaMes folha) {
        if (folha.getId() > 0) { // Se uma folha já existir, atualiza
            // SQL para atualizar uma folha existente
            String sql = "UPDATE folha_mes SET competencia = ?, dias_uteis = ?, status = ?, data_inicio = ?, data_fim = ? WHERE id = ?";
            // Conexão com o banco de dados e preparação do statement
            try (Connection conn = ConexaoBD.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Setando os parâmetros do statement
                pstmt.setString(1, folha.getCompetencia());
                pstmt.setInt(2, folha.getDiasUteis());
                pstmt.setString(3, folha.getStatus());
                // Validação para datas
                if (folha.getDataInicio() != null)
                    pstmt.setDate(4, java.sql.Date.valueOf(folha.getDataInicio()));
                else
                    pstmt.setNull(4, java.sql.Types.DATE);
                if (folha.getDataFim() != null)
                    pstmt.setDate(5, java.sql.Date.valueOf(folha.getDataFim()));
                else
                    pstmt.setNull(5, java.sql.Types.DATE);
                pstmt.setInt(6, folha.getId());
                // Executa o statement
                pstmt.executeUpdate();
            } catch (Exception e) { // Captura qualquer exceção que possa ocorrer
                throw new RuntimeException("Erro ao atualizar folha de mês no Banco de Dados", e);
            }
        } else { // Se a folha não existir, insere uma nova
            // SQL para inserir uma nova folha
            String sql = "INSERT INTO folha_mes (competencia, dias_uteis, status, data_inicio, data_fim) VALUES (?, ?, ?, ?, ?)";
            // Conexão com o banco de dados e preparação do statement
            try (Connection conn = ConexaoBD.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Setando os parâmetros do statement
                pstmt.setString(1, folha.getCompetencia());
                pstmt.setInt(2, folha.getDiasUteis());
                pstmt.setString(3, folha.getStatus());
                // Validação para datas
                if (folha.getDataInicio() != null)
                    pstmt.setDate(4, java.sql.Date.valueOf(folha.getDataInicio()));
                else
                    pstmt.setNull(4, java.sql.Types.DATE);
                if (folha.getDataFim() != null)
                    pstmt.setDate(5, java.sql.Date.valueOf(folha.getDataFim()));
                else
                    pstmt.setNull(5, java.sql.Types.DATE);
                // Executa o statement
                pstmt.executeUpdate();
            } catch (Exception e) { // Captura qualquer exceção que possa ocorrer
                throw new RuntimeException("Erro ao inserir nova folha de mês no Banco de Dados", e);
            }
        }
    }

    /**
     * @brief Busca uma folha de pagamento por competência
     * @param competencia Competência da folha
     * @return FolhaMes
     */
    @Override
    public FolhaMes buscarPorCompetencia(String competencia) {
        // SQL para buscar uma folha por competência
        String sql = "SELECT * FROM folha_mes WHERE competencia = ?";
        // Conexão com o banco de dados e preparação do statement
        try (Connection conn = ConexaoBD.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Setando os parâmetros do statement
            pstmt.setString(1, competencia);
            // Executa o statement
            ResultSet rs = pstmt.executeQuery();
            // Mapeia o resultado para a folha
            if (rs.next()) {
                return mapearResultSetParaFolha(rs);
            }
        } catch (Exception e) { // Captura qualquer exceção que possa ocorrer
            throw new RuntimeException("Erro ao buscar folha por competência", e);
        }
        return null;
    }

    /**
     * @brief Busca todas as folhas de pagamento
     * @return List<FolhaMes>
     */
    @Override
    public List<FolhaMes> buscarTodas() {
        // SQL para buscar todas as folhas de pagamento
        String sql = "SELECT * FROM folha_mes";
        // Conexão com o banco de dados e preparação do statement
        try (Connection conn = ConexaoBD.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Executa o statement
            ResultSet rs = pstmt.executeQuery();
            List<FolhaMes> folhas = new ArrayList<>();
            // Mapeia cada resultado para uma lista de folha
            while (rs.next()) {
                folhas.add(mapearResultSetParaFolha(rs));
            }
            return folhas; // Retorna a lista

        } catch (Exception e) { // Captura qualquer exceção que possa ocorrer
            throw new RuntimeException("Erro ao buscar todas as folhas de mês", e);
        }
    }

    /**
     * @brief Atualiza o status de uma folha de pagamento
     * @param id         ID da folha de pagamento
     * @param novoStatus Novo status da folha de pagamento
     */
    @Override
    public void atualizarStatus(int id, String novoStatus) {
        // SQL para atualizar o status de uma folha de pagamento
        String sql = "UPDATE folha_mes SET status = ? WHERE id = ?";
        // Conexão com o banco de dados e preparação do statement
        try (Connection conn = ConexaoBD.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Setando os parâmetros do statement
            pstmt.setString(1, novoStatus);
            pstmt.setInt(2, id);
            // Executa o statement
            pstmt.executeUpdate();
        } catch (Exception e) { // Captura qualquer exceção que possa ocorrer
            throw new RuntimeException("Erro ao atualizar status da folha", e);
        }
    }

    @Override
    public FolhaMes buscarFolhaAberta() {
        // SQL para buscar a folha aberta
        String sql = "SELECT * FROM folha_mes WHERE status = 'Aberta' ORDER BY id DESC LIMIT 1";
        // Conexão com o banco de dados e preparação do statement
        try (Connection conn = ConexaoBD.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Executa o statement
            ResultSet rs = pstmt.executeQuery();
            // Mapeia o resultado para a folha
            if (rs.next()) {
                return mapearResultSetParaFolha(rs);
            }
        } catch (Exception e) { // Captura qualquer exceção que possa ocorrer
            throw new RuntimeException("Erro ao buscar folha aberta", e);
        }
        return null; // Retorna null se nenhuma folha aberta for encontrada
    }

    /**
     * @brief Exclui todas as folhas de pagamento e lançamentos
     */
    public void excluirTodasFolhas() {
        // Conexão com o banco de dados
        try (Connection conn = ConexaoBD.getConnection()) {
            // Deleta todos os lançamentos
            conn.createStatement().executeUpdate("DELETE FROM lancamento");
            // Deleta todas as folhas
            conn.createStatement().executeUpdate("DELETE FROM folha_mes");
        } catch (Exception e) { // Captura qualquer exceção que possa ocorrer
            throw new RuntimeException("Erro ao excluir todas as folhas", e);
        }
    }

    /**
     * @brief Mapeia um ResultSet para uma FolhaMes
     * @param rs ResultSet contendo os dados da folha
     * @return FolhaMes
     * @throws SQLException Se ocorrer um erro ao mapear o ResultSet
     */
    private FolhaMes mapearResultSetParaFolha(ResultSet rs) throws SQLException {
        // Mapeia as datas do ResultSet para LocalDate
        LocalDate dInicio = rs.getDate("data_inicio") != null ? rs.getDate("data_inicio").toLocalDate()
                : null;
        LocalDate dFim = rs.getDate("data_fim") != null ? rs.getDate("data_fim").toLocalDate() : null;

        // Retorna uma nova FolhaMes com os dados mapeados
        return new FolhaMes(
                rs.getInt("id"),
                rs.getString("competencia"),
                rs.getInt("dias_uteis"),
                rs.getString("status"),
                dInicio,
                dFim);
    }
}
