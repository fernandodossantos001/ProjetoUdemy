package br.com.curso.mc.service;

import br.com.curso.mc.dto.CategoriaDTO;
import br.com.curso.mc.entity.Categoria;
import br.com.curso.mc.exception.ObjectNotFoundException;
import br.com.curso.mc.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    public Categoria findById(Integer id){
        Optional<Categoria> categoria = repository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ! id " + id + ", Tipo " + Categoria.class.getSimpleName()));
    }

    public Categoria save(Categoria categoria) {
        return repository.save(categoria);
    }

    public Categoria update(Categoria categoria) {
        Categoria newCategoria = findById(categoria.getId());
        updateDate(newCategoria,categoria);
        return repository.save(newCategoria);
    }

    private void updateDate(Categoria newCategoria, Categoria categoria) {
        newCategoria.setNome(categoria.getNome());
    }

    public void deleteById(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Page<Categoria> findAllByPage(Integer page, Integer linePerPage,String orderBy,String direction){
        PageRequest pageRequest = PageRequest.of(page,linePerPage, Sort.Direction.valueOf(direction),orderBy);
        return repository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO categoriaDTO){
        return new Categoria(categoriaDTO.getId(),categoriaDTO.getNome());
    }

    public List<Categoria> findAllById(List<Integer> ids){
        return repository.findAllById(ids);
    }
}
