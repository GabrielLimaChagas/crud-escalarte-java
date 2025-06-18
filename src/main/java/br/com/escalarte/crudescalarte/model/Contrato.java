package br.com.escalarte.crudescalarte.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Contrato extends BaseModel implements Serializable {

    private String colaborador;
    private String cargo;
    private double cargaHorariaDiaria;
    private String status;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int diasTrabalhoSemanal;
    private List<String> diasFolgaSemanal;

    public Contrato(int id, String status, double cargaHorariaDiaria, String cargo, String colaborador,
                    LocalDate dataInicio, LocalDate dataFim,
                    int diasTrabalhoSemanal, List<String> diasFolgaSemanal) {
        super(id);
        this.status = status;
        this.cargaHorariaDiaria = cargaHorariaDiaria;
        this.cargo = cargo;
        this.colaborador = colaborador;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.diasTrabalhoSemanal = diasTrabalhoSemanal;
        this.diasFolgaSemanal = diasFolgaSemanal;
    }

    // Getters e Setters

    public String getColaborador() {
        return colaborador;
    }

    public void setColaborador(String colaborador) {
        this.colaborador = colaborador;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getCargaHorariaDiaria() {
        return cargaHorariaDiaria;
    }

    public void setCargaHorariaDiaria(double cargaHorariaDiaria) {
        this.cargaHorariaDiaria = cargaHorariaDiaria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public int getDiasTrabalhoSemanal() {
        return diasTrabalhoSemanal;
    }

    public void setDiasTrabalhoSemanal(int diasTrabalhoSemanal) {
        this.diasTrabalhoSemanal = diasTrabalhoSemanal;
    }

    public List<String> getDiasFolgaSemanal() {
        return diasFolgaSemanal;
    }

    public void setDiasFolgaSemanal(List<String> diasFolgaSemanal) {
        this.diasFolgaSemanal = diasFolgaSemanal;
    }
    @Override
    public String toString() {
        return colaborador + " - " + cargo + " - " + status;
    }
}
