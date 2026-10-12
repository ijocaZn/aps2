package br.edu.insper.aps2.ex1.jogador;

import br.edu.insper.aps2.ex1.campeonato.CampeonatoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class JogadorController {
    private final JogadorService service;

    public JogadorController(JogadorService service) {
        this.service = service;
    }

    @PostMapping("/jogadores")
    @ResponseStatus(HttpStatus.CREATED)
    public Jogador criar(@RequestBody CampeonatoService.JogadorRequest request) {
        return service.criar(request);
    }

    @GetMapping("/jogadores")
    public ArrayList<Jogador> listar() {
        return service.listar();
    }

    @GetMapping("/jogadores/{id}")
    public Jogador buscar(@PathVariable String id) {
        return service.buscar(id);
    }
}
