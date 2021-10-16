package mille_bornes.cartes;

import mille_bornes.EtatJoueur;
import mille_bornes.Jeu;

public abstract class Bataille extends Carte{

    public Bataille(String nom, Categorie categorie){
        super(nom, categorie);
    }

    @Override
    public void appliqueEffet(Jeu jeu, EtatJoueur joueur) throws IllegalStateException{ }

    public boolean estContreeParFeuVert(){
        if (super.categorie == Categorie.ATTAQUE && super.nom == "FeuRouge"){
            return true;
        }
        else { return false;}
    }

    public boolean estContreeParFinDeLimite(){
        if (super.categorie == Categorie.ATTAQUE && super.nom == "LimiteVitesse"){
            return true;
        }
        else { return false;}
    }

    public boolean estContreeParEssence(){
        if (super.categorie == Categorie.ATTAQUE && super.nom == "PanneEssence"){
            return true;
        }
        else { return false;}
    }

    public boolean estContreeParRoueDeSecours(){
        if (super.categorie == Categorie.ATTAQUE && super.nom == "Crevaison"){
            return true;
        }
        else { return false;}
    }

    public boolean estContreeParReparations(){
        if (super.categorie == Categorie.ATTAQUE && super.nom == "Accident"){
            return true;
        }
        else { return false;}
    }


    public boolean estContreeParVehiculePrioritaire(){
        if (super.categorie == Categorie.ATTAQUE && super.nom == "FeuRouge"){
            return true;
        }
        else { return false;}
    }

    public boolean estContreeParCiterne(){
        if (super.categorie == Categorie.ATTAQUE && super.nom == "PanneEssence"){
            return true;
        }
        else { return false;}
    }

    public boolean estContreeParIncrevable(){
        if (super.categorie == Categorie.ATTAQUE && super.nom == "Crevaison"){
            return true;
        }
        else { return false;}
    }

    public boolean estContreeParAsDuVolant(){
        if (super.categorie == Categorie.ATTAQUE && super.nom == "Accident"){
            return true;
        }
        else { return false;}
    }

    public abstract boolean contre(Attaque carte);

}
