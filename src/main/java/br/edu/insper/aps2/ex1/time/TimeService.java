package br.edu.insper.aps2.ex1.time;

import br.edu.insper.aps2.ex1.campeonato.CampeonatoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TimeService {
    private final CampeonatoService campeonatoService;

    public TimeService(CampeonatoService campeonatoService) {
        this.campeonatoService = campeonatoService;
    }

    public Time criar(CampeonatoService.TimeRequest request) {
        return campeonatoService.criarTime(request);
    }

    public ArrayList<Time> listar() {
        return campeonatoService.listarTimes();
    }

    public Time buscar(String id) {
        return campeonatoService.buscarTime(id);
    }

    public Time adicionarJogador(String idTime, String idJogador) {
        return campeonatoService.adicionarJogador(idTime, idJogador);
    }
}
