package mille_bornes.cartes.attaques;

import mille_bornes.cartes.Attaque;

public class Crevaison extends Attaque {
    public Crevaison(){
        super("Crevaison");
    }

    @Override
    public boolean estContreeParIncrevable() {
        return super.estContreeParIncrevable();
    }

    @Override
    public boolean estContreeParRoueDeSecours() {
        return super.estContreeParRoueDeSecours();
    }
}
