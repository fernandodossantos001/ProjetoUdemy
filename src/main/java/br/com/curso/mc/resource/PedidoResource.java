package br.com.curso.mc.resource;

import br.com.curso.mc.dto.PedidoDTO;
import br.com.curso.mc.entity.Pedido;
import br.com.curso.mc.service.ClienteService;
import br.com.curso.mc.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {
    @Autowired
    private PedidoService service;


    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Integer id){
        Pedido pedido = service.findById(id);
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<Void> savePedido(@Valid @RequestBody Pedido pedido){
        Pedido savedPedido = service.insert(pedido);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedPedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
