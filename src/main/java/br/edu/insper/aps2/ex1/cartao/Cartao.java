package br.edu.insper.aps2.ex1.cartao;

import br.edu.insper.aps2.ex1.jogador.Jogador;
import br.edu.insper.aps2.ex1.partida.Partida;

public class Cartao {
    private Jogador jogador;
    private int minuto;
    private String tipo;
    private Partida partida;
    public Cartao(Jogador jogador, int minuto, String tipo) {
        this.jogador = jogador;
        this.minuto = minuto;
        if ("AMARELO".equalsIgnoreCase(tipo)
                || "VERMELHO".equalsIgnoreCase(tipo)) {
            this.tipo = tipo.toUpperCase();
        }
        else {
            throw new IllegalArgumentException(
                    "O cartão deve ser AMARELO ou VERMELHO"
            );
        }
    }

    public Jogador getJogador() { return jogador; }
    public int getMinuto() { return minuto; }
    public String getTipo() { return tipo; }
    public Partida getPartida() { return partida; }
    public void setPartida(Partida partida) { this.partida = partida; }

}
