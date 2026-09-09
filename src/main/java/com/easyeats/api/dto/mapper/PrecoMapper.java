package com.easyeats.api.dto.mapper;

import org.springframework.stereotype.Component;

import com.easyeats.api.dto.PrecoDto;
import com.easyeats.api.entity.Preco;
import com.easyeats.api.entity.Produto;

@Component
public class PrecoMapper {

    public Preco toEntity(PrecoDto dto, Produto produto) {
        Preco preco = new Preco();
        preco.setValor(dto.valor());
        preco.setProduto(produto);
        return preco;
    }

    public PrecoDto toDto(Preco preco) {
        return new PrecoDto(
                preco.getValor(),
                preco.getProduto().getId()
        );
    }
}