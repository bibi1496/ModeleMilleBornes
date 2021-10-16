package mille_bornes.cartes;

public enum Categorie {
    ATTAQUE("attaque"),
    BORNE(10),
    BOTTE("botte"),
    PARADE("parade");

    private Categorie(String name){
    }

    private Categorie(int km){
    }
}
