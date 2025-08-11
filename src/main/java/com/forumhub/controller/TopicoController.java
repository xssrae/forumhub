package com.forumhub.controller;

import com.forumhub.dto.TopicoCreateDto;
import com.forumhub.dto.TopicoResponseDto;
import com.forumhub.dto.TopicoUpdateDto;
import com.forumhub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<TopicoResponseDto> criarTopico(
            @RequestBody @Valid TopicoCreateDto dto,
            UriComponentsBuilder uriBuilder) {

        TopicoResponseDto topico = topicoService.criarTopico(dto);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.id()).toUri();

        return ResponseEntity.created(uri).body(topico);
    }

    @GetMapping
    public ResponseEntity<Page<TopicoResponseDto>> listarTopicos(
            @PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable pageable) {

        Page<TopicoResponseDto> topicos = topicoService.listarTopicos(pageable);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponseDto> detalharTopico(@PathVariable Long id) {
        TopicoResponseDto topico = topicoService.buscarTopicoPorId(id);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponseDto> atualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid TopicoUpdateDto dto) {

        TopicoResponseDto topico = topicoService.atualizarTopico(id, dto);
        return ResponseEntity.ok(topico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTopico(@PathVariable Long id) {
        topicoService.deletarTopico(id);
        return ResponseEntity.noContent().build();
    }
}

