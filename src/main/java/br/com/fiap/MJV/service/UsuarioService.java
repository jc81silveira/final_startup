package br.com.fiap.MJV.service;

import br.com.fiap.MJV.entity.Usuario;
import br.com.fiap.MJV.error.UsuarioNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    void cadastraUsuario(Usuario usuario);

    List<Usuario> getUsuarios();

    Usuario getUsuarioById(Long id) throws UsuarioNotFoundException;

    void deletarUsuarioById(Long id);

    Usuario updateUsuario(Long id, Usuario usuario) throws UsuarioNotFoundException;
}
