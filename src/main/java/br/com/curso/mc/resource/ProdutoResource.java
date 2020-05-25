package br.com.curso.mc.resource;

import br.com.curso.mc.dto.ProdutoDTO;
import br.com.curso.mc.entity.Produto;
import br.com.curso.mc.resource.utils.URL;
import br.com.curso.mc.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/produtos")
@RestController
public class ProdutoResource {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Integer id){
        Produto produto = produtoService.findById(id);
        return ResponseEntity.ok(produto);
    }
    @GetMapping("/page")
    public ResponseEntity<Page<ProdutoDTO>> search(@RequestParam(value = "page",defaultValue = "0") Integer page,
                                                   @RequestParam(value = "linePerPage",defaultValue = "24")Integer linePerPage,
                                                   @RequestParam(value = "orderBy",defaultValue = "nome")String orderBy,
                                                   @RequestParam(value = "direction",defaultValue = "ASC")String direction,
                                                   @RequestParam(value = "nome",defaultValue = "") String nome,
                                                   @RequestParam(value = "categorias",defaultValue = "") String categorias){
        Page<Produto> produtoPage = produtoService.search(URL.decodeParam(nome), URL.decodeIntList(categorias), page, linePerPage, orderBy, direction);
        Page<ProdutoDTO> produtosDTO = produtoPage.map(produto -> new ProdutoDTO(produto));
        return ResponseEntity.ok(produtosDTO);
    }
}
