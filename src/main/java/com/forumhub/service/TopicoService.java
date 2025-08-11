package com.forumhub.service;

import com.forumhub.dto.TopicoMapper;
import com.forumhub.dto.TopicoCreateDto;
import com.forumhub.dto.TopicoUpdateDto;
import com.forumhub.dto.TopicoResponseDto;
import com.forumhub.infra.exception.ResourceNotFoundException;
import com.forumhub.infra.exception.ValidacaoException;
import com.forumhub.model.Curso;
import com.forumhub.model.Topico;
import com.forumhub.model.Usuario;
import com.forumhub.repository.CursoRepository;
import com.forumhub.repository.TopicoRepository;
import com.forumhub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoMapper topicoMapper;

    @Transactional
    public TopicoResponseDto criarTopico(TopicoCreateDto dto) {
        validarTopicoDuplicado(dto.titulo(), dto.mensagem());

        Usuario autor = buscarUsuarioLogado();
        Curso curso = buscarCursoPorId(dto.cursoId());

        Topico topico = new Topico();
        topico.setTitulo(dto.titulo());
        topico.setMensagem(dto.mensagem());
        topico.setAutor(autor);
        topico.setCurso(curso);

        Topico savedTopico = topicoRepository.save(topico);
        return topicoMapper.toResponseDto(savedTopico);
    }

    public Page<TopicoResponseDto> listarTopicos(Pageable pageable) {
        return topicoRepository.findAllByOrderByDataCriacaoDesc(pageable)
                .map(topicoMapper::toResponseDto);
    }

    public TopicoResponseDto buscarTopicoPorId(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico não encontrado"));
        return topicoMapper.toResponseDto(topico);
    }

    @Transactional
    public TopicoResponseDto atualizarTopico(Long id, TopicoUpdateDto dto) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico não encontrado"));

        validarAutorDoTopico(topico);

        topico.setTitulo(dto.titulo());
        topico.setMensagem(dto.mensagem());

        Topico updatedTopico = topicoRepository.save(topico);
        return topicoMapper.toResponseDto(updatedTopico);
    }

    @Transactional
    public void deletarTopico(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico não encontrado"));

        validarAutorDoTopico(topico);
        topicoRepository.deleteById(id);
    }

    private void validarTopicoDuplicado(String titulo, String mensagem) {
        if (topicoRepository.existsByTituloAndMensagem(titulo, mensagem)) {
            throw new ValidacaoException("Já existe um tópico com este título e mensagem");
        }
    }

    private Usuario buscarUsuarioLogado() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return (Usuario) usuarioRepository.findByEmail(email);
    }

    private Curso buscarCursoPorId(Long cursoId) {
        return cursoRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));
    }

    private void validarAutorDoTopico(Topico topico) {
        Usuario usuarioLogado = buscarUsuarioLogado();
        if (!topico.getAutor().getId().equals(usuarioLogado.getId())) {
            throw new ValidacaoException("Usuário não autorizado a modificar este tópico");
        }
    }
}
