package com.easyeats.api.service;

import com.easyeats.api.dto.IngredienteDto;
import com.easyeats.api.dto.mapper.IngredienteMapper;
import com.easyeats.api.entity.Ingrediente;
import com.easyeats.api.repository.IngredienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;
    private final IngredienteMapper mapper;

    public IngredienteDto salvar(IngredienteDto dto) {
        Ingrediente ingrediente = mapper.toEntity(dto);

        Ingrediente ingredienteSalvo = ingredienteRepository.save(ingrediente);

        return mapper.toDto(ingredienteSalvo);
    }

    public List<IngredienteDto> listarTodos() {
        List<Ingrediente> listaOriginal = ingredienteRepository.findAll();
        List<IngredienteDto> listaConvertida = new ArrayList<>();

        for (Ingrediente ingrediente : listaOriginal) {
            listaConvertida.add(mapper.toDto(ingrediente));
        }

        return listaConvertida;
    }

    public Optional<IngredienteDto> buscarPorId(Long id) {
        return ingredienteRepository.findById(id)
                .map(mapper::toDto);
    }

    public Optional<IngredienteDto> buscarPorNome(String nome) {
        return ingredienteRepository.findByNome(nome)
                .map(mapper::toDto);
    }

    public void excluir(Long id) {
        ingredienteRepository.deleteById(id);
    }

    public IngredienteDto alterar(Long id, IngredienteDto dto) {
        Optional<Ingrediente> ingredienteExistente = ingredienteRepository.findById(id);

        if (ingredienteExistente.isEmpty()) {
            return null;
        }

        Ingrediente ingredienteAtualizado = mapper.toEntity(dto);
        ingredienteAtualizado.setId(id);

        Ingrediente ingredienteSalvo = ingredienteRepository.save(ingredienteAtualizado);

        return mapper.toDto(ingredienteSalvo);
    }

}
