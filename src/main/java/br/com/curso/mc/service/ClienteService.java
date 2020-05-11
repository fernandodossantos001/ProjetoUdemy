package br.com.curso.mc.service;

import br.com.curso.mc.dto.ClienteDTO;
import br.com.curso.mc.entity.Cliente;
import br.com.curso.mc.exception.ObjectNotFoundException;
import br.com.curso.mc.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findById(Integer id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() ->
             new ObjectNotFoundException("Objeto não encontrado id " + id + ", tipo " + Cliente.class.getSimpleName()));
    }

    public Page<Cliente> findAllByPage(Integer page, Integer linePerPage, String orderBy, String direction){
        PageRequest pageClients = PageRequest.of(page, linePerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageClients);
    }
    
    public Cliente fromDTO(ClienteDTO clienteDTO){
        return new Cliente(clienteDTO.getNome(),clienteDTO.getEmail(),null,null);
    }


    public Cliente saveClient(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void delete(Integer id) {
        findById(id);
        clienteRepository.deleteById(id);
    }

    public Cliente update(Cliente client) {
        Cliente newClient = findById(client.getId());
        updateClient(newClient,client);
        return clienteRepository.save(newClient);
    }

    private void updateClient(Cliente newClient, Cliente client) {
        newClient.setNome(client.getNome());
        newClient.setEmail(client.getEmail());
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
}
