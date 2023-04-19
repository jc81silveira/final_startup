package br.com.fiap.MJV.service;

import br.com.fiap.MJV.entity.Produto;
import br.com.fiap.MJV.error.ProdutoNotFoundException;
import br.com.fiap.MJV.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Override
    public void cadastraProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    @Override
    public List<Produto> getProdutos() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto getProdutoById(Long id) throws ProdutoNotFoundException {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (!produtoOptional.isPresent()) {
            throw new ProdutoNotFoundException("Produto Não Encontrado");
        }

        return produtoOptional.get();
    }

    @Override
    public void cadastraProdutos(List<Produto> produtos) {
        produtoRepository.saveAll(produtos);
    }

    @Override
    public void deletaProdutoById(Long id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public Produto updateProduto(Long id, Produto produto) throws ProdutoNotFoundException {
        Produto produtoBanco = getProdutoById(id);

        if (Objects.nonNull(produto.getNomeProduto())
                && !"".equalsIgnoreCase(produto.getNomeProduto())) {
            produtoBanco.setNomeProduto(produto.getNomeProduto());
        }

        if (Objects.nonNull(produto.getPreco())
                && produto.getPreco().doubleValue() >= 0.00) {
            produtoBanco.setPreco(produto.getPreco());
        }
        if (Objects.nonNull(produto.getCliente())) {
            produtoBanco.setCliente(produto.getCliente());
        }

        return produtoRepository.save(produtoBanco);

    }

    @Override
    public Produto findNomeProdutoIgnoreCase(String nome) {
        return produtoRepository.findByNomeProdutoIgnoreCase(nome);
    }

}
