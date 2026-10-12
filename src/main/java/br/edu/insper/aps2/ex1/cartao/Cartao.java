package br.edu.insper.aps2.ex1.cartao;

import br.edu.insper.aps2.ex1.jogador.Jogador;
import br.edu.insper.aps2.ex1.partida.Partida;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;

@Entity
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    private Jogador jogador;
    private int minuto;
    private String tipo;
    @ManyToOne
    private Partida partida;
    protected Cartao() {}
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
    @JsonIgnore
    public Partida getPartida() { return partida; }
    public void setPartida(Partida partida) { this.partida = partida; }

}
