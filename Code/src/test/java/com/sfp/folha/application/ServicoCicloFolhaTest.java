package com.sfp.folha.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.sfp.folha.domain.FolhaMes;
import com.sfp.folha.domain.FolhaMesRepository;
import com.sfp.empresa.domain.Empresa;
import com.sfp.empresa.application.ControladorEmpresa;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ServicoCicloFolhaTest {

    @Test
    @DisplayName("Deve colocar folha Aberta em Transição se a data fim foi ultrapassada")
    void testGerenciarCicloFolhas_AbertaParaTransicao() {
        // Mock manual do ControladorEmpresa
        ControladorEmpresa fakeControlador = new ControladorEmpresa() {
            @Override
            public Empresa buscarEmpresa() {
                return new Empresa("00.000.000/0001-00", "Empresa Fictícia", "contato@empresa.com", "Resp Legal", 15);
            }
        };

        LocalDate dataFimPassada = LocalDate.now().minusDays(2);
        FolhaMes folhaAtiva = new FolhaMes(1, "05/2026", 22, "Aberta", dataFimPassada.minusDays(30), dataFimPassada);

        // Mock manual do Repository
        FakeFolhaMesRepository fakeRepo = new FakeFolhaMesRepository(folhaAtiva);

        // Injeção de dependência pelo construtor
        ServicoCicloFolha servicoCicloFolha = new ServicoCicloFolha(fakeRepo, fakeControlador);
        
        servicoCicloFolha.gerenciarCicloFolhas();

        // Verifica se o fakeRepo registrou a atualização para 'Em Transição'
        assertTrue(fakeRepo.statusAtualizadoParaEmTransicao);
        assertEquals("Em Transição", folhaAtiva.getStatus());
    }

    // Classe utilitária local para fingir o banco de dados
    class FakeFolhaMesRepository implements FolhaMesRepository {
        FolhaMes folhaAtiva;
        boolean statusAtualizadoParaEmTransicao = false;

        FakeFolhaMesRepository(FolhaMes folhaAtiva) {
            this.folhaAtiva = folhaAtiva;
        }

        public void salvar(FolhaMes folha) {}
        public FolhaMes buscarPorCompetencia(String competencia) { return null; }
        public List<FolhaMes> buscarTodas() { return new ArrayList<>(); }
        public FolhaMes buscarFolhaAberta() { return null; }
        
        public List<FolhaMes> buscarFolhasAtivas() {
            List<FolhaMes> list = new ArrayList<>();
            list.add(folhaAtiva);
            return list;
        }

        public void atualizarStatus(int id, String novoStatus) {
            if (novoStatus.equals("Em Transição")) {
                statusAtualizadoParaEmTransicao = true;
                folhaAtiva.setStatus("Em Transição");
            }
        }
        
        public void atualizarDatas(int id, LocalDate inicio, LocalDate fim) {}
        public void excluirTodasFolhas() {}
    }
}
