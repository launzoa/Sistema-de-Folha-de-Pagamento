/**
 * @brief Interface responsável por gerenciar as empresas
 */
package com.sfp.empresa.domain;

import java.util.List;

public interface EmpresaRepository {
    /**
     * @brief Método responsável por salvar a empresa
     * @param empresa Empresa a ser salva
     */
    void salvarEmpresa(Empresa empresa);

    /**
     * @brief Método responsável por excluir a empresa
     * @param cnpj CNPJ da empresa
     */
    void excluirEmpresa(String cnpj);

    /**
     * @brief Método responsável por buscar a empresa
     * @return Empresa encontrada
     */
    Empresa buscarEmpresaUnica();

    /**
     * @brief Método responsável por salvar o endereço
     * @param endereco Endereço a ser salvo
     */
    void salvarEndereco(EnderecoEmpresa endereco);

    /**
     * @brief Método responsável por excluir o endereço
     * @param id ID do endereço
     */
    void excluirEndereco(int id);

    /**
     * @brief Método responsável por listar os endereços
     * @param cnpjEmpresa CNPJ da empresa
     * @return Lista de endereços
     */
    List<EnderecoEmpresa> listarEnderecos(String cnpjEmpresa);
}
