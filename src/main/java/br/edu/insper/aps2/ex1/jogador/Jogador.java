package br.edu.insper.aps2.ex1.jogador;

public class Jogador {

    private String id;
        private String nome;
        private int numero;
        private String posicao;
        private int idade;

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
    
}
