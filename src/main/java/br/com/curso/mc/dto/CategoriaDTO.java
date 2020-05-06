package br.com.curso.mc.dto;

import br.com.curso.mc.entity.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CategoriaDTO implements Serializable {
    @NotNull(message = "nome nãoo pode ser nulo")
    @NotBlank(message = "nome não pode estar vazio")
    @Length(min = 5,max = 80,message = "O tamanho precisar ser entreb 5 e 80 caracteres")
    private String nome;
    private Integer id;
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
