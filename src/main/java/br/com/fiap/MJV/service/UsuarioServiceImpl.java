package br.com.fiap.MJV.service;

import br.com.fiap.MJV.entity.Usuario;
import br.com.fiap.MJV.error.UsuarioNotFoundException;
import br.com.fiap.MJV.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void cadastraUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getUsuarioById(Long id) throws UsuarioNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (!usuario.isPresent()) {
            throw new UsuarioNotFoundException("Usuario Não Encontrado");
        }
        return usuario.get();
    }

    @Override
    public void deletarUsuarioById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario updateUsuario(Long id, Usuario usuario) throws UsuarioNotFoundException {
        Usuario usuarioBanco = getUsuarioById(id);

        if (Objects.nonNull(usuario.getNome())
                && !"".equalsIgnoreCase(usuario.getNome())) {
            usuarioBanco.setNome(usuario.getNome());
        }

        if (Objects.nonNull(usuario.getCpf())
                && !"".equalsIgnoreCase(usuario.getCpf())) {
            usuarioBanco.setCpf(usuario.getCpf());
        }

        if (Objects.nonNull(usuario.getSaldo())
                && (usuario.getSaldo().doubleValue() >= 0.00)) {
            usuarioBanco.setSaldo(usuario.getSaldo());
        }

        // verificar se o id do produto existe!
        if (Objects.nonNull(usuario.getProduto())
                && usuario.getProduto().getId() != 0) {
            usuarioBanco.setProduto(usuario.getProduto());
        }

        return usuarioRepository.save(usuarioBanco);
    }
}
