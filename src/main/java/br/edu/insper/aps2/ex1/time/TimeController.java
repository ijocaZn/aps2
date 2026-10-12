package br.edu.insper.aps2.ex1.time;

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
public class TimeController {
    private final TimeService service;

    public TimeController(TimeService service) {
        this.service = service;
    }

    @PostMapping("/times")
    @ResponseStatus(HttpStatus.CREATED)
    public Time criarTime(@RequestBody CampeonatoService.TimeRequest request) {
        return service.criar(request);
    }

    @GetMapping("/times")
    public ArrayList<Time> listarTimes() {
        return service.listar();
    }

    @GetMapping("/times/{id}")
    public Time buscarTime(@PathVariable String id) {
        return service.buscar(id);
    }

    @PostMapping("/times/{idTime}/jogadores/{idJogador}")
    public Time adicionarJogador(@PathVariable String idTime, @PathVariable String idJogador) {
        return service.adicionarJogador(idTime, idJogador);
    }
}