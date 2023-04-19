package br.com.fiap.MJV.controller;

import br.com.fiap.MJV.entity.Produto;
import br.com.fiap.MJV.error.ProdutoNotFoundException;
import br.com.fiap.MJV.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping("/api/v1/produto")
    public String cadastraProduto(@RequestBody Produto produto){
        produtoService.cadastraProduto(produto);
        return "Produto Cadastro Sucesso";
    }

    @GetMapping("/api/v1/produto")
    public List<Produto> getProdutos(){
        return produtoService.getProdutos();
    }

    @GetMapping("/api/v1/produto/{id}")
    public Produto getProdutoById(@PathVariable Long id) throws ProdutoNotFoundException {
        return produtoService.getProdutoById(id);
    }

    @DeleteMapping("/api/v1/produto/{id}")
    public String deletaProdutoById(@PathVariable("id") Long id){
        produtoService.deletaProdutoById(id);
        return "Produto Deletado";
    }

    @PutMapping("/api/v1/produto/{id}")
    public Produto updateProduto(@PathVariable("id") Long id, @RequestBody Produto produto) throws ProdutoNotFoundException {
        return produtoService.updateProduto(id, produto);
    }


}
