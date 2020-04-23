package br.com.curso.mc.service;

import br.com.curso.mc.entity.Cliente;
import br.com.curso.mc.entity.Pedido;
import br.com.curso.mc.exception.ObjectNotFoundException;
import br.com.curso.mc.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido findById(Integer id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getSimpleName()));
    }

}
