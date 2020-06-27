package br.com.curso.mc.entity;

import br.com.curso.mc.entity.enums.Perfil;
import br.com.curso.mc.entity.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

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
    @Column(name = "ds_cpf_cnpj")
    private String cpfOuCnpj;
    @Column(name = "ds_tipo_cliente")
    private Integer tipoCliente;
    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
    private List<Endereco> enderecos;
    @ElementCollection
    @CollectionTable(name = "t_telefone")
    private Set<String> telefones;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Pedido> pedidos;
    @JsonIgnore
    private String senha;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "t_perfis")
    private Set<Integer> perfis = new HashSet<Integer>();;

    public Cliente() {
        enderecos = new ArrayList<Endereco>();
        telefones = new HashSet<String>();
        pedidos = new ArrayList<Pedido>();
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(String nome, String email, String cpnOuCnpj, TipoCliente tipoCliente) {
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpnOuCnpj;
        this.tipoCliente = (tipoCliente==null) ? null: tipoCliente.getCodigo();
        enderecos = new ArrayList<Endereco>();
        telefones = new HashSet<String>();
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(String nome,String senha, String email, String cpnOuCnpj, TipoCliente tipoCliente) {
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpnOuCnpj;
        this.tipoCliente = (tipoCliente==null) ? null: tipoCliente.getCodigo();
        enderecos = new ArrayList<Endereco>();
        telefones = new HashSet<String>();
        this.senha = senha;
        addPerfil(Perfil.CLIENTE);
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

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public TipoCliente getTipoCliente() {
        return TipoCliente.toEnum(tipoCliente);
    }

//    public void setTipoCliente(TipoCliente tipoCliente) {
//        this.tipoCliente = tipoCliente.getCodigo();
//    }

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

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void setTipoCliente(Integer tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis(){
        return perfis.stream().map(perfil -> Perfil.toEnum(perfil)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil){
        perfis.add(perfil.getCodigo());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) &&
                Objects.equals(nome, cliente.nome) &&
                Objects.equals(email, cliente.email) &&
                Objects.equals(cpfOuCnpj, cliente.cpfOuCnpj) &&
                Objects.equals(tipoCliente, cliente.tipoCliente) &&
                Objects.equals(enderecos, cliente.enderecos) &&
                Objects.equals(telefones, cliente.telefones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, cpfOuCnpj, tipoCliente, enderecos, telefones);
    }
}
