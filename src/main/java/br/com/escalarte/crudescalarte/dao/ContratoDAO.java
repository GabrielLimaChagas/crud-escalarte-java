package br.com.escalarte.crudescalarte.dao;

import br.com.escalarte.crudescalarte.model.Contrato;
import br.com.escalarte.crudescalarte.util.AlertUtils;
import br.com.escalarte.crudescalarte.util.ObjectPersistenceUtils;
import br.com.escalarte.crudescalarte.util.ValidationUtils;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.ArrayList;

public class ContratoDAO {

    public static int gerarNovoId() {
        return contratos.stream()
                .mapToInt(Contrato::getId)
                .max()
                .orElse(0) + 1;
    }

    private static ArrayList<Contrato> contratos = new ArrayList<>();

    public static ArrayList<Contrato> getContratos() {
        return contratos;
    }

    public static void cadastrar( String id,  String status, String cargaHorariaDiaria, String cargo, String colaborador, String dataInicio, String dataFim, String diasTrabalhoSemanal, String diasTrabalhoMensal) {

        if (ValidationUtils.campoVazio(id) || ValidationUtils.campoVazio(status) ||
                ValidationUtils.campoVazio(cargaHorariaDiaria) || ValidationUtils.campoVazio(cargo) ||
                ValidationUtils.campoVazio(colaborador) || ValidationUtils.campoVazio(dataInicio) ||
                ValidationUtils.campoVazio(dataFim) || ValidationUtils.campoVazio(diasTrabalhoSemanal) ||
                ValidationUtils.campoVazio(diasTrabalhoMensal)) {
            return;
        }

        int novoId = gerarNovoId();

        double cargaDiaria = ValidationUtils.strParaDouble(cargaHorariaDiaria);
        if (cargaDiaria <= 0 || cargaDiaria > 24) {
            AlertUtils.mostrarErro("Erro", "Carga horária diária deve estar entre 1 e 24 horas");
            return;
        }

        LocalDate inicio = ValidationUtils.strParaLocalDate(dataInicio);
        LocalDate fim = ValidationUtils.strParaLocalDate(dataFim);
        if (!ValidationUtils.validarDatas(inicio, fim)) return;

        int diasSemanais = ValidationUtils.strParaInt(diasTrabalhoSemanal);
        int diasMensais = ValidationUtils.strParaInt(diasTrabalhoMensal);

        if (diasSemanais <= 0 || diasSemanais > 7) {
            AlertUtils.mostrarErro("Erro", "Dias de trabalho semanal deve estar entre 1 e 7");
            return;
        }

        if (diasMensais <= 0 || diasMensais > 31) {
            AlertUtils.mostrarErro("Erro", "Dias de trabalho mensal deve estar entre 1 e 31");
            return;
        }

        if (cargo.length() < 2 || colaborador.length() < 2) {
            AlertUtils.mostrarErro("Erro", "Cargo e nome do colaborador devem ter no mínimo 2 caracteres");
            return;
        }

        for (Contrato c : contratos) {
            if (c.getId() == novoId) {
                AlertUtils.mostrarErro("Erro", "ID já existente");
                return;
            }
        }

        Contrato novoContrato = new Contrato(
                novoId,
                status,
                cargaDiaria,
                cargo,
                colaborador,
                inicio,
                fim,
                diasSemanais,
                diasMensais
        );

        contratos.add(novoContrato);
        ObjectPersistenceUtils.gravarDados("contratos.dat", contratos);
        AlertUtils.mostrarInfo("Cadastro", "Contrato cadastrado com sucesso");
    }

    public static void editar( String id, String status, String cargaHorariaDiaria, String cargo, String colaborador, String dataInicio, String dataFim, String diasTrabalhoSemanal, String diasTrabalhoMensal) {
        if (ValidationUtils.campoVazio(id)) return;

        int idEditado = ValidationUtils.strParaInt(id);
        if (idEditado <= 0) return;

        for (Contrato c : contratos) {
            if (c.getId() == idEditado) {

                status = status.trim();
                if (!ValidationUtils.validarStatus(status)) return;

                double cargaDiaria = ValidationUtils.strParaDouble(cargaHorariaDiaria);
                if (cargaDiaria <= 0 || cargaDiaria > 24) {
                    AlertUtils.mostrarErro("Erro", "Carga horária diária inválida");
                    return;
                }

                LocalDate inicio = ValidationUtils.strParaLocalDate(dataInicio);
                LocalDate fim = ValidationUtils.strParaLocalDate(dataFim);
                if (!ValidationUtils.validarDatas(inicio, fim)) return;

                int diasSemanais = ValidationUtils.strParaInt(diasTrabalhoSemanal);
                int diasMensais = ValidationUtils.strParaInt(diasTrabalhoMensal);

                if (diasSemanais <= 0 || diasSemanais > 7 || diasMensais <= 0 || diasMensais > 31) {
                    AlertUtils.mostrarErro("Erro", "Quantidade de dias de trabalho inválida");
                    return;
                }

                if (cargo.length() < 2 || colaborador.length() < 2) {
                    AlertUtils.mostrarErro("Erro", "Cargo e nome do colaborador devem ter no mínimo 2 caracteres");
                    return;
                }

                // Atualiza os campos do contrato
                c.setStatus(status);
                c.setCargaHorariaDiaria(cargaDiaria);
                c.setCargo(cargo);
                c.setColaborador(colaborador);
                c.setDataInicio(inicio);
                c.setDataFim(fim);
                c.setDiasTrabalhoSemanal(diasSemanais);
                c.setDiasTrabalhoMensal(diasMensais);

                ObjectPersistenceUtils.gravarDados("contratos.dat", contratos);
                AlertUtils.mostrarInfo("Edição", "Contrato editado com sucesso");
                return;
            }
        }

        AlertUtils.mostrarErro("Erro", "Contrato não encontrado");
    }

    public static void excluir(String id) {
        try {
            int idEscolhido = ValidationUtils.strParaInt(id);
            boolean removido = contratos.removeIf(c -> c.getId() == idEscolhido);
            if (removido) {
                ObjectPersistenceUtils.gravarDados("contratos.dat", contratos);
                AlertUtils.mostrarInfo("Exclusão", "Contrato excluído com sucesso");
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            AlertUtils.mostrarErro("Erro", "ID não encontrado");
        }
    }

    public static void atualizar(TableView<Contrato> table) {
        try {
            ObjectPersistenceUtils.lerDados("contratos.dat", contratos);
            table.getItems().setAll(contratos);
            AlertUtils.mostrarInfo("Atualizado", "Lista de contratos atualizada com sucesso");
        } catch (Exception e) {
            AlertUtils.mostrarErro("Erro", "Erro ao atualizar contratos");
        }
    }

    public static void limpar(TextField... campos) {
        for (TextField campo : campos) {
            campo.clear();
        }
    }
}
