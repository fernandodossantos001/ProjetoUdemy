package br.com.curso.mc.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "t_endereco")
public class Endereco  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Integer id;
    @Column(name = "ds_logradouro")
    private String logradouro;
    @Column(name = "nr_numero")
    private String numero;
    @Column(name = "ds_complemento")
    private String complemento;
    @Column(name = "ds_bairro")
    private String bairro;
    @Column(name = "ds_cep")
    private String cep;
    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private Cidade cidade;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public Endereco() {
    }

    public Endereco(String logradouro, String numero, String complemento, String bairro, String cep, Cidade cidade, Cliente cliente) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(id, endereco.id) &&
                Objects.equals(logradouro, endereco.logradouro) &&
                Objects.equals(numero, endereco.numero) &&
                Objects.equals(complemento, endereco.complemento) &&
                Objects.equals(bairro, endereco.bairro) &&
                Objects.equals(cep, endereco.cep) &&
                Objects.equals(cidade, endereco.cidade) &&
                Objects.equals(cliente, endereco.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, logradouro, numero, complemento, bairro, cep, cidade, cliente);
    }
}
