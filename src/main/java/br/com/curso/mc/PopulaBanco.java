package br.com.curso.mc;

import br.com.curso.mc.entity.Categoria;
import br.com.curso.mc.entity.Cidade;
import br.com.curso.mc.entity.Estado;
import br.com.curso.mc.entity.Produto;
import br.com.curso.mc.repository.CategoriaRepository;
import br.com.curso.mc.repository.CidadeRepository;
import br.com.curso.mc.repository.EstadoRepository;
import br.com.curso.mc.repository.ProdutoRepository;

import java.util.Arrays;

public class PopulaBanco {


    public static void CreateCategoriaAndProduto(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository){
        Categoria categoriainformatica= new Categoria("Informatica" );
        Categoria categoriaEscritorio = new Categoria("Escritorio" );

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

    public static void createEsdadoAndCidade(CidadeRepository cidadeRepository, EstadoRepository estadoRepository){
        Estado estadoMinas = new Estado("Minas Gerais");
        Estado estadoSaoPaulo = new Estado("Sao Paulo");

        Cidade cidadeUberlancia = new Cidade("Uberlandia",estadoMinas);
        Cidade cidadeSaoPaulo = new Cidade("Sao Paulo",estadoSaoPaulo);
        Cidade cidadeCampinas = new Cidade("Campinas",estadoSaoPaulo);

        estadoMinas.setCidades(Arrays.asList(cidadeUberlancia));
        estadoSaoPaulo.setCidades(Arrays.asList(cidadeCampinas,cidadeSaoPaulo));

        estadoRepository.saveAll(Arrays.asList(estadoMinas,estadoSaoPaulo));
        cidadeRepository.saveAll(Arrays.asList(cidadeUberlancia,cidadeCampinas,cidadeSaoPaulo));
    }

}
