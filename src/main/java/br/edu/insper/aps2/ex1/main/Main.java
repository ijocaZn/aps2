package br.edu.insper.aps2.ex1.main;

import br.edu.insper.aps2.ex1.campeonato.Campeonato;
import br.edu.insper.aps2.ex1.cartao.Cartao;
import br.edu.insper.aps2.ex1.jogador.Jogador;
import br.edu.insper.aps2.ex1.partida.Partida;
import br.edu.insper.aps2.ex1.time.Time;

public class Main {
    public static void main(String[] args){
        Campeonato brasileirao = new Campeonato("Brasileirão", 2026);

        Time corinthians = new Time("Corinthians", "São Paulo");
        Time flamengo = new Time("Flamengo", "Rio de Janeiro");
        Time palmeiras = new Time("Palmeiras", "São Paulo");
        Time saoPaulo = new Time("São Paulo", "São Paulo");
        Time gremio = new Time("Grêmio", "Porto Alegre");

        brasileirao.addTime(corinthians);
        brasileirao.addTime(flamengo);
        brasileirao.addTime(palmeiras);
        brasileirao.addTime(saoPaulo);
        brasileirao.addTime(gremio);

        Jogador cor1 = new Jogador("Memphis Depay", 94, "Atacante", 30);
        Jogador cor2 = new Jogador("Rodrigo Garro", 10, "Meia", 26);
        Jogador cor3 = new Jogador("Hugo Souza", 1, "Goleiro", 25);
        corinthians.adicionarJogador(cor1);
        corinthians.adicionarJogador(cor2);
        corinthians.adicionarJogador(cor3);

        Jogador fla1 = new Jogador("Pedro", 9, "Atacante", 26);
        Jogador fla2 = new Jogador("Arrascaeta", 14, "Meia", 29);
        Jogador fla3 = new Jogador("Léo Ortiz", 3, "Zagueiro", 28);
        flamengo.adicionarJogador(fla1);
        flamengo.adicionarJogador(fla2);
        flamengo.adicionarJogador(fla3);

        Jogador pal1 = new Jogador("Raphael Veiga", 23, "Meia", 28);
        Jogador pal2 = new Jogador("Gustavo Gómez", 15, "Zagueiro", 31);
        Jogador pal3 = new Jogador("Flaco López", 42, "Atacante", 23);
        palmeiras.adicionarJogador(pal1);
        palmeiras.adicionarJogador(pal2);
        palmeiras.adicionarJogador(pal3);

        Jogador sp1 = new Jogador("Calleri", 9, "Atacante", 30);
        Jogador sp2 = new Jogador("Lucas Moura", 7, "Meia", 31);
        Jogador sp3 = new Jogador("Arboleda", 5, "Zagueiro", 32);
        saoPaulo.adicionarJogador(sp1);
        saoPaulo.adicionarJogador(sp2);
        saoPaulo.adicionarJogador(sp3);

        Jogador gre1 = new Jogador("Diego Costa", 19, "Atacante", 35);
        Jogador gre2 = new Jogador("Cristaldo", 10, "Meia", 27);
        Jogador gre3 = new Jogador("Kannemann", 4, "Zagueiro", 33);
        gremio.adicionarJogador(gre1);
        gremio.adicionarJogador(gre2);
        gremio.adicionarJogador(gre3);

        Partida p1 = new Partida();
        p1.registrarTime(corinthians, palmeiras);
        p1.registrarGol(2, 0);
        p1.registrarCartao(new Cartao(cor2, 30, "AMARELO"));
        p1.registrarCartao(new Cartao(pal2, 85, "VERMELHO"));
        brasileirao.addPartida(p1);

        Partida p2 = new Partida();
        p2.registrarTime(flamengo, saoPaulo);
        p2.registrarGol(2, 1);
        p2.registrarCartao(new Cartao(fla3, 40, "AMARELO"));
        brasileirao.addPartida(p2);

        Partida p3 = new Partida();
        p3.registrarTime(corinthians, flamengo);
        p3.registrarGol(3, 1);
        p3.registrarCartao(new Cartao(cor1, 20, "AMARELO"));
        p3.registrarCartao(new Cartao(fla1, 55, "AMARELO"));
        brasileirao.addPartida(p3);

        Partida p4 = new Partida();
        p4.registrarTime(palmeiras, gremio);
        p4.registrarGol(1, 1);
        p4.registrarCartao(new Cartao(gre3, 62, "AMARELO"));
        brasileirao.addPartida(p4);

        Partida p5 = new Partida();
        p5.registrarTime(gremio, corinthians);
        p5.registrarGol(0, 2);
        p5.registrarCartao(new Cartao(cor3, 75, "AMARELO"));
        brasileirao.addPartida(p5);

        Partida p6 = new Partida();
        p6.registrarTime(saoPaulo, corinthians);
        p6.registrarGol(1, 2);
        p6.registrarCartao(new Cartao(sp2, 15, "AMARELO"));
        p6.registrarCartao(new Cartao(cor2, 80, "AMARELO"));
        brasileirao.addPartida(p6);

        System.out.println("=== RESULTADOS DAS PARTIDAS ===");
        for (Partida partida : brasileirao.listarPartidas()) {
            System.out.println(partida.exibirPlacar());
        }
        System.out.println();

        System.out.println("=== CLASSIFICAÇÃO DO CAMPEONATO ===");
        brasileirao.exibirClassificacao();
    }
}