package mille_bornes.cartes.attaques;

import mille_bornes.EtatJoueur;
import mille_bornes.Jeu;
import mille_bornes.cartes.Attaque;

public class LimiteVitesse extends Attaque {

    public LimiteVitesse(){
        super("LimiteVitesse");
    }

    @Override
    public void appliqueEffet(Jeu jeu, EtatJoueur joueur) throws IllegalStateException{
        super.appliqueEffet(jeu, joueur);
    }

    @Override
    public boolean estContreeParFinDeLimite() {
        return super.estContreeParFinDeLimite();
    }

    @Override
    public boolean estContreeParVehiculePrioritaire() {
        return super.estContreeParVehiculePrioritaire();
    }
}
