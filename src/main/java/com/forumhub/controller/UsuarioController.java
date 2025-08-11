package com.forumhub.controller;

import com.forumhub.dto.CadastroUsuarioDto;
import com.forumhub.dto.UsuarioResponseDto;
import com.forumhub.model.Usuario;
import com.forumhub.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PutMapping
    public ResponseEntity<UsuarioResponseDto> cadastrarUsuario(
            @RequestBody @Valid CadastroUsuarioDto dados,
            UriComponentsBuilder uriBuilder) {

        Usuario usuario = usuarioService.cadastrarUsuario(dados);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new UsuarioResponseDto(usuario));
    }
}