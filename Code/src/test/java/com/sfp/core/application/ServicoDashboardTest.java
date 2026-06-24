package com.sfp.core.application;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ServicoDashboardTest {
    @Test
    void testDashboard() {
        ServicoDashboard sd = new ServicoDashboard();
        DashboardDados dados = sd.processarDadosDashboard();
        assertNotNull(dados, "Os dados do dashboard não deveriam ser nulos");
    }
}
