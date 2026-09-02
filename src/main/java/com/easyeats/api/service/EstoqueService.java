package com.easyeats.api.service;

import com.easyeats.api.dto.EstoqueDto;
import com.easyeats.api.dto.mapper.EstoqueMapper;
import com.easyeats.api.entity.Estoque;
import com.easyeats.api.entity.Ingrediente;
import com.easyeats.api.repository.EstoqueRepository;
import com.easyeats.api.repository.IngredienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstoqueService {
    private final EstoqueRepository estoqueRepository;
    private final IngredienteRepository ingredienteRepository;
    private final EstoqueMapper mapper;

    public EstoqueDto salvar(EstoqueDto dto) {

        Ingrediente ingrediente = ingredienteRepository.findById(dto.idIngrediente())
                .orElseThrow(() ->
                        new RuntimeException("Ingrediente não encontrado: " + dto.idIngrediente())
                );

        Estoque estoque = mapper.toEntity(dto);

        estoque.setIngrediente(ingrediente);

        Estoque estoqueSalvo = estoqueRepository.save(estoque);

        return mapper.toDto(estoqueSalvo);
    }

    public List<EstoqueDto> listarTodos() {
        List<Estoque> listaOriginal = estoqueRepository.findAll();
        List<EstoqueDto> listaConvertida = new ArrayList<>();

        for (Estoque estoque : listaOriginal) {
            listaConvertida.add(mapper.toDto(estoque));
        }

        return listaConvertida;
    }

    public Optional<EstoqueDto> buscarPorId(Long id) {
        return estoqueRepository.findById(id)
                .map(mapper::toDto);
    }

    public Optional<EstoqueDto> buscarPorIngrediente(Long idIngrediente) {
        return estoqueRepository.findByIngredienteId(idIngrediente)
                .map(mapper::toDto);
    }

    public void excluir(Long id) {
        estoqueRepository.deleteById(id);
    }

    public EstoqueDto alterar(Long id, EstoqueDto dto) {
        Optional<Estoque> estoqueExistente = estoqueRepository.findById(id);

        if (estoqueExistente.isEmpty()) {
            return null;
        }

        Estoque estoqueAtualizado = mapper.toEntity(dto);
        estoqueAtualizado.setId(id);
        estoqueAtualizado.setIngrediente(estoqueExistente.get().getIngrediente());

        Estoque estoqueSalvo = estoqueRepository.save(estoqueAtualizado);

        return mapper.toDto(estoqueSalvo);
    }


}
