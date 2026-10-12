package br.edu.insper.aps2.Exercicio2.cliente;

import br.edu.insper.aps2.Exercicio2.pedido.Pedido;
import java.util.ArrayList;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

@Entity
public class Cliente {
    private String nome;
    @Id
    private String cpf;
    private String email;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private ArrayList<Pedido> pedidos;

    protected Cliente() {}

    public Cliente(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.pedidos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
        pedido.setCliente(this);
    }

    public ArrayList<Pedido> listarPedidos() {
        return pedidos;
    }

    public double calcularTotalGasto() {
        double total = 0.0;
        for (Pedido p : pedidos) {
            total += p.calcularTotal();
        }
        return total;
    }

    public Pedido buscarPedidoMaisCaro() {
        if (pedidos.isEmpty()) {
            return null;
        }
        Pedido maisCaro = pedidos.get(0);
        for (Pedido p : pedidos) {
            if (p.calcularTotal() > maisCaro.calcularTotal()) {
                maisCaro = p;
            }
        }
        return maisCaro;
    }
}
