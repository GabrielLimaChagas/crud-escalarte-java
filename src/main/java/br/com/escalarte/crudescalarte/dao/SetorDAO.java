package br.com.escalarte.crudescalarte.dao;


import java.util.ArrayList;
import br.com.escalarte.crudescalarte.model.Setor;
import br.com.escalarte.crudescalarte.util.AlertUtils;
import br.com.escalarte.crudescalarte.util.ObjectPersistenceUtils;
import br.com.escalarte.crudescalarte.util.ValidationUtils;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SetorDAO {
    private static ArrayList<Setor> setores = new ArrayList<>();

    public static ArrayList<Setor> getSetores() {
        return setores;
    }

    //Cadastrar novo setor

    public static void cadastrar(
            String id,
            String nomeSetor,
            String nomeGerente,
            String quantidadeColaboradores
            )
    {

        int novoId = ValidationUtils.strParaInt(id);
        if (novoId <= 0) {
            return;
        }

        if (nomeSetor.length() < 4 || nomeSetor.length() > 50) {
            AlertUtils.mostrarErro("Erro", "O nome do setor deve ter entre 1 e 20 caracteres");
            return;
        }

        for (Setor setor : setores) {
            if (setor.getId() == novoId) {
                AlertUtils.mostrarErro("Erro", "ID já existente no sistema");
                return;
            }
            if (setor.getNomeSetor().equals(nomeSetor)) {
                AlertUtils.mostrarErro("Erro", "Existe um setor com o mesmo nome");
                return;
            }
            if (setor.getNomeGerente().equals(nomeGerente)) {
                AlertUtils.mostrarErro("Erro", "Gerente já cadastrado em outro setor");
                return;
            }

        }

        // talvez eu precise rever essa parte
        int qtd = Integer.parseInt(quantidadeColaboradores);

        Setor setor = new Setor(novoId, nomeSetor, nomeGerente, qtd );
        setores.add(setor);

        ObjectPersistenceUtils.gravarDados("setores.dat", setores);
        AlertUtils.mostrarInfo("Cadastro", "Novo setor foi cadastrado com sucesso");
    }

    public static void editar(
            String id,
            int quantidadeColaboradores,
            String nomeSetor,
            String nomeGerente) {

        int novoId = ValidationUtils.strParaInt(id);
        if (novoId <= 0) {
            return;
        }

        for (Setor setor : setores) {
            if (setor.getId() == novoId) {
                AlertUtils.mostrarErro("Erro", "ID já existente no sistema");
                return;
            }
            if (setor.getNomeSetor().equals(nomeSetor)) {
                AlertUtils.mostrarErro("Erro", "Existe um setor com o mesmo nome");
                return;
            }
            if (setor.getNomeGerente().equals(nomeGerente)) {
                AlertUtils.mostrarErro("Erro", "Gerente já cadastrado em outro setor");
                return;
            }
        }

        for (Setor setorExistente : setores) {
            if (setorExistente.getId() == novoId) {
                setorExistente.setNomeSetor(nomeSetor);
                setorExistente.setNomeGerente(nomeGerente);
                break;
            }
        }

        ObjectPersistenceUtils.gravarDados("Setores.dat", setores);
        AlertUtils.mostrarInfo("Editar", "Setor editado!");
    }

    public static void apagar(String id) {
        try {
            int idEscolhido = ValidationUtils.strParaInt(id);
            boolean encontrado = false;


            for (Setor setor : setores) {
                if (setor.getId() == idEscolhido) {
                    encontrado = true;
                    setores.remove(setor);
                    ObjectPersistenceUtils.gravarDados("setores.dat", setores);
                    AlertUtils.mostrarInfo("Excluir", "O setor foi apagado com sucesso");
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

    public static void atualizar(TableView<Setor> table) {
        try {
            ObjectPersistenceUtils.lerDados("setores.dat", SetorDAO.setores);
            table.getItems().setAll(SetorDAO.setores);
            AlertUtils.mostrarInfo("Atualizado", "Lista atualizada com sucesso");
        }
        catch (Exception e) {
            AlertUtils.mostrarErro("Erro", "Falha ao atualizar lista: ");
        }
    }

    public static void limpar(TextField id, TextField nomeSetor, TextField nomeGerente, TextField quantidadeColaboradores) {
        id.clear();
        nomeSetor.clear();
        nomeGerente.clear();
        quantidadeColaboradores.clear();
    }
}

