/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sfp.auditoria.application;

import com.sfp.core.ConexaoBD;
import com.sfp.core.domain.RegistroAuditoria;
import com.sfp.core.domain.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manoe
 */
public class CatalogoAuditoria {
    public void registrar(String usuario, String perfil, String acao, String entidade, String detalhes) {
        String sql = "INSERT INTO auditoria (usuario, perfil, acao, entidade, detalhes) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConexaoBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, perfil);
            ps.setString(3, acao);
            ps.setString(4, entidade);
            ps.setString(5, detalhes);
            ps.executeUpdate();

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public List<RegistroAuditoria> buscar(String usuario, LocalDate data) {
        List<RegistroAuditoria> lista = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM auditoria WHERE 1=1");

        if (usuario != null && !usuario.isBlank()) sql.append(" AND usuario LIKE ?");
        if (data != null) sql.append(" AND DATE(timestamp) = ?");
        sql.append(" ORDER BY timestamp DESC");

        try (Connection con = ConexaoBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql.toString())) 
        {
            int i = 1;
            if (usuario != null && !usuario.isBlank()) ps.setString(i++, "%" + usuario + "%");
            if (data != null) ps.setDate(i, Date.valueOf(data));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RegistroAuditoria r = new RegistroAuditoria();
                r.setId(rs.getInt("id"));
                r.setData_hora(rs.getTimestamp("timestamp").toLocalDateTime());
                r.setUsuario(rs.getString("usuario"));
                r.setPerfil(rs.getString("perfil"));
                r.setAcao(rs.getString("acao"));
                r.setEntidade(rs.getString("entidade"));
                r.setDetalhes(rs.getString("detalhes"));
                lista.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }    
}
