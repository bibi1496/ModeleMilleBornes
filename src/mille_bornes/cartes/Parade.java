package mille_bornes.cartes;

import mille_bornes.CouleurTextes;
import mille_bornes.EtatJoueur;
import mille_bornes.Jeu;
import mille_bornes.cartes.parades.FeuVert;
import mille_bornes.cartes.parades.FinDeLimite;

public abstract class Parade extends Bataille{
    public Parade(String Nom){
        super(Nom, Categorie.PARADE);
    }

    @Override
    public void appliqueEffet(Jeu jeu, EtatJoueur joueur) throws IllegalStateException {
        // Poser le premier feuvert
        if (this instanceof FeuVert && joueur.getBataille() == null) {
            joueur.setBataille(this);
            return;
        }

        // Pour toutes les autres cartes si il n'y a pas de bataille
        if (joueur.getBataille() == null){
            throw new IllegalStateException(CouleurTextes.ROUGE + "Pas de carte dans bataille !" + CouleurTextes.NORMAL);
        }

        // Appliquer effet
        if (joueur.getBataille().categorie == Categorie.ATTAQUE) {
            if (this.contre((Attaque) joueur.getBataille())) {
                if (this instanceof FinDeLimite) {
                    joueur.setLimiteVitesse(false);
                }else {
                    joueur.setBataille(this);
                }

                return;
            }else {
                throw new IllegalStateException(CouleurTextes.ROUGE + "La parade jouée ne peut pas contrer l'attaque courante !" + CouleurTextes.NORMAL);
            }
        }else {
            throw new IllegalStateException(CouleurTextes.ROUGE + "Vous pouvez déjà avancer !" + CouleurTextes.NORMAL);
        }
    }
}
