package br.edu.insper.aps2.Exercicio2.produto;

import br.edu.insper.aps2.Exercicio2.categoria.Categoria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Produto {
    @Id
    private int codigo;
    private String nome;
    private double preco;
    @ManyToOne
    private Categoria categoria;

    protected Produto() {}

    public Produto(int codigo, String nome, double preco, Categoria categoria) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @JsonIgnore
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void aplicarDesconto(double percentual) {
        this.preco -= this.preco * (percentual / 100.0);
    }

    public void aumentarPreco(double percentual) {
        this.preco += this.preco * (percentual / 100.0);
    }
}