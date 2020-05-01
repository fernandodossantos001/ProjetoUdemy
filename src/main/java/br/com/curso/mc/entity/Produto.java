package br.com.curso.mc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "t_produto")
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preco;
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "t_produto_categoria",
            joinColumns = @JoinColumn(name = "id_produto"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private List<Categoria> categorias;

    @OneToMany(mappedBy = "id.produto",fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<ItemPedido> itens = new HashSet<ItemPedido>();

    public Produto(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Produto(){
        this.categorias = new ArrayList<Categoria>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    @JsonIgnore
    public List<Pedido> getPedidos(){
        List<Pedido> pedidos = new ArrayList<Pedido>();
        itens.forEach(itemPedido -> {
            pedidos.add(itemPedido.getPedido());
        });

        return pedidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id.equals(produto.id) &&
                Objects.equals(nome, produto.nome) &&
                Objects.equals(preco, produto.preco) &&
                Objects.equals(categorias, produto.categorias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, preco, categorias);
    }
}
