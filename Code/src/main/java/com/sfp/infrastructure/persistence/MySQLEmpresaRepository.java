package com.sfp.infrastructure.persistence;

import com.sfp.core.domain.Empresa;
import com.sfp.core.domain.EnderecoEmpresa;
import com.sfp.core.domain.EmpresaRepository;
import com.sfp.core.ConexaoBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLEmpresaRepository implements EmpresaRepository {

    @Override
    public List<Empresa> buscarTodas() {
        List<Empresa> lista = new ArrayList<>();
        String sql = "SELECT * FROM empresa";
        try (Connection con = ConexaoBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Empresa emp = mapResultSetToEmpresa(rs);
                emp.setEnderecos(buscarEnderecos(emp.getCnpj()));
                lista.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Empresa buscarPorCnpj(String cnpj) {
        String sql = "SELECT * FROM empresa WHERE cnpj = ?";
        try (Connection con = ConexaoBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cnpj);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Empresa emp = mapResultSetToEmpresa(rs);
                emp.setEnderecos(buscarEnderecos(emp.getCnpj()));
                return emp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void salvar(Empresa empresa) {
        String sql = "INSERT INTO empresa (cnpj, razao_social, email, resp_legal, aliquota_fgts, horas_mensais, val_cesta_basic, perc_hora_extra50, perc_hora_extra100) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexaoBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, empresa.getCnpj());
            ps.setString(2, empresa.getRazaoSocial());
            ps.setString(3, empresa.getEmail());
            ps.setString(4, empresa.getRespLegal());
            ps.setDouble(5, empresa.getAliquotaFgts());
            ps.setInt(6, empresa.getHorasMensais());
            ps.setDouble(7, empresa.getValCestaBasic());
            ps.setDouble(8, empresa.getPercHoraExtra50());
            ps.setDouble(9, empresa.getPercHoraExtra100());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Empresa empresa) {
        String sql = "UPDATE empresa SET razao_social=?, email=?, resp_legal=?, aliquota_fgts=?, horas_mensais=?, val_cesta_basic=?, perc_hora_extra50=?, perc_hora_extra100=? WHERE cnpj=?";
        try (Connection con = ConexaoBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, empresa.getRazaoSocial());
            ps.setString(2, empresa.getEmail());
            ps.setString(3, empresa.getRespLegal());
            ps.setDouble(4, empresa.getAliquotaFgts());
            ps.setInt(5, empresa.getHorasMensais());
            ps.setDouble(6, empresa.getValCestaBasic());
            ps.setDouble(7, empresa.getPercHoraExtra50());
            ps.setDouble(8, empresa.getPercHoraExtra100());
            ps.setString(9, empresa.getCnpj());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(String cnpj) {
        String sql = "DELETE FROM empresa WHERE cnpj = ?";
        try (Connection con = ConexaoBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cnpj);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EnderecoEmpresa> buscarEnderecos(String cnpjEmpresa) {
        List<EnderecoEmpresa> lista = new ArrayList<>();
        String sql = "SELECT * FROM endereco_empresa WHERE cnpj_empresa = ?";
        try (Connection con = ConexaoBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cnpjEmpresa);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EnderecoEmpresa end = new EnderecoEmpresa();
                end.setId(rs.getInt("id"));
                end.setCnpjEmpresa(rs.getString("cnpj_empresa"));
                end.setCep(rs.getString("cep"));
                end.setLogradouro(rs.getString("logradouro"));
                end.setBairro(rs.getString("bairro"));
                end.setComplemento(rs.getString("complemento"));
                lista.add(end);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void salvarEndereco(EnderecoEmpresa endereco) {
        String sql = "INSERT INTO endereco_empresa (cnpj_empresa, cep, logradouro, bairro, complemento) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexaoBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, endereco.getCnpjEmpresa());
            ps.setString(2, endereco.getCep());
            ps.setString(3, endereco.getLogradouro());
            ps.setString(4, endereco.getBairro());
            ps.setString(5, endereco.getComplemento());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluirEndereco(int id) {
        String sql = "DELETE FROM endereco_empresa WHERE id = ?";
        try (Connection con = ConexaoBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Empresa mapResultSetToEmpresa(ResultSet rs) throws SQLException {
        Empresa emp = new Empresa();
        emp.setCnpj(rs.getString("cnpj"));
        emp.setRazaoSocial(rs.getString("razao_social"));
        emp.setEmail(rs.getString("email"));
        emp.setRespLegal(rs.getString("resp_legal"));
        emp.setAliquotaFgts(rs.getDouble("aliquota_fgts"));
        emp.setHorasMensais(rs.getInt("horas_mensais"));
        emp.setValCestaBasic(rs.getDouble("val_cesta_basic"));
        emp.setPercHoraExtra50(rs.getDouble("perc_hora_extra50"));
        emp.setPercHoraExtra100(rs.getDouble("perc_hora_extra100"));
        return emp;
    }
}
