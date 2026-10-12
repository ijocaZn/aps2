package br.edu.insper.aps2.ex1.jogador;

import br.edu.insper.aps2.ex1.campeonato.CampeonatoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JogadorService {
    private final CampeonatoService campeonatoService;

    public JogadorService(CampeonatoService campeonatoService) {
        this.campeonatoService = campeonatoService;
    }

    public Jogador criar(CampeonatoService.JogadorRequest request) {
        return campeonatoService.criarJogador(request);
    }

    public ArrayList<Jogador> listar() {
        return campeonatoService.listarJogadores();
    }

    public Jogador buscar(String id) {
        return campeonatoService.buscarJogador(id);
    }
}
