package br.com.escalarte.crudescalarte.model;

import java.io.Serializable; //permite que objetos dessa classe possam ser salvos em arquivos ou enviados pela rede
import java.time.LocalDate;

public class Contrato implements Serializable {

    //Atributos
    private String nomeColaborador;
    private String cargo;
    private double salarioBase;
    private int cargaHorariaSemanal;
    private String status;
    private LocalDate dataInicio;
    private LocalDate dataCriacao;

    //Construtor Não Padrão
    public Contrato(String nomeColaborador, String cargo, double salarioBase, int cargaHorariaSemanal, String status, LocalDate dataInicio, LocalDate dataCriacao) {
        this.nomeColaborador = nomeColaborador;
        this.cargo = cargo;
        this.salarioBase = salarioBase;
        this.cargaHorariaSemanal = cargaHorariaSemanal;
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataCriacao = dataCriacao;
    }

    // Getters e Setters
    public String getNomeColaborador() { return nomeColaborador; }
    public void setNomeColaborador(String nomeColaborador) { this.nomeColaborador = nomeColaborador; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public double getSalarioBase() { return salarioBase; }
    public void setSalarioBase(double salarioBase) { this.salarioBase = salarioBase; }

    public int getCargaHorariaSemanal() { return cargaHorariaSemanal; }
    public void setCargaHorariaSemanal(int cargaHorariaSemanal) { this.cargaHorariaSemanal = cargaHorariaSemanal; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDate dataCriacao) { this.dataCriacao = dataCriacao; }

    //Define como o contrato será representado como texto
    @Override //quando um método é herdado da superclasse
    public String toString() {
        return nomeColaborador + " - " + cargo + " - " + status;
    }
}


