package Exercicio2;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class LojaService {
    private static final Pattern CPF_PATTERN = Pattern.compile("\\d{11}");
    private final Map<Integer, Categoria> categorias = new HashMap<>();
    private final Map<Integer, Produto> produtos = new HashMap<>();
    private final Map<String, Cliente> clientes = new HashMap<>();
    private final Map<Integer, Pedido> pedidos = new HashMap<>();

    public Categoria criarCategoria(CategoriaRequest request) {
        if (categorias.containsKey(request.codigo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Categoria já cadastrada");
        }
        Categoria categoria = new Categoria(request.codigo(), request.nome());
        categorias.put(request.codigo(), categoria);
        return categoria;
    }

    public ArrayList<Categoria> listarCategorias() { return new ArrayList<>(categorias.values()); }
    public Categoria buscarCategoria(int codigo) { return buscar(categorias, codigo, "Categoria"); }

    public Produto criarProduto(ProdutoRequest request) {
        if (produtos.containsKey(request.codigo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Produto já cadastrado");
        }
        Categoria categoria = buscarCategoria(request.codigoCategoria());
        Produto produto = new Produto(request.codigo(), request.nome(), request.preco(), categoria);
        produtos.put(request.codigo(), produto);
        categoria.adicionarProduto(produto);
        return produto;
    }

    public ArrayList<Produto> listarProdutos(Integer categoriaId) {
        if (categoriaId == null) return new ArrayList<>(produtos.values());
        return buscarCategoria(categoriaId).listarProdutos();
    }

    public Produto buscarProduto(int codigo) { return buscar(produtos, codigo, "Produto"); }

    public Cliente criarCliente(ClienteRequest request) {
        String cpf = normalizarCpf(request.cpf());
        if (cpf == null || !CPF_PATTERN.matcher(cpf).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF inválido");
        }
        if (clientes.containsKey(cpf)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cliente já cadastrado");
        }
        Cliente cliente = new Cliente(request.nome(), cpf, request.email());
        clientes.put(cpf, cliente);
        return cliente;
    }

    public ArrayList<Cliente> listarClientes() { return new ArrayList<>(clientes.values()); }
    public Cliente buscarCliente(String cpf) { return buscar(clientes, normalizarCpf(cpf), "Cliente"); }

    public ArrayList<Pedido> listarPedidosDoCliente(String cpf) {
        return buscarCliente(cpf).listarPedidos();
    }

    public Pedido criarPedido(PedidoRequest request) {
        if (pedidos.containsKey(request.numero())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Pedido já cadastrado");
        }
        Cliente cliente = buscarCliente(request.cpfCliente());
        Pedido pedido = new Pedido(request.numero(), cliente);
        pedidos.put(request.numero(), pedido);
        cliente.adicionarPedido(pedido);
        return pedido;
    }

    public ArrayList<Pedido> listarPedidos() { return new ArrayList<>(pedidos.values()); }
    public Pedido buscarPedido(int numero) { return buscar(pedidos, numero, "Pedido"); }

    public Pedido adicionarItem(int numero, ItemRequest request) {
        if (request.quantidade() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A quantidade deve ser positiva");
        }
        Pedido pedido = buscarPedido(numero);
        if (!"ABERTO".equals(pedido.getStatus())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Só é possível adicionar itens a pedidos ABERTOS");
        }
        pedido.adicionarProduto(buscarProduto(request.codigoProduto()), request.quantidade());
        return pedido;
    }

    public Pedido alterarStatus(int numero, StatusRequest request) {
        Pedido pedido = buscarPedido(numero);
        if (request.status() == null ||
            !(request.status().equals("ABERTO") || request.status().equals("PAGO")
                || request.status().equals("CANCELADO"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status deve ser ABERTO, PAGO ou CANCELADO");
        }
        pedido.alterarStatus(request.status());
        return pedido;
    }

    private <K, V> V buscar(Map<K, V> mapa, K chave, String nome) {
        V value = mapa.get(chave);
        if (value == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, nome + " não encontrado");
        return value;
    }

    private String normalizarCpf(String cpf) {
        return cpf == null ? null : cpf.replaceAll("[.-]", "");
    }

    public record CategoriaRequest(int codigo, String nome) {}
    public record ProdutoRequest(int codigo, String nome, double preco, int codigoCategoria) {}
    public record ClienteRequest(String nome, String cpf, String email) {}
    public record PedidoRequest(int numero, String cpfCliente) {}
    public record ItemRequest(int codigoProduto, int quantidade) {}
    public record StatusRequest(String status) {}
}
