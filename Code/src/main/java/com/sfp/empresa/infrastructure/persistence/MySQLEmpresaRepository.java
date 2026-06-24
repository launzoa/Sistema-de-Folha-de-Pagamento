package com.sfp.empresa.infrastructure.persistence;

import com.sfp.core.database.ConexaoBD;
import com.sfp.empresa.domain.Empresa;
import com.sfp.empresa.domain.EmpresaRepository;
import com.sfp.empresa.domain.EnderecoEmpresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @brief Classe responsável por implementar a interface EmpresaRepository
 */
public class MySQLEmpresaRepository implements EmpresaRepository {

    /**
     * @brief Método responsável por salvar a empresa
     * @param empresa Empresa a ser salva
     */
    @Override
    public void salvarEmpresa(Empresa empresa) {
        Empresa existente = buscarEmpresaUnica();

        if (existente == null) { // Se não existir empresa, insere uma nova
            // SQL para inserir nova empresa no BD com parametros padroes temporarios
            String sql = "INSERT INTO empresa (cnpj, razao_social, email, resp_legal, dia_fechamento_ponto) VALUES (?, ?, ?, ?, ?)";
            // Tenta conectar ao BD e prepara a query
            try (Connection conexao = ConexaoBD.getConnection();
                    PreparedStatement ps = conexao.prepareStatement(sql)) {
                // Insere os dados da empresa na query
                ps.setString(1, empresa.getCnpj());
                ps.setString(2, empresa.getRazaoSocial());
                ps.setString(3, empresa.getEmail());
                ps.setString(4, empresa.getRespLegal());
                ps.setInt(5, empresa.getDiaFechamentoPonto());
                // Executa a query
                ps.executeUpdate();
            } catch (Exception e) {
                throw new IllegalStateException("Erro ao inserir nova empresa", e);
            }
        } else { // Se existir empresa, atualiza
            // SQL para atualizar empresa preservando as colunas
            String sql = "UPDATE empresa SET cnpj = ?, razao_social = ?, email = ?, resp_legal = ?, dia_fechamento_ponto = ? WHERE cnpj = ?";
            // Tenta conectar ao BD e prepara a query
            try (Connection conexao = ConexaoBD.getConnection();
                    PreparedStatement ps = conexao.prepareStatement(sql)) {
                // Insere os dados da empresa na query
                ps.setString(1, empresa.getCnpj());
                ps.setString(2, empresa.getRazaoSocial());
                ps.setString(3, empresa.getEmail());
                ps.setString(4, empresa.getRespLegal());
                ps.setInt(5, empresa.getDiaFechamentoPonto());
                ps.setString(6, existente.getCnpj());
                // Executa a query
                ps.executeUpdate();
            } catch (Exception e) {
                throw new IllegalStateException("Erro ao atualizar empresa", e);
            }
        }
    }

    /**
     * @brief Método responsável por excluir a empresa
     * @param cnpj CNPJ da empresa a ser excluída
     */
    @Override
    public void excluirEmpresa(String cnpj) {
        // SQL para excluir empresa
        String sql = "DELETE FROM empresa WHERE cnpj = ?";
        // Tenta conectar ao BD e prepara a query
        try (Connection conexao = ConexaoBD.getConnection();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            // Insere o CNPJ da empresa na query
            ps.setString(1, cnpj);
            // Executa a query
            ps.executeUpdate();
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao excluir empresa", e);
        }
    }

    /**
     * @brief Método responsável por buscar a empresa única
     * @return Empresa única
     */
    @Override
    public Empresa buscarEmpresaUnica() {
        // SQL para buscar empresa única
        String sql = "SELECT * FROM empresa LIMIT 1";
        // Tenta conectar ao BD e prepara a query, assim como executa a query
        try (Connection con = ConexaoBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            // Se encontrar empresa, retorna
            if (rs.next()) {
                return new Empresa(
                        rs.getString("cnpj"),
                        rs.getString("razao_social"),
                        rs.getString("email"),
                        rs.getString("resp_legal"),
                        rs.getInt("dia_fechamento_ponto"));
            }
        } catch (Exception ex) {
            throw new IllegalStateException("Erro ao buscar empresa única", ex);
        }
        return null;
    }

