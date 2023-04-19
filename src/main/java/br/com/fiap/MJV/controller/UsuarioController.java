package br.com.fiap.MJV.controller;

import br.com.fiap.MJV.entity.Produto;
import br.com.fiap.MJV.entity.Usuario;
import br.com.fiap.MJV.error.ProdutoNotFoundException;
import br.com.fiap.MJV.error.UsuarioNotFoundException;
import br.com.fiap.MJV.service.ProdutoService;
import br.com.fiap.MJV.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    ProdutoService produtoService;

    @PostMapping("/api/v1/usuario")
    public String cadastraUsuario(@RequestBody Usuario usuario) throws ProdutoNotFoundException {
        // Fetch the Produto entity from the database using its ID
        Produto produtoOptional = produtoService.getProdutoById(usuario.getProduto().getId());

        if (produtoOptional != null) {
            usuario.setProduto(produtoOptional);
            usuarioService.cadastraUsuario(usuario);
            return "Usuario Cadastrado";
        }
        return "Usuario Não Cadastro! Verifique os campos.";
    }

    @GetMapping("/api/v1/usuario")
    public List<Usuario> getUsuarios() {
        return usuarioService.getUsuarios();
    }

    @GetMapping("/api/v1/usuario/{id}")
    public Usuario getUsuarioById(@PathVariable("id") Long id) throws UsuarioNotFoundException {
        return usuarioService.getUsuarioById(id);
    }

    @DeleteMapping("/api/v1/usuario/{id}")
    public String deletarUsuarioById(@PathVariable("id") Long id) {
        usuarioService.deletarUsuarioById(id);
        return "Usuario Deletado";
    }

    @PutMapping("/api/v1/usuario/{id}")
    public Usuario updateUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) throws UsuarioNotFoundException {
        return usuarioService.updateUsuario(id, usuario);
    }

}
