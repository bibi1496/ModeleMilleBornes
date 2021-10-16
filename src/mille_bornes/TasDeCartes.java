package mille_bornes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import mille_bornes.cartes.Borne;
import mille_bornes.cartes.Carte;
import mille_bornes.cartes.attaques.*;
import mille_bornes.cartes.bottes.AsDuVolant;
import mille_bornes.cartes.bottes.Citerne;
import mille_bornes.cartes.bottes.Increvable;
import mille_bornes.cartes.bottes.VehiculePrioritaire;
import mille_bornes.cartes.parades.*;

public class TasDeCartes implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Carte> cartes = new ArrayList<>();

    public TasDeCartes(boolean creerLesCartes){
        if (creerLesCartes) this.creeLesCartes();
    }

    void creeLesCartes(){
        // Ajout des bornes 25/50/75.
        for (int i = 0; i < 10; i++){
            cartes.add(new Borne(25));
            cartes.add(new Borne(50));
            cartes.add(new Borne(75));
        }

        // Ajout des Attaques
        for (int y = 0; y < 6; y++){
            cartes.add(new FinDeLimite());
            cartes.add(new Essence());
            cartes.add(new RoueDeSecours());
            cartes.add(new Reparations());
            if (y != 5){
                cartes.add(new FeuRouge());
            }
        }

        // Ajout des bornes 100.
        for (int x = 0; x < 12; x++){
            cartes.add(new Borne(100));
        }

        // Ajout des bornes 200 et des Parades
        for (int m = 0; m < 4; m++){
            cartes.add(new Borne(200));
            cartes.add(new LimiteVitesse());
            if (m != 3){
                cartes.add(new PanneEssence());
                cartes.add(new Crevaison());
                cartes.add(new Accident());
            }
        }
        for (int d = 0; d < 14; d++){
            cartes.add(new FeuVert());
        }

        // Ajout des Bottes
        cartes.add(new VehiculePrioritaire());
        cartes.add(new Citerne());
        cartes.add(new Increvable());
        cartes.add(new AsDuVolant());
    }

    public void melangeCartes(){
        Collections.shuffle(cartes);
    }

    public int getNbCartes(){
        return cartes.size();
    }

    public boolean estVide(){
        return cartes.isEmpty();
    }

    public Carte regarde(){
        return cartes.get(cartes.size()-1);
    }

    public Carte prend(){
        Carte cartePrise = cartes.get(cartes.size()-1);
        cartes.remove(cartes.size()-1);
        return cartePrise;
    }

    public void pose(Carte carte){
        cartes.add(carte);
    }
}
