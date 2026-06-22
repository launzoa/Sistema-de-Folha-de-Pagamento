package com.sfp;
import com.sfp.core.database.ServicoDatabase;

public class TestRunner {
    public static void main(String[] args) {
        try {
            ServicoDatabase db = new ServicoDatabase();
            db.gerarTesteDeMesa();
            System.out.println("SUCESSO");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
