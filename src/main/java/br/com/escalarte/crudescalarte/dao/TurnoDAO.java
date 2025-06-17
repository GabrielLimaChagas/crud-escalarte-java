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
    private static ArrayList<Turno> turnos = new ArrayList<>();

    public static ArrayList<Turno> getTurnos() {
        return turnos;
    }

    private static int gerarNovoId() {
        if (turnos.isEmpty()) {
            return 1;
        }
        return turnos.stream()
                .mapToInt(Turno::getId)
                .max()
                .getAsInt() + 1;
    }

    public static boolean cadastrar(
            String nome,
            String horarioInicio,
            String horarioFim) {

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

        LocalTime novoHorarioInicio = ValidationUtils.strParaLocalTime(horarioInicio);
        if (novoHorarioInicio.equals(LocalTime.of(0, 0))) {
            return false;
        }
        LocalTime novoHorarioFim = ValidationUtils.strParaLocalTime(horarioFim);
        if (novoHorarioFim.equals(LocalTime.of(0, 0))) {
            return false;
        }

        if (novoHorarioFim.isBefore(novoHorarioInicio)) {
            AlertUtils.mostrarErro("Erro", "Turno Inicio deve ser antes do Turno Final");
            return false;
        }

        for (Turno turno : turnos) {
            if (turno.getNome().equals(nome)) {
                AlertUtils.mostrarErro("Erro", "Nome já existente no sistema");
                return false;
            }
            if (turno.getHorarioFim().equals(novoHorarioFim) && turno.getHorarioInicio().equals(novoHorarioInicio)) {
                AlertUtils.mostrarErro("Erro", "Há um turno com este horário já existente no sistema");
                return false;
            }
        }

        Turno turno = new Turno(novoId, nome, novoHorarioInicio, novoHorarioFim);
        turnos.add(turno);

        ObjectPersistenceUtils.gravarDados("turnos.dat", turnos);
        AlertUtils.mostrarInfo("Cadastro", "Turno cadastrado com sucesso");
        return true;
    }

    public static boolean editar(
            String id,
            String nome,
            String horarioInicio,
            String horarioFim) {

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

        LocalTime novoHorarioInicio = ValidationUtils.strParaLocalTime(horarioInicio);
        if (novoHorarioInicio.equals(LocalTime.of(0, 0))) {
            return false;
        }

        LocalTime novoHorarioFim = ValidationUtils.strParaLocalTime(horarioFim);
        if (novoHorarioFim.equals(LocalTime.of(0, 0))) {
            return false;
        }

        if (novoHorarioFim.isBefore(novoHorarioInicio)) {
            AlertUtils.mostrarErro("Erro", "Turno Inicio deve ser antes do Turno Final");
            return false;
        }

        for (Turno turnoExistente : turnos) {
            if (turnoExistente.getId() == novoId) {
                for (Turno outroTurno : turnos) {
                    if (outroTurno == turnoExistente) continue;

                    if (outroTurno.getId() == novoId) {
                        AlertUtils.mostrarErro("Erro", "ID já existente no sistema");
                        return false;
                    }

                    if (outroTurno.getNome().equals(nome)) {
                        AlertUtils.mostrarErro("Erro", "Nome já existente no sistema");
                        return false;
                    }

                    if (outroTurno.getHorarioFim().equals(novoHorarioFim) &&
                            outroTurno.getHorarioInicio().equals(novoHorarioInicio)) {
                        AlertUtils.mostrarErro("Erro", "Há um turno com este horário já existente no sistema");
                        return false;
                    }
                }

                turnoExistente.setNome(nome);
                turnoExistente.setHorarioInicio(novoHorarioInicio);
                turnoExistente.setHorarioFim(novoHorarioFim);

                ObjectPersistenceUtils.gravarDados("turnos.dat", turnos);
                AlertUtils.mostrarInfo("Edição", "Turno editado com sucesso");
            }
        }
        return true;
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

    public static void limpar(TextField nome, TextField horarioInicio, TextField horarioFim) {
        nome.clear();
        horarioInicio.clear();
        horarioFim.clear();
    }
}
