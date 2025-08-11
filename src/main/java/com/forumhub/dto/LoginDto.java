package com.forumhub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank
        @Email
        String email,

        @NotBlank
        String senha
) {}