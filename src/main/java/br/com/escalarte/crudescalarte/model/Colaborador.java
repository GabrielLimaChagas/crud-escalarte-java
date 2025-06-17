package br.com.escalarte.crudescalarte.model;

import java.io.Serializable;

public class Colaborador extends BaseModel implements Serializable {
    private String nome;
    private String senha;
    private String dataNascimento;

    public Colaborador(int id, String nome, String senha, String dataNascimento) {
        super(id);
        this.nome = nome;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        String a = "Id: " + getId() + "\n";
        String b = "Nome: " + getNome() + "\n";
        String c = "Senha: " + getSenha() + "\n";
        String d = "Data de Nascimento: " + getDataNascimento() + "\n";
        return a + b + c + d;
    }
}
