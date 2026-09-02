package com.easyeats.api.dto;

import jakarta.validation.constraints.NotBlank;

public record IngredienteDto(
        Long id,
        @NotBlank(message = "O nome do ingrediente é obrigatório") String nome,
        @NotBlank(message = "A unidade de medida é obrigatória") String unidadeMedida
) {
}
