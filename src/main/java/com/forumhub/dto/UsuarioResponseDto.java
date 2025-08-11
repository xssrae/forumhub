package com.forumhub.dto;

import com.forumhub.model.Usuario;

public record UsuarioResponseDto(
        Long id,
        String nome,
        String email
) {
    public UsuarioResponseDto(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}