package br.com.curso.mc.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_estado")
public class Estado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Integer id;
    @Column(name = "nm_estado")
    private String nome;

    @OneToMany(mappedBy = "estado")
    private List<Cidade> cidades;

    public Estado(){
        cidades = new ArrayList<>();
    }

    public Estado(String nomeEstado) {
        this.nome = nomeEstado;
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

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estado estado = (Estado) o;
        return Objects.equals(id, estado.id) &&
                Objects.equals(nome, estado.nome) &&
                Objects.equals(cidades, estado.cidades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cidades);
    }
}
