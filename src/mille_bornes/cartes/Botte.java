package mille_bornes.cartes;

import mille_bornes.EtatJoueur;
import mille_bornes.Jeu;

public abstract class Botte extends Carte{

    public Botte(String nom){
        super(nom, Categorie.BOTTE);
    }

    @Override
    public void appliqueEffet(Jeu jeu, EtatJoueur joueur) throws IllegalStateException {

        joueur.addBotte(this);
        joueur.getMain().remove(this);
    }

    public abstract boolean contre(Attaque carte);
}
