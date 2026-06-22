/**
 * @brief Classe responsável por controlar as operações de funcionário
 */
package com.sfp.funcionario.application;

import java.util.List;

import com.sfp.funcionario.domain.Funcionario;
import com.sfp.funcionario.domain.FuncionarioRepository;
import com.sfp.funcionario.infrastructure.persistence.MySQLFuncionarioRepository;
import com.sfp.auditoria.application.ServicoAuditoria;

public class ControladorFuncionario {
    private FuncionarioRepository funcionarioRepository = new MySQLFuncionarioRepository();

    /**
     * @brief Método que cadastra um funcionário
     * @param funcionario Objeto que contém os dados do funcionário
     */
    public void cadastrarFuncionario(Funcionario funcionario) {
        funcionarioRepository.salvar(funcionario);
        ServicoAuditoria.registrar("Cadastro", "Funcionário", "CPF: " + funcionario.getCpf());
    }

    /**
     * @brief Método que atualiza um funcionário
     * @param funcionario Objeto que contém os dados do funcionário
     */
    public void atualizarFuncionario(Funcionario funcionario) {
        funcionarioRepository.atualizar(funcionario);
        ServicoAuditoria.registrar("Edição", "Funcionário", "CPF: " + funcionario.getCpf());
    }

    /**
     * @brief Método que lista todos os funcionários
     * @return Lista de funcionários
     */
    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.buscarTodos();
    }
}
