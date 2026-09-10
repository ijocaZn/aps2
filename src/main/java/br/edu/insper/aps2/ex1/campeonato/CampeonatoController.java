package br.edu.insper.aps2.ex1;

import Exercicio1.Campeonato;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CampeonatoController {
    private final CampeonatoService service;

    public CampeonatoController(CampeonatoService service) {
        this.service = service;
    }

    @PostMapping("/campeonatos")
    @ResponseStatus(HttpStatus.CREATED)
    public Campeonato criarCampeonato(@RequestBody CampeonatoService.CampeonatoRequest request) {
        return service.criarCampeonato(request);
    }

    @GetMapping("/campeonatos/{id}/classificacao")
    public ArrayList<CampeonatoService.ClassificationEntry> classificacao(@PathVariable String id) {
        return service.classificacao(id);
    }
}
