package mille_bornes.cartes.parades;

import mille_bornes.cartes.Attaque;
import mille_bornes.cartes.Categorie;
import mille_bornes.cartes.Parade;

public class RoueDeSecours extends Parade {
    public RoueDeSecours(){
        super("RoueDeSecours");
    }

    @Override
    public boolean contre(Attaque carte) {
        if (carte.estContreeParRoueDeSecours()) return true;
        return false;
    }
}
