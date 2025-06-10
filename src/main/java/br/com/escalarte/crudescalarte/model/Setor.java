package br.com.escalarte.crudescalarte.model;

import java.io.Serializable;

public class Setor extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nomeSetor;
    private String nomeGerente;
    private int quantidadeColaboradores;

    public Setor( int id, String nomeSetor, String nomeGerente, int quantidadeColaboradores) {
        super(id);
        this.nomeSetor = nomeSetor;
        this.nomeGerente = nomeGerente;
        this.quantidadeColaboradores = quantidadeColaboradores;
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

    public String getNomeGerente() {
        return nomeGerente;
    }

    public void setNomeGerente(String nomeGerente) {
        this.nomeGerente = nomeGerente;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + "\n"
                + "Setor: " + getNomeSetor() + "\n"
                + "Gerente: " + getNomeGerente() + "\n"
                + "Colaboradores: " + getQuantidadeColaboradores() + "\n";
    }

}

