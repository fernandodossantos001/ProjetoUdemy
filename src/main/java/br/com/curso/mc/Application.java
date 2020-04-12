package br.com.curso.mc;

import br.com.curso.mc.entity.Categoria;
import br.com.curso.mc.entity.Cidade;
import br.com.curso.mc.entity.Estado;
import br.com.curso.mc.entity.Produto;
import br.com.curso.mc.repository.CategoriaRepository;
import br.com.curso.mc.repository.CidadeRepository;
import br.com.curso.mc.repository.EstadoRepository;
import br.com.curso.mc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private  CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;


    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       PopulaBanco.createEsdadoAndCidade(cidadeRepository,estadoRepository);
    }
}
