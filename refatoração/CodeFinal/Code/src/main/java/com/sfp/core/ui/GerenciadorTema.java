package com.sfp.core.ui;

/**
 * @brief Arquivo responsável por gerenciar o tema da aplicação.
 *        Ele é responsável por mudar (alternar) o tema para modo escuro ou
 *        claro.
 */
public class GerenciadorTema {
    private GerenciadorTema() {
    }

    // Variavel estatica para controlar o modo escuro
    public static boolean modoEscuroAtivo = false;
}
