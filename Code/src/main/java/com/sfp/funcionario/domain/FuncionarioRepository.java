/**
 * @brief: Interface que representa o repositório de funcionários (interação com banco de dados)
 */

package com.sfp.funcionario.domain;

import java.util.List;

public interface FuncionarioRepository {
    /**
     * @brief: Salva um funcionário no banco de dados
     * @param funcionario: Objeto Funcionario a ser salvo
     */
    void salvar(Funcionario funcionario);

    /**
     * @brief: Busca todos os funcionários salvos
     * @return List<Funcionario>: Lista de funcionários
     */
    List<Funcionario> buscarTodos();

    /**
     * @brief: Atualiza um funcionário no banco de dados
     * @param funcionario: Objeto Funcionario a ser atualizado
     */
    void atualizar(Funcionario funcionario);
}
