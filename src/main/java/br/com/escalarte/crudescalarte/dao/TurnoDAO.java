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
        LocalTime novoHorarioInicio = ValidationUtils.strParaLocalTime(horarioInicio);
        LocalTime novoHorarioFim = ValidationUtils.strParaLocalTime(horarioFim);

        try {

            Turno turno = new Turno(novoId, nome, novoHorarioInicio, novoHorarioFim);
            turnos.add(turno);

            ObjectPersistenceUtils.gravarDados("turnos.dat", turnos);
            AlertUtils.confirmar("Confirmar Cadastro", "Deseja cadastrar um novo Turno?");
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static void editar(
            String id,
            String nome,
            String horarioInicio,
            String horarioFim) {

        int novoId = ValidationUtils.strParaInt(id);
        LocalTime novoHorarioInicio = ValidationUtils.strParaLocalTime(horarioInicio);
        LocalTime novoHorarioFim = ValidationUtils.strParaLocalTime(horarioFim);


        try {
            for (Turno turnoExistente : turnos) {
                if (turnoExistente.getId() == novoId) {
                    if (!turnoExistente.getNome().equals(nome)) {
                        turnoExistente.setNome(nome);
                    }
                    turnoExistente.setHorarioInicio(novoHorarioInicio);
                    turnoExistente.setHorarioFim(novoHorarioFim);
                }
            }
            ObjectPersistenceUtils.gravarDados("turnos.dat", turnos);
            AlertUtils.mostrarErro("Confirmar Edição", "Deseja editar um novo Turno?");
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static void excluir(int id) {
        try {
            for (Turno turno : turnos) {
                if (turno.getId() == id) {
                    turnos.remove(turno);
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
