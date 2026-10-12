package br.edu.insper.aps2.ex1.time;

import br.edu.insper.aps2.ex1.jogador.Jogador;

import java.util.ArrayList;

public class Time {
    private String id;
    private String nome;
    private String cidade;
    private ArrayList<Jogador> jogadores = new ArrayList<>();

    public Time(String nome, String cidade) {
        this.nome = nome;
        this.cidade = cidade;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public ArrayList<Jogador> getJogadores() { return jogadores; }

    public void adicionarJogador(Jogador jogador) {
        jogadores.add(jogador);
    }
}