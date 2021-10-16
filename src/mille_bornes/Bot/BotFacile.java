package mille_bornes.Bot;

import mille_bornes.CouleurTextes;
import mille_bornes.Jeu;
import mille_bornes.Joueur;
import mille_bornes.cartes.Carte;
import mille_bornes.cartes.parades.FeuVert;

public class BotFacile extends Bot {
    public BotFacile(String nom, Jeu lejeu) {
        super(nom, lejeu);
    }

    @Override
    public void joueCarte(Jeu jeu, int numero) throws IllegalStateException{
        System.out.println(CouleurTextes.VERT + numero + CouleurTextes.NORMAL);

        if (numero < 0){
            numero = -numero;
            defausseCarte(jeu,numero-1);
        }else {
            etat.joueCarte(jeu,numero-1);
        }
    }

    // Choisis une carte en fonction de ce qu'il se passe sur le jeu
    @Override
    public int choisitCarte(){
        int laPosition = -1;

        if(this.getBataille() != null && this.getBataille() instanceof FeuVert){
            laPosition = positionBorne();
        }else {
            laPosition = positionFeuxVert();
        }
        return laPosition;
    }

    // Prends le prochain joueur comme adversaire
    @Override
    public Joueur choisitAdversaire(Carte carte) throws IllegalStateException {
        return this.getProchainJoueur();
    }
}