package mille_bornes;

import mille_bornes.cartes.*;

import java.io.Serializable;
import java.util.*;

public class EtatJoueur implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Botte> bottes = new ArrayList<>();
    private Joueur joueur;
    private int km;
    private boolean limiteVitesse;
    private List<Carte> main = new ArrayList<>();
    private Stack<Bataille> pileBataille = new Stack<>();

    public EtatJoueur(Joueur joueur){
        this.joueur = joueur;
    }

    public int getKm(){
        return this.km;
    }

    public void ajouteKm(int km) throws IllegalStateException{
        this.km = this.km + km;
    }

    public boolean getLimiteVitesse(){
        return this.limiteVitesse;
    }

    public String ditPourquoiPeutPasAvancer(){
        String str = "";
        return str;

    }

    public void setLimiteVitesse(boolean limiteVitesse){
        this.limiteVitesse = limiteVitesse;
    }

    public Bataille getBataille(){
        if (pileBataille.empty()){
            return null;
        }
        else{
            return pileBataille.peek();
        }
    }

    public void setBataille(Bataille carte){
        getMain().remove(carte);
        pileBataille.push(carte);
    }

    public void defausseBataille(Jeu jeu){
        pileBataille.pop();
        jeu.defausse(pileBataille.pop());
    }

    public List<Carte> getMain(){
        return main;
    }

    public void addBotte(Botte carte){
        bottes.add(carte);
    }

    public void attaque(Jeu jeu, Attaque carte) throws IllegalStateException{
        for (Botte botte : getBottes()) {
            if (botte.contre(carte)){
                throw new IllegalStateException(CouleurTextes.ROUGE + "Une des bottes de l'adversaire bloque l'attaque !" + CouleurTextes.NORMAL);
            }
        }

        carte.appliqueEffet(jeu,this);
    }

    public String toString(){
        String output = "Distance parcourue : " + this.km + ". Limite de vitesse : " + this.limiteVitesse + ". Bottes :";
        for (Botte botte: bottes) {
            output += botte.toString() + "  ";
        }
        output += ".";
        output += pileBataille.toString() +" . ";
        return output;
    }

    public void prendCarte(Carte carte) throws IllegalStateException{
        if(getMain().size() <= 6){
            main.add(carte);
        }else{
            throw new IllegalStateException(CouleurTextes.ROUGE + "Le joueur " + joueur.nom + " a déjà trop de carte (" + main.size() + ") !" + CouleurTextes.NORMAL);
        }
    }

    public void defausseCarte(Jeu jeu, int numero){
        Carte carte = main.get(numero);

        jeu.defausse(carte);
        main.remove(carte);
    }

    public void joueCarte(Jeu jeu, int numero) throws IllegalStateException{
        Carte carte = getMain().get(numero);

        switch (carte.categorie) {
            case ATTAQUE:
                joueCarte(jeu, numero, joueur.choisitAdversaire(carte));
                break;
            case BOTTE:
            case PARADE:
            case BORNE:
                carte.appliqueEffet(jeu,this);
                break;
            default:
                break;
        }
        System.out.println("La carte a bien été joué.");
    }

    public void joueCarte(Jeu jeu, int numero, Joueur adversaire) throws IllegalStateException{
        Carte carte = getMain().get(numero);
        switch (carte.categorie) {
            case ATTAQUE:
                adversaire.attaque(jeu, (Attaque) carte);
                break;
            case BOTTE:
            case PARADE:
            case BORNE:
                joueCarte(jeu,numero);
                break;
            default:
                break;
        }
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public List<Botte> getBottes(){
        return this.bottes;
    }
}
