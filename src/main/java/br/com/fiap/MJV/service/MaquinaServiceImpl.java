package br.com.fiap.MJV.service;

import br.com.fiap.MJV.entity.Maquina;
import br.com.fiap.MJV.error.MaquinaNotFoundException;
import br.com.fiap.MJV.repository.MaquinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MaquinaServiceImpl implements MaquinaService {

    @Autowired
    private MaquinaRepository maquinaRepository;

    @Override
    public void cadastraMaquina(Maquina maquina) {
        maquinaRepository.save(maquina);
    }

    @Override
    public List<Maquina> getMaquinas() {
        return maquinaRepository.findAll();
    }

    @Override
    public Maquina getMaquinaById(Long id) throws MaquinaNotFoundException {
        Optional<Maquina> maquina = maquinaRepository.findById(id);
        if (!maquina.isPresent()) {
            throw new MaquinaNotFoundException("Maquina Não Encontrada");
        }
        return maquina.get();
    }

  //  @Override
   // public void deletarMaquinaById(Long id) {
     //   maquinaRepository.deleteById(id);


    @Override
    public Maquina updateMaquina(Long id, Maquina maquina) throws MaquinaNotFoundException {
        Maquina maquinaBanco = getMaquinaById(id);

        if (Objects.nonNull(maquina.getLocal())
                && !"".equalsIgnoreCase(maquina.getLocal())) {
            maquinaBanco.setLocal(maquina.getLocal());
        }

        if (Objects.nonNull(maquina.getNumero_serie())
                && !"".equalsIgnoreCase(maquina.getNumero_serie())) {
            maquinaBanco.setNumero_serie(maquina.getNumero_serie());
        }

        if (Objects.nonNull(maquina.getQuantidade())
                && (maquina.getQuantidade().doubleValue() >= 0.00)) {
            maquinaBanco.setQuantidade(maquina.getQuantidade());
        }

        // verificar se o id do produto existe!
        if (Objects.nonNull(maquina.getProduto())
                && maquina.getProduto().getId() != 0) {
            maquinaBanco.setProduto(maquina.getProduto());
        }

        return maquinaRepository.save(maquinaBanco);
    }

    @Override
    public void deletarMaquinaById(Long id) {

    }
}
