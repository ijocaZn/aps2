package br.edu.insper.aps2.ex1;

import Exercicio1.Campeonato;
import Exercicio1.Cartao;
import Exercicio1.Jogador;
import Exercicio1.Partida;
import Exercicio1.Time;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class CampeonatoService {
    private final Map<String, Jogador> jogadores = new HashMap<>();
    private final Map<String, Time> times = new HashMap<>();
    private final Map<String, Partida> partidas = new HashMap<>();
    private final Map<String, Campeonato> campeonatos = new HashMap<>();

    public Jogador criarJogador(JogadorRequest request) {
        Jogador jogador = new Jogador(request.nome(), request.numero(), request.posicao(), request.idade());
        jogador.setId(UUID.randomUUID().toString());
        jogadores.put(jogador.getId(), jogador);
        return jogador;
    }

    public ArrayList<Jogador> listarJogadores() { return new ArrayList<>(jogadores.values()); }
    public Jogador buscarJogador(String id) { return buscar(jogadores, id, "Jogador"); }

    public Time criarTime(TimeRequest request) {
        Time time = new Time(request.nome(), request.cidade());
        String id = UUID.randomUUID().toString();
        time.setId(id);
        times.put(id, time);
        return time;
    }

    public ArrayList<Time> listarTimes() { return new ArrayList<>(times.values()); }
    public Time buscarTime(String id) { return buscar(times, id, "Time"); }

    public Time adicionarJogador(String timeId, String jogadorId) {
        Time time = buscarTime(timeId);
        Jogador jogador = buscarJogador(jogadorId);
        time.adicionarJogador(jogador);
        return time;
    }

    public Campeonato criarCampeonato(CampeonatoRequest request) {
        Campeonato campeonato = new Campeonato(request.nome(), request.ano());
        campeonato.setId(UUID.randomUUID().toString());
        campeonatos.put(campeonato.getId(), campeonato);
        return campeonato;
    }

    public Campeonato buscarCampeonato(String id) { return buscar(campeonatos, id, "Campeonato"); }

    public Partida criarPartida(PartidaRequest request) {
        Time mandante = buscarTime(request.idTimeMandante());
        Time visitante = buscarTime(request.idTimeVisitante());
        if (mandante == visitante) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mandante e visitante devem ser times diferentes");
        }
        Campeonato campeonato = buscarCampeonato(request.idCampeonato());
        Partida partida = new Partida();
        partida.setData(request.data() == null ? LocalDate.now() : request.data());
        partida.registrarTime(mandante, visitante);
        partida.setCampeonato(campeonato);
        String id = UUID.randomUUID().toString();
        partidas.put(id, partida);
        campeonato.addPartida(partida);
        return partida;
    }

    public Partida buscarPartida(String id) { return buscar(partidas, id, "Partida"); }

    public Partida atualizarPlacar(String id, PlacarRequest request) {
        if (request.golMandante() < 0 || request.golVisitante() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os gols não podem ser negativos");
        }
        Partida partida = buscarPartida(id);
        partida.registrarGol(request.golMandante(), request.golVisitante());
        return partida;
    }

    public Partida registrarCartao(String id, CartaoRequest request) {
        Partida partida = buscarPartida(id);
        Jogador jogador = buscarJogador(request.idJogador());
        if (request.minuto() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O minuto não pode ser negativo");
        }
        partida.registrarCartao(new Cartao(jogador, request.minuto(), request.tipo()));
        return partida;
    }

    public ArrayList<ClassificationEntry> classificacao(String id) {
        Campeonato campeonato = buscarCampeonato(id);
        ArrayList<ClassificationEntry> response = new ArrayList<>();
        for (Map.Entry<Time, Integer> entry : campeonato.classificacao()) {
            response.add(new ClassificationEntry(entry.getKey(), entry.getValue()));
        }
        return response;
    }

    private <T> T buscar(Map<String, T> mapa, String id, String nome) {
        T value = mapa.get(id);
        if (value == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, nome + " não encontrado");
        }
        return value;
    }

    public record JogadorRequest(String nome, int numero, String posicao, int idade) {}
    public record TimeRequest(String nome, String cidade) {}
    public record CampeonatoRequest(String nome, int ano) {}
    public record PartidaRequest(LocalDate data, String idTimeMandante, String idTimeVisitante, String idCampeonato) {}
    public record PlacarRequest(int golMandante, int golVisitante) {}
    public record CartaoRequest(String idJogador, int minuto, String tipo) {}
    public record ClassificationEntry(Time time, int pontos) {}
}
