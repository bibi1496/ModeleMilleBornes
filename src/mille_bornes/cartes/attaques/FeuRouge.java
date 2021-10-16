package mille_bornes.cartes.attaques;

import mille_bornes.cartes.Attaque;

public class FeuRouge extends Attaque {

    public FeuRouge(){
        super("FeuRouge");
    }

    @Override
    public boolean estContreeParFeuVert() {
        return super.estContreeParFeuVert();
    }

    @Override
    public boolean estContreeParVehiculePrioritaire() {
        return super.estContreeParVehiculePrioritaire();
    }
}
