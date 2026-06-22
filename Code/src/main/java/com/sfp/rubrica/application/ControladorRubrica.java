/**
 * @brief Classe responsável por controlar as operações de rubricas
 */
package com.sfp.rubrica.application;

import java.util.List;

import com.sfp.rubrica.domain.Rubrica;
import com.sfp.rubrica.domain.RubricaRepository;
import com.sfp.rubrica.infrastructure.persistence.MySQLRubricaRepository;
import com.sfp.auditoria.application.ServicoAuditoria;

public class ControladorRubrica {

    private RubricaRepository rubricaRepository = new MySQLRubricaRepository();

    /**
     * @brief Cadastra uma rubrica
     * @param rubrica Rubrica a ser cadastrada
     */
    public void cadastrarRubrica(Rubrica rubrica) {
        rubricaRepository.cadastrarRubrica(rubrica);
        ServicoAuditoria.registrar("Cadastro", "Rubrica", "Código: " + rubrica.getCodigo());
    }

    /**
     * @brief Edita uma rubrica
     * @param rubrica Rubrica a ser editada
     */
    public void editarRubrica(Rubrica rubrica) {
        rubricaRepository.editarRubrica(rubrica);
        ServicoAuditoria.registrar("Edição", "Rubrica", "Código: " + rubrica.getCodigo());
    }

    /**
     * @brief Exclui uma rubrica
     * @param codigo Código da rubrica a ser excluída
     */
    public void excluirRubrica(int codigo) {
        rubricaRepository.excluirRubrica(codigo);
        ServicoAuditoria.registrar("Exclusão", "Rubrica", "Código: " + codigo);
    }

    /**
     * @brief Lista todas as rubricas ativas
     * @return Lista de rubricas ativas
     */
    public List<Rubrica> listarTodasRubricas() {
        return rubricaRepository.listarAtivas();
    }

    /**
     * @brief Busca uma rubrica por código
     * @param codigo Código da rubrica a ser buscada
     * @return Rubrica buscada
     */
    public Rubrica buscarRubricaCod(int codigo) {
        return rubricaRepository.buscarPorCodigo(codigo);
    }

    /**
     * @brief Verifica se uma rubrica pode ser excluída
     * @param codigo Código da rubrica
     * @return true se a rubrica pode ser excluída, false caso contrário
     */
    public boolean podeExcluir(int codigo) {
        return codigo > 5; // 001-005 não podem ser excluídas
    }

    /**
     * @brief Verifica se uma rubrica pode ser editada
     * @param codigo Código da rubrica
     * @return true se a rubrica pode ser editada, false caso contrário
     */
    public boolean podeEditar(int codigo) {
        return codigo > 5; // 001-005 são somente leitura
    }
}
