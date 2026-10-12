package br.edu.insper.aps2.Exercicio2.pedido;

import br.edu.insper.aps2.Exercicio2.cliente.Cliente;
import br.edu.insper.aps2.Exercicio2.produto.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

@Entity
public class Pedido {
    @Id
    private int numero;
    @ManyToOne
    private Cliente cliente;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private ArrayList<ItemPedido> itens;
    private String status;

    protected Pedido() {}

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
        ItemPedido item = new ItemPedido(produto, quantidade);
        item.setPedido(this);
        itens.add(item);
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
