package Exercicio2;

import java.util.ArrayList;

public class Loja {
    private String nome;
    private ArrayList<Produto> produtos;
    private ArrayList<Cliente> clientes;

    public Loja(String nome) {
        this.nome = nome;
        this.produtos = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Produto buscarProduto(int codigo) {
        for (Produto p : produtos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    public Cliente buscarCliente(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equalsIgnoreCase(cpf)) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Produto> listarProdutos() {
        return produtos;
    }

    public ArrayList<Cliente> listarClientes() {
        return clientes;
    }

    public ArrayList<Produto> listarProdutosPorCategoria(Categoria categoria) {
        ArrayList<Produto> filtrados = new ArrayList<>();
        for (Produto p : produtos) {
            if (p.getCategoria().getCodigo() == categoria.getCodigo()) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }
}
