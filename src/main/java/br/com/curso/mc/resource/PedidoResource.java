package br.com.curso.mc.resource;

import br.com.curso.mc.entity.Pedido;
import br.com.curso.mc.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
