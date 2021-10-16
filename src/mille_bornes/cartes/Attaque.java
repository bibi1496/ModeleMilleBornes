package mille_bornes.cartes;

import mille_bornes.Bot.Bot;
import mille_bornes.CouleurTextes;
import mille_bornes.Jeu;
import mille_bornes.EtatJoueur;
import mille_bornes.cartes.attaques.LimiteVitesse;

public class Attaque extends Bataille{
    public Attaque(String Nom){
        super(Nom, Categorie.ATTAQUE);
    }

    @Override
    public void appliqueEffet(Jeu jeu, EtatJoueur joueur) throws IllegalStateException {
        // Possède deja une carte attaque
        if (joueur.getBataille() != null){
            if (joueur.getBataille().categorie == Categorie.ATTAQUE) {
                throw new IllegalStateException(CouleurTextes.ROUGE + "Le joueur visé est déjà handicapé par une carte attaque !" + CouleurTextes.NORMAL);
            }
        }

        // Est bloqué par une botte
        for (Botte botte : joueur.getBottes()){
            if (botte.contre((Attaque) joueur.getBataille())){
                throw new IllegalStateException(CouleurTextes.ROUGE + "Un botte bloque l'attaque !" + CouleurTextes.NORMAL);
            }
        }


        // application
        if (this instanceof LimiteVitesse){
            joueur.setLimiteVitesse(true);
        }else {
            joueur.setBataille(this);
            jeu.getJoueurActif().getMain().remove(this);
        }

        if (!(joueur.getJoueur() instanceof Bot)){
            // Coups fourés
            Carte carteDeLaMainJoueurM = null;
            for (Carte carteDeLaMainJoueur : joueur.getMain()){
                if (carteDeLaMainJoueur.categorie == Categorie.BOTTE){
                    Botte botte = (Botte) carteDeLaMainJoueur;
                    carteDeLaMainJoueurM = carteDeLaMainJoueur;
                    if (botte.contre(this)){
                        if (joueur.getJoueur().confirmation(joueur.getJoueur().nom + ", Voulez vous effectuer un coup fourré ?")){ // si le coup fourré est effectué
                            System.out.println("Vous venez d'activer un coup fourré votre carte botte est mise dans votre tas et vous pouvez rejouer.");
                            joueur.addBotte(botte);
                            jeu.setProchainJoueur(joueur.getJoueur());
                        }
                    }
                }
            }
            if(carteDeLaMainJoueurM != null){
                joueur.getMain().remove(carteDeLaMainJoueurM);
            }
        }
    }

    @Override
    public boolean contre(Attaque carte) {
        return false;
    }
}
