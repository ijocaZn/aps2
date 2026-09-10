package Exercicio2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ProdutoController {
    private final ProdutoService service;

    public ProdutoController(ProdutoService service) { this.service = service; }

    @PostMapping("/produtos")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto criar(@RequestBody LojaService.ProdutoRequest request) {
        return service.criar(request);
    }

    @GetMapping("/produtos")
    public Object listar(@RequestParam(required = false) Integer categoriaId,
                         @RequestParam(required = false) Integer page,
                         @RequestParam(required = false) Integer size) {
        if (page == null && size == null) {
            return service.listar(categoriaId);
        }
        if (page == null || size == null) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.BAD_REQUEST,
                    "page e size devem ser informados juntos");
        }
        return service.listarPaginado(categoriaId, page, size);
    }
}