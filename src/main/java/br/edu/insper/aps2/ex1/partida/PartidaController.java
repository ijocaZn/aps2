package br.edu.insper.aps2.ex1.partida;

import br.edu.insper.aps2.ex1.campeonato.CampeonatoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PartidaController {
    private final PartidaService service;

    public PartidaController(PartidaService service) {
        this.service = service;
    }

    @PostMapping("/partidas")
    @ResponseStatus(HttpStatus.CREATED)
    public Partida criar(@RequestBody CampeonatoService.PartidaRequest request) {
        return service.criar(request);
    }

    @GetMapping("/partidas/{id}")
    public Partida buscar(@PathVariable String id) {
        return service.buscar(id);
    }

    @PatchMapping("/partidas/{id}/placar")
    public Partida atualizarPlacar(@PathVariable String id,
                                   @RequestBody CampeonatoService.PlacarRequest request) {
        return service.atualizarPlacar(id, request);
    }

    @PostMapping("/partidas/{id}/cartoes")
    public Partida registrarCartao(@PathVariable String id,
                                   @RequestBody CampeonatoService.CartaoRequest request) {
        return service.registrarCartao(id, request);
    }
}