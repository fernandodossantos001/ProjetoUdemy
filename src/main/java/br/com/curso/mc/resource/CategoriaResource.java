package br.com.curso.mc.resource;

import br.com.curso.mc.entity.Categoria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> list(){
        Categoria categoria = new Categoria(1,"informática");
        Categoria categoria2 = new Categoria(2,"escritório");
        return Arrays.asList(categoria,categoria2);
    }
}
