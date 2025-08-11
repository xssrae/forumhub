package com.forumhub.repository;

import com.forumhub.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findAllByOrderByDataCriacaoDesc(Pageable pageable);
    boolean existsByTituloAndMensagem(String titulo, String mensagem);
}