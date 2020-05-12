package br.com.curso.mc.service;

import br.com.curso.mc.entity.Endereco;
import br.com.curso.mc.exception.ObjectNotFoundException;
import br.com.curso.mc.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;
    public Endereco findById(Integer id){
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        return endereco.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado id " + id + ", tipo " + Endereco.class.getSimpleName()));
    }

    public List<Endereco> save(List<Endereco> enderecos){
        return enderecoRepository.saveAll(enderecos);
    }

    public Endereco update(Endereco endereco){
        findById(endereco.getId());
        return enderecoRepository.save(endereco);
    }
}
