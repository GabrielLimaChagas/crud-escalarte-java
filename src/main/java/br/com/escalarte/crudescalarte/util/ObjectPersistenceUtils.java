package br.com.escalarte.crudescalarte.util;

import java.io.*;
import java.util.ArrayList;

public class ObjectPersistenceUtils {
    public static void lerDados(String nomeArquivo, ArrayList lista) {
        File file = new File(nomeArquivo);
        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                ArrayList dados = (ArrayList) ois.readObject();
                lista.clear();
                lista.addAll(dados);
            } catch (IOException | ClassNotFoundException e) {
                AlertUtils.mostrarErro("Erro", "Não foi possível ler os dados");
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<Object> lerDados(String nomeArquivo) {
        File file = new File(nomeArquivo);
        ArrayList<Object> dados = new ArrayList<>();
        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                dados = (ArrayList<Object>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                AlertUtils.mostrarErro("Erro", "Não foi possível ler os dados");
                e.printStackTrace();
            }
        }
        return dados;
    }

    public static void gravarDados(String nomeArquivo, ArrayList lista) {
        try (FileOutputStream fos = new FileOutputStream(nomeArquivo);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(lista);
        } catch (IOException e) {
            AlertUtils.mostrarErro("Erro", "Não foi possível salvar os dados");
            e.printStackTrace();
        }
    }
}