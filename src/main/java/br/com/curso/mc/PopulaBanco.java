package br.com.curso.mc;

import br.com.curso.mc.entity.*;
import br.com.curso.mc.entity.enums.EstadoPagamento;
import br.com.curso.mc.entity.enums.TipoCliente;
import br.com.curso.mc.repository.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class PopulaBanco {


    public static void createCategoriaAndProduto(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository){
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

    public static void createClienteAndEndereco(ClienteRepository clienteRepository,
                                                EnderecoRepository enderecoRepository,
                                                CidadeRepository cidadeRepository,
                                                EstadoRepository estadoRepository){
        Cliente clienteMaria = new Cliente("Maria Silva", "maria@gmail.com"
                ,"36378912377" , TipoCliente.PESSOAFISICA);

        clienteMaria.getTelefones().addAll(Arrays.asList("27218996","11964842299"));


        Optional<Estado> estadoMinas = estadoRepository.findById(1);
        Optional<Estado> estadoSaoPaulo = estadoRepository.findById(2);
        Optional<Cidade> cidadeUberlancia = cidadeRepository.findById(1);
        Optional<Cidade> cidadeSaoPaulo = cidadeRepository.findById(3);


        Endereco enderecoSaoPaulo = new Endereco("Rua Flores",
                "300",
                "apto 203",
                "Jardim",
                "0393020",
                cidadeUberlancia.get(),
                clienteMaria);
        Endereco enderecoMinasGerais = new Endereco("Avenida Matos",
                "105",
                "sala 800",
                "Centro",
                "38777012",
                cidadeSaoPaulo.get(),
                clienteMaria);

        clienteMaria.setEnderecos(Arrays.asList(enderecoSaoPaulo,enderecoMinasGerais));

        clienteRepository.save(clienteMaria);
        enderecoRepository.saveAll(Arrays.asList(enderecoMinasGerais,enderecoSaoPaulo));
    }

    public static void createPedido(PedidoRepository pedidoRepository,PagamentoRepository pagamentoRepository,ClienteRepository clienteRepository,EnderecoRepository enderecoRepository) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Optional<Cliente> cliente = clienteRepository.findById(1);
        Optional<Endereco> endereco1 = enderecoRepository.findById(1);
        Optional<Endereco> endereco2 = enderecoRepository.findById(2);

        Pedido pedido = new Pedido(dateFormat.parse("30/09/2019 10:32"),cliente.get(),endereco1.get());
        Pedido pedido2 = new Pedido(dateFormat.parse("10/01/2019 19:54"),cliente.get(),endereco2.get());

        Pagamento pagamentoCartao = new PagamentoComCartao(EstadoPagamento.QUITADO,pedido,6);
        pedido.setPagamento(pagamentoCartao);

        Pagamento pagamentoBoleto = new PagamentoComBoleto(EstadoPagamento.PENDENTE,pedido2,dateFormat.parse("13/01/2019 00:00"),null);
        pedido2.setPagamento(pagamentoBoleto);

        cliente.get().setPedidos(Arrays.asList(pedido,pedido2));

        pedidoRepository.saveAll(Arrays.asList(pedido,pedido2));
        pagamentoRepository.saveAll(Arrays.asList(pagamentoBoleto,pagamentoCartao));
    }
}
