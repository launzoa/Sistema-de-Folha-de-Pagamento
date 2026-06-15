package com.sfp.core.domain;

public interface FuncionarioRepository {
    // @brief: Salva um funcionário no banco de dados
    // @param funcionario: Objeto Funcionario a ser salvo
    void salvar(Funcionario funcionario);
    
    // @brief: Busca todos os funcionários salvos
    // @return List<Funcionario>: Lista de funcionários
    java.util.List<Funcionario> buscarTodos();

    // @brief: Atualiza um funcionário no banco de dados
    // @param funcionario: Objeto Funcionario a ser atualizado
    void atualizar(Funcionario funcionario);
}
