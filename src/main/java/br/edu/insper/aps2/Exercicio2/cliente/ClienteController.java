package Exercicio2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ClienteController {
    private final ClienteService service;

    public ClienteController(ClienteService service) { this.service = service; }

    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente criar(@RequestBody LojaService.ClienteRequest request) {
        return service.criar(request);
    }

    @GetMapping("/clientes")
    public ArrayList<Cliente> listar() { return service.listar(); }

    @GetMapping("/clientes/{cpf}/pedidos")
    public ArrayList<Pedido> listarPedidos(@PathVariable String cpf) {
        return service.listarPedidos(cpf);
    }
}