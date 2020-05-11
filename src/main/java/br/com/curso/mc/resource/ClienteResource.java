package br.com.curso.mc.resource;

import br.com.curso.mc.dto.ClienteDTO;
import br.com.curso.mc.entity.Cliente;
import br.com.curso.mc.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Integer id){
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
         List<Cliente> clientes = clienteService.findAll();
        List<ClienteDTO> clienteDTO = clientes.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
        return ResponseEntity.ok().body(clienteDTO);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ClienteDTO>> findAllByPage(
            @RequestParam(value = "page",defaultValue = "0")Integer page,
            @RequestParam(value = "linePerPage" ,defaultValue = "24") Integer linePerPage,
            @RequestParam(value = "orderBy",defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        Page<Cliente> pageClient = clienteService.findAllByPage(page, linePerPage, orderBy, direction);
        Page<ClienteDTO> clienteDTO = pageClient.map(cliente -> new ClienteDTO(cliente));
        return ResponseEntity.ok().body(clienteDTO);
    }

    @PostMapping
    public ResponseEntity<Void> saveClient(@RequestBody ClienteDTO clienteDTO){
        Cliente cliente = clienteService.fromDTO(clienteDTO);
        clienteService.saveClient(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClient(@RequestBody ClienteDTO clienteDTO,@PathVariable Integer id ){
        Cliente cliente = clienteService.fromDTO(clienteDTO);
        cliente.setId(id);
        clienteService.update(cliente);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
