package br.com.curso.mc.service;

import br.com.curso.mc.entity.ItemPedido;
import br.com.curso.mc.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ItemPedidoService {
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    public List<ItemPedido> saveItemPedido(Set<ItemPedido> itemPedido){
        return itemPedidoRepository.saveAll(itemPedido);
    }
}
