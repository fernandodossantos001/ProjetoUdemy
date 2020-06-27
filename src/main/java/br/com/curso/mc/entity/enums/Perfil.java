package br.com.curso.mc.entity.enums;

public enum Perfil {
    ADMIN(1,"ROLE_ADMIN"),
    CLIENTE(2,"ROLE_CLIENTE");

    private Integer codigo;
    private String descricao;


    private Perfil (Integer codigo,String descricao){
        this.codigo    = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static Perfil toEnum(Integer codigo){
        if(null== codigo)
            return null;

        for(Perfil perfil:Perfil.values()){
            if (codigo == perfil.getCodigo()){
                return perfil;
            }
        }

        throw new IllegalArgumentException("Codigo inválido" + codigo);
    }
}
