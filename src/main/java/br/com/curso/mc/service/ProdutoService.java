package br.com.curso.mc.service;

import br.com.curso.mc.entity.Categoria;
import br.com.curso.mc.entity.Produto;
import br.com.curso.mc.exception.ObjectNotFoundException;
import br.com.curso.mc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaService categoriaService;

    public Produto findById(Integer id){
        Optional<Produto> product = repository.findById(id);
        return product.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado id " + id + ", tipo " + Produto.class.getSimpleName()));
    }

    public Page<Produto> search(String nome, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        List<Categoria> categorias = categoriaService.findAllById(ids);

        return repository.search(nome,categorias,pageRequest);

    }
}
