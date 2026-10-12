package br.edu.insper.aps2.ex1.partida;

import br.edu.insper.aps2.ex1.campeonato.CampeonatoService;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {
    private final CampeonatoService campeonatoService;

    public PartidaService(CampeonatoService campeonatoService) {
        this.campeonatoService = campeonatoService;
    }

    public Partida criar(CampeonatoService.PartidaRequest request) {
        return campeonatoService.criarPartida(request);
    }

    public Partida buscar(String id) {
        return campeonatoService.buscarPartida(id);
    }

    public Partida atualizarPlacar(String id, CampeonatoService.PlacarRequest request) {
        return campeonatoService.atualizarPlacar(id, request);
    }

    public Partida registrarCartao(String id, CampeonatoService.CartaoRequest request) {
        return campeonatoService.registrarCartao(id, request);
    }
}