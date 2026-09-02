package com.easyeats.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EstoqueDto(
        Long id,
        @NotBlank(message = "A quantidade atual é obrigatória.")
        @Min(value = 0, message = "A quantidade atual não pode ser menor que zero")
        Integer qtdAtual,
        @NotBlank(message = "A quantidade mínima é obrigatória.")
        @Min(value = 1, message = "A quantidade mínima deve ser maior que zero.")
        Integer qtdMinima,
        LocalDate dtAtualizacao,
        @NotNull(message = "O ingrediente é obrigatório") Long idIngrediente,
        String nomeIngrediente
) {
}
