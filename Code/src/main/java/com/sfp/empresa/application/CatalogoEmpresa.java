/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sfp.empresa.application;

import com.sfp.core.ConexaoBD;
import com.sfp.core.domain.Empresa;
import com.sfp.core.domain.EnderecoEmpresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author maria
 */
public class CatalogoEmpresa {
    public void cadastrarEmpresa(Empresa empresa)
    {
        String sql = "INSERT INTO empresa (cnpj, razao_social, email, resp_legal) VALUES (?, ?, ?, ?)";    
        try (Connection conexao = ConexaoBD.getConnection(); PreparedStatement ps = conexao.prepareStatement(sql)) 
        {

            ps.setString(1, empresa.getCnpj());
            ps.setString(2, empresa.getRazaoSocial());
            ps.setString(3, empresa.getEmail());
            ps.setString(4, empresa.getRespLegal());

            ps.executeUpdate();

        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }    
    }
    
    public void atualizarEmpresa(Empresa empresa) 
    {
        String sql = "UPDATE empresa SET razao_social = ?, email = ?, resp_legal = ? WHERE cnpj = ?";

        try (Connection conexao = ConexaoBD.getConnection(); PreparedStatement ps = conexao.prepareStatement(sql)) 
        {

            ps.setString(1, empresa.getRazaoSocial());
            ps.setString(2, empresa.getEmail());
            ps.setString(3, empresa.getRespLegal());
            ps.setString(4, empresa.getCnpj());

            ps.executeUpdate();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void excluirEmpresa(String cnpj) 
    {
        String sql = "DELETE FROM empresa WHERE cnpj = ?";

        try (Connection conexao = ConexaoBD.getConnection(); PreparedStatement ps = conexao.prepareStatement(sql)) 
        {
            ps.setString(1, cnpj);
            ps.executeUpdate();

        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public Empresa buscarEmpresa()
    {
        String sql = "SELECT * FROM empresa LIMIT 1";
        try(Connection con = ConexaoBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery())
        {
            if(rs.next())
            {
                return new Empresa(
                        rs.getString("cnpj"),
                        rs.getString("razao_social"),
                        rs.getString("email"),
                        rs.getString("resp_legal")
                );
            }
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(CatalogoEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void cadastrarEndereco(EnderecoEmpresa endereco) 
    {
        String sql = "INSERT INTO endereco_empresa (cnpj_empresa, cep, logradouro, bairro, complemento) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoBD.getConnection(); PreparedStatement ps = conexao.prepareStatement(sql)) 
        {
            ps.setString(1, endereco.getCnpjEmpresa());
            ps.setString(2, endereco.getCep());
            ps.setString(3, endereco.getLogradouro());
            ps.setString(4, endereco.getBairro());
            ps.setString(5, endereco.getComplemento());

            ps.executeUpdate();

        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void atualizarEndereco(EnderecoEmpresa endereco) 
    {
        String sql = "UPDATE endereco_empresa SET cep = ?, logradouro = ?, bairro = ?, complemento = ? WHERE id = ?";

        try (Connection conexao = ConexaoBD.getConnection(); PreparedStatement ps = conexao.prepareStatement(sql)) 
        {
            ps.setString(1, endereco.getCep());
            ps.setString(2, endereco.getLogradouro());
            ps.setString(3, endereco.getBairro());
            ps.setString(4, endereco.getComplemento());
            ps.setInt(5, endereco.getId());

            ps.executeUpdate();

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void excluirEndereco(int id)
    {
        String sql = "DELETE FROM endereco_empresa WHERE id = ?";

        try (Connection conexao = ConexaoBD.getConnection();PreparedStatement ps = conexao.prepareStatement(sql)) 
        {
            ps.setInt(1, id);
            ps.executeUpdate();

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public List<EnderecoEmpresa> listarEnderecos(String cnpjEmpresa) 
    {
        List<EnderecoEmpresa> enderecos = new ArrayList<>();
        String sql = "SELECT * FROM endereco_empresa WHERE cnpj_empresa = ? ORDER BY id";

        try (Connection conexao = ConexaoBD.getConnection(); PreparedStatement ps = conexao.prepareStatement(sql))
        {
            ps.setString(1, cnpjEmpresa);

            try (ResultSet rs = ps.executeQuery()) 
            {
                while (rs.next()) 
                {
                    EnderecoEmpresa endereco = new EnderecoEmpresa(
                            rs.getInt("id"),
                            rs.getString("cnpj_empresa"),
                            rs.getString("cep"),
                            rs.getString("logradouro"),
                            rs.getString("bairro"),
                            rs.getString("complemento")
                    );

                    enderecos.add(endereco);
                }
            }

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return enderecos;
    }
}
