package mille_bornes.cartes.bottes;

import mille_bornes.cartes.Attaque;
import mille_bornes.cartes.Botte;

public class AsDuVolant extends Botte {

    public AsDuVolant(){
        super("AsDuVolant");
    }

    private static AsDuVolant unique;

    public boolean contre(Attaque carte){
        if (carte != null) return carte.estContreeParAsDuVolant();
        return false;
    }
}
