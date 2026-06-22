/**
 * @brief Classe responsável pela persistência dos lançamentos no banco de dados
 */

package com.sfp.folha.infrastructure.persistence;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sfp.core.database.ConexaoBD;
import com.sfp.folha.domain.Lancamento;
import com.sfp.folha.domain.LancamentoRepository;

public class MySQLLancamentoRepository implements LancamentoRepository {

    /**
     * @brief Insere um novo lançamento no banco de dados
     * @param lancamento Objeto contendo os dados do lançamento a ser salvo
     */
    @Override
    public void salvar(Lancamento lancamento) {
        // Comando SQL para inserir um novo lançamento
        String sql = "INSERT INTO lancamento (id_folha, cpf_funcionario, codigo_rubrica, quantidade, data_clt, valor, modalidade, base_calculo, data_inicio, data_fim, path_pdf) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Conexão com o banco de dados e preparo do statement
        try (Connection conn = ConexaoBD.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Setando os parâmetros do statement
            pstmt.setInt(1, lancamento.getIdFolha());
            pstmt.setString(2, lancamento.getCpfFuncionario());
            pstmt.setInt(3, lancamento.getCodigoRubrica());
            pstmt.setDouble(4, lancamento.getQuantidade());
            // Se a data CLT não for nula, insere a data CLT
            // Caso contrário, insere NULL no banco
            if (lancamento.getDataClt() != null) {
                pstmt.setDate(5, java.sql.Date.valueOf(lancamento.getDataClt()));
            } else {
                pstmt.setNull(5, Types.DATE);
            }
            // Se o valor não for nulo, insere o valor
            // Caso contrário, insere NULL no banco
            if (lancamento.getValor() != null) {
                pstmt.setBigDecimal(6, lancamento.getValor());
            } else {
                pstmt.setNull(6, Types.DECIMAL);
            }
            pstmt.setString(7, lancamento.getModalidade());
            pstmt.setString(8, lancamento.getBaseCalculo());
            // Se a data de início não for nula, insere a data de início
            // Caso contrário, insere NULL no banco
            if (lancamento.getDataInicio() != null) {
                pstmt.setDate(9, java.sql.Date.valueOf(lancamento.getDataInicio()));
            } else {
                pstmt.setNull(9, Types.DATE);
            }
            // Se a data final não for nula, insere a data final
            // Caso contrário, insere NULL no banco
            if (lancamento.getDataFim() != null) {
                pstmt.setDate(10, java.sql.Date.valueOf(lancamento.getDataFim()));
            } else {
                pstmt.setNull(10, Types.DATE);
            }
            pstmt.setString(11, lancamento.getPathPdf());

            // Executa o comando SQL no banco de dados!
            pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar lançamento", e);
        }
    }

