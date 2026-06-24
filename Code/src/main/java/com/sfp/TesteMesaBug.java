package com.sfp;
import com.sfp.core.database.ServicoDatabase;
public class TesteMesaBug {
    public static void main(String[] args) {
        try {
            System.out.println("Iniciando...");
            ServicoDatabase servico = new ServicoDatabase();
            servico.gerarTesteDeMesa();
            System.out.println("Sucesso!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
