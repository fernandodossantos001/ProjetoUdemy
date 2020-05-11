package br.com.curso.mc.dto;

import br.com.curso.mc.entity.Cliente;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ClienteDTO implements Serializable {
    private Integer id;
    @NotNull(message = "O nome não pode ser nulo")
    @NotEmpty(message = "O nome não pode estar vazio")
    @Length(min = 5,max = 120 , message = "O nome precisar ter entre 5 e 120 caracteres")
    private String nome;
    @NotEmpty(message = "O email não pode ser nulo")
    @NotEmpty(message = "O email não pode estar vazio")
    @Email(message = "Email inválido")
    private String email;

    public ClienteDTO(Cliente cliente){
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
    }

    public ClienteDTO(){

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
