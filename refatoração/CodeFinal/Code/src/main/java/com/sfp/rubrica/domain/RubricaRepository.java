/**
 * @brief Interface para o repositório de rubricas
 */
package com.sfp.rubrica.domain;

import java.util.List;

public interface RubricaRepository {
    /**
     * Cadastra uma rubrica
     * 
     * @param rubrica Rubrica a ser cadastrada
     */
    void cadastrarRubrica(Rubrica rubrica);

    /**
     * Edita uma rubrica
     * 
     * @param rubrica Rubrica a ser editada
     */
    void editarRubrica(Rubrica rubrica);

    /**
     * Exclui uma rubrica
     * 
     * @param codigo Código da rubrica a ser excluída
     */
    void excluirRubrica(int codigo);

    /**
     * Lista todas as rubricas ativas
     * 
     * @return Lista de rubricas ativas
     */
    List<Rubrica> listarAtivas();

    /**
     * Lista todas as rubricas
     * 
     * @return Lista de todas as rubricas
     */
    List<Rubrica> buscarTodas();

    /**
     * Busca uma rubrica por código
     * 
     * @param codigo Código da rubrica a ser buscada
     * @return Rubrica buscada
     */
    Rubrica buscarPorCodigo(int codigo);
}