package com.easyeats.api.controller;

import com.easyeats.api.dto.IngredienteDto;
import com.easyeats.api.service.IngredienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredientes")
@RequiredArgsConstructor
public class IngredienteController {

    private final IngredienteService ingredienteService;

    @PostMapping
    public ResponseEntity<IngredienteDto> criar(@RequestBody IngredienteDto ingrediente) {
        IngredienteDto salvo = ingredienteService.salvar(ingrediente);

        if (salvo == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<IngredienteDto>> listarTodos() {
        List<IngredienteDto> ingredientes = ingredienteService.listarTodos();

        if (ingredientes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(ingredientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredienteDto> buscarPorId(@PathVariable Long id) {
        return ingredienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<IngredienteDto> buscarPorNome(@PathVariable String nome) {
        return ingredienteService.buscarPorNome(nome)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        ingredienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredienteDto> alterar(
            @PathVariable Long id,
            @RequestBody IngredienteDto ingrediente) {

        IngredienteDto modificado = ingredienteService.alterar(id, ingrediente);

        if (modificado == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(modificado);
    }
}
