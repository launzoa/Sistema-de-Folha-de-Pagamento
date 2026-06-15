/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sfp.empresa.application;


import com.sfp.core.domain.Empresa;
import com.sfp.core.domain.EnderecoEmpresa;
import java.util.List;
/**
 *
 * @author maria
 */
public class ControladorEmpresa {
   private CatalogoEmpresa catalogoEmpresa = new CatalogoEmpresa();
   
   public void cadastrarEmpresa(Empresa empresa)
   {
       catalogoEmpresa.cadastrarEmpresa(empresa);
   }
   public void atualizarEmpresa(Empresa empresa)
   {
       catalogoEmpresa.atualizarEmpresa(empresa);
   }
   
   public void excluirEmpresa(String cnpj) {
        catalogoEmpresa.excluirEmpresa(cnpj);
   }

   public List<Empresa> listarEmpresas() 
   {
        return catalogoEmpresa.listarEmpresas();
   }

   public void cadastrarEndereco(EnderecoEmpresa endereco) 
   {
        catalogoEmpresa.cadastrarEndereco(endereco);
   }

   public void atualizarEndereco(EnderecoEmpresa endereco)
   {
        catalogoEmpresa.atualizarEndereco(endereco);
   }

   public void excluirEndereco(int id)
   {
        catalogoEmpresa.excluirEndereco(id);
   }

   public List<EnderecoEmpresa> listarEnderecos(String cnpjEmpresa)
   {
        return catalogoEmpresa.listarEnderecos(cnpjEmpresa);
   }
   
}
