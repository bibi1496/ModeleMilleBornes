package mille_bornes.cartes;

import mille_bornes.CouleurTextes;
import mille_bornes.EtatJoueur;
import mille_bornes.Jeu;
import mille_bornes.Joueur;
import mille_bornes.cartes.parades.FeuVert;

public class Borne extends Carte {
    public final int km;

    public Borne(int km){
        super("Borne", Categorie.BORNE);
        this.km = km;
    }

    @Override
    public String toString() {
        return "Borne" + "(" + km + ")";
    }

    public void appliqueEffet(Jeu jeu, EtatJoueur joueur) throws IllegalStateException {

        if (joueur.getBataille() == null){
            throw new IllegalStateException(CouleurTextes.ROUGE + "La pile de bataille doit obligatoirement contenir comme première carte un feu vert !" + CouleurTextes.NORMAL);
        }
        if (joueur.getBataille() instanceof Attaque){
            throw new IllegalStateException(CouleurTextes.ROUGE + "Une attaque bloque votre carte borne !" + CouleurTextes.NORMAL);
        }
        if (joueur.getLimiteVitesse()) {
            if (this.km <= 50) {
                throw new IllegalStateException(CouleurTextes.ROUGE + "La borne dépasse la limite de vitesse !" + CouleurTextes.NORMAL);
            }
        }
        if (joueur.getKm() + this.km > 1000){
            throw new IllegalStateException(CouleurTextes.ROUGE + "La borne fait dépasser les 1000 points utilisez en une plus petite !" + CouleurTextes.NORMAL);
        }

        joueur.ajouteKm(this.km);
        joueur.getMain().remove(this);
    }
}