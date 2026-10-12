package br.edu.insper.aps2.Exercicio2.cliente;

import br.edu.insper.aps2.Exercicio2.loja.LojaService;
import br.edu.insper.aps2.Exercicio2.pedido.Pedido;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClienteService {
    private final LojaService lojaService;

    public ClienteService(LojaService lojaService) { this.lojaService = lojaService; }

    public Cliente criar(LojaService.ClienteRequest request) { return lojaService.criarCliente(request); }
    public ArrayList<Cliente> listar() { return lojaService.listarClientes(); }
    public ArrayList<Pedido> listarPedidos(String cpf) { return lojaService.listarPedidosDoCliente(cpf); }
}
