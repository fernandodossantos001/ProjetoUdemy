package br.com.curso.mc;

import br.com.curso.mc.entity.*;
import br.com.curso.mc.entity.enums.EstadoPagamento;
import br.com.curso.mc.entity.enums.TipoCliente;
import br.com.curso.mc.repository.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Arrays.*;

public class PopulaBanco {


    public static void createCategoriaAndProduto(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository){
        Categoria categoriainformatica= new Categoria("Informatica" );
        Categoria categoriaEscritorio = new Categoria("Escritorio" );

        Produto produtoComputador = new Produto("Computador", 2000.00);
        Produto produtoImpressora = new Produto("Impressora", 800.00);
        Produto produtoMouse = new Produto("Mouse", 80.00);

        categoriainformatica.setProdutos(asList(produtoComputador,produtoImpressora,produtoMouse));
        categoriaEscritorio.setProdutos(asList(produtoImpressora));

        produtoComputador.setCategorias(asList(categoriainformatica));
        produtoImpressora.setCategorias(asList(categoriainformatica,categoriaEscritorio));
        produtoMouse.setCategorias(asList(categoriainformatica));

        categoriaRepository.saveAll(asList(categoriainformatica,categoriaEscritorio));

        produtoRepository.saveAll(asList(produtoImpressora,produtoComputador,produtoMouse));
    }

    public static void createEsdadoAndCidade(CidadeRepository cidadeRepository, EstadoRepository estadoRepository){
        Estado estadoMinas = new Estado("Minas Gerais");
        Estado estadoSaoPaulo = new Estado("Sao Paulo");

        Cidade cidadeUberlancia = new Cidade("Uberlandia",estadoMinas);
        Cidade cidadeSaoPaulo = new Cidade("Sao Paulo",estadoSaoPaulo);
        Cidade cidadeCampinas = new Cidade("Campinas",estadoSaoPaulo);

        estadoMinas.setCidades(asList(cidadeUberlancia));
        estadoSaoPaulo.setCidades(asList(cidadeCampinas,cidadeSaoPaulo));

        estadoRepository.saveAll(asList(estadoMinas,estadoSaoPaulo));
        cidadeRepository.saveAll(asList(cidadeUberlancia,cidadeCampinas,cidadeSaoPaulo));
    }

    public static void createClienteAndEndereco(ClienteRepository clienteRepository,
                                                EnderecoRepository enderecoRepository,
                                                CidadeRepository cidadeRepository,
                                                EstadoRepository estadoRepository){
        Cliente clienteMaria = new Cliente("Maria Silva", "maria@gmail.com"
                ,"36378912377" , TipoCliente.PESSOAFISICA);

        clienteMaria.getTelefones().addAll(asList("27218996","11964842299"));


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

        clienteMaria.setEnderecos(asList(enderecoSaoPaulo,enderecoMinasGerais));

        clienteRepository.save(clienteMaria);
        enderecoRepository.saveAll(asList(enderecoMinasGerais,enderecoSaoPaulo));
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

        cliente.get().setPedidos(asList(pedido,pedido2));

        pedidoRepository.saveAll(asList(pedido,pedido2));
        pagamentoRepository.saveAll(asList(pagamentoBoleto,pagamentoCartao));

    }

    public static void createItemPedido(PedidoRepository pedidoRepository,ProdutoRepository produtoRepository,ItemPedidoRepository itemPedidoRepository){
        Optional<Pedido> pedido = pedidoRepository.findById(1);
        Optional<Pedido> pedido2 = pedidoRepository.findById(2);
        Optional<Produto> impressora = produtoRepository.findById(1);
        Optional<Produto> computador = produtoRepository.findById(2);
        Optional<Produto> mouse = produtoRepository.findById(3);

        ItemPedido itemPedidoComputador = new ItemPedido(pedido.get(),computador.get(),0.0,1,2000.0);
        ItemPedido itemPedidoMouse = new ItemPedido(pedido.get(),mouse.get(),0.0,2,80.0);
        ItemPedido itemPedidoImpressora = new ItemPedido(pedido.get(),impressora.get(),100.0,1,800.0);

        pedido.get().getItens().addAll(Arrays.asList(itemPedidoComputador,itemPedidoMouse));

        pedido2.get().getItens().addAll(Arrays.asList(itemPedidoImpressora));

        impressora.get().getItens().addAll(Arrays.asList(itemPedidoImpressora));
        mouse.get().getItens().addAll(Arrays.asList(itemPedidoMouse));
        computador.get().getItens().addAll(Arrays.asList(itemPedidoComputador));


        itemPedidoRepository.saveAll(Arrays.asList(itemPedidoImpressora,itemPedidoComputador,itemPedidoMouse));

        pedidoRepository.saveAll(Arrays.asList(pedido.get(),pedido2.get()));


    }
}
