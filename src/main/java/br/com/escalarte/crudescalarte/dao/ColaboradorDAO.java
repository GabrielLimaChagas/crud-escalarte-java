package br.com.escalarte.crudescalarte.dao;

import br.com.escalarte.crudescalarte.model.Colaborador;
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

    public static void cadastrar(
            String id,
            String nome,
            String senha,
            String dataNascimento) {

        int novoId = ValidationUtils.strParaInt(id);
        if (novoId <= 0) {
            return;
        }

        try {
            ValidationUtils.validarNome(nome);
        } catch (IllegalArgumentException e) {
            AlertUtils.mostrarErro("Erro", "Nome inválido. Use apenas letras de A a Z (maiúsculas ou minúsculas).");
            return;
        }

        if (nome.length() < 4 || nome.length() > 50) {
            AlertUtils.mostrarErro("Erro", "O nome deve ter entre 4 a 50 caracteres");
            return;
        }



        for (Colaborador colaborador : colaboradores) {
            if (colaborador.getId() == novoId) {
                AlertUtils.mostrarErro("Erro", "ID já existente no sistema");
                return;
            }
            if (colaborador.getNome().equals(nome)) {
                AlertUtils.mostrarErro("Erro", "Nome já existente no sistema");
                return;
            }
        }

        Colaborador colaborador = new Colaborador(novoId, nome, senha, dataNascimento);
        colaboradores.add(colaborador);

        ObjectPersistenceUtils.gravarDados("colaborador.dat", colaboradores);
        AlertUtils.mostrarInfo("Cadastro", "Colaborador cadastrado com sucesso");
    }

    public static void editar(
            String id,
            String nome,
            String senha,
            String dataNascimento) {

        int novoId = ValidationUtils.strParaInt(id);
        if (novoId <= 0) {
            return;
        }

        try {
            ValidationUtils.validarNome(nome);
        } catch (IllegalArgumentException e) {
            AlertUtils.mostrarErro("Erro", "Nome inválido. Use apenas letras de A a Z (maiúsculas ou minúsculas).");
            return;
        }

        if (nome.length() < 4 || nome.length() > 50) {
            AlertUtils.mostrarErro("Erro", "O nome deve ter entre 4 a 50 caracteres");
            return;
        }


        for (Colaborador colaboradorExistente : colaboradores) {
            if (colaboradorExistente.getId() == novoId) {
                for (Colaborador outroColaborador : colaboradores) {
                    if (outroColaborador == colaboradorExistente) continue;

                    if (outroColaborador.getId() == novoId) {
                        AlertUtils.mostrarErro("Erro", "ID já existente no sistema");
                        return;
                    }

                    if (outroColaborador.getNome().equals(nome)) {
                        AlertUtils.mostrarErro("Erro", "Nome já existente no sistema");
                        return;
                    }

                }

                colaboradorExistente.setNome(nome);
                colaboradorExistente.setSenha(senha);
                colaboradorExistente.setDataNascimento(dataNascimento);

                ObjectPersistenceUtils.gravarDados("colaborador.dat", colaboradores);
                AlertUtils.mostrarInfo("Edição", "Colaborador editado com sucesso");
                return;
            }
        }
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

    public static void atualizar(TableView<Colaborador> table) {
        try {
            ObjectPersistenceUtils.lerDados("colaborador.dat", colaboradores);
            table.getItems().setAll(ColaboradorDAO.colaboradores);
            AlertUtils.mostrarInfo("Atualizado", "Lista atualizada com sucesso");
        }
        catch (Exception e) {
            AlertUtils.mostrarErro("Erro", "Falha ao atualizar lista: ");
        }
    }

    public static void limpar(TextField id, TextField nome, TextField senha, TextField dataNascimento) {
        id.clear();
        nome.clear();
        senha.clear();
        dataNascimento.clear();
    }
}
