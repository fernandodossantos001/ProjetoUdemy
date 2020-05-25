package br.com.curso.mc.repository;

import br.com.curso.mc.entity.Categoria;
import br.com.curso.mc.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {
    @Transactional(readOnly=true)
    @Query("SELECT DISTINCT produto from Produto  produto INNER  JOIN produto.categorias categorias where produto.nome LIKE %:nome% AND categorias IN :categoriasRecebidas")
    Page<Produto> search(@Param("nome") String nome,@Param("categoriasRecebidas") List<Categoria> categoriasRecebidas, Pageable pageRequest);

}
