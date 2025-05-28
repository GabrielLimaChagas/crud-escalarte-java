package br.com.escalarte.crudescalarte.dao;

import br.com.escalarte.crudescalarte.model.Contrato;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContratoDAO {
    private final String arquivo = "contratos.dat"; //Nome do arquivo onde os dados serão armazenados ou lidos.

    public void salvar(List<Contrato> contratos) throws IOException { // Salvar uma lista de contratos em um arquivo binário
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) { //Usa ObjectOutputStream e FileOutputStream para escrever objetos no arquivo.
            oos.writeObject(contratos); //O arquivo será sobrescrito com o conteúdo atual da lista
        }
    }

    public List<Contrato> carregar() throws IOException, ClassNotFoundException { // Ler a lista de contratos do arquivo e devolvê-la.
        File file = new File(arquivo);
        if (!file.exists()) return new ArrayList<>(); //Se o arquivo não existir, retorna uma nova lista vazia

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Contrato>) ois.readObject(); //Se o arquivo existir, lê seu conteúdo usando ObjectInputStream
        }
    }
}