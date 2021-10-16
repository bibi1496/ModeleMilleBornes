package mille_bornes.cartes.bottes;

import mille_bornes.cartes.Attaque;
import mille_bornes.cartes.Botte;

public class VehiculePrioritaire extends Botte {

    public VehiculePrioritaire(){
        super("VehiculePrioritaire");
    }

    private static VehiculePrioritaire unique;

    @Override
    public boolean contre(Attaque carte) {
        if (carte != null) return carte.estContreeParVehiculePrioritaire();
        return false;
    }
}
