package br.com.curso.mc.service;

import br.com.curso.mc.entity.Cidade;
import br.com.curso.mc.exception.ObjectNotFoundException;
import br.com.curso.mc.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;
    public Cidade findById(Integer id){
        Optional<Cidade> cidade = cidadeRepository.findById(id);
        return cidade.orElseThrow(()->new ObjectNotFoundException("Objeto não encontrado id " + id + ", tipo " + Cidade.class.getSimpleName()));

    }

    public Cidade save(Cidade cidade){
        return cidadeRepository.save(cidade);
    }

    public Cidade update(Cidade cidade){
        findById(cidade.getId());
        return cidadeRepository.save(cidade);
    }
}
