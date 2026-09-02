package com.easyeats.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tb_ingredientes")
@NoArgsConstructor
@AllArgsConstructor
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private String unidadeMedida;

    @OneToOne(mappedBy = "ingrediente", cascade = CascadeType.ALL)
    private Estoque estoque;

    @OneToMany(mappedBy = "ingrediente")
    private List<ProdutoIngrediente> produtos = new ArrayList<>();

}
