package br.com.fiap.MJV.controller;

import br.com.fiap.MJV.entity.Cliente;
import br.com.fiap.MJV.entity.Produto;
import br.com.fiap.MJV.error.ClieteNotFoundException;
import br.com.fiap.MJV.repository.ProdutoRepository;
import br.com.fiap.MJV.service.ClienteService;
import br.com.fiap.MJV.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    ClienteService clienteService;
    @Autowired
    ProdutoService produtoService;

    @PostMapping("/api/v1/cliente")
    public String cadastraCliente(@RequestBody Cliente cliente){
        Cliente clienteSalvo = clienteService.cadastraCliente(cliente);

        for (Produto produto : clienteSalvo.getProduto()){
            produto.setCliente(clienteSalvo);
        }
        produtoService.cadastraProdutos(clienteSalvo.getProduto());

        return "Cliente Cadastrado";
    }

    @GetMapping("/api/v1/cliente")
    public List<Cliente> getClientes(){
        return clienteService.getClientes();
    }

    @GetMapping("/api/v1/cliente/{id}")
    public  Cliente getClienteById(@PathVariable("id") Long id) throws ClieteNotFoundException {
        return  clienteService.getClienteById(id);
    }

    @DeleteMapping("/api/v1/cliente/{id}")
    public String deletaClienteById(@PathVariable("id") Long id){
        clienteService.deletaClienteById(id);
        return "Cliente deletado com sucesso!";
    }

    @PutMapping("/api/v1/cliente/{id}")
    public Cliente updateCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) throws ClieteNotFoundException {
        return clienteService.updateCliente(id, cliente);
    }


}
