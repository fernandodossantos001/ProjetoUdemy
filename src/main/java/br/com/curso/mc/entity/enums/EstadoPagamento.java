package br.com.curso.mc.entity.enums;

public enum EstadoPagamento {
    PENDENTE("Pendende",1),
    QUITADO("Quitado",2),
    CANCELADO("Cancelado",3);

    private String estado;
    private Integer codigo;

    private EstadoPagamento(String estado, Integer codigo){
        this.estado = estado;
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public static EstadoPagamento toEnum(Integer codigo){
        if (codigo == null)
           return null;

        for(EstadoPagamento estadoPagamento: EstadoPagamento.values()){
            if(estadoPagamento.codigo == codigo){
                return estadoPagamento;
            }
        }

        throw new IllegalArgumentException("Codigo inválido " + codigo);
    }

}