    /**
     * @brief Busca os lançamentos de uma folha e funcionário específico
     * @param idFolha        ID da folha
     * @param cpfFuncionario CPF do funcionário
     * @return List<Lancamento>
     */
    @Override
    public List<Lancamento> buscarPorFolhaEFuncionario(int idFolha, String cpfFuncionario) {
        // Comando SQL para buscar os lançamentos de uma folha e funcionário específico
        String sql = "SELECT * FROM lancamento WHERE id_folha = ? AND cpf_funcionario = ?";
        List<Lancamento> lancamentos = new ArrayList<>();

        // Conexão com o banco de dados e preparo do statement
        try (Connection conn = ConexaoBD.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Setando os parâmetros do statement
            pstmt.setInt(1, idFolha);
            pstmt.setString(2, cpfFuncionario);
            // Executando o statement e pegando o resultado
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) { // Enquanto houver resultados, executa o bloco
                // Puxando os dados do ResultSet
                int dbId = rs.getInt("id");
                int dbIdFolha = rs.getInt("id_folha");
                String dbCpfFuncionario = rs.getString("cpf_funcionario");
                int dbCodigoRubrica = rs.getInt("codigo_rubrica");
                double dbQuantidade = rs.getDouble("quantidade");
                Date sqlDataClt = rs.getDate("data_clt");
                LocalDate dbDataClt = (sqlDataClt != null) ? sqlDataClt.toLocalDate() : null;
                BigDecimal dbValor = rs.getBigDecimal("valor");
                String dbModalidade = rs.getString("modalidade");
                String dbBaseCalculo = rs.getString("base_calculo");
                Date sqlDataInicio = rs.getDate("data_inicio");
                LocalDate dbDataInicio = (sqlDataInicio != null) ? sqlDataInicio.toLocalDate() : null;
                Date sqlDataFim = rs.getDate("data_fim");
                LocalDate dbDataFim = (sqlDataFim != null) ? sqlDataFim.toLocalDate() : null;
                String dbPathPdf = rs.getString("path_pdf");
                // Criando um novo objeto Lancamento com os dados que puxou
                Lancamento lancamento = new Lancamento(dbId, dbIdFolha, dbCpfFuncionario, dbCodigoRubrica,
                        dbQuantidade, dbDataClt, dbValor, dbModalidade, dbBaseCalculo, dbDataInicio, dbDataFim,
                        dbPathPdf);
                // Adicionando este objeto na lista 'lancamentos'
                lancamentos.add(lancamento);
            }

        } catch (Exception e) { // Se der erro, lança uma exceção
            throw new RuntimeException("Erro ao buscar lançamentos por folha e funcionário", e);
        }
        return lancamentos; // Retorna a lista de lançamentos
    }

    /**
     * @brief Deleta um lançamento do banco de dados
     * @param id ID do lançamento
     */
    @Override
    public void deletar(int id) {
        // Comando SQL para deletar um lançamento
        String sql = "DELETE FROM lancamento WHERE id = ?";

        // Conexão com o banco de dados e preparo do statement
        try (Connection conn = ConexaoBD.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Setando os parâmetros do statement
            pstmt.setInt(1, id);
            // Executando o statement
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar lançamento", e);
        }
    }

    /**
     * @brief Busca todos os lançamentos do banco de dados
     * @return List<Lancamento>
     */
    @Override
    public List<Lancamento> buscarTodos() {
        // Comando SQL para buscar todos os lançamentos
        String sql = "SELECT * FROM lancamento";
        List<Lancamento> lancamentos = new ArrayList<>();
        // Conexão com o banco de dados, preparo do statement e execução do statement
        try (Connection conn = ConexaoBD.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) { // Enquanto houver resultados, executa o bloco
                // Puxando os dados do ResultSet
                int dbId = rs.getInt("id");
                int dbIdFolha = rs.getInt("id_folha");
                String dbCpfFuncionario = rs.getString("cpf_funcionario");
                int dbCodigoRubrica = rs.getInt("codigo_rubrica");
                double dbQuantidade = rs.getDouble("quantidade");
                Date sqlDataClt = rs.getDate("data_clt");
                LocalDate dbDataClt = (sqlDataClt != null) ? sqlDataClt.toLocalDate() : null;
                BigDecimal dbValor = rs.getBigDecimal("valor");
                String dbModalidade = rs.getString("modalidade");
                String dbBaseCalculo = rs.getString("base_calculo");
                Date sqlDataInicio = rs.getDate("data_inicio");
                LocalDate dbDataInicio = (sqlDataInicio != null) ? sqlDataInicio.toLocalDate() : null;
                Date sqlDataFim = rs.getDate("data_fim");
                LocalDate dbDataFim = (sqlDataFim != null) ? sqlDataFim.toLocalDate() : null;
                String dbPathPdf = rs.getString("path_pdf");
                // Criando um novo objeto Lancamento com os dados que puxou
                Lancamento lancamento = new Lancamento(dbId, dbIdFolha, dbCpfFuncionario, dbCodigoRubrica,
                        dbQuantidade, dbDataClt, dbValor, dbModalidade, dbBaseCalculo, dbDataInicio, dbDataFim,
                        dbPathPdf);
                // Adicionando este objeto na lista 'lancamentos'
                lancamentos.add(lancamento);
            }
        } catch (Exception e) { // Se der erro, lança uma exceção
            throw new RuntimeException("Erro ao buscar todos os lançamentos", e);
        }
        return lancamentos; // Retorna a lista de lançamentos
    }

    /**
     * @brief Busca um lançamento por ID
     * @param id ID do lançamento
     * @return Lancamento
     */
    @Override
    public Lancamento buscarPorId(int id) {
        // Comando SQL para buscar um lançamento
        String sql = "SELECT * FROM lancamento WHERE id = ?";
        Lancamento lancamento = null;

        // Conexão com o banco de dados e preparo do statement
        try (Connection conn = ConexaoBD.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Setando os parâmetros do statement
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) { // Executando o statement e pegando o resultado
                if (rs.next()) { // Se houver resultados, executa o bloco
                    // Puxando os dados do ResultSet
                    int dbId = rs.getInt("id");
                    int dbIdFolha = rs.getInt("id_folha");
                    String dbCpfFuncionario = rs.getString("cpf_funcionario");
                    int dbCodigoRubrica = rs.getInt("codigo_rubrica");
                    double dbQuantidade = rs.getDouble("quantidade");
                    Date sqlDataClt = rs.getDate("data_clt");
                    LocalDate dbDataClt = (sqlDataClt != null) ? sqlDataClt.toLocalDate() : null;
                    BigDecimal dbValor = rs.getBigDecimal("valor");
                    String dbModalidade = rs.getString("modalidade");
                    String dbBaseCalculo = rs.getString("base_calculo");
                    Date sqlDataInicio = rs.getDate("data_inicio");
                    LocalDate dbDataInicio = (sqlDataInicio != null) ? sqlDataInicio.toLocalDate() : null;
                    Date sqlDataFim = rs.getDate("data_fim");
                    LocalDate dbDataFim = (sqlDataFim != null) ? sqlDataFim.toLocalDate() : null;
                    String dbPathPdf = rs.getString("path_pdf");
                    // Criando um novo objeto Lancamento com os dados que puxou
                    lancamento = new Lancamento(dbId, dbIdFolha, dbCpfFuncionario, dbCodigoRubrica,
                            dbQuantidade, dbDataClt, dbValor, dbModalidade, dbBaseCalculo, dbDataInicio, dbDataFim,
                            dbPathPdf);
                }
            }
        } catch (Exception e) { // Se der erro, lança uma exceção
            throw new RuntimeException("Erro ao buscar lançamento por ID", e);
        }
        return lancamento; // Retorna o lançamento
    }

    /**
     * @brief Atualiza um lançamento no banco de dados
     * @param lancamento objeto Lancamento com os dados a serem atualizados
     */
    @Override
    public void atualizar(Lancamento lancamento) {
        // Comando SQL para atualizar um lançamento
        String sql = "UPDATE lancamento SET cpf_funcionario = ?, codigo_rubrica = ?, quantidade = ?, data_clt = ?, valor = ?, modalidade = ?, base_calculo = ? WHERE id = ?";
        // Conexão com o banco de dados e preparo do statement
        try (Connection conn = ConexaoBD.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Setando os parâmetros do statement
            pstmt.setString(1, lancamento.getCpfFuncionario());
            pstmt.setInt(2, lancamento.getCodigoRubrica());
            pstmt.setDouble(3, lancamento.getQuantidade());
            // Verificando se a data CLT não é nula para adicionar ao statement
            if (lancamento.getDataClt() != null) {
                pstmt.setDate(4, java.sql.Date.valueOf(lancamento.getDataClt()));
            } else {
                pstmt.setNull(4, Types.DATE);
            }
            // Verificando se o valor não é nulo para adicionar ao statement
            if (lancamento.getValor() != null) {
                pstmt.setBigDecimal(5, lancamento.getValor());
            } else {
                pstmt.setNull(5, Types.DECIMAL);
            }
            pstmt.setString(6, lancamento.getModalidade());
            pstmt.setString(7, lancamento.getBaseCalculo());
            pstmt.setInt(8, lancamento.getId());
            // Executando o statement
            pstmt.executeUpdate();
        } catch (Exception e) { // Se der erro, lança uma exceção
            throw new RuntimeException("Erro ao atualizar lançamento", e);
        }
    }
}