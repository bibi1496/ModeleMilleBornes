package mille_bornes.Bot;

import mille_bornes.CouleurTextes;
import mille_bornes.Jeu;
import mille_bornes.Joueur;
import mille_bornes.cartes.*;
import mille_bornes.cartes.parades.FeuVert;
import mille_bornes.cartes.parades.FinDeLimite;

import java.util.Random;

public class BotDifficile extends Bot {
    public BotDifficile(String nom, Jeu leJeu) {
        super(nom, leJeu);
    }

    @Override
    public void joueCarte(Jeu jeu, int numero) throws IllegalStateException {
        System.out.println(CouleurTextes.VERT + numero + CouleurTextes.NORMAL);

        if (numero < 0) {
            numero = -numero;
            defausseCarte(jeu, numero - 1);
        } else {
            etat.joueCarte(jeu, numero - 1);
        }
    }

    // Choisis une carte random
    @Override
    public int choisitCarte() {
        int laPosition = -1;

        if (this.getBataille() == null) {
            laPosition += positionFeuxVert() + 1;
        } else if (getLimiteVitesse()) { // mettre parade de limite
            int PositionTempo = 1;
            for (Carte carteParade : this.getMain()) {
                if (carteParade instanceof FinDeLimite) {
                    return PositionTempo;
                }
                PositionTempo++;
            }
        } else if (this.getBataille().categorie == Categorie.ATTAQUE) { // mettre parade
            int PositionTempo = 1;
            for (Carte carteParade : this.getMain()) {
                if (carteParade instanceof Parade) {
                    if (((Parade) carteParade).contre((Attaque) this.getBataille())) {
                        return PositionTempo;
                    }
                }
                PositionTempo++;
            }
        } else if (this.getBataille().categorie == Categorie.PARADE && !getLimiteVitesse()) { // borne
            laPosition = positionBorne();
        }

        int PositionTempo = 1;
        for (Carte carteParade : this.getMain()) {
            if (carteParade instanceof Botte) {
                return PositionTempo;
            }
            PositionTempo++;
        }

        return laPosition;
    }

    // Prends le prochain joueur comme adversaire
    @Override
    public Joueur choisitAdversaire(Carte carte) throws IllegalStateException {
        return this.getProchainJoueur();
    }
}


