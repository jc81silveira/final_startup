package br.com.fiap.MJV.service;

import br.com.fiap.MJV.entity.Produto;
import br.com.fiap.MJV.error.ProdutoNotFoundException;

import java.util.List;

public interface ProdutoService {
    void cadastraProduto(Produto produto);

    Produto getProdutoById(Long id) throws ProdutoNotFoundException;

    void cadastraProdutos(List<Produto> produto);

    List<Produto> getProdutos();

    void deletaProdutoById(Long id);

    Produto updateProduto(Long id, Produto produto) throws ProdutoNotFoundException;

    Produto findNomeProdutoIgnoreCase(String nome);
}
