package com.easyeats.api.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EstoqueDto(

        Long id,

        @NotNull(message = "A quantidade atual é obrigatória")
        Integer qtdAtual,

        @NotNull(message = "A quantidade mínima é obrigatória")
        Integer qtdMinima,

        LocalDateTime dtAtualizacao,

        Long idIngrediente,

        String nomeIngrediente

) {
}