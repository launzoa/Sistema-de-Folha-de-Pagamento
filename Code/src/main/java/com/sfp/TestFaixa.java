package com.sfp;
import com.sfp.infrastructure.persistence.MySQLFaixaINSSRepository;
public class TestFaixa {
    public static void main(String[] args) {
        try {
            new MySQLFaixaINSSRepository().buscarTodas();
            System.out.println("SUCESSO: Tabela existe!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
