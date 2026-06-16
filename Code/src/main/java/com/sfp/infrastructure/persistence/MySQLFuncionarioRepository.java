package com.sfp.infrastructure.persistence;

import com.sfp.core.ConexaoBD;
import com.sfp.core.domain.FuncionarioRepository;
import com.sfp.core.domain.Funcionario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLFuncionarioRepository implements FuncionarioRepository {
    /**
     * @brief Salva um novo funcionário.
     * @param funcionario Funcionário responsável pelo holerite.
     */
    @Override
    public void salvar(Funcionario funcionario) {
        // Script sql para inserir um novo funcionário
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

            if (linhasAfetadas == 1) { // Verifica se o statement foi executado com sucesso
                System.out.println("Funcionário cadastrado com sucesso!");
            } else {
                System.out.println("Erro ao cadastrar funcionário!");
            }
        } catch (SQLException e) { // Caso ocorra um erro no banco de dados, lança uma exceção
            throw new RuntimeException("Erro no Banco de Dados: " + e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(MySQLFuncionarioRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // @brief: Busca todos os funcionários
    // @return List<Funcionario>: Lista de funcionários
    @Override
    public List<Funcionario> buscarTodos() {
        // Script sql para buscar todos os funcionários
        String sql = "SELECT * FROM funcionarios";
        List<Funcionario> funcionarios = new ArrayList<>();

        // Conexão com banco de dados mysql
        try (Connection conn = DriverManager.getConnection()) {
            // Prepara statement para buscar todos os funcionários
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Executa o statement
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) { // Verifica se o statement foi executado com sucesso
                // Pega os dados do funcionário
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String cargo = rs.getString("cargo");
                java.sql.Date sqlDate = rs.getDate("data_admissao");
                java.time.LocalDate dataAdmissao = (sqlDate != null) ? sqlDate.toLocalDate()
                        : java.time.LocalDate.now();
                java.math.BigDecimal salarioBruto = rs.getBigDecimal("salario_bruto");
                int numeroDependentes = rs.getInt("numero_dependentes");
                boolean status = rs.getBoolean("status");

                // Adiciona o funcionário na lista
                Funcionario funcionario = new Funcionario(nome, cpf, cargo, dataAdmissao, salarioBruto, status,
                        numeroDependentes);
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) { // Caso ocorra um erro no banco de dados, lança uma exceção
            throw new RuntimeException("Erro ao buscar funcionários: " + e.getMessage());
        }

        return funcionarios; // Retorna a lista de funcionários
    }

    // @brief: Atualiza os dados de um funcionário
    // @param funcionario: Funcionário a ser atualizado
    @Override
    public void atualizar(Funcionario funcionario) {
        String sql = "UPDATE funcionarios SET nome = ?, cargo = ?, data_admissao = ?, salario_bruto = ?, status = ? WHERE cpf = ?";
        try (Connection conn = DriverManager.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getCargo());
            pstmt.setDate(3, java.sql.Date.valueOf(funcionario.getDataAdmissao()));
            pstmt.setBigDecimal(4, funcionario.getSalarioBruto());
            pstmt.setBoolean(5, funcionario.getStatus());
            pstmt.setString(6, funcionario.getCpf());

            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Funcionário atualizado com sucesso!");
            } else {
                System.out.println("Nenhum funcionário encontrado com o CPF informado para atualizar!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }
}
