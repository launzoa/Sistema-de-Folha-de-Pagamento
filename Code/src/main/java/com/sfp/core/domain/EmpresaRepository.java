package com.sfp.core.domain;

import java.util.List;

public interface EmpresaRepository {
    List<Empresa> buscarTodas();
    Empresa buscarPorCnpj(String cnpj);
    void salvar(Empresa empresa);
    void atualizar(Empresa empresa);
    void excluir(String cnpj);

    List<EnderecoEmpresa> buscarEnderecos(String cnpjEmpresa);
    void salvarEndereco(EnderecoEmpresa endereco);
    void excluirEndereco(int id);
}
