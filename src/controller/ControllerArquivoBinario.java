package controller;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @author fabricio
 */
public class ControllerArquivoBinario extends ControllerArquivo {

    protected ArrayList<? extends Object> objects = new ArrayList<>();
    private ObjectInputStream leitor = null;
    private ObjectOutputStream escritor = null;

    public ArrayList<? extends Object> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<? extends Object> objects) {
        this.objects = objects;
    }

    @Override
    public boolean ler() {
        objects = null;
        try {
            leitor = new ObjectInputStream(new FileInputStream(arquivo));
            objects = (ArrayList<? extends Object>) leitor.readObject();
            leitor.close();
            return true;
        } catch (ClassNotFoundException erro) {
            //erro.printStackTrace();
            System.err.println(erro.getMessage() + "Classe não encontrada.");
            return false;
        } catch (IOException erro) {
            System.err.println(erro.getMessage() + "Erro ao ler arquivo binário.");
            return false;
        }
    }

    @Override
    public boolean escrever(boolean append) {
        if (arquivo != null) {
            try {
                arquivo.delete();
                arquivo.createNewFile();
                escritor = new ObjectOutputStream(new FileOutputStream(arquivo, append));
                escritor.writeObject(objects);
                escritor.close();
                return true;
            } catch (IOException erro) {
                System.err.println(erro.getMessage() + "Erro ao escrever arquivo binário.");
                return false;
            }
        } else {
            return false;
        }
    }
}
