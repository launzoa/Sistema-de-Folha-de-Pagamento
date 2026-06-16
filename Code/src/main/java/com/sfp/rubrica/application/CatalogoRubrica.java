/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sfp.rubrica.application;

import com.sfp.core.ConexaoBD;
import com.sfp.core.domain.Rubrica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manoe
 */
public class CatalogoRubrica {
   public void cadastrarRubrica(Rubrica r)
   {
       String sql = "INSERT INTO rubrica (codigo, descricao, natureza, tipo, incide_inss, incide_fgts, incide_irrf, padrao, ativo)VALUES(?,?,?,?,?,?,?,FALSE,TRUE)";
        try(Connection con = ConexaoBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, r.getCodigo());
            ps.setString(2, r.getDescricao());
            ps.setString(3, r.getNatureza());
            ps.setString(4, r.getTipo());
            ps.setBoolean(5, r.isIncideINSS());
            ps.setBoolean(6, r.isIncideFGTS());
            ps.setBoolean(7, r.isIncideIRRF());
            ps.executeUpdate();
        } 
        catch (Exception e) 
        { 
            e.printStackTrace(); 
        }        
   }
   
   public void editarRubrica (Rubrica r)
   {
        String sql = "UPDATE rubrica SET descricao=?, natureza=?, tipo=?, incide_inss=?, incide_fgts=?, incide_irrf=?, ativo=? WHERE codigo=? AND padrao=FALSE";
        try(Connection con = ConexaoBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setString(1, r.getDescricao());
            ps.setString(2, r.getNatureza());
            ps.setString(3, r.getTipo());
            ps.setBoolean(4, r.isIncideINSS());
            ps.setBoolean(5, r.isIncideFGTS());
            ps.setBoolean(6, r.isIncideIRRF());
            ps.setBoolean(7,r.isAtivo());
            ps.setInt(8, r.getCodigo());
            
            ps.executeUpdate();
        } 
        catch (Exception e)
        { 
            e.printStackTrace(); 
        }
   }
   
   public void excluirRubrica(int codigo)
   {
       String sql = "DELETE FROM rubrica WHERE codigo=? AND codigo>5";
        try(Connection con = ConexaoBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, codigo);
            ps.executeUpdate();
        } 
        catch (Exception e) 
        { 
            e.printStackTrace(); 
        }   
   }
   
    public List<Rubrica> listarTodasRubricas() 
    {
        List<Rubrica> lista = new ArrayList<>();
        String sql = "SELECT * FROM rubrica WHERE ativo=TRUE ORDER BY codigo";
        try(Connection con = ConexaoBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) 
        {
            while (rs.next())
            {
                lista.add(
                    new Rubrica(
                        rs.getInt("codigo"),
                        rs.getString("descricao"),
                        rs.getString("natureza"),
                        rs.getString("tipo"),
                        rs.getBoolean("incide_inss"),
                        rs.getBoolean("incide_fgts"),
                        rs.getBoolean("incide_irrf"),
                        rs.getBoolean("padrao"),
                        rs.getBoolean("ativo") 
                    )
                );
            }
        } 
        catch (Exception e) 
        { 
            e.printStackTrace(); 
        }
        return lista;
    }
    
    public Rubrica buscarRubricaCod(int codigo) 
    {
        String sql = "SELECT * FROM rubrica WHERE codigo=?";
        try(Connection con = ConexaoBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                return new Rubrica(
                        rs.getInt("codigo"),
                        rs.getString("descricao"),
                        rs.getString("natureza"),
                        rs.getString("tipo"),
                        rs.getBoolean("incide_inss"),
                        rs.getBoolean("incide_fgts"),
                        rs.getBoolean("incide_irrf"),
                        rs.getBoolean("padrao"),
                        rs.getBoolean("ativo") 
                    );
            }
        } 
        catch (Exception e) 
        { 
            e.printStackTrace(); 
        }
        return null;
    }
 
    public void desativarRubrica(int codigo) 
    {
        String sql = "UPDATE rubrica SET ativo=FALSE WHERE codigo=? AND padrao=FALSE";
        try(Connection con = ConexaoBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) 
        {
            ps.setInt(1, codigo);
            ps.executeUpdate();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
