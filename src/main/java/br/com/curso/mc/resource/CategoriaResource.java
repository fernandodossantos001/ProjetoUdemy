package br.com.curso.mc.resource;

import br.com.curso.mc.entity.Categoria;
import br.com.curso.mc.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Integer id){
        Categoria categoria = service.findById(id);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping()
    public ResponseEntity<Void> saveCategoria(@RequestBody Categoria categoria){
        Categoria categoriaSaved = service.save(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(categoriaSaved.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
