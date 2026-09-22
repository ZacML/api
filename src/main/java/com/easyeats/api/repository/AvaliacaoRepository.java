package com.easyeats.api.repository;

import com.easyeats.api.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    List<Avaliacao> findByProdutoId(Long idProduto);

    List<Avaliacao> findByUsuarioId(Long idUsuario);

    Optional<Avaliacao> findByUsuarioIdAndProdutoId(Long idUsuario, Long idProduto);

    boolean existsByUsuarioIdAndProdutoId(Long idUsuario, Long idProduto);
}
