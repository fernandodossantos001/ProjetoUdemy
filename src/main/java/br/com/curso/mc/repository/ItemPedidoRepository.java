package br.com.curso.mc.repository;

import br.com.curso.mc.entity.ItemPedido;
import br.com.curso.mc.entity.ItemPedidoPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
