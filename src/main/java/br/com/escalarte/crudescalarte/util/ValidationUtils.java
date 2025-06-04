package br.com.escalarte.crudescalarte.util;

import java.time.DateTimeException;
import java.time.LocalTime;

public class ValidationUtils {
    public static int strParaInt(String texto) {
        try {
            return Integer.parseInt(texto);
        }
        catch (NumberFormatException e) {
            AlertUtils.mostrarErro("Erro", "Insira um número inteiro válido");
            throw new IllegalArgumentException("Número inteiro inválido: " + texto);
        }
    }

    public static LocalTime strParaLocalTime(String texto) {
        try {
            return LocalTime.parse(texto);
        }
        catch (DateTimeException e) {
            AlertUtils.mostrarErro("Erro", "Insira o horário no formato correto");
            throw new IllegalArgumentException("Hora inválida: " + texto);
        }
    }
}
