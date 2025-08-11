package com.forumhub.dto;

import com.forumhub.model.enums.StatusTopico;
import java.time.LocalDateTime;

public record TopicoResponseDto(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        StatusTopico status,
        String nomeAutor,
        String nomeCurso,
        Integer totalRespostas
) {
    public TopicoResponseDto {
        // Constructor validation - only validate the parameters, don't call external services
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título não pode ser nulo ou vazio");
        }
        if (mensagem == null || mensagem.isBlank()) {
            throw new IllegalArgumentException("Mensagem não pode ser nula ou vazia");
        }
        if (nomeAutor == null || nomeAutor.isBlank()) {
            throw new IllegalArgumentException("Nome do autor não pode ser nulo ou vazio");
        }
        if (nomeCurso == null || nomeCurso.isBlank()) {
            throw new IllegalArgumentException("Nome do curso não pode ser nulo ou vazio");
        }
    }
}