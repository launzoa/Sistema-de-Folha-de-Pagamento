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

    public void editarRubrica(Rubrica rubrica) {
        if (!podeEditar(rubrica.getCodigo())) {
            throw new RuntimeException("Violação Mecânica: Rubricas nativas constitucionais não podem ser alteradas!");
        }
        rubricaRepository.editarRubrica(rubrica);
        ServicoAuditoria.registrar("Edição", "Rubrica", "Código: " + rubrica.getCodigo());
    }

    public void excluirRubrica(int codigo) {
        if (!podeExcluir(codigo)) {
            throw new RuntimeException("Violação Mecânica: Rubricas nativas constitucionais não podem ser excluídas!");
        }
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

    public boolean podeExcluir(int codigo) {
        return !((codigo >= 1 && codigo <= 5) || (codigo >= 100 && codigo <= 103) || codigo == 901 || codigo == 902);
    }

    public boolean podeEditar(int codigo) {
        return !((codigo >= 1 && codigo <= 5) || (codigo >= 100 && codigo <= 103) || codigo == 901 || codigo == 902);
    }
}
