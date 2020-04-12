package br.com.curso.mc.repository;

import br.com.curso.mc.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco,Integer> {
}
