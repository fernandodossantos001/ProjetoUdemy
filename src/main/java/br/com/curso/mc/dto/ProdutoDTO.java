package br.com.curso.mc.dto;

import br.com.curso.mc.entity.Produto;

import java.io.Serializable;

public class ProdutoDTO implements Serializable {
    private Integer id;
    private String nome;
    private Double preco;

    public ProdutoDTO(Integer id, String nome,Double preco) {
        this.id    = id;
        this.nome  = nome;
        this.preco = preco;
    }

    public ProdutoDTO(Produto produto){
        this.id    = produto.getId();
        this.nome  = produto.getNome();
        this.preco = produto.getPreco();
    }

    public ProdutoDTO() {
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
}
