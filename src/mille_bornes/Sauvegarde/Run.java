package mille_bornes.Sauvegarde;

import mille_bornes.EtatJoueur;
import mille_bornes.Joueur;

public class Run {
    public static void main(String[] args) {
        Joueur game = new Joueur("Michel");
        EtatJoueur test = new EtatJoueur(game);
        test.setLimiteVitesse(true);
        Sauvegarde a = new Sauvegarde();

    }
}


