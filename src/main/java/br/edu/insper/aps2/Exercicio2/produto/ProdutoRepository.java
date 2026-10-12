package br.edu.insper.aps2.Exercicio2.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findByCategoriaCodigo(int codigoCategoria);
}
