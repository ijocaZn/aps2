package br.edu.insper.aps2.Exercicio2.produto;

import br.edu.insper.aps2.Exercicio2.loja.LojaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProdutoService {
    private final LojaService lojaService;

    public ProdutoService(LojaService lojaService) { this.lojaService = lojaService; }

    public Produto criar(LojaService.ProdutoRequest request) { return lojaService.criarProduto(request); }
    public ArrayList<Produto> listar(Integer categoriaId) { return lojaService.listarProdutos(categoriaId); }

    public ProdutoPage listarPaginado(Integer categoriaId, int pagina, int tamanho) {
        if (pagina < 0 || tamanho <= 0) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.BAD_REQUEST,
                    "Página deve ser >= 0 e tamanho deve ser positivo");
        }
        ArrayList<Produto> produtos = listar(categoriaId);
        int inicio = Math.min(pagina * tamanho, produtos.size());
        int fim = Math.min(inicio + tamanho, produtos.size());
        return new ProdutoPage(produtos.subList(inicio, fim), pagina, tamanho, produtos.size());
    }

    public record ProdutoPage(java.util.List<Produto> produtos, int pagina, int tamanho, int total) {}
}
