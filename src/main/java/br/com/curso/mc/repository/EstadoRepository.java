package br.com.curso.mc.repository;

import br.com.curso.mc.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado,Integer> {
}
