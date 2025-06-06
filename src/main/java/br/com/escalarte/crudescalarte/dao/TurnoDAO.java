package br.com.escalarte.crudescalarte.dao;

import br.com.escalarte.crudescalarte.model.Turno;
import br.com.escalarte.crudescalarte.util.AlertUtils;
import br.com.escalarte.crudescalarte.util.ObjectPersistenceUtils;
import br.com.escalarte.crudescalarte.util.ValidationUtils;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.LocalTime;
import java.util.ArrayList;

public class TurnoDAO {
    public static ArrayList<Turno> turnos = new ArrayList<>();

    public static void cadastrar(
            String id,
            String nome,
            String horarioInicio,
            String horarioFim) {

        int novoId = ValidationUtils.strParaInt(id);
        if (novoId <= 0) {
            return;
        }

        LocalTime novoHorarioInicio = ValidationUtils.strParaLocalTime(horarioInicio);
        if (novoHorarioInicio.equals(LocalTime.of(0, 0))) {
            return;
        }
        LocalTime novoHorarioFim = ValidationUtils.strParaLocalTime(horarioFim);
        if (novoHorarioFim.equals(LocalTime.of(0, 0))) {
            return;
        }

        for (Turno turno : turnos) {
            if (turno.getId() == novoId) {
                AlertUtils.mostrarErro("Erro", "ID já existente no sistema");
                return;
            }
            if (turno.getNome().equals(nome)) {
                AlertUtils.mostrarErro("Erro", "Nome já existente no sistema");
                return;
            }
            if (turno.getHorarioInicio().equals(novoHorarioInicio)) {
                AlertUtils.mostrarErro("Erro", "Horário de Início já existente no sistema");

                return;
            }
            if (turno.getHorarioFim().equals(novoHorarioFim)) {
                AlertUtils.mostrarErro("Erro", "Horário Final já existente no sistema");
                return;
            }
        }

        Turno turno = new Turno(novoId, nome, novoHorarioInicio, novoHorarioFim);
        turnos.add(turno);

        ObjectPersistenceUtils.gravarDados("turnos.dat", turnos);
        AlertUtils.mostrarInfo("Cadastro", "Turno cadastrado com sucesso");
    }

    public static void editar(
            String id,
            String nome,
            String horarioInicio,
            String horarioFim) {

        int novoId = ValidationUtils.strParaInt(id);
        if (novoId <= 0) {
            return;
        }
        LocalTime novoHorarioInicio = ValidationUtils.strParaLocalTime(horarioInicio);
        if (novoHorarioInicio.equals(LocalTime.of(0, 0))) {
            return;
        }
        LocalTime novoHorarioFim = ValidationUtils.strParaLocalTime(horarioFim);
        if (novoHorarioFim.equals(LocalTime.of(0, 0))) {
            return;
        }

        for (Turno turno : turnos) {
            if (turno.getId() == novoId) {
                AlertUtils.mostrarErro("Erro", "ID já existente no sistema");
                return;
            }
            if (turno.getNome().equals(nome)) {
                AlertUtils.mostrarErro("Erro", "Nome já existente no sistema");
                return;
            }
            if (turno.getHorarioInicio().equals(novoHorarioInicio)) {
                AlertUtils.mostrarErro("Erro", "Horário de Início já existente no sistema");

                return;
            }
            if (turno.getHorarioFim().equals(novoHorarioFim)) {
                AlertUtils.mostrarErro("Erro", "Horário Final já existente no sistema");
                return;
            }
        }


        try {
            for (Turno turnoExistente : turnos) {
                if (turnoExistente.getId() == novoId) {
                    turnoExistente.setNome(nome);
                    turnoExistente.setHorarioInicio(novoHorarioInicio);
                    turnoExistente.setHorarioFim(novoHorarioFim);
                    break;
                }
            }

            ObjectPersistenceUtils.gravarDados("turnos.dat", turnos);
            AlertUtils.mostrarInfo("Edição", "Turno editado com sucesso");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void excluir(String id) {
        try {
            int idEscolhido = ValidationUtils.strParaInt(id);
            boolean encontrado = false;


            for (Turno turno : turnos) {
                if (turno.getId() == idEscolhido) {
                    encontrado = true;
                    turnos.remove(turno);
                    ObjectPersistenceUtils.gravarDados("turnos.dat", turnos);
                    AlertUtils.mostrarInfo("Exclusão", "Turno excluído com sucesso");
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

    public static void atualizar(TableView<Turno> table) {
        try {
            ObjectPersistenceUtils.lerDados("turnos.dat", TurnoDAO.turnos);
            table.getItems().setAll(TurnoDAO.turnos);
            AlertUtils.mostrarInfo("Atualizado", "Lista atualizada com sucesso");
        }
        catch (Exception e) {
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
