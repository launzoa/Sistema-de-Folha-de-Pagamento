package com.sfp.auditoria.application;

import java.time.LocalDate;
import java.util.List;

import com.sfp.auditoria.domain.AuditoriaRepository;
import com.sfp.auditoria.domain.RegistroAuditoria;
import com.sfp.auditoria.infrastructure.persistence.MySQLAuditoriaRepository;

/**
 * @brief Classe que controla as operações de auditoria.
 */
public class ControladorAuditoria {
    private AuditoriaRepository auditoriaRepository = new MySQLAuditoriaRepository();

    /**
     * @brief Registra uma ação de auditoria.
     * @param usuario  O usuário que realizou a ação.
     * @param perfil   O perfil do usuário que realizou a ação.
     * @param acao     A ação realizada.
     * @param entidade A entidade afetada.
     * @param detalhes Os detalhes da ação.
     */
    public void registrar(String usuario, String perfil, String acao, String entidade, String detalhes) {
        auditoriaRepository.registrar(usuario, perfil, acao, entidade, detalhes);
    }

    /**
     * @brief Busca os registros de auditoria de um usuário em uma data específica.
     * @param usuario O usuário que realizou a ação.
     * @param data    A data da ação.
     * @return A lista de registros de auditoria.
     */
    public List<RegistroAuditoria> buscar(String usuario, LocalDate data) {
        return auditoriaRepository.buscar(usuario, data);
    }
}
