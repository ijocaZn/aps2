package br.edu.insper.aps2.ex1.campeonato;

import br.edu.insper.aps2.ex1.cartao.Cartao;
import br.edu.insper.aps2.ex1.jogador.Jogador;
import br.edu.insper.aps2.ex1.jogador.JogadorRepository;
import br.edu.insper.aps2.ex1.partida.Partida;
import br.edu.insper.aps2.ex1.partida.PartidaRepository;
import br.edu.insper.aps2.ex1.time.Time;
import br.edu.insper.aps2.ex1.time.TimeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

@Service
public class CampeonatoService {
    private final JogadorRepository jogadorRepository;
    private final TimeRepository timeRepository;
    private final PartidaRepository partidaRepository;
    private final CampeonatoRepository campeonatoRepository;

    public CampeonatoService(JogadorRepository jogadorRepository, TimeRepository timeRepository,
                             PartidaRepository partidaRepository, CampeonatoRepository campeonatoRepository) {
        this.jogadorRepository = jogadorRepository;
        this.timeRepository = timeRepository;
        this.partidaRepository = partidaRepository;
        this.campeonatoRepository = campeonatoRepository;
    }

    public Jogador criarJogador(JogadorRequest request) {
        Jogador jogador = new Jogador(request.nome(), request.numero(), request.posicao(), request.idade());
        return jogadorRepository.save(jogador);
    }

    public ArrayList<Jogador> listarJogadores() { return new ArrayList<>(jogadorRepository.findAll()); }
    public Jogador buscarJogador(String id) { return buscar(jogadorRepository.findById(id), "Jogador"); }

    public Time criarTime(TimeRequest request) {
        Time time = new Time(request.nome(), request.cidade());
        return timeRepository.save(time);
    }

    public ArrayList<Time> listarTimes() { return new ArrayList<>(timeRepository.findAll()); }
    public Time buscarTime(String id) { return buscar(timeRepository.findById(id), "Time"); }

    public Time adicionarJogador(String timeId, String jogadorId) {
        Time time = buscarTime(timeId);
        Jogador jogador = buscarJogador(jogadorId);
        time.adicionarJogador(jogador);
        jogadorRepository.save(jogador);
        return time;
    }

    public Campeonato criarCampeonato(CampeonatoRequest request) {
        Campeonato campeonato = new Campeonato(request.nome(), request.ano());
        return campeonatoRepository.save(campeonato);
    }

    public Campeonato buscarCampeonato(String id) { return buscar(campeonatoRepository.findById(id), "Campeonato"); }

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
        partidaRepository.save(partida);
        campeonato.addPartida(partida);
        campeonatoRepository.save(campeonato);
        return partida;
    }

    public Partida buscarPartida(String id) { return buscar(partidaRepository.findById(id), "Partida"); }

    public Partida atualizarPlacar(String id, PlacarRequest request) {
        if (request.golMandante() < 0 || request.golVisitante() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os gols não podem ser negativos");
        }
        Partida partida = buscarPartida(id);
        partida.registrarGol(request.golMandante(), request.golVisitante());
        return partidaRepository.save(partida);
    }

    public Partida registrarCartao(String id, CartaoRequest request) {
        Partida partida = buscarPartida(id);
        Jogador jogador = buscarJogador(request.idJogador());
        if (request.minuto() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O minuto não pode ser negativo");
        }
        partida.registrarCartao(new Cartao(jogador, request.minuto(), request.tipo()));
        return partidaRepository.save(partida);
    }

    public ArrayList<ClassificationEntry> classificacao(String id) {
        Campeonato campeonato = buscarCampeonato(id);
        ArrayList<ClassificationEntry> response = new ArrayList<>();
        for (Map.Entry<Time, Integer> entry : campeonato.classificacao()) {
            response.add(new ClassificationEntry(entry.getKey(), entry.getValue()));
        }
        return response;
    }

    private <T> T buscar(java.util.Optional<T> value, String nome) {
        return value.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, nome + " não encontrado"));
    }

    public record JogadorRequest(String nome, int numero, String posicao, int idade) {}
    public record TimeRequest(String nome, String cidade) {}
    public record CampeonatoRequest(String nome, int ano) {}
    public record PartidaRequest(LocalDate data, String idTimeMandante, String idTimeVisitante, String idCampeonato) {}
    public record PlacarRequest(int golMandante, int golVisitante) {}
    public record CartaoRequest(String idJogador, int minuto, String tipo) {}
    public record ClassificationEntry(Time time, int pontos) {}
}
