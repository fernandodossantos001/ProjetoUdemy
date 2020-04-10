package br.com.curso.mc.resource;

import br.com.curso.mc.entity.Categoria;
import br.com.curso.mc.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Categoria>> findById(@PathVariable Integer id){
        Optional<Categoria> categoria = service.findById(id);
        return ResponseEntity.ok(categoria);
    }
}
