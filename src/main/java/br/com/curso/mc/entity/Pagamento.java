package br.com.curso.mc.entity;
import br.com.curso.mc.entity.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "t_pagamento")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {
    @Id
    @Column(name = "id_pagamento")
    private Integer id;
    @Column(name = "nr_status_pagamento")
    private Integer estadoPagamento;

    @OneToOne
    @JoinColumn(name = "id_pedido")
    @MapsId
    @JsonBackReference
    private Pedido pedido;

    public Pagamento(EstadoPagamento estadoPagamento, Pedido pedido) {
        this.estadoPagamento = estadoPagamento.getCodigo();
        this.pedido = pedido;
    }

    public Pagamento(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoPagamento getEstadoPagamento() {
        return EstadoPagamento.toEnum(this.estadoPagamento);
    }

    public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
        this.estadoPagamento = estadoPagamento.getCodigo();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id) &&
                estadoPagamento == pagamento.estadoPagamento &&
                Objects.equals(pedido, pagamento.pedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estadoPagamento, pedido);
    }
}
