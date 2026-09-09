package com.easyeats.api.dto.mapper;

import com.easyeats.api.dto.ProdutoIngredienteDto;
import com.easyeats.api.entity.ProdutoIngrediente;
import com.easyeats.api.entity.ProdutoIngredienteId;

import org.springframework.stereotype.Component;

@Component
public class ProdutoIngredienteMapper {

    public ProdutoIngredienteDto toDto(ProdutoIngrediente produtoIngrediente) {
        if (produtoIngrediente == null) {
            return null;
        }

        return new ProdutoIngredienteDto(
                produtoIngrediente.getId().getProdutoId(),
                produtoIngrediente.getId().getIngredienteId(),
                produtoIngrediente.getQuantidade()
        );
    }

    public ProdutoIngrediente toEntity(ProdutoIngredienteDto dto) {
        if (dto == null) {
            return null;
        }

        ProdutoIngrediente produtoIngrediente = new ProdutoIngrediente();

        produtoIngrediente.setId(
                new ProdutoIngredienteId(
                        dto.getProdutoId(),
                        dto.getIngredienteId()
                )
        );

        produtoIngrediente.setQuantidade(dto.getQuantidade());

        return produtoIngrediente;
    }
}