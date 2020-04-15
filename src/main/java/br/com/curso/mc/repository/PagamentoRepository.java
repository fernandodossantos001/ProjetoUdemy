package br.com.curso.mc.repository;

import br.com.curso.mc.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento,Integer> {
}
