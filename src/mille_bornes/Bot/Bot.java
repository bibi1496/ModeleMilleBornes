package mille_bornes.Bot;

import mille_bornes.Joueur;
import mille_bornes.cartes.*;
import mille_bornes.cartes.parades.FeuVert;
import mille_bornes.Jeu;

public abstract class Bot extends Joueur {
    protected Jeu jeu;
    public Bot(String name, Jeu jeuBot){
        super(name);
        this.jeu =jeuBot;
    }

    public int positionFeuxVert(){
        int PositionTempo = 1;
        for(Carte carte: this.getMain()){
            if(carte instanceof FeuVert){ return PositionTempo;}
            PositionTempo++;
        }
        return -1;
    }

    public int positionBorne(){
        int PositionTempo = 1;
        for(Carte carte: this.getMain()){
            if(carte instanceof Borne){
                if ((((Borne) carte).km + this.getKm() <= 1000) && (this.getBataille().categorie != Categorie.ATTAQUE) && (!this.getLimiteVitesse())) {
                    return PositionTempo;
                }
            }
            PositionTempo++;
        }
        return -1;
    }
}
