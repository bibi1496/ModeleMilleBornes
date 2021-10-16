package mille_bornes.cartes.bottes;

import mille_bornes.cartes.Attaque;
import mille_bornes.cartes.Botte;

public class Citerne extends Botte {

    public Citerne(){
        super("Citerne");
    }

    private static Citerne unique;

    @Override
    public boolean contre(Attaque carte) {
        if (carte != null) return carte.estContreeParCiterne();
        return false;
    }
}
