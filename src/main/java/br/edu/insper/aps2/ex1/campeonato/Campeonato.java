package Exercicio1;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Campeonato {

    private String id;
    private String nome;
    private int ano;

    private ArrayList<Time> times;
    private ArrayList<Partida> partidas;

    public Campeonato(String nome, int ano) {
        this.nome = nome;
        this.ano = ano;
        this.times = new ArrayList<>();
        this.partidas = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void addTime(Time time) {
        this.times.add(time);
    }

    public void addPartida(Partida partida) {
        this.partidas.add(partida);
    }

    public ArrayList<Time> listarTimes() {
        return times;
    }

    public ArrayList<Partida> listarPartidas() {
        return partidas;
    }

    public ArrayList<Map.Entry<Time, Integer>> classificacao() {
        Map<Time, Integer> pontosTimes = new HashMap<>();
        for (Time time : times) {
            int pontos = 0;
            for (Partida partida : partidas) {
                if (!partida.isPlacarRegistrado()) {
                    continue;
                }
                Time vencedor = partida.buscarVencedor();
                if (vencedor == time) {
                    pontos += 3;
                } else if (vencedor == null && partida.getGolMandante() == partida.getGolVisitante()
                        && (partida.getTimeMandante() == time || partida.getTimeVisitante() == time)) {
                    pontos++;
                }
            }
            pontosTimes.put(time, pontos);
        }
        ArrayList<Map.Entry<Time, Integer>> classificacao = new ArrayList<>(pontosTimes.entrySet());
        classificacao.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));
        return classificacao;
    }

    public Time buscarTime(String nome) {
        for (Time time : times) {
            if (time.getNome().equalsIgnoreCase(nome)) {
                return time;
            }
        }

        return null;
    }
    public void exibirClassificacao() {
        Map<Time,Integer> pontosTimes = new HashMap<>();
        for (Time time : times) {
            int pontos = 0;
            for (Partida partida : partidas) {
                Time vencedor = partida.buscarVencedor();
                if (vencedor == time){
                    pontos = pontos + 3;
                }
                if (vencedor == null && (partida.getTimeMandante() == time || partida.getTimeVisitante() == time)) {
                    pontos += 1;
                }
            }
            pontosTimes.put(time, pontos);
        }
        ArrayList<Map.Entry<Time, Integer>> classificacao =
                new ArrayList<>(pontosTimes.entrySet());

        classificacao.sort((entrada1, entrada2) ->
                Integer.compare(
                        entrada2.getValue(),
                        entrada1.getValue()
                )
        );

        int posicao = 1;

        for (Map.Entry<Time, Integer> entrada : classificacao) {
            Time time = entrada.getKey();
            int pontos = entrada.getValue();

            System.out.println(
                    posicao + "º - " +
                            time.getNome() + ": " +
                            pontos + " pontos"
            );

            posicao++;
        }

    }
}