package com.easyeats.api.dto;

import com.easyeats.api.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    private Long id;
    private String nome;
    private Role role;
    private boolean ativo;
}