package br.com.curso.mc.entity;

import br.com.curso.mc.entity.enums.TipoCliente;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "t_cliente")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer id;
    @Column(name = "nm_nome")
    private String nome;
    @Column(name = "ds_email")
    private String email;
    @Column(name = "ds_cpn_cnpj")
    private String cpnOuCnpj;
    @Column(name = "ds_tipo_cliente")
    private Integer tipoCliente;
    @OneToMany(mappedBy = "cliente")
    private List<Endereco> enderecos;
    @ElementCollection
    @CollectionTable(name = "t_telefone")
    private Set<String> telefones;

    public Cliente() {
        enderecos = new ArrayList<Endereco>();
        telefones = new HashSet<String>();
    }

    public Cliente(String nome, String email, String cpnOuCnpj, TipoCliente tipoCliente) {
        this.nome = nome;
        this.email = email;
        this.cpnOuCnpj = cpnOuCnpj;
        this.tipoCliente = tipoCliente.getCodigo();
        enderecos = new ArrayList<Endereco>();
        telefones = new HashSet<String>();
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

    public String getCpnOuCnpj() {
        return cpnOuCnpj;
    }

    public void setCpnOuCnpj(String cpnOuCnpj) {
        this.cpnOuCnpj = cpnOuCnpj;
    }

    public TipoCliente getTipoCliente() {
        return TipoCliente.toEnum(tipoCliente);
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente.getCodigo();
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) &&
                Objects.equals(nome, cliente.nome) &&
                Objects.equals(email, cliente.email) &&
                Objects.equals(cpnOuCnpj, cliente.cpnOuCnpj) &&
                Objects.equals(tipoCliente, cliente.tipoCliente) &&
                Objects.equals(enderecos, cliente.enderecos) &&
                Objects.equals(telefones, cliente.telefones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, cpnOuCnpj, tipoCliente, enderecos, telefones);
    }
}
