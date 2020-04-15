package br.com.curso.mc.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "t_pedido")
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer id;
    @Column(name = "dt_instante")
    private Date instante;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "pedido")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_endereco_entrega")
    private Endereco enderecoEntraga;

    public Pedido() {
    }

    public Pedido(Date instante, Pagamento pagamento, Cliente cliente, Endereco enderecoEntraga) {
        this.instante = instante;
        this.pagamento = pagamento;
        this.cliente = cliente;
        this.enderecoEntraga = enderecoEntraga;
    }

    public Pedido(Date instante, Cliente cliente, Endereco endereco) {
        this.instante = instante;
        this.cliente = cliente;
        this.enderecoEntraga = endereco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstante() {
        return instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEnderecoEntraga() {
        return enderecoEntraga;
    }

    public void setEnderecoEntraga(Endereco enderecoEntraga) {
        this.enderecoEntraga = enderecoEntraga;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id) &&
                Objects.equals(instante, pedido.instante) &&
                Objects.equals(pagamento, pedido.pagamento) &&
                Objects.equals(cliente, pedido.cliente) &&
                Objects.equals(enderecoEntraga, pedido.enderecoEntraga);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, instante, pagamento, cliente, enderecoEntraga);
    }
}
