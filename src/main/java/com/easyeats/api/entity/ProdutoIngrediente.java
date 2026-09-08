package com.easyeats.api.entity;

import com.easyeats.api.repository.ProdutoIngredienteId;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_produto_ingrediente")
public class ProdutoIngrediente {

    @EmbeddedId
    private ProdutoIngredienteId id = new ProdutoIngredienteId();

    private Double quantidade;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @MapsId("ingredienteId")
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;
}