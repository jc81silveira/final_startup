package br.com.fiap.MJV.controller;

import br.com.fiap.MJV.entity.Produto;
import br.com.fiap.MJV.entity.Maquina;
import br.com.fiap.MJV.error.ProdutoNotFoundException;
import br.com.fiap.MJV.error.MaquinaNotFoundException;
import br.com.fiap.MJV.service.ProdutoService;
import br.com.fiap.MJV.service.MaquinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MaquinaController {

    @Autowired
    private MaquinaService maquinaService;
    @Autowired
    ProdutoService produtoService;

    @PostMapping("/api/v1/maquina")
    public String cadastraMaquina(@RequestBody Maquina maquina) throws ProdutoNotFoundException {
        // Fetch the Produto entity from the database using its ID
        Produto produtoOptional = produtoService.getProdutoById(maquina.getProduto().getId());

        if (produtoOptional != null) {
            maquina.setProduto(produtoOptional);
            maquinaService.cadastraMaquina(maquina);
            return "Maquina Cadastrada";
        }
        return "Maquina Não Cadastro! Verifique os campos.";
    }

    @GetMapping("/api/v1/maquina")
    public List<Maquina> getMaquinas() {
        return maquinaService.getMaquinas();
    }

    @GetMapping("/api/v1/maquina/{id}")
    public Maquina getMaquinaById(@PathVariable("id") Long id) throws MaquinaNotFoundException {
        return maquinaService.getMaquinaById(id);
    }

    @DeleteMapping("/api/v1/maquina/{id}")
    public String deletarMaquinaById(@PathVariable("id") Long id) {
        maquinaService.deletarMaquinaById(id);
        return "Maquina Deletada";
    }

    @PutMapping("/api/v1/maquina/{id}")
    public Maquina updateMaquina(@PathVariable("id") Long id, @RequestBody Maquina maquina) throws MaquinaNotFoundException {
        return maquinaService.updateMaquina(id, maquina);
    }

    @GetMapping("/api/v1/maquina/{id}/produto/{nome}")
    public String adicionaProdutoAoSaldo(@PathVariable Long id, @PathVariable String nome) throws MaquinaNotFoundException {
        Maquina maquinaBanco = maquinaService.getMaquinaById(id);
        Produto produtoBanco = produtoService.findNomeProdutoIgnoreCase(nome);
        Double saldoAnterior = maquinaBanco.getQuantidade().doubleValue();

        Double novoSaldo = saldoAnterior + produtoBanco.getPreco().doubleValue();

        String saldoBefore = "A maquina: " + maquinaBanco.getLocal() + " possui uma quantidade de  " + saldoAnterior
                + "\nAgora ela tem uma quantidade de " + novoSaldo + ".\nApós ter inserido o produto: " + nome;

        return saldoBefore;
    }

}
