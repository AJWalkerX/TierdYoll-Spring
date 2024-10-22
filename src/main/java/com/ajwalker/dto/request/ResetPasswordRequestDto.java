package com.ajwalker.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequestDto(
        @NotBlank(message = "Yeni şifre boş olamaz")
        String password
) {
}
