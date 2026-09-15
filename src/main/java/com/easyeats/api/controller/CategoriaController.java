package com.easyeats.api.controller;

import com.easyeats.api.dto.CategoriaDto;
import com.easyeats.api.entity.Categoria;
import com.easyeats.api.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // Criar
    @PostMapping
    public ResponseEntity<Categoria> criar(
            @Valid @RequestBody CategoriaDto dto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoriaService.criar(dto));
    }

    // Listar
    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        return ResponseEntity.ok(categoriaService.listar());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    // Editar
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> editar(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaDto dto
    ) {
        return ResponseEntity.ok(
                categoriaService.editar(id, dto)
        );
    }

    // Inativar
    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Categoria> inativar(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                categoriaService.inativar(id)
        );
    }
}