package br.com.escalarte.crudescalarte.model;

import java.io.Serializable;

public class Colaborador extends BaseModel implements Serializable {
    private String nome;
    private String senha;
    private String dataNascimento;
    private String email;
    private String telefone;
    private String cpf;
    private TipoUsuario tipoUsuario;

    public enum TipoUsuario {
        ADMIN,
        GERENTE,
        OPERADOR
    }

    public Colaborador(int id, String nome, String senha, String dataNascimento, String email, String telefone, String cpf, TipoUsuario tipoUsuario) {
        super(id);
        this.nome = nome;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.tipoUsuario = tipoUsuario;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        String a = "Id: " + getId() + "\n";
        String b = "Nome: " + getNome() + "\n";
        String c = "Senha: " + getSenha() + "\n";
        String d = "Data de Nascimento: " + getDataNascimento() + "\n";
        String e = "Email: " + getEmail() + "\n";
        String f = "Telefone: " + getTelefone() + "\n";
        String g = "CPF: " + getCpf() + "\n";
        return a + b + c + d + e + f + g;
    }
}
