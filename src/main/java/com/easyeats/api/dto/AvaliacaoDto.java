package com.easyeats.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AvaliacaoDto(

        Long id,

        @NotNull(message = "A nota é obrigatória")
        @Min(value = 1, message = "A nota deve ser no mínimo 1")
        @Max(value = 5, message = "A nota deve ser no máximo 5")
        Integer nota,

        String comentario,

        LocalDateTime dataCriacao,

        @NotNull(message = "O usuário é obrigatório")
        Long idUsuario,

        String nomeUsuario,

        @NotNull(message = "O produto é obrigatório")
        Long idProduto,

        String nomeProduto

) {
}
