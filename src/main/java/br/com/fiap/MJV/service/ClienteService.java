package br.com.fiap.MJV.service;

import br.com.fiap.MJV.entity.Cliente;
import br.com.fiap.MJV.error.ClieteNotFoundException;
import br.com.fiap.MJV.error.ProdutoNotFoundException;

import java.util.List;

public interface ClienteService {
    Cliente cadastraCliente(Cliente cliente);

    List<Cliente> getClientes();

    Cliente getClienteById(Long id) throws ClieteNotFoundException;

    void deletaClienteById(Long id);

    Cliente updateCliente(Long id, Cliente cliente) throws ClieteNotFoundException, ProdutoNotFoundException;
}
