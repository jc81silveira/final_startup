package br.com.fiap.MJV.service;

import br.com.fiap.MJV.entity.Maquina;
import br.com.fiap.MJV.error.MaquinaNotFoundException;

import java.util.List;

public interface MaquinaService {
    void cadastraMaquina(Maquina maquina);

    List<Maquina> getMaquinas();

    Maquina getMaquinaById(Long id) throws MaquinaNotFoundException;

  //  void deletarUsuarioById(Long id);

    void deletarMaquinaById(Long id);

    Maquina updateMaquina(Long id, Maquina maquina) throws MaquinaNotFoundException;

   // void deletarMaquinaById(Long id);
}
