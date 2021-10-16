package mille_bornes.cartes.bottes;

import mille_bornes.cartes.Attaque;
import mille_bornes.cartes.Botte;

public class Increvable extends Botte {

    public Increvable(){
        super("Increvable");
    }

    private static Increvable unique;

    @Override
    public boolean contre(Attaque carte) {
        if (carte != null) return carte.estContreeParIncrevable();
        return false;
    }
}
