package com.easyeats.api.service;

import com.easyeats.api.dto.AvaliacaoDto;
import com.easyeats.api.dto.mapper.AvaliacaoMapper;
import com.easyeats.api.entity.Avaliacao;
import com.easyeats.api.entity.Produto;
import com.easyeats.api.entity.Usuario;
import com.easyeats.api.repository.AvaliacaoRepository;
import com.easyeats.api.repository.ProdutoRepository;
import com.easyeats.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;
    private final AvaliacaoMapper mapper;

    public AvaliacaoDto criar(AvaliacaoDto dto) {

        Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado: " + dto.idUsuario())
                );

        Produto produto = produtoRepository.findById(dto.idProduto())
                .orElseThrow(() ->
                        new RuntimeException("Produto não encontrado: " + dto.idProduto())
                );

        if (avaliacaoRepository.existsByUsuarioIdAndProdutoId(dto.idUsuario(), dto.idProduto())) {
            throw new IllegalArgumentException("Este usuário já avaliou esse produto");
        }

        Avaliacao avaliacao = mapper.toEntity(dto);
        avaliacao.setUsuario(usuario);
        avaliacao.setProduto(produto);

        Avaliacao avaliacaoSalva = avaliacaoRepository.save(avaliacao);

        return mapper.toDto(avaliacaoSalva);
    }

    public List<AvaliacaoDto> listarTodos() {
        List<Avaliacao> listaOriginal = avaliacaoRepository.findAll();
        List<AvaliacaoDto> listaConvertida = new ArrayList<>();

        for (Avaliacao avaliacao : listaOriginal) {
            listaConvertida.add(mapper.toDto(avaliacao));
        }

        return listaConvertida;
    }

    public Optional<AvaliacaoDto> buscarPorId(Long id) {
        return avaliacaoRepository.findById(id)
                .map(mapper::toDto);
    }

    public List<AvaliacaoDto> buscarPorProduto(Long idProduto) {
        List<AvaliacaoDto> lista = new ArrayList<>();

        for (Avaliacao avaliacao : avaliacaoRepository.findByProdutoId(idProduto)) {
            lista.add(mapper.toDto(avaliacao));
        }

        return lista;
    }

    public List<AvaliacaoDto> buscarPorUsuario(Long idUsuario) {
        List<AvaliacaoDto> lista = new ArrayList<>();

        for (Avaliacao avaliacao : avaliacaoRepository.findByUsuarioId(idUsuario)) {
            lista.add(mapper.toDto(avaliacao));
        }

        return lista;
    }

    public AvaliacaoDto editar(Long id, AvaliacaoDto dto) {

        Avaliacao avaliacaoExistente = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada: " + id));

        avaliacaoExistente.setNota(dto.nota());
        avaliacaoExistente.setComentario(dto.comentario());

        Avaliacao avaliacaoSalva = avaliacaoRepository.save(avaliacaoExistente);

        return mapper.toDto(avaliacaoSalva);
    }

    public void excluir(Long id) {
        avaliacaoRepository.deleteById(id);
    }
}
