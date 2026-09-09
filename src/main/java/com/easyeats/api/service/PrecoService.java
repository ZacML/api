package com.easyeats.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.easyeats.api.dto.PrecoDto;
import com.easyeats.api.dto.mapper.PrecoMapper;
import com.easyeats.api.entity.Preco;
import com.easyeats.api.entity.Produto;
import com.easyeats.api.repository.PrecoRepository;
import com.easyeats.api.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrecoService {

    private final PrecoRepository repository;
    private final ProdutoRepository produtoRepository;
    private final PrecoMapper mapper;

    public PrecoDto salvar(PrecoDto dto) {

        Optional<Produto> produto = produtoRepository.findById(dto.produtoId());

        if (produto.isEmpty() || !"S".equals(produto.get().getFlativo())) {
            return null;
        }

        Preco preco = mapper.toEntity(dto, produto.get());
        preco.setFlativo("S");

        return mapper.toDto(repository.save(preco));
    }

    public PrecoDto alterar(Long id, PrecoDto dto) {

        Optional<Preco> precoExistente = repository.findById(id);

        if (precoExistente.isEmpty()) {
            return null;
        }

        Optional<Produto> produto = produtoRepository.findById(dto.produtoId());

        if (produto.isEmpty() || !"S".equals(produto.get().getFlativo())) {
            return null;
        }

        Preco precoAtualizado = mapper.toEntity(dto, produto.get());
        precoAtualizado.setId(id);
        precoAtualizado.setFlativo(precoExistente.get().getFlativo());

        return mapper.toDto(repository.save(precoAtualizado));
    }

    public List<PrecoDto> listarTodos() {
        return repository.findAll().stream()
                .filter(preco -> "S".equals(preco.getFlativo()))
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<PrecoDto> buscarPorId(Long id) {

        Optional<Preco> preco = repository.findById(id);

        if (preco.isEmpty() || !"S".equals(preco.get().getFlativo())) {
            return Optional.empty();
        }

        return Optional.of(mapper.toDto(preco.get()));
    }

    public List<PrecoDto> listarPorProduto(Long produtoId) {
        return repository.findByProdutoId(produtoId).stream()
                .filter(preco -> "S".equals(preco.getFlativo()))
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public void excluir(Long id) {

        Optional<Preco> preco = repository.findById(id);

        if (preco.isPresent()) {
            preco.get().setFlativo("N");
            repository.save(preco.get());
        }
    }
}