package br.com.escalarte.crudescalarte.dao;

import java.util.ArrayList;

import br.com.escalarte.crudescalarte.model.Cargo;
import br.com.escalarte.crudescalarte.util.AlertUtils;
import br.com.escalarte.crudescalarte.util.ObjectPersistenceUtils;
import br.com.escalarte.crudescalarte.util.ValidationUtils;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CargoDAO {
    private static ArrayList<Cargo> cargos = new ArrayList<>();

    public static ArrayList<Cargo> getCargos() {
        return cargos;
    }

    public static void cadastrar(
            String nomeStr,
            String cargaHorariaStr,
            String interjornadaStr) {
        int newId;
        boolean idExists = true;
        do {
            newId = (int) (Math.random() * 1000);
            final int finalNewId = newId;
            idExists = cargos.stream().anyMatch(c -> c.getId() == finalNewId);
        } while (idExists);

        if (nomeStr.length() < 4 || nomeStr.length() > 50) {
            AlertUtils.mostrarErro("Erro", "O nome deve ter entre 4 a 50 caracteres");
            return;
        }

        Integer cargaHoraria = ValidationUtils.strParaInt(cargaHorariaStr);
        if (cargaHoraria <= 0) {
            return;
        }
        Integer interjornada = ValidationUtils.strParaInt(interjornadaStr);
        if (interjornada <= 0)
            return;

        for (Cargo cargo : cargos) {
            if (cargo.getNome().equals(nomeStr)) {
                AlertUtils.mostrarErro("Erro", "Nome já existente no sistema");
                return;
            }
        }

        Cargo cargo = new Cargo(newId, nomeStr, cargaHoraria, interjornada);

        if (cargo != null) {
            cargos.add(cargo);
            ObjectPersistenceUtils.gravarDados(Cargo.NOME_ARQUIVO, cargos);
            AlertUtils.mostrarInfo("Cadastro", "Cargo cadastrado com sucesso");
            return;
        }
        AlertUtils.mostrarErro("Cadastro", "Erro ao cadastrar cargo.");
    }

    public static void editar(
            String id,
            String nomeStr,
            String cargaHorariaStr,
            String interjornadaStr) {
        int idInt = ValidationUtils.strParaInt(id);

        if (nomeStr.length() < 4 || nomeStr.length() > 50) {
            AlertUtils.mostrarErro("Erro", "O nome deve ter entre 4 a 50 caracteres");
            return;
        }

        Integer cargaHoraria = ValidationUtils.strParaInt(cargaHorariaStr);
        if (cargaHoraria <= 0) {
            return;
        }
        Integer interjornada = ValidationUtils.strParaInt(interjornadaStr);
        if (interjornada <= 0)
            return;

        for (Cargo cargo : cargos) {
            if (cargo.getNome().equals(nomeStr)) {
                AlertUtils.mostrarErro("Erro", "Nome já existente no sistema");
                return;
            }
        }

        for (Cargo cargoExistente : cargos) {
            if (cargoExistente.getId() == idInt) {
                cargoExistente.setNome(nomeStr);
                cargoExistente.setCargaHoraria(cargaHoraria);
                cargoExistente.setInterjornada(interjornada);
                break;
            }
        }

        ObjectPersistenceUtils.gravarDados(Cargo.NOME_ARQUIVO, cargos);
        AlertUtils.mostrarInfo("Edição", "Cargo editado com sucesso");
    }

    public static void excluir(Cargo cargo) {
        try {
            if (cargo != null) {
                cargos.remove(cargo);
                ObjectPersistenceUtils.gravarDados(Cargo.NOME_ARQUIVO, cargos);
                AlertUtils.mostrarInfo("Exclusão", "Cargo excluído com sucesso");
                return;
            }
            AlertUtils.mostrarErro("Erro", "Selecione um cargo para excluir");
        } catch (Exception e) {
            AlertUtils.mostrarErro("Erro", "Erro ao remover cargo");
        }
    }

    public static void atualizar(TableView<Cargo> table) {
        try {
            ObjectPersistenceUtils.lerDados(Cargo.NOME_ARQUIVO, CargoDAO.cargos);
            table.getItems().setAll(CargoDAO.cargos);
            AlertUtils.mostrarInfo("Atualizado", "Lista atualizada com sucesso");
        } catch (Exception e) {
            AlertUtils.mostrarErro("Erro", "Falha ao atualizar lista: ");
        }
    }

    public static void limpar(TextField id, TextField nome, TextField horarioInicio, TextField horarioFim) {
        id.clear();
        nome.clear();
        horarioInicio.clear();
        horarioFim.clear();
    }
}
