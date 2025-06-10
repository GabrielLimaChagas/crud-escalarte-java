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

    private static Cargo converterCampos(
            String idStr,
            String nomeStr,
            String cargaHorariaStr,
            String interjornadaStr) {
        int id = ValidationUtils.strParaInt(idStr);
        if (id <= 0)
            return null;

        if (nomeStr.length() < 4 || nomeStr.length() > 50) {
            AlertUtils.mostrarErro("Erro", "O nome deve ter entre 4 a 50 caracteres");
            return null;
        }

        Integer cargaHoraria = ValidationUtils.strParaInt(cargaHorariaStr);
        if (cargaHoraria <= 0) {
            return null;
        }
        Integer interjornada = ValidationUtils.strParaInt(interjornadaStr);
        if (interjornada <= 0)
            return null;

        for (Cargo cargo : cargos) {
            if (cargo.getId() == id) {
                AlertUtils.mostrarErro("Erro", "ID já existente no sistema");
                return null;
            }
            if (cargo.getNome().equals(nomeStr)) {
                AlertUtils.mostrarErro("Erro", "Nome já existente no sistema");
                return null;
            }
        }

        Cargo cargo = new Cargo(id, nomeStr, cargaHoraria, interjornada);
        return cargo;
    }

    public static void cadastrar(
            String idStr,
            String nomeStr,
            String cargaHorariaStr,
            String interjornadaStr) {

        Cargo cargo = converterCampos(idStr, nomeStr, cargaHorariaStr, interjornadaStr);
        if (cargo == null) {
            return;
        }
        cargos.add(cargo);

        ObjectPersistenceUtils.gravarDados(Cargo.NOME_ARQUIVO, cargos);
        AlertUtils.mostrarInfo("Cadastro", "Cargo cadastrado com sucesso");
    }

    public static void editar(
            String id,
            String nome,
            String horarioInicio,
            String horarioFim) {
        Cargo newCargo = converterCampos(
                String.valueOf(id),
                nome,
                horarioInicio,
                horarioFim);
        if (newCargo == null) {
            return;
        }
        for (Cargo cargoExistente : cargos) {
            if (cargoExistente.getId() == newCargo.getId()) {
                cargoExistente.setNome(nome);
                cargoExistente.setCargaHoraria(ValidationUtils.strParaInt(horarioInicio));
                cargoExistente.setInterjornada(ValidationUtils.strParaInt(horarioFim));
                break;
            }
        }

        ObjectPersistenceUtils.gravarDados(Cargo.NOME_ARQUIVO, cargos);
        AlertUtils.mostrarInfo("Edição", "Cargo editado com sucesso");
    }

    public static void excluir(String id) {
        try {
            int idEscolhido = ValidationUtils.strParaInt(id);
            boolean encontrado = false;

            for (Cargo cargo : cargos) {
                if (cargo.getId() == idEscolhido) {
                    encontrado = true;
                    cargos.remove(cargo);
                    ObjectPersistenceUtils.gravarDados(Cargo.NOME_ARQUIVO, cargos);
                    AlertUtils.mostrarInfo("Exclusão", "Cargo excluído com sucesso");
                    break;
                }
            }
            if (!encontrado) {
                throw new Exception();
            }
        } catch (Exception e) {
            AlertUtils.mostrarErro("Erro", "ID não encontrado");
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
