package Exercicio2;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class Pedido {
    private int numero;
    private Cliente cliente;
    private ArrayList<ItemPedido> itens;
    private String status;

    public Pedido(int numero, Cliente cliente) {
        this.numero = numero;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.status = "ABERTO";
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @JsonIgnore
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public String getStatus() {
        return status;
    }

    public double getTotal() {
        return calcularTotal();
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        itens.add(new ItemPedido(produto, quantidade));
    }

    public void removerProduto(Produto produto) {
        itens.removeIf(item -> item.getProduto().getCodigo() == produto.getCodigo());
    }

    public double calcularTotal() {
        double total = 0.0;
        for (ItemPedido item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    public int calcularQuantidadeItens() {
        int totalItens = 0;
        for (ItemPedido item : itens) {
            totalItens += item.getQuantidade();
        }
        return totalItens;
    }

    public ItemPedido buscarItemMaisCaro() {
        if (itens.isEmpty()) {
            return null;
        }
        ItemPedido maisCaro = itens.get(0);
        for (ItemPedido item : itens) {
            if (item.getPrecoUnitario() > maisCaro.getPrecoUnitario()) {
                maisCaro = item;
            }
        }
        return maisCaro;
    }

    public void alterarStatus(String status) {
        this.status = status;
    }
}