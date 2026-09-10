package Exercicio2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoController {
    private final PedidoService service;

    public PedidoController(PedidoService service) { this.service = service; }

    @PostMapping("/pedidos")
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido criar(@RequestBody LojaService.PedidoRequest request) {
        return service.criar(request);
    }

    @PostMapping("/pedidos/{id}/itens")
    public Pedido adicionarItem(@PathVariable int id, @RequestBody LojaService.ItemRequest request) {
        return service.adicionarItem(id, request);
    }

    @PatchMapping("/pedidos/{id}/status")
    public Pedido alterarStatus(@PathVariable int id, @RequestBody LojaService.StatusRequest request) {
        return service.alterarStatus(id, request);
    }
}