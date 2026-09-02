package com.easyeats.api.dto.mapper;

import com.easyeats.api.dto.ProdutoDto;
import com.easyeats.api.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {
    public ProdutoDto toDto(Produto produto){
        return new ProdutoDto(
                produto.getNome(),
                produto.getDescricao(),
                produto.getFlativo()
        );
    }

    public Produto toEntity(ProdutoDto dto){
        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setFlativo(dto.flativo());

        return produto;
    }

}