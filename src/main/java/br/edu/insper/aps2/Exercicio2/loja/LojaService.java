package br.edu.insper.aps2.Exercicio2.loja;

import br.edu.insper.aps2.Exercicio2.categoria.Categoria;
import br.edu.insper.aps2.Exercicio2.categoria.CategoriaRepository;
import br.edu.insper.aps2.Exercicio2.cliente.Cliente;
import br.edu.insper.aps2.Exercicio2.cliente.ClienteRepository;
import br.edu.insper.aps2.Exercicio2.pedido.Pedido;
import br.edu.insper.aps2.Exercicio2.pedido.PedidoRepository;
import br.edu.insper.aps2.Exercicio2.produto.Produto;
import br.edu.insper.aps2.Exercicio2.produto.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.regex.Pattern;

@Service
public class LojaService {
    private static final Pattern CPF_PATTERN = Pattern.compile("\\d{11}");
    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;

    public LojaService(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository,
                       ClienteRepository clienteRepository, PedidoRepository pedidoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public Categoria criarCategoria(CategoriaRequest request) {
        if (categoriaRepository.existsById(request.codigo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Categoria já cadastrada");
        }
        Categoria categoria = new Categoria(request.codigo(), request.nome());
        return categoriaRepository.save(categoria);
    }

    public ArrayList<Categoria> listarCategorias() { return new ArrayList<>(categoriaRepository.findAll()); }
    public Categoria buscarCategoria(int codigo) { return buscar(categoriaRepository.findById(codigo), "Categoria"); }

    public Produto criarProduto(ProdutoRequest request) {
        if (produtoRepository.existsById(request.codigo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Produto já cadastrado");
        }
        Categoria categoria = buscarCategoria(request.codigoCategoria());
        Produto produto = new Produto(request.codigo(), request.nome(), request.preco(), categoria);
        categoria.adicionarProduto(produto);
        return produtoRepository.save(produto);
    }

    public ArrayList<Produto> listarProdutos(Integer categoriaId) {
        if (categoriaId == null) return new ArrayList<>(produtoRepository.findAll());
        buscarCategoria(categoriaId);
        return new ArrayList<>(produtoRepository.findByCategoriaCodigo(categoriaId));
    }

    public Produto buscarProduto(int codigo) { return buscar(produtoRepository.findById(codigo), "Produto"); }

    public Cliente criarCliente(ClienteRequest request) {
        String cpf = normalizarCpf(request.cpf());
        if (cpf == null || !CPF_PATTERN.matcher(cpf).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF inválido");
        }
        if (clienteRepository.existsById(cpf)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cliente já cadastrado");
        }
        Cliente cliente = new Cliente(request.nome(), cpf, request.email());
        return clienteRepository.save(cliente);
    }

    public ArrayList<Cliente> listarClientes() { return new ArrayList<>(clienteRepository.findAll()); }
    public Cliente buscarCliente(String cpf) { return buscar(clienteRepository.findById(normalizarCpf(cpf)), "Cliente"); }

    public ArrayList<Pedido> listarPedidosDoCliente(String cpf) {
        buscarCliente(cpf);
        return new ArrayList<>(pedidoRepository.findByClienteCpf(normalizarCpf(cpf)));
    }

    public Pedido criarPedido(PedidoRequest request) {
        if (pedidoRepository.existsById(request.numero())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Pedido já cadastrado");
        }
        Cliente cliente = buscarCliente(request.cpfCliente());
        Pedido pedido = new Pedido(request.numero(), cliente);
        cliente.adicionarPedido(pedido);
        return pedidoRepository.save(pedido);
    }

    public ArrayList<Pedido> listarPedidos() { return new ArrayList<>(pedidoRepository.findAll()); }
    public Pedido buscarPedido(int numero) { return buscar(pedidoRepository.findById(numero), "Pedido"); }

    public Pedido adicionarItem(int numero, ItemRequest request) {
        if (request.quantidade() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A quantidade deve ser positiva");
        }
        Pedido pedido = buscarPedido(numero);
        if (!"ABERTO".equals(pedido.getStatus())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Só é possível adicionar itens a pedidos ABERTOS");
        }
        pedido.adicionarProduto(buscarProduto(request.codigoProduto()), request.quantidade());
        return pedidoRepository.save(pedido);
    }

    public Pedido alterarStatus(int numero, StatusRequest request) {
        Pedido pedido = buscarPedido(numero);
        if (request.status() == null ||
            !(request.status().equals("ABERTO") || request.status().equals("PAGO")
                || request.status().equals("CANCELADO"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status deve ser ABERTO, PAGO ou CANCELADO");
        }
        pedido.alterarStatus(request.status());
        return pedidoRepository.save(pedido);
    }

    private <T> T buscar(java.util.Optional<T> value, String nome) {
        return value.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, nome + " não encontrado"));
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