    /**
     * @brief Método responsável por salvar o endereço da empresa
     * @param endereco Endereço da empresa a ser salvo
     */
    @Override
    public void salvarEndereco(EnderecoEmpresa endereco) {
        // Se o endereço não existir, insere um novo
        if (endereco.getId() == 0) {
            // SQL para inserir endereço
            String sql = "INSERT INTO endereco_empresa (cnpj_empresa, cep, logradouro, bairro, complemento) VALUES (?, ?, ?, ?, ?)";
            // Tenta conectar ao BD e prepara a query
            try (Connection conexao = ConexaoBD.getConnection();
                    PreparedStatement ps = conexao.prepareStatement(sql)) {
                // Insere os dados do endereço na query
                ps.setString(1, endereco.getCnpjEmpresa());
                ps.setString(2, endereco.getCep());
                ps.setString(3, endereco.getLogradouro());
                ps.setString(4, endereco.getBairro());
                ps.setString(5, endereco.getComplemento());
                // Executa a query
                ps.executeUpdate();
            } catch (Exception e) {
                throw new IllegalStateException("Erro ao inserir endereço da empresa", e);
            }
        } else {
            // Se o endereço existir, atualiza
            // SQL para atualizar endereço
            String sql = "UPDATE endereco_empresa SET cep = ?, logradouro = ?, bairro = ?, complemento = ? WHERE id = ?";
            // Tenta conectar ao BD e prepara a query
            try (Connection conexao = ConexaoBD.getConnection();
                    PreparedStatement ps = conexao.prepareStatement(sql)) {
                // Insere os dados do endereço na query
                ps.setString(1, endereco.getCep());
                ps.setString(2, endereco.getLogradouro());
                ps.setString(3, endereco.getBairro());
                ps.setString(4, endereco.getComplemento());
                ps.setInt(5, endereco.getId());
                // Executa a query
                ps.executeUpdate();
            } catch (Exception e) {
                throw new IllegalStateException("Erro ao atualizar endereço da empresa", e);
            }
        }
    }

    /**
     * @brief Método responsável por excluir o endereço da empresa
     * @param id ID do endereço a ser excluído
     */
    @Override
    public void excluirEndereco(int id) {
        // SQL para excluir endereço
        String sql = "DELETE FROM endereco_empresa WHERE id = ?";
        // Tenta conectar ao BD e prepara a query
        try (Connection conexao = ConexaoBD.getConnection();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            // Insere o ID do endereço na query
            ps.setInt(1, id);
            // Executa a query
            ps.executeUpdate();
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao excluir endereço da empresa", e);
        }
    }

    /**
     * @brief Método responsável por listar os endereços da empresa
     * @param cnpjEmpresa CNPJ da empresa
     * @return Lista de endereços da empresa
     */
    @Override
    public List<EnderecoEmpresa> listarEnderecos(String cnpjEmpresa) {
        List<EnderecoEmpresa> enderecos = new ArrayList<>();
        // SQL para listar endereços
        String sql = "SELECT * FROM endereco_empresa WHERE cnpj_empresa = ? ORDER BY id";
        // Tenta conectar ao BD e prepara a query
        try (Connection conexao = ConexaoBD.getConnection();
                PreparedStatement ps = conexao.prepareStatement(sql)) {
            // Insere o CNPJ da empresa na query
            ps.setString(1, cnpjEmpresa);
            // Tenta executar a query e obter os resultados
            try (ResultSet rs = ps.executeQuery()) {
                // Enquanto houver resultados, adiciona à lista
                while (rs.next()) {
                    enderecos.add(new EnderecoEmpresa(
                            rs.getInt("id"),
                            rs.getString("cnpj_empresa"),
                            rs.getString("cep"),
                            rs.getString("logradouro"),
                            rs.getString("bairro"),
                            rs.getString("complemento")));
                }
            }
        } catch (Exception e) { // Caso ocorra um erro
            throw new IllegalStateException("Erro ao listar endereços da empresa", e);
        }
        return enderecos;
    }
}