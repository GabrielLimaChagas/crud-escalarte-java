package br.com.escalarte.crudescalarte.Setor;

import java.io.Serializable;

public class Setor implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idSetor;
    private int quantidadeColaboradores;
    private String nomeSetor;


    public Setor(int idSetor, String nomeSetor, int quantidadeColaboradores) {
        this.idSetor = idSetor;
        this.nomeSetor = nomeSetor;
        this.quantidadeColaboradores = quantidadeColaboradores;
    }

    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    public int getQuantidadeColaboradores() {
        return quantidadeColaboradores;
    }

    public void setQuantidadeColaboradores(int quantidadeColaboradores) {
        this.quantidadeColaboradores = quantidadeColaboradores;
    }

    public String toString() {
        return this.nomeSetor;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Setor setor = (Setor) obj;
        return this.idSetor == setor.idSetor;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idSetor);
    }
}
