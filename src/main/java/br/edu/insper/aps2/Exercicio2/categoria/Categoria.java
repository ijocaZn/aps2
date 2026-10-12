package br.edu.insper.aps2.Exercicio2.categoria;

import br.edu.insper.aps2.Exercicio2.produto.Produto;
import java.util.ArrayList;

public class Categoria {
    private int codigo;
    private String nome;
    private ArrayList<Produto> produtos;

    public Categoria(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.produtos = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public ArrayList<Produto> listarProdutos() {
        return produtos;
    }

    public double calcularPrecoMedio() {
        if (produtos.isEmpty()) {
            return 0.0;
        }
        double soma = 0.0;
        for (Produto p : produtos) {
            soma += p.getPreco();
        }
        return soma / produtos.size();
    }

    public Produto buscarProdutoMaisCaro() {
        if (produtos.isEmpty()) {
            return null;
        }
        Produto maisCaro = produtos.get(0);
        for (Produto p : produtos) {
            if (p.getPreco() > maisCaro.getPreco()) {
                maisCaro = p;
            }
        }
        return maisCaro;
    }
}