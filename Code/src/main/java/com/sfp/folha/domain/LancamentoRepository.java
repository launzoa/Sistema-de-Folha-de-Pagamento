/**
 * @brief Interface que representa o repositório de lançamentos
 */
package com.sfp.folha.domain;

import java.util.List;

public interface LancamentoRepository {

    /**
     * @brief Registra um novo evento no banco
     * @param lancamento Objeto do tipo Lancamento
     */
    void salvar(Lancamento lancamento);

    /**
     * @brief Busca todos os eventos de um funcionário para um mês específico
     * @param idFolha        ID da folha
     * @param cpfFuncionario CPF do funcionário
     * @return Lista de lançamentos do funcionário
     */
    List<Lancamento> buscarPorFolhaEFuncionario(int idFolha, String cpfFuncionario);

    /**
     * @brief Permite deletar um lançamento que foi feito errado pelo RH
     * @param id ID do lançamento
     */
    void deletar(int id);

    /**
     * @brief Busca todos os lançamentos cadastrados no banco
     * @return Lista de lançamentos
     */
    List<Lancamento> buscarTodos();

    /**
     * @brief Busca lançamentos por uma folha específica
     * @param idFolha ID da folha
     * @return Lista de lançamentos da folha
     */
    List<Lancamento> buscarPorFolha(int idFolha);

    /**
     * @brief Busca um lançamento pelo ID
     * @param id ID do lançamento
     * @return Lançamento
     */
    Lancamento buscarPorId(int id);

    /**
     * @brief Atualiza um lançamento
     * @param lancamento Objeto do tipo Lancamento
     */
    void atualizar(Lancamento lancamento);
}