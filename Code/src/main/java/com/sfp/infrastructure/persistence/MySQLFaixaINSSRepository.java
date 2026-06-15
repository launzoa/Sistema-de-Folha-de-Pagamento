package com.sfp.infrastructure.persistence;

import com.sfp.folha.domain.FaixaINSS;
import com.sfp.core.domain.FaixaINSSRepository;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLFaixaINSSRepository implements FaixaINSSRepository {
    String url = "jdbc:mysql://localhost:3306/SFP";
    String user = "root";
    String password = "admin";

    // brief: insere uma nova faixa de aliquota do INSS
    // @param FaixaINSS faixaINSS: objeto FaixaINSS que será inserido no BD
    @Override
    public void salvar(FaixaINSS faixaINSS) {
        // script sql para inserir uma nova faixa de aliquota do INSS
        String sql = "INSERT INTO faixa_inss (piso, teto, aliquota, parcela_deduzir) VALUES (?, ?, ?, ?)";

        // conexão com banco de dados mysql
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // prepara statement para inserir dados
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // setando os valores para o statement
            pstmt.setBigDecimal(1, faixaINSS.getPiso());
            pstmt.setBigDecimal(2, faixaINSS.getTeto());
            pstmt.setBigDecimal(3, faixaINSS.getAliquota());
            pstmt.setBigDecimal(4, faixaINSS.getParcelaDeduzir());

            // executa o statement
            int linhasAfetadas = pstmt.executeUpdate();

            // verifica se o statement foi executado com sucesso
            if (linhasAfetadas == 1) {
                System.out.println("Faixa de aliquota do INSS cadastrada com sucesso!");
            } else {
                System.out.println("Erro ao cadastrar faixa de aliquota do INSS!");
            }
        } catch (SQLException e) { // caso ocorra um erro no banco de dados, lança uma exceção
            throw new RuntimeException("Erro no Banco de Dados: " + e.getMessage());
        }
    }

    // brief: busca todas as faixas do INSS
    // @return List<FaixaINSS>: lista de faixas do INSS
    @Override
    public List<FaixaINSS> buscarTodas() {
        List<FaixaINSS> faixas = new ArrayList<>();
        // script sql para buscar todas as faixas do INSS
        String sql = "SELECT * FROM faixa_inss";

        // conexão com banco de dados mysql
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // prepara statement para buscar dados
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // executa o statement
            ResultSet rs = pstmt.executeQuery();
            // percorre o resultset
            while (rs.next()) { // para cada linha do resultset
                // cria um objeto FaixaINSS com os dados da linha
                FaixaINSS faixa = new FaixaINSS(rs.getBigDecimal("piso"), rs.getBigDecimal("teto"),
                        rs.getBigDecimal("aliquota"), rs.getBigDecimal("parcela_deduzir"));
                // adiciona o objeto FaixaINSS na lista
                faixas.add(faixa);
            }
        } catch (SQLException e) { // caso ocorra um erro no banco de dados, lança uma exceção
            throw new RuntimeException("Erro no Banco de Dados: " + e.getMessage());
        }

        return faixas;
    }
}
