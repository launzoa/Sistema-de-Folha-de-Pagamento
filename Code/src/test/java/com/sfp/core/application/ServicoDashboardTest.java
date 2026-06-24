package com.sfp.core.application;

import org.junit.jupiter.api.Test;

public class ServicoDashboardTest {
    @Test
    public void testDashboard() {
        try {
            ServicoDashboard sd = new ServicoDashboard();
            sd.processarDadosDashboard();
            System.out.println("SUCESSO DASHBOARD TESTE!");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
