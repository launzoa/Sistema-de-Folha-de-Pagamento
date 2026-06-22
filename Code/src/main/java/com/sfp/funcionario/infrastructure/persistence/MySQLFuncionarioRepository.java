/**
 * @brief: Implementação da interface FuncionarioRepository para persistência de dados em MySQL
 * CRUD básico para a classe Funcionário e a tabela funcionarios do BD:
 * salvar() - Inserção de novos funcionários no BD.
 * buscarTodos() - Busca todos os funcionários do BD.
 * atualizar() - Atualiza os dados de um funcionário no BD.
 */

package com.sfp.funcionario.infrastructure.persistence;

import com.sfp.core.database.ConexaoBD;
import com.sfp.funcionario.domain.FuncionarioRepository;
import com.sfp.funcionario.domain.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySQLFuncionarioRepository implements FuncionarioRepository {
    /**
     * @brief: Salva um funcionário no banco de dados
     * @param funcionario: Objeto Funcionario a ser salvo
     */
    @Override
    public void salvar(Funcionario funcionario) {
        // Query para inserir dados na tabela funcionarios no BD
        String sql = "INSERT INTO funcionarios (nome, cpf, cargo, data_admissao, salario_bruto, numero_dependentes, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Conexão com banco de dados mysql
        try (Connection conn = ConexaoBD.getConnection()) {
            // Prepara statement para inserir dados
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Setando os valores para o statement
            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getCpf());
            pstmt.setString(3, funcionario.getCargo());
            pstmt.setDate(4, java.sql.Date.valueOf(funcionario.getDataAdmissao()));
            pstmt.setBigDecimal(5, funcionario.getSalarioBruto());
            pstmt.setInt(6, funcionario.getNumeroDependentes());
            pstmt.setBoolean(7, funcionario.getStatus());

            int linhasAfetadas = pstmt.executeUpdate(); // Executa o statement
            if (linhasAfetadas == 0) { // Verifica se foi afetada alguma linha
                throw new RuntimeException("Erro ao cadastrar funcionário no Banco de Dados");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao cadastrar funcionário no Banco de Dados", e);
        }
    }

    /**
     * @brief: Busca todos os funcionários
     * @return List<Funcionario>: Lista de funcionários
     */
    @Override
    public List<Funcionario> buscarTodos() {
        // Script sql para buscar todas as linhas da tabela funcionarios
        String sql = "SELECT * FROM funcionarios";
        List<Funcionario> funcionarios = new ArrayList<>();

        // Conexão com BD
        try (Connection conn = ConexaoBD.getConnection()) {
            // Prepara statement para buscar todos os funcionários
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Executa o statement
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) { // Enquanto houver uma linha para ler, executa o código
                // Pega os dados do funcionário na linha atual
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String cargo = rs.getString("cargo");
                java.sql.Date sqlDate = rs.getDate("data_admissao");
                java.time.LocalDate dataAdmissao = (sqlDate != null) ? sqlDate.toLocalDate()
                        : java.time.LocalDate.now();
                java.math.BigDecimal salarioBruto = rs.getBigDecimal("salario_bruto");
                int numeroDependentes = rs.getInt("numero_dependentes");
                boolean status = rs.getBoolean("status");

                // Instancia e adiciona o funcionário na lista
                Funcionario funcionario = new Funcionario(nome, cpf, cargo, dataAdmissao, salarioBruto, status,
                        numeroDependentes);
                funcionarios.add(funcionario);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar funcionários", e);
        }

        return funcionarios; // Retorna a lista de funcionários
    }

    /**
     * @brief: Atualiza um funcionário no banco de dados
     * @param funcionario: Objeto Funcionario a ser atualizado
     */
    @Override
    public void atualizar(Funcionario funcionario) {
        // Script sql para atualizar um funcionário na tabela funcionarios
        String sql = "UPDATE funcionarios SET nome = ?, cargo = ?, data_admissao = ?, salario_bruto = ?, numero_dependentes = ?, status = ? WHERE cpf = ?";
        // Conexão com o BD
        try (Connection conn = ConexaoBD.getConnection()) {
            // Prepara statement para atualizar um funcionário
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Setando os valores para o statement
            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getCargo());
            pstmt.setDate(3, java.sql.Date.valueOf(funcionario.getDataAdmissao()));
            pstmt.setBigDecimal(4, funcionario.getSalarioBruto());
            pstmt.setInt(5, funcionario.getNumeroDependentes());
            pstmt.setBoolean(6, funcionario.getStatus());
            pstmt.setString(7, funcionario.getCpf());

            // Executa o statement
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar funcionário", e);
        }
    }
}
