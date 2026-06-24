/**
 * @brief Implementação do repositório de rubricas
 */

package com.sfp.rubrica.infrastructure.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sfp.core.database.ConexaoBD;
import com.sfp.rubrica.domain.Rubrica;
import com.sfp.rubrica.domain.RubricaRepository;

public class MySQLRubricaRepository implements RubricaRepository {

    /**
     * @brief Cadastra uma rubrica
     * @param r Rubrica a ser cadastrada
     */
    @Override
    public void cadastrarRubrica(Rubrica r) {
        // SQL para inserir a rubrica
        String sql = "INSERT INTO rubrica (codigo, descricao, natureza, tipo, incide_inss, incide_fgts, incide_irrf, padrao, ativo) VALUES (?,?,?,?,?,?,?,FALSE,TRUE)";
        // Abre a conexão com o banco de dados e prepara o statement
        try (Connection con = ConexaoBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            // Seta os valores do statement
            ps.setInt(1, r.getCodigo());
            ps.setString(2, r.getDescricao());
            ps.setString(3, r.getNatureza());
            ps.setString(4, r.getTipo());
            ps.setBoolean(5, r.isIncideINSS());
            ps.setBoolean(6, r.isIncideFGTS());
            ps.setBoolean(7, r.isIncideIRRF());
            // Executa o statement
            ps.executeUpdate();
        } catch (Exception e) {// Captura a exceção
            throw new IllegalStateException("Erro ao cadastrar Rubrica no Banco de Dados", e);
        }
    }

    /**
     * @brief Edita uma rubrica
     * @param r Rubrica a ser editada
     */
    @Override
    public void editarRubrica(Rubrica r) {
        // SQL para editar a rubrica
        String sql = "UPDATE rubrica SET descricao=?, natureza=?, tipo=?, incide_inss=?, incide_fgts=?, incide_irrf=?, ativo=? WHERE codigo=? AND padrao=FALSE";
        // Abre a conexão com o banco de dados e prepara o statement
        try (Connection con = ConexaoBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            // Seta os valores do statement
            ps.setString(1, r.getDescricao());
            ps.setString(2, r.getNatureza());
            ps.setString(3, r.getTipo());
            ps.setBoolean(4, r.isIncideINSS());
            ps.setBoolean(5, r.isIncideFGTS());
            ps.setBoolean(6, r.isIncideIRRF());
            ps.setBoolean(7, r.isAtivo());
            ps.setInt(8, r.getCodigo());
            // Executa o statement
            ps.executeUpdate();
        } catch (Exception e) {// Captura a exceção
            throw new IllegalStateException("Erro ao editar Rubrica no Banco de Dados", e);
        }
    }

    /**
     * @brief Exclui uma rubrica
     * @param codigo Código da rubrica a ser excluída
     */
    @Override
    public void excluirRubrica(int codigo) {
        // SQL para excluir a rubrica (Req 3.1.4.1, 3.1.4.2 e 3.1.4.5)
        String sql = "DELETE FROM rubrica WHERE codigo=? AND padrao=FALSE";
        // Abre a conexão com o banco de dados e prepara o statement
        try (Connection con = ConexaoBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            // Seta os valores do statement
            ps.setInt(1, codigo);
            // Executa o statement
            ps.executeUpdate();
        } catch (Exception e) {// Captura a exceção
            throw new IllegalStateException("Erro ao excluir Rubrica no Banco de Dados", e);
        }
    }

    /**
     * @brief Listar as rubricas ativas
     * @return Lista de rubricas ativas
     */
    @Override
    public List<Rubrica> listarAtivas() {
        List<Rubrica> lista = new ArrayList<>();
        // SQL para listar as rubricas ativas
        String sql = "SELECT * FROM rubrica WHERE ativo=TRUE ORDER BY codigo";
        // Abre a conexão com o banco de dados, prepara o statement e executa o query
        try (Connection con = ConexaoBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            // Itera sobre o resultado
            while (rs.next()) {
                // Cria um objeto Rubrica e adiciona na lista
                lista.add(new Rubrica(
                        rs.getInt("codigo"),
                        rs.getString("descricao"),
                        rs.getString("natureza"),
                        rs.getString("tipo"),
                        rs.getBoolean("incide_inss"),
                        rs.getBoolean("incide_fgts"),
                        rs.getBoolean("incide_irrf"),
                        rs.getBoolean("padrao"),
                        rs.getBoolean("ativo")));
            }
        } catch (Exception e) {// Captura a exceção
            throw new IllegalStateException("Erro ao listar rubricas ativas", e);
        }
        return lista; // Retorna a lista de rubricas ativas
    }

    /**
     * @brief Buscar todas as rubricas
     * @return Lista de rubricas
     */
    @Override
    public List<Rubrica> buscarTodas() {
        // SQL para buscar todas as rubricas
        String sql = "SELECT * FROM rubrica";
        List<Rubrica> rubricas = new ArrayList<>();

        // Abre a conexão com o banco de dados, prepara o statement e executa o query
        try (Connection conn = ConexaoBD.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            // Itera sobre o resultado
            while (rs.next()) {
                // Cria um objeto Rubrica e adiciona na lista
                Rubrica r = new Rubrica(
                        rs.getInt("codigo"),
                        rs.getString("descricao"),
                        rs.getString("natureza"),
                        rs.getString("tipo"),
                        rs.getBoolean("incide_inss"),
                        rs.getBoolean("incide_fgts"),
                        rs.getBoolean("incide_irrf"),
                        rs.getBoolean("padrao"),
                        rs.getBoolean("ativo"));
                rubricas.add(r);
            }

        } catch (Exception e) {// Captura a exceção
            throw new IllegalStateException("Erro ao buscar todas as rubricas", e);
        }
        return rubricas;// Retorna a lista de rubricas
    }

    /**
     * @brief Buscar rubrica por código
     * @param codigo Código da rubrica
     * @return Rubrica
     */
    @Override
    public Rubrica buscarPorCodigo(int codigo) {
        // SQL para buscar rubrica por código
        String sql = "SELECT * FROM rubrica WHERE codigo = ?";

        // Abre a conexão com o banco de dados e prepara o statement
        try (Connection conn = ConexaoBD.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Seta os valores do statement
            pstmt.setInt(1, codigo);
            // Executa o statement
            ResultSet rs = pstmt.executeQuery();
            // Se o resultado não for nulo
            if (rs.next()) {
                // Retorna a rubrica
                return new Rubrica(
                        rs.getInt("codigo"),
                        rs.getString("descricao"),
                        rs.getString("natureza"),
                        rs.getString("tipo"),
                        rs.getBoolean("incide_inss"),
                        rs.getBoolean("incide_fgts"),
                        rs.getBoolean("incide_irrf"),
                        rs.getBoolean("padrao"),
                        rs.getBoolean("ativo"));
            }

        } catch (Exception e) {// Captura a exceção
            throw new IllegalStateException("Erro ao buscar rubrica por código", e);
        }
        return null; // Retorna null se a rubrica não for encontrada
    }
}