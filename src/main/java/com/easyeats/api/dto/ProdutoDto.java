package com.easyeats.api.dto;

import jakarta.validation.constraints.NotBlank;

public record ProdutoDto(
        @NotBlank(message = "O nome é obrigatório") String nome,
        String descricao,
        String flativo
) {
}
