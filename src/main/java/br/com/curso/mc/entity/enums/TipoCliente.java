package br.com.curso.mc.entity.enums;

public enum TipoCliente {

    PESSOAFISICA (1,"Pessoa Fisica"),
    PESSOAJURIDICA (2, "Pessoa Juridica");

    private int codigo;
    private String descricao;

    private TipoCliente(int codigo,String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoCliente toEnum(Integer codigo){
        if (codigo == null)
                return null;

        for (TipoCliente tipoCliente: TipoCliente.values()){
            if(tipoCliente.getCodigo() == codigo)
                return tipoCliente;
        }

        throw new IllegalArgumentException("Id inválido" + codigo);
    }
}
