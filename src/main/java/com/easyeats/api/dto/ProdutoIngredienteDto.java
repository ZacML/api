package com.easyeats.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoIngredienteDto {

    private Long produtoId;

    private Long ingredienteId;

    private Double quantidade;
}