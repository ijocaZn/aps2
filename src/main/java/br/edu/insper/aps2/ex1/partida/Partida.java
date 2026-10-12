package br.edu.insper.aps2.ex1.partida;

import br.edu.insper.aps2.ex1.campeonato.Campeonato;
import br.edu.insper.aps2.ex1.cartao.Cartao;
import br.edu.insper.aps2.ex1.time.Time;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.ArrayList;

public class Partida {

        private LocalDate data;
        private Time timeMandante;
        private Time timeVisitante;
        private int golMandante;
        private int golVisitante;
        private boolean placarRegistrado;
        private ArrayList<Cartao> cartoes;
        private Campeonato campeonato;

        public Partida() {
            this.data = LocalDate.now();
            this.cartoes = new ArrayList<>();
        }

        public Time  getTimeMandante() {
            return timeMandante;
        }
        public Time getTimeVisitante() {
            return timeVisitante;
        }

        public LocalDate getData() { return data; }
        public int getGolMandante() { return golMandante; }
        public int getGolVisitante() { return golVisitante; }
        public ArrayList<Cartao> getCartoes() { return cartoes; }
        @JsonIgnore
        public Campeonato getCampeonato() { return campeonato; }
        public void setData(LocalDate data) { this.data = data; }
        public void setCampeonato(Campeonato campeonato) { this.campeonato = campeonato; }

        public void registrarTime(Time timeMandante, Time timeVisitante){
            this.timeMandante = timeMandante;
            this.timeVisitante = timeVisitante;
        }
        public void registrarGol(int golMandante, int golVisitante){
            this.golMandante = golMandante;
            this.golVisitante = golVisitante;
            this.placarRegistrado = true;
        }

        public boolean isPlacarRegistrado() { return placarRegistrado; }
        public void registrarCartao(Cartao cartao){
            cartoes.add(cartao);
        }
        public Time buscarVencedor(){
            if (golMandante - golVisitante > 0){
                return timeMandante;
            }
            if (golVisitante - golMandante > 0){
                return timeVisitante;
            }
            else{
                return null;
            }
        }
        public String exibirPlacar() {
            return String.format(
                "%s %d x %d %s",
                timeMandante.getNome(),
                golMandante,
                golVisitante,
                timeVisitante.getNome()
                );
    }

}
