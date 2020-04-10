package br.com.curso.mc.service;

import br.com.curso.mc.entity.Categoria;
import br.com.curso.mc.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    public Optional<Categoria> findById(Integer id){
        return repository.findById(id);
    }
}
