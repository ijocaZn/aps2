package br.edu.insper.aps2.Exercicio2.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByClienteCpf(String cpf);
}
