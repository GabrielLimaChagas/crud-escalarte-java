package br.com.escalarte.crudescalarte.model;

public class Cargo extends BaseModel {
    public static final String NOME_ARQUIVO = "cargos.dat";
    private String nome;
    private int cargaHoraria;
    private Integer interjornada;

    public Cargo(int id, String nomeCargo, Integer cargaHorarioLimite, Integer intervaloInterjornada) {
        super(id);
        this.nome = nomeCargo;
        this.cargaHoraria = cargaHorarioLimite;
        this.interjornada = intervaloInterjornada;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nomeCargo) {
        this.nome = nomeCargo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }
    public void setCargaHoraria(int cargaHorarioLimite) {
        this.cargaHoraria = cargaHorarioLimite;
    }

    public Integer getInterjornada() {
        return interjornada;
    }
    public void setInterjornada(Integer intervaloInterjornada) {
        this.interjornada = intervaloInterjornada;
    }
}
