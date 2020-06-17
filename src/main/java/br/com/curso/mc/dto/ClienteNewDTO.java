package br.com.curso.mc.dto;

import br.com.curso.mc.service.validation.ClientInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@ClientInsert
public class ClienteNewDTO implements Serializable {

    @NotNull(message = "Preenchimento Obrigatório")
    @NotEmpty(message = "O nome não pode estar vazio")
    @Length(min = 5,max = 120 , message = "O nome precisar ter entre 5 e 120 caracteres")
    private String nome;
    @NotNull(message = "Preenchimento Obrigatório")
    @Email(message = "Email inválido")
    private String email;
    @NotEmpty(message = "O CPF/CNPJ não poder estar vazio")
    @NotNull(message = "Preenchimento Obrigatório")
    private String cpfOuCnpj;
    @NotNull(message = "Preenchimento Obrigatório")
    private Integer tipoCliente;
    @NotNull(message = "Preenchimento Obrigatório")
    private String logradouro;
    @NotNull(message = "Preenchimento Obrigatório")
    private String numero;
    @NotNull(message = "Preenchimento Obrigatório")
    private String complemento;
    @NotNull(message = "Preenchimento Obrigatório")
    private String bairro;
    @NotNull(message = "Preenchimento Obrigatório")
    private String cep;
    @NotNull(message = "Preenchimento Obrigatório")
    private String telefone;
    private String telefone2;
    private String telefone3;
    @NotNull(message = "Preenchimento Obrigatório")
    private Integer idCidade;
    @NotNull
    @NotEmpty
    private String senha;

    public ClienteNewDTO(){

    }

    public ClienteNewDTO(String nome, String email, String cpnOuCnpj, Integer tipoCliente,
                         String logradouro, String numero, String complemento, String bairro,
                         String cep, String telefone, String telefone2, String telefone3, Integer idCidade) {
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpnOuCnpj;
        this.tipoCliente = tipoCliente;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.telefone = telefone;
        this.telefone2 = telefone2;
        this.telefone3 = telefone3;
        this.idCidade = idCidade;
    }

    public ClienteNewDTO(String nome,String senha, String email, String cpnOuCnpj, Integer tipoCliente,
                         String logradouro, String numero, String complemento, String bairro,
                         String cep, String telefone, String telefone2, String telefone3, Integer idCidade) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.cpfOuCnpj = cpnOuCnpj;
        this.tipoCliente = tipoCliente;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.telefone = telefone;
        this.telefone2 = telefone2;
        this.telefone3 = telefone3;
        this.idCidade = idCidade;
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

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public Integer getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(Integer tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
