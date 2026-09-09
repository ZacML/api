package com.easyeats.api.service;

import com.easyeats.api.dto.ProdutoIngredienteDto;
import com.easyeats.api.dto.mapper.ProdutoIngredienteMapper;
import com.easyeats.api.entity.Ingrediente;
import com.easyeats.api.entity.Produto;
import com.easyeats.api.entity.ProdutoIngrediente;
import com.easyeats.api.entity.ProdutoIngredienteId;
import com.easyeats.api.repository.IngredienteRepository;
import com.easyeats.api.repository.ProdutoIngredienteRepository;
import com.easyeats.api.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoIngredienteService {

    private final ProdutoIngredienteRepository repository;
    private final ProdutoIngredienteMapper mapper;
    private final ProdutoRepository produtoRepository;
    private final IngredienteRepository ingredienteRepository;

    public ProdutoIngredienteDto salvar(ProdutoIngredienteDto dto) {

        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow();

        Ingrediente ingrediente = ingredienteRepository.findById(dto.getIngredienteId())
                .orElseThrow();

        ProdutoIngrediente produtoIngrediente = mapper.toEntity(dto);

        produtoIngrediente.setProduto(produto);
        produtoIngrediente.setIngrediente(ingrediente);

        ProdutoIngrediente salvo = repository.save(produtoIngrediente);

        return mapper.toDto(salvo);
    }

    public List<ProdutoIngredienteDto> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public ProdutoIngredienteDto buscarPorId(Long produtoId, Long ingredienteId) {
        ProdutoIngredienteId id =
                new ProdutoIngredienteId(produtoId, ingredienteId);

        ProdutoIngrediente produtoIngrediente = repository.findById(id)
                .orElseThrow();

        return mapper.toDto(produtoIngrediente);
    }

    public void excluir(Long produtoId, Long ingredienteId) {
        ProdutoIngredienteId id =
                new ProdutoIngredienteId(produtoId, ingredienteId);

        repository.deleteById(id);
    }
}