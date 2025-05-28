package br.com.escalarte.crudescalarte.dao;

import java.io.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.com.escalarte.crudescalarte.model.Setor;

public class SetorDAO {

    private static final String ARQUIVO_SETOR = "Setor.dat";

    public static void salvarLista(ArrayList<Setor> setor){
        FileOutputStream f;
        try {
            File arq = new File(ARQUIVO_SETOR);
            if(!arq.exists()){
                arq.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arq));
            oos.writeObject(setor);
            oos.close();
            System.out.println("Lista de setores salva com sucesso.");
        }
        catch (FileNotFoundException e) {
            System.err.println("Erro ao salvar lista: " + e.getMessage());
        }
        catch (IOException e) {
            System.err.println("Erro ao salvar lista: " + e.getMessage());
        }
    }

    public static ArrayList<Setor> lerLista() {
        ArrayList<Setor> lista = new ArrayList<>();
        try {
            File arq = new File(ARQUIVO_SETOR);
            if (arq.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_SETOR));
                lista = (ArrayList<Setor>) ois.readObject();
                ois.close();
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("Erro ao ler lista: " + e.getMessage());
        }
        catch (IOException e) {
            System.err.println("Erro ao ler lista: " + e.getMessage());
        }
        catch (ClassNotFoundException e) {
            System.err.println("Erro ao ler lista: " + e.getMessage());
        }
        return lista;
    }

    public static void adicionarSetor(Setor setor) {
        ArrayList<Setor> setores = lerLista();
        setores.add(setor);
        salvarLista(setores);
    }

    public static void removerSetor(Setor setor) {
        ArrayList<Setor> setores = lerLista();
        setores.remove(setor);
        salvarLista(setores);
    }
}

