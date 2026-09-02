package com.easyeats.api.service;

import com.easyeats.api.dto.ProdutoDto;
import com.easyeats.api.dto.mapper.ProdutoMapper;
import com.easyeats.api.entity.Produto;
import com.easyeats.api.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    public ProdutoDto salvar(ProdutoDto dto) {

        Produto produto = mapper.toEntity(dto);

        produto.setFlativo("S");

        return mapper.toDto(repository.save(produto));
    }

    public ProdutoDto alterar(Long id, ProdutoDto dto) {

        Optional<Produto> produtoExistente = repository.findById(id);

        if (produtoExistente.isEmpty()) {
            return null;
        }

        Produto produtoAtualizado = mapper.toEntity(dto);
        produtoAtualizado.setId(id);

        // Mantém o status atual do produto
        produtoAtualizado.setFlativo(produtoExistente.get().getFlativo());

        return mapper.toDto(repository.save(produtoAtualizado));
    }

    public List<ProdutoDto> listarTodos() {

        return repository.findAll().stream()
                .filter(produto -> "S".equals(produto.getFlativo()))
                .map(mapper::toDto)
                .toList();
    }

    public Optional<ProdutoDto> buscarPorId(Long id) {

        Optional<Produto> produto = repository.findById(id);

        if (produto.isEmpty() || !"S".equals(produto.get().getFlativo())) {
            return Optional.empty();
        }

        return Optional.of(mapper.toDto(produto.get()));
    }

    public void excluir(Long id) {

        Optional<Produto> produto = repository.findById(id);

        if (produto.isPresent()) {
            produto.get().setFlativo("N");
            repository.save(produto.get());
        }
    }


}
