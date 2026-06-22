/**
 * @brief Classe que representa um usuário do sistema.
 */
package com.sfp.usuario.domain;

public class Usuario {
    private int id;
    private String nome;
    private String senha;
    private boolean perfil; // True para adm; False para RH
    private boolean status;

    /**
     * @brief Construtor padrão.
     */
    public Usuario() {
    }

    /**
     * @brief Construtor completo.
     * @param id     ID do usuário.
     * @param nome   Nome do usuário.
     * @param senha  Senha do usuário.
     * @param perfil Perfil do usuário.
     * @param status Status do usuário.
     */
    public Usuario(int id, String nome, String senha, boolean perfil, boolean status) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.perfil = perfil;
        this.status = status;
    }

    /**
     * @brief Pega o ID do usuário.
     * @return Retorna o ID do usuário.
     */
    public int getId() {
        return id;
    }

    /**
     * @brief Define o ID do usuário.
     * @param id ID do usuário.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @brief Pega o nome do usuário.
     * @return Retorna o nome do usuário.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @brief Define o nome do usuário.
     * @param nome Nome do usuário.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @brief Pega a senha do usuário.
     * @return Retorna a senha do usuário.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @brief Define a senha do usuário.
     * @param senha Senha do usuário.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @brief Pega o perfil do usuário.
     * @return Retorna o perfil do usuário.
     */
    public boolean isPerfil() {
        return perfil;
    }

    /**
     * @brief Define o perfil do usuário.
     * @param perfil Perfil do usuário.
     */
    public void setPerfil(boolean perfil) {
        this.perfil = perfil;
    }

    /**
     * @brief Pega o status do usuário.
     * @return Retorna o status do usuário.
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @brief Define o status do usuário.
     * @param status Status do usuário.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}
