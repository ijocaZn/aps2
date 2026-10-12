package br.edu.insper.aps2.ex1.partida;

import br.edu.insper.aps2.ex1.campeonato.Campeonato;
import br.edu.insper.aps2.ex1.cartao.Cartao;
import br.edu.insper.aps2.ex1.time.Time;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.ArrayList;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

@Entity
public class Partida {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private String id;
        private LocalDate data;
        @ManyToOne
        private Time timeMandante;
        @ManyToOne
        private Time timeVisitante;
        private int golMandante;
        private int golVisitante;
        private boolean placarRegistrado;
        @OneToMany(mappedBy = "partida", cascade = CascadeType.ALL)
        private ArrayList<Cartao> cartoes;
        @ManyToOne
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
            cartao.setPartida(this);
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
