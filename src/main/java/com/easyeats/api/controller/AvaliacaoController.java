package com.easyeats.api.controller;

import com.easyeats.api.dto.AvaliacaoDto;
import com.easyeats.api.service.AvaliacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @PostMapping
    public ResponseEntity<AvaliacaoDto> criar(@Valid @RequestBody AvaliacaoDto dto) {
        AvaliacaoDto salva = avaliacaoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @GetMapping
    public ResponseEntity<List<AvaliacaoDto>> listarTodos() {
        List<AvaliacaoDto> avaliacoes = avaliacaoService.listarTodos();

        if (avaliacoes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(avaliacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDto> buscarPorId(@PathVariable Long id) {
        return avaliacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/produto/{idProduto}")
    public ResponseEntity<List<AvaliacaoDto>> buscarPorProduto(@PathVariable Long idProduto) {
        List<AvaliacaoDto> avaliacoes = avaliacaoService.buscarPorProduto(idProduto);

        if (avaliacoes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(avaliacoes);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<AvaliacaoDto>> buscarPorUsuario(@PathVariable Long idUsuario) {
        List<AvaliacaoDto> avaliacoes = avaliacaoService.buscarPorUsuario(idUsuario);

        if (avaliacoes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(avaliacoes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoDto> editar(
            @PathVariable Long id,
            @Valid @RequestBody AvaliacaoDto dto
    ) {
        return ResponseEntity.ok(avaliacaoService.editar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        avaliacaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
