package com.forumhub.dto;

import com.forumhub.model.Topico;
import org.springframework.stereotype.Component;

@Component
public class TopicoMapper {

    public TopicoResponseDto toResponseDto(Topico topico) {
        return new TopicoResponseDto(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor().getNome(),
                topico.getCurso().getNome(),
                topico.getRespostas().size()
        );
    }
}