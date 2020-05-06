package br.com.curso.mc.resource;

import br.com.curso.mc.dto.CategoriaDTO;
import br.com.curso.mc.entity.Categoria;
import br.com.curso.mc.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<Void> saveCategoria(@Valid @RequestBody CategoriaDTO categoriaDto){
        Categoria categoria = service.fromDTO(categoriaDto);
        service.save(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategoria(@PathVariable Integer id,@Valid @RequestBody CategoriaDTO categoriaDTO){
        Categoria categoria = service.fromDTO(categoriaDTO);
        categoria.setId(id);
        service.update(categoria);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll(){
        List<Categoria> categorias = service.findAll();
        List<CategoriaDTO> categoriaDTOS = categorias.stream().map(categoria -> new CategoriaDTO(categoria)).collect(Collectors.toList());
        return ResponseEntity.ok().body(categoriaDTOS);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CategoriaDTO>> findAllByPage(@RequestParam(value = "page",defaultValue = "0") Integer page,
                                                            @RequestParam(value = "linePerPage",defaultValue = "24")Integer linePerPage,
                                                            @RequestParam(value = "orderBy",defaultValue = "nome")String orderBy,
                                                            @RequestParam(value = "direction",defaultValue = "ASC")String direction){
        Page<Categoria> pageCategoria = service.findAllByPage(page, linePerPage, orderBy, direction);
        Page<CategoriaDTO> pageCategoriaDto = pageCategoria.map(categoria -> new CategoriaDTO(categoria));
        return ResponseEntity.ok().body(pageCategoriaDto);
    }
}
