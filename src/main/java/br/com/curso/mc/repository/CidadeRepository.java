package br.com.curso.mc.repository;

import br.com.curso.mc.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade,Integer> {
}
