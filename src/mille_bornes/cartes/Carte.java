package mille_bornes.cartes;

import mille_bornes.EtatJoueur;
import mille_bornes.Jeu;

import java.io.Serializable;
import java.lang.IllegalStateException;

public abstract class Carte implements Serializable {
    public final Categorie categorie;
    protected final String nom;

    public Carte(String Nom, Categorie categorie){
        this.nom = Nom;
        this.categorie = categorie;
    }

    public String toString(){
        return this.nom;
    }

    public abstract void appliqueEffet(Jeu jeu, EtatJoueur joueur) throws IllegalStateException;
}
