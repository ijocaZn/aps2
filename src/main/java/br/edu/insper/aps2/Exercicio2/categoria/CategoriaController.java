package br.edu.insper.aps2.Exercicio2.categoria;

import br.edu.insper.aps2.Exercicio2.loja.LojaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriaController {
    private final CategoriaService service;

    public CategoriaController(CategoriaService service) { this.service = service; }

    @PostMapping("/categorias")
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria criar(@RequestBody LojaService.CategoriaRequest request) {
        return service.criar(request);
    }
}