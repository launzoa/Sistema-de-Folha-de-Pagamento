/**
 * @brief Repositório de auditoria.
 * Implementa a interface AuditoriaRepository.
 */

package com.sfp.auditoria.infrastructure.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import com.sfp.auditoria.domain.AuditoriaRepository;
import com.sfp.auditoria.domain.RegistroAuditoria;
import com.sfp.core.database.ConexaoBD;

public class MySQLAuditoriaRepository implements AuditoriaRepository {

    /**
     * @brief Registra uma ação de auditoria.
     * @param usuario  Usuário que realizou a ação.
     * @param perfil   Perfil do usuário.
     * @param acao     Ação realizada.
     * @param entidade Entidade afetada.
     * @param detalhes Detalhes da ação.
     */
    @Override
    public void registrar(String usuario, String perfil, String acao, String entidade, String detalhes) {
        // SQL para inserir um registro de auditoria na tabela auditoria.
        String sql = "INSERT INTO auditoria (usuario, perfil, acao, entidade, detalhes) VALUES (?, ?, ?, ?, ?)";
        // Tenta fazer a conexão com o DB e prepara a query.
        try (Connection con = ConexaoBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            // Preenche os dados.
            ps.setString(1, usuario);
            ps.setString(2, perfil);
            ps.setString(3, acao);
            ps.setString(4, entidade);
            ps.setString(5, detalhes);
            // Executa a query.
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao registrar auditoria no Banco de Dados", e);
        }
    }

    /**
     * @brief Busca registros de auditoria por usuário e data.
     * @param usuario Usuário a ser buscado.
     * @param data    Data a ser buscada.
     * @return Lista de registros de auditoria.
     */
    @Override
    public List<RegistroAuditoria> buscar(String usuario, LocalDate data) {
        List<RegistroAuditoria> lista = new ArrayList<>();
        // String para buscar os registros de auditoria na tabela auditoria.
        StringBuilder sql = new StringBuilder("SELECT * FROM auditoria WHERE 1=1");

        if (usuario != null && !usuario.isBlank()) // Se o usuário não for vazio adiciona na query.
            sql.append(" AND usuario LIKE ?");
        if (data != null) // Se a data não for vazia adiciona na query.
            sql.append(" AND DATE(timestamp) = ?");
        // Ordena os registros por data e hora.
        sql.append(" ORDER BY timestamp DESC");
        // Tenta a conexão com o DB e prepara a query.
        try (Connection con = ConexaoBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql.toString())) {
            int i = 1;
            if (usuario != null && !usuario.isBlank()) // Se o usuário não for vazio adiciona na query.
                ps.setString(i++, "%" + usuario + "%");
            if (data != null) // Se a data não for vazia adiciona na query.
                ps.setDate(i, Date.valueOf(data));
            // Executa a query.
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { // Enquanto houver resultados na query.
                // Cria um objeto RegistroAuditoria e adiciona os dados.
                RegistroAuditoria r = new RegistroAuditoria();
                r.setId(rs.getInt("id"));
                r.setDataHora(rs.getTimestamp("timestamp").toLocalDateTime());
                r.setUsuario(rs.getString("usuario"));
                r.setPerfil(rs.getString("perfil"));
                r.setAcao(rs.getString("acao"));
                r.setEntidade(rs.getString("entidade"));
                r.setDetalhes(rs.getString("detalhes"));
                lista.add(r); // Adiciona o objeto na lista.
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar registros de auditoria", e);
        }
        return lista;
    }
}