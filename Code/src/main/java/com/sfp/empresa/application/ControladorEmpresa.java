/**
 * @brief classe que representa o controller de empresa
 * Ela faz a interface com o catálogo de empresa
 */
package com.sfp.empresa.application;

import com.sfp.empresa.domain.Empresa;
import com.sfp.empresa.domain.EmpresaRepository;
import com.sfp.empresa.domain.EnderecoEmpresa;
import com.sfp.empresa.infrastructure.persistence.MySQLEmpresaRepository;
import com.sfp.auditoria.application.ServicoAuditoria;
import java.util.List;

public class ControladorEmpresa {
     private EmpresaRepository empresaRepository = new MySQLEmpresaRepository();

     /**
      * @brief método que cadastra uma empresa
      * @param empresa empresa a ser cadastrada
      */
     public void cadastrarEmpresa(Empresa empresa) {
          empresaRepository.salvarEmpresa(empresa);
          ServicoAuditoria.registrar("Cadastro", "Empresa", "CNPJ: " + empresa.getCnpj());
     }

     /**
      * @brief método que atualiza uma empresa
      * @param empresa empresa a ser atualizada
      */
     public void atualizarEmpresa(Empresa empresa) {
          empresaRepository.salvarEmpresa(empresa);
          ServicoAuditoria.registrar("Edição", "Empresa", "CNPJ: " + empresa.getCnpj());
     }

     /**
      * @brief método que exclui uma empresa
      * @param cnpj CNPJ da empresa a ser excluída
      */
     public void excluirEmpresa(String cnpj) {
          empresaRepository.excluirEmpresa(cnpj);
          ServicoAuditoria.registrar("Exclusão", "Empresa", "CNPJ: " + cnpj);
     }

     /**
      * @brief método que busca a empresa unica do sistema
      * @return empresa buscada
      */
     public Empresa buscarEmpresa() {
          return empresaRepository.buscarEmpresaUnica();
     }

     /**
      * @brief método que cadastra um endereço
      * @param endereco endereço a ser cadastrado para a empresa
      */
     public void cadastrarEndereco(EnderecoEmpresa endereco) {
          empresaRepository.salvarEndereco(endereco);
     }

     /**
      * @brief método que atualiza um endereço
      * @param endereco endereço a ser atualizado para a empresa
      */
     public void atualizarEndereco(EnderecoEmpresa endereco) {
          empresaRepository.salvarEndereco(endereco);
     }

     /**
      * @brief método que exclui um endereço
      * @param id id do endereço a ser excluído
      */
     public void excluirEndereco(int id) {
          empresaRepository.excluirEndereco(id);
     }

     /**
      * @brief método que lista os endereços de uma empresa
      * @param cnpjEmpresa CNPJ da empresa a ter os endereços listados
      * @return lista de endereços da empresa
      */
     public List<EnderecoEmpresa> listarEnderecos(String cnpjEmpresa) {
          return empresaRepository.listarEnderecos(cnpjEmpresa);
     }
}
