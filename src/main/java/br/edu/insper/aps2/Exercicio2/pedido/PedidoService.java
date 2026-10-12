package br.edu.insper.aps2.Exercicio2.pedido;

import br.edu.insper.aps2.Exercicio2.loja.LojaService;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    private final LojaService lojaService;

    public PedidoService(LojaService lojaService) { this.lojaService = lojaService; }

    public Pedido criar(LojaService.PedidoRequest request) { return lojaService.criarPedido(request); }
    public Pedido adicionarItem(int id, LojaService.ItemRequest request) { return lojaService.adicionarItem(id, request); }
    public Pedido alterarStatus(int id, LojaService.StatusRequest request) { return lojaService.alterarStatus(id, request); }
}
