package com.easyeats.api.dto.mapper;

import com.easyeats.api.dto.AvaliacaoDto;
import com.easyeats.api.entity.Avaliacao;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoMapper {

    public AvaliacaoDto toDto(Avaliacao avaliacao) {

        if (avaliacao == null) {
            return null;
        }

        Long idUsuario = null;
        String nomeUsuario = null;

        if (avaliacao.getUsuario() != null) {
            idUsuario = avaliacao.getUsuario().getId();
            nomeUsuario = avaliacao.getUsuario().getNome();
        }

        Long idProduto = null;
        String nomeProduto = null;

        if (avaliacao.getProduto() != null) {
            idProduto = avaliacao.getProduto().getId();
            nomeProduto = avaliacao.getProduto().getNome();
        }

        return new AvaliacaoDto(
                avaliacao.getId(),
                avaliacao.getNota(),
                avaliacao.getComentario(),
                avaliacao.getDataCriacao(),
                idUsuario,
                nomeUsuario,
                idProduto,
                nomeProduto
        );
    }

    public Avaliacao toEntity(AvaliacaoDto dto) {

        if (dto == null) {
            return null;
        }

        Avaliacao avaliacao = new Avaliacao();

        avaliacao.setNota(dto.nota());
        avaliacao.setComentario(dto.comentario());

        return avaliacao;
    }
}
