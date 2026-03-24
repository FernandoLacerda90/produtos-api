package io.github.FernandoLacerda90.produtosapi.repository;

import io.github.FernandoLacerda90.produtosapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, String> {

    List<Produto> findByNome(String nome);
}
