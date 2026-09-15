package com.easyeats.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import javax.management.relation.Role;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private LocalDateTime dataAlteracao;

    @Column(nullable = false)
    private boolean ativo;

    @PrePersist
    protected void aoCriar() {
        LocalDateTime agora = LocalDateTime.now();
        dataCriacao = agora;
        dataAlteracao = agora;
    }

    @PreUpdate
    protected void aoAtualizar() {
        dataAlteracao = LocalDateTime.now();
    }
}