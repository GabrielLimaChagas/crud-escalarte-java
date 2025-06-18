package br.com.escalarte.crudescalarte.dao;

import br.com.escalarte.crudescalarte.model.Colaborador;
import br.com.escalarte.crudescalarte.model.Turno;
import br.com.escalarte.crudescalarte.util.AlertUtils;
import br.com.escalarte.crudescalarte.util.ObjectPersistenceUtils;
import br.com.escalarte.crudescalarte.util.ValidationUtils;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


import java.util.ArrayList;


public class ColaboradorDAO {
    private static ArrayList<Colaborador> colaboradores = new ArrayList<>();

    public static ArrayList<Colaborador> getColaboradores() {
        return colaboradores;
    }

    private static int gerarNovoId() {
        if (colaboradores.isEmpty()) {
            return 1;
        }
        return colaboradores.stream()
                .mapToInt(Colaborador::getId)
                .max()
                .getAsInt() + 1;
    }

    public static boolean cadastrar(String nome, String senha, String dataNascimento, String email, String telefone, String cpf) {

        int novoId = gerarNovoId();

        try {
            ValidationUtils.validarNome(nome);
        } catch (IllegalArgumentException e) {
            AlertUtils.mostrarErro("Erro", "Nome inválido. Use apenas letras de A a Z (maiúsculas ou minúsculas).");
            return false;
        }
        if (nome.length() < 4 || nome.length() > 50) {
            AlertUtils.mostrarErro("Erro", "O nome deve ter entre 4 a 50 caracteres");
            return false;
        }
        if (!ValidationUtils.validarDataNascimento(dataNascimento)) {
            AlertUtils.mostrarErro("Erro", "Data de nascimento inválida. " + "Use um dos formatos: DD-MM-YYYY ou DD/MM/YYYY." );
            return false;
        } else if (!ValidationUtils.validarEmail(email)) {
            AlertUtils.mostrarErro("Erro", "Email inválido. Use um formato como exemplo@dominio.com.");
            return false;
        }
        else if (!ValidationUtils.validarTelefone(telefone)) {
            AlertUtils.mostrarErro("Erro","Telefone inválido. Use um formato como (11) 91234-5678 ou 1123456789.");
            return false;
        } else if (!ValidationUtils.validarCPF(cpf)) {
            AlertUtils.mostrarErro("Erro","CPF inválido. Use o formato 123.456.789-09 ou 12345678909.");
            return false;
        }

        for (Colaborador colaborador : colaboradores) {
            if (colaborador.getId() == novoId) {
                AlertUtils.mostrarErro("Erro", "ID já existente no sistema");
                return false;
            }
            if (colaborador.getNome().equals(nome)) {
                AlertUtils.mostrarErro("Erro", "Nome já existente no sistema");
                return false;
            }
            if (colaborador.getEmail().equals(email)) {
                AlertUtils.mostrarErro("Erro", "Email já existente no sistema");
                return false;
            }
            if (colaborador.getCpf().equals(cpf)) {
                AlertUtils.mostrarErro("Erro", "CPF já existente no sistema");
                return false;
            }
        }

        Colaborador colaborador = new Colaborador(novoId, nome, senha, dataNascimento, email, telefone, cpf);
        colaboradores.add(colaborador);

        ObjectPersistenceUtils.gravarDados("colaborador.dat", colaboradores);
        AlertUtils.mostrarInfo("Cadastro", "Colaborador cadastrado com sucesso");
        return true;
    }

    public static boolean editar(
            String id,
            String nome,
            String senha,
            String dataNascimento,
            String email,
            String telefone,
            String cpf) {

        int novoId = ValidationUtils.strParaInt(id);
        if (novoId <= 0) {
            return false;
        }

        try {
            ValidationUtils.validarNome(nome);
        } catch (IllegalArgumentException e) {
            AlertUtils.mostrarErro("Erro", "Nome inválido. Use apenas letras de A a Z (maiúsculas ou minúsculas).");
            return false;
        }

        if (nome.length() < 4 || nome.length() > 50) {
            AlertUtils.mostrarErro("Erro", "O nome deve ter entre 4 a 50 caracteres");
            return false;
        }

        try {
            ValidationUtils.validarDataNascimento(dataNascimento);
        }catch (IllegalArgumentException e) {
            AlertUtils.mostrarErro("Erro", e.getMessage());
            return false;
        }

        try {
            ValidationUtils.validarEmail(email);
        } catch (IllegalArgumentException e) {
            AlertUtils.mostrarErro("Erro", e.getMessage());
            return false;
        }

        for (Colaborador colaboradorExistente : colaboradores) {
            if (colaboradorExistente.getId() == novoId) {
                for (Colaborador outroColaborador : colaboradores) {
                    if (outroColaborador == colaboradorExistente) continue;

                    if (outroColaborador.getId() == novoId) {
                        AlertUtils.mostrarErro("Erro", "ID já existente no sistema");
                        return false;
                    }

                    if (outroColaborador.getNome().equals(nome)) {
                        AlertUtils.mostrarErro("Erro", "Nome já existente no sistema");
                        return false;
                    }
                    if (outroColaborador.getEmail().equals(email)) {
                        AlertUtils.mostrarErro("Erro", "Email já existente no sistema");
                        return false;
                    }
                    if (outroColaborador.getCpf().equals(cpf)) {
                        AlertUtils.mostrarErro("Erro", "CPF já existente no sistema");
                        return false;
                    }

                }

                colaboradorExistente.setNome(nome);
                colaboradorExistente.setSenha(senha);
                colaboradorExistente.setDataNascimento(dataNascimento);
                colaboradorExistente.setEmail(email);
                colaboradorExistente.setTelefone(telefone);
                colaboradorExistente.setCpf(cpf);

                ObjectPersistenceUtils.gravarDados("colaborador.dat", colaboradores);
                AlertUtils.mostrarInfo("Edição", "Colaborador editado com sucesso");
            }
        }
        return true;
    }

    public static void excluir(String id) {
        try {
            int idEscolhido = ValidationUtils.strParaInt(id);
            boolean encontrado = false;


            for (Colaborador colaborador : colaboradores) {
                if (colaborador.getId() == idEscolhido) {
                    encontrado = true;
                    colaboradores.remove(colaborador);
                    ObjectPersistenceUtils.gravarDados("colaborador.dat", colaboradores);
                    AlertUtils.mostrarInfo("Exclusão", "Colaborador excluído com sucesso");
                    break;
                }
            }
            if (!encontrado) {
                throw new Exception();
            }
        }
        catch (Exception e) {
            AlertUtils.mostrarErro("Erro", "ID não encontrado");
        }
    }



    public static void limpar( TextField nome, TextField senha, TextField dataNascimento, TextField email, TextField telefone, TextField cpf) {
        nome.clear();
        senha.clear();
        dataNascimento.clear();
        email.clear();
        telefone.clear();
        cpf.clear();
    }

}
