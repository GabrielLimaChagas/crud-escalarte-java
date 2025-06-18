package br.com.escalarte.crudescalarte.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ValidationUtils {
    public static int strParaInt(String texto) {
        try {
            return Integer.parseInt(texto);
        }
        catch (NumberFormatException e) {
            AlertUtils.mostrarErro("Erro", "Insira um número inteiro válido");
            return 0;
        }
    }

    public static double strParaDouble(String texto) {
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            AlertUtils.mostrarErro("Erro", "Insira um número decimal válido");
            return 0.0;
        }
    }

    public static LocalTime strParaLocalTime(String texto) {
        try {
            return LocalTime.parse(texto);
        }
        catch (DateTimeException e) {
            AlertUtils.mostrarErro("Erro", "Insira o horário no formato correto");
            return LocalTime.of(0, 0);
        }
    }

    public static LocalDate strParaLocalDate(String texto) {
        try {
            return LocalDate.parse(texto); // Exige o formato yyyy-MM-dd
        } catch (DateTimeException e) {
            AlertUtils.mostrarErro("Erro", "Insira a data no formato yyyy-MM-dd");
            return null;
        }
    }

    public static boolean campoVazio(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            AlertUtils.mostrarErro("Erro", "Não é permitido deixar campos em branco");
            return true;
        }
        return false;
    }

    public static boolean validarStatus(String status) {
        if (status == null) {
            AlertUtils.mostrarErro("Erro", "Status não pode ser nulo");
            return false;
        }
        return true;
    }

    public static void validarNome(String nome) {
        if (!nome.matches("^[A-Za-zÀ-ÿ]+( [A-Za-zÀ-ÿ]+)*$")) {
            throw new IllegalArgumentException();
        }
    }

    public static boolean validarDataNascimento(String data) {
        String regex = "^(0[1-9]|[12][0-9]|3[01])[-/](0[1-9]|1[0-2])[-/](\\d{4})$";
        if (!data.matches(regex)) {
           AlertUtils.mostrarErro("Erro","Data de nascimento inválida. " + "Use um dos formatos: DD-MM-YYYY ou DD/MM/YYYY." );
           return false;
        }
        return true;
    }

    public static boolean validarEmail(String email) {
        if (!email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$")) {
            AlertUtils.mostrarErro("Erro", "Email inválido. Use um formato como exemplo@dominio.com.");
            return false;
        }
        return true;
    }

    public static boolean validarTelefone(String telefone) {
        if (!telefone.matches("^(\\(?\\d{2}\\)?\\s?)?(\\d{4,5})[-\\s]?(\\d{4})$")) {
            AlertUtils.mostrarErro("Erro","Telefone inválido. Use um formato como (11) 91234-5678 ou 1123456789.");
            return false;
        }
        return true;
    }

    public static boolean validarCPF(String cpf) {
        if (!cpf.matches("^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$")) {
            AlertUtils.mostrarErro("Erro","CPF inválido. Use o formato 123.456.789-09 ou 12345678909.");
            return false;
        }
        return true;
    }

    public static boolean validarDatas(LocalDate inicio, LocalDate fim) {
        if (inicio == null || fim == null) {
            AlertUtils.mostrarErro("Erro", "Datas inválidas");
            return false;
        }
        if (fim.isBefore(inicio)) {
            AlertUtils.mostrarErro("Erro", "A data de fim não pode ser anterior à data de início");
            return false;
        }
        return true;
    }
}
