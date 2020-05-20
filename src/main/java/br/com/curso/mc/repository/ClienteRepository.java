package br.com.curso.mc.repository;

import br.com.curso.mc.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
    @Transactional(readOnly = true)
    Cliente findByEmail(String email);
}
