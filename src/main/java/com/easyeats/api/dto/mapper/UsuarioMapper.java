package com.easyeats.api.dto.mapper;

import com.easyeats.api.dto.UsuarioDto;
import com.easyeats.api.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDto toDto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getRole(),
                usuario.isAtivo()
        );
    }

    public Usuario toEntity(UsuarioDto dto) {
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setNome(dto.getNome());
        usuario.setRole(dto.getRole());
        usuario.setAtivo(dto.isAtivo());

        return usuario;
    }

}