package br.com.escalarte.crudescalarte.model;

import java.io.Serializable;
import java.time.LocalTime;

public class Turno extends BaseModel implements Serializable {
    private String nome;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;

    public Turno(int id, String nome, LocalTime horarioInicio, LocalTime horarioFim) {
        super(id);
        this.nome = nome;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
    }

    public Turno() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    @Override
    public String toString() {
        String a = "Id: " + getId() + "\n";
        String b = "Nome: " + getNome() + "\n";
        String c = "Horário Inicio: " + getHorarioInicio() + "\n";
        String d = "Horário Fim: " + getHorarioFim() + "\n";
        return a + b + c + d;
    }
}
