package br.com.escalarte.crudescalarte.dao;

import br.com.escalarte.crudescalarte.model.Turno;
import br.com.escalarte.crudescalarte.util.AlertUtils;
import br.com.escalarte.crudescalarte.util.ObjectPersistenceUtils;
import br.com.escalarte.crudescalarte.util.ValidationUtils;
import javafx.scene.control.TableView;

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

        try {

            Turno turno = new Turno(novoId, nome, novoHorarioInicio, novoHorarioFim);
            turnos.add(turno);

            ObjectPersistenceUtils.gravarDados("turnos.dat", turnos);
            AlertUtils.confirmar("Confirmar Cadastro", "Deseja cadastrar o Turno?");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
            AlertUtils.confirmar("Confirmar Edição", "Deseja editar o Turno?");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void excluir(String id) {
        try {
            int idEscolhido = ValidationUtils.strParaInt(id);


            for (Turno turno : turnos) {
                if (turno.getId() == idEscolhido) {
                    turnos.remove(turno);
                    ObjectPersistenceUtils.gravarDados("turnos.dat", turnos);
                    AlertUtils.confirmar("Confirmar Exclusão", "Esta ação é irreversível");
                    break;
                }
            }
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static void atualizar(TableView<Turno> table) {
        try {
            ObjectPersistenceUtils.lerDados("turnos.dat", TurnoDAO.turnos);
            table.getItems().setAll(TurnoDAO.turnos);
            AlertUtils.confirmar("Atualizado", "Lista atualizada com sucesso!");
        }
        catch (Exception e) {
            AlertUtils.mostrarErro("Erro", "Falha ao atualizar lista: ");
        }
    }
}
