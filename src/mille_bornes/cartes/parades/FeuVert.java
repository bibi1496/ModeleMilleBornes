package mille_bornes.cartes.parades;

import mille_bornes.EtatJoueur;
import mille_bornes.Jeu;
import mille_bornes.cartes.Attaque;
import mille_bornes.cartes.Parade;

public class FeuVert extends Parade {
    public FeuVert(){
        super("FeuVert");
    }

    @Override
    public boolean contre(Attaque carte) {
        if (carte.estContreeParFeuVert()) return true;
        return false;
    }

    @Override
    public void appliqueEffet(Jeu jeu, EtatJoueur joueur) throws IllegalStateException {
        super.appliqueEffet(jeu, joueur);
    }
}
