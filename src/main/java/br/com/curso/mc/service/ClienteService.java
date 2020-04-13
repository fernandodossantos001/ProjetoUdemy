package br.com.curso.mc.service;

import br.com.curso.mc.entity.Cliente;
import br.com.curso.mc.exception.ObjectNotFoundException;
import br.com.curso.mc.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Optional<Cliente> findById(Integer id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return Optional.ofNullable(cliente.orElseThrow(() -> {
            throw new ObjectNotFoundException("Objeto não encontrado id " + id + ", tipo " + Cliente.class.getSimpleName());
        }));
    }

}
