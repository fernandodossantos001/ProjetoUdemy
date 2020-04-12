package br.com.curso.mc;

import br.com.curso.mc.entity.Categoria;
import br.com.curso.mc.entity.Produto;
import br.com.curso.mc.repository.CategoriaRepository;
import br.com.curso.mc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria categoriainformatica= new Categoria("Informática" );
        Categoria categoriaEscritorio = new Categoria("Escritório" );

        Produto produtoComputador = new Produto("Computador", 2000.00);
        Produto produtoImpressora = new Produto("Impressora", 800.00);
        Produto produtoMouse = new Produto("Mouse", 80.00);

        categoriainformatica.setProdutos(Arrays.asList(produtoComputador,produtoImpressora,produtoMouse));
        categoriaEscritorio.setProdutos(Arrays.asList(produtoImpressora));

        produtoComputador.setCategorias(Arrays.asList(categoriainformatica));
        produtoImpressora.setCategorias(Arrays.asList(categoriainformatica,categoriaEscritorio));
        produtoMouse.setCategorias(Arrays.asList(categoriainformatica));

        categoriaRepository.saveAll(Arrays.asList(categoriainformatica,categoriaEscritorio));

        produtoRepository.saveAll(Arrays.asList(produtoImpressora,produtoComputador,produtoMouse));

    }
}
