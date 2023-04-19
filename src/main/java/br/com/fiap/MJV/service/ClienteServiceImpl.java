package br.com.fiap.MJV.service;

import br.com.fiap.MJV.entity.Cliente;
import br.com.fiap.MJV.entity.Produto;
import br.com.fiap.MJV.error.ClieteNotFoundException;
import br.com.fiap.MJV.error.ProdutoNotFoundException;
import br.com.fiap.MJV.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ProdutoService produtoService;

    @Override
    public Cliente cadastraCliente(Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente;
    }

    @Override
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getClienteById(Long id) throws ClieteNotFoundException {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (!cliente.isPresent()) {
            throw new ClieteNotFoundException("Cliente Não Encontrado");
        }

        return cliente.get();
    }

    @Override
    public void deletaClienteById(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Cliente updateCliente(Long id, Cliente cliente) throws ClieteNotFoundException, ProdutoNotFoundException {
        Cliente clienteBanco = getClienteById(id);

        if(Objects.nonNull(cliente.getNomeCliente())
                && !"".equalsIgnoreCase(cliente.getNomeCliente())){
            clienteBanco.setNomeCliente(cliente.getNomeCliente());
        }

        if(Objects.nonNull(cliente.getCnpj())
        && !"".equalsIgnoreCase(cliente.getCnpj())){
            clienteBanco.setCnpj(cliente.getCnpj());
        }


        if(Objects.nonNull(cliente.getProduto())){
            List<Produto> produtos = cliente.getProduto();
            List<Produto> produtosBanco = clienteBanco.getProduto();

            produtosBanco.clear();

            for (Produto produto : produtos){
                Produto produtoBanco = produtoService.getProdutoById(produto.getId());

                produtoBanco.setNomeProduto(produto.getNomeProduto());
                produtoBanco.setPreco(produto.getPreco());
                produtoBanco.setCliente(clienteBanco);

                produtosBanco.add(produtoBanco);
            }
            clienteBanco.setProduto(produtosBanco);
        }

        return clienteRepository.save(clienteBanco);
    }
}
