package br.com.curso.mc.dto;

import br.com.curso.mc.entity.Categoria;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {
    private Integer id;
    private String nome;

    public CategoriaDTO(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaDTO(){

    }

    public CategoriaDTO(Categoria categoria){
        this.nome = categoria.getNome();
        this.id = categoria.getId();
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
}
