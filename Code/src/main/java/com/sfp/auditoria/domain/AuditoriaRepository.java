/**
 * @brief Interface para o repositório de auditoria.
 
 */
package com.sfp.auditoria.domain;

import java.time.LocalDate;
import java.util.List;

public interface AuditoriaRepository {
    /**
     * @brief Registra uma ação de auditoria.
     * @param usuario  O usuário que realizou a ação.
     * @param perfil   O perfil do usuário que realizou a ação.
     * @param acao     A ação realizada.
     * @param entidade A entidade afetada.
     * @param detalhes Os detalhes da ação.
     */
    void registrar(String usuario, String perfil, String acao, String entidade, String detalhes);

    /**
     * @brief Busca registros de auditoria por usuário e data.
     * @param usuario O usuário.
     * @param data    A data.
     * @return Uma lista de registros de auditoria.
     */
    List<RegistroAuditoria> buscar(String usuario, LocalDate data);
}
