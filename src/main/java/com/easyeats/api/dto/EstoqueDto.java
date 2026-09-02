package com.easyeats.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record EstoqueDto(
    @NotBlank (message = "A quantidade atual é obrigatória.")
    @Min(value = 0, message = "A quantidade atual não pode ser menor que zero")
    Integer qtdAtual,
    @NotBlank (message = "A quantidade mínima é obrigatória.")
    @Min (value = 1,message = "A quantidade mínima deve ser maior que zero.")
    Integer qtdMinima,
    LocalDate dtAtualizacao
) {
}
