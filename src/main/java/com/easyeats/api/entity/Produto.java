package com.easyeats.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private String data_criacao;
    private String data_alteracao;
    private String flativo;

    @OneToMany(mappedBy = "produto")
    private List<ProdutoIngrediente> ingredientes = new ArrayList<>();

}
