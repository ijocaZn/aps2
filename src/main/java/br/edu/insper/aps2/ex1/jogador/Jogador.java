package br.edu.insper.aps2.ex1.jogador;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import br.edu.insper.aps2.ex1.time.Time;

@Entity
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
        private String nome;
        private int numero;
        private String posicao;
        @ManyToOne
        private Time time;
        private int idade;

        protected Jogador() {}

    public Jogador(String nome, int numero, String posicao,int idade) {
        this.nome = nome;
        this.numero = numero;
        this.posicao= posicao;
        this.idade = idade;
    }

        public String getId() {return id;}
        public void setId(String id) {this.id = id;}

        public String getNome() {return nome;}
        public void setNome(String nome) {this.nome = nome;}
        public int getNumero() {return numero;}
        public void setNumero(int numero) {this.numero = numero;}
        public String getPosicao() {return posicao;}
        public void setPosicao(String posicao) {this.posicao = posicao;}
        public int getIdade() {return idade;}
        public void setIdade(int idade) {this.idade = idade;}
        @JsonIgnore
        public Time getTime() { return time; }
        public void setTime(Time time) { this.time = time; }
    
}
