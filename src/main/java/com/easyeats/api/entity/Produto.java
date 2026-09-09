package com.easyeats.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    private LocalDateTime dataAlteracao;
    private String flativo;

    @OneToMany(mappedBy = "produto")
    private List<ProdutoIngrediente> ingredientes = new ArrayList<>();

    @PrePersist
    public void antesDeCriar() {
        dataCriacao = LocalDateTime.now();
        dataAlteracao = LocalDateTime.now();
    }

    @PreUpdate
    public void antesDeAtualizar() {
        dataAlteracao = LocalDateTime.now();
    }

}
