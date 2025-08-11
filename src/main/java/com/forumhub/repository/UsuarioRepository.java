package com.forumhub.repository;

import com.forumhub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email); // método que você provavelmente já tem

    // Adicione este método para verificar se o email já existe
    boolean existsByEmail(String email);
}