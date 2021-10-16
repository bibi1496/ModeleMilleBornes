package mille_bornes.Sauvegarde;

import mille_bornes.Jeu;
import java.io.*;

public class Sauvegarde {
    private static ObjectOutputStream oos = null;
    private static ObjectInputStream ois = null;
    private static final String NameFileSave = "Save_game.ser";
    private static final File fileSave = new File(NameFileSave);

    public Sauvegarde() { }

    public boolean sauvegarde(Jeu jeu) {
        if(estCree()){
            detruireFichier();
        }
        try {
            final FileOutputStream file = new FileOutputStream(NameFileSave);
            oos = new ObjectOutputStream(file);
            oos.writeObject(jeu);
            oos.flush();
            oos.close();
            return true;
        } catch (final java.io.IOException e) {
            return false;
        }
    }

    public static Jeu LireSauvegarde(){
        try {
            final FileInputStream fichier = new FileInputStream(NameFileSave);
            ois = new ObjectInputStream(fichier);
            final Jeu readFile = (Jeu) ois.readObject();
            return readFile;
        } catch (final IOException | ClassNotFoundException e) {
            return null;
        }
    }

    public static boolean estCree() {
        return fileSave.exists();
    }

    public static boolean detruireFichier() {
        try {
            fileSave.delete();
            return true;
        } catch (SecurityException e) {
            e.printStackTrace();
            return false;
        }
    }
}


