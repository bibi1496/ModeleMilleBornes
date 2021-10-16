package mille_bornes;

import java.io.Serializable;
import java.util.*;

import mille_bornes.cartes.*;

public class Joueur implements Serializable {
    private static final long serialVersionUID = 1L;
    public final String nom;
    protected final EtatJoueur etat;
    private transient Scanner input;
    private Joueur prochainJoueur;

    public Joueur(String nom){
        this.nom = nom;
        this.etat = new EtatJoueur(this);
        this.input = new Scanner(System.in);
    }

    public Joueur getProchainJoueur(){
        return prochainJoueur;
    }

    public void setProchainJoueur(Joueur joueur){
        this.prochainJoueur = joueur;
    }

    public List<Carte> getMain(){
        return etat.getMain();
    }

    public int getKm(){
        return etat.getKm();
    }

    public boolean getLimiteVitesse(){
        return etat.getLimiteVitesse();
    }

    public int choisitCarte(){
        int resultInt = 100;
        while(resultInt == 0 || 7 < resultInt || -7 > resultInt) {
            try {
                System.out.println("Choisissez une carte entre -7 et 7 (tapper "+CouleurTextes.JAUNE+"'sauvegarde'"+CouleurTextes.NORMAL+" pour sauvegarder la partie) : ");
                if(input == null){
                    this.input = new Scanner(System.in);
                }
                String nombre = input.nextLine();
                if(nombre.equalsIgnoreCase("sauvegarde")){
                    return -100;
                } else {
                    resultInt = Integer.parseInt(nombre);
                }
            } catch (NoSuchElementException | NumberFormatException e) {
                System.out.println("Impossible de prendre cette carte !");
            }
        }
        return resultInt;
    }

    public Joueur choisitAdversaire(Carte carte) throws IllegalStateException {
        // récupérer tous les joueurs du jeu dans la variable joueurs
        List<Joueur> joueurs = new ArrayList<>();

        joueurs.add(this);

        Joueur leJoueur = prochainJoueur;

        joueurs.add(leJoueur);

        while (!leJoueur.prochainJoueur.nom.equals(this.nom)){
            leJoueur = leJoueur.prochainJoueur;
            joueurs.add(leJoueur);
        }

        // Scanner
        Joueur resultJoueur = null;

        System.out.println("Liste des joueurs : ");
        for (Joueur joueur : joueurs) {
            System.out.print(CouleurTextes.CYAN + "     " + joueur.nom);
        }
        System.out.println(CouleurTextes.NORMAL);

        while (resultJoueur == null) {
            System.out.println("Choisissez l'adversaire que vous voulez attaquer : ");
            String nom = input.nextLine();

            for (Joueur joueur : joueurs) {
                if (joueur.nom.equalsIgnoreCase(nom) && !joueur.nom.equalsIgnoreCase(this.nom)) {
                    resultJoueur = joueur;
                }else if(this.nom.equalsIgnoreCase(nom) && this.nom.equalsIgnoreCase(joueur.nom)){
                    System.out.println(CouleurTextes.ROUGE + "Vous ne pouvez pas vous attaquer vous même !" + CouleurTextes.NORMAL);
                }
            }
        }

        if (!confirmation("Voulez vous vraiment attaquer " + resultJoueur.nom + " ?")){
            throw new IllegalStateException(CouleurTextes.ROUGE + "Le joueur a annulé son attaque !" + CouleurTextes.NORMAL);
        }

        return resultJoueur;
    }

    public void prendCarte(Carte carte) throws IllegalStateException {
        etat.prendCarte(carte);
    }

    public void joueCarte(Jeu jeu, int numero) throws IllegalStateException {
        if (numero > 0){
            etat.joueCarte(jeu,numero-1);
        }else{
            numero = -numero;
            defausseCarte(jeu,numero-1);
        }
    }

    public void defausseCarte(Jeu jeu, int numero){
        etat.defausseCarte(jeu,numero);
        System.out.println("la carte est mise à la defause.");
    }

    public void attaque(Jeu jeu, Attaque carte) throws IllegalStateException {
        this.etat.attaque(jeu,carte);
    }

    public void joueCarte(Jeu jeu, int numero, Joueur adversaire) throws IllegalStateException {
        etat.joueCarte(jeu, numero, adversaire);
    }

    public Bataille getBataille(){
        return this.etat.getBataille();
    }

    public String ditPourquoiPeutPasAvancer(){
        return etat.ditPourquoiPeutPasAvancer();
    }

    public String toString(){
        return "Nom : " + this.nom + " " + this.etat.toString();
    }

    public List<Botte> getBottes(){
        return this.etat.getBottes();
    }

    public EtatJoueur getEtat() {
        return etat;
    }

    public boolean confirmation(String message){ // confirmer une action dans le jeu (méthode rajouté pour simplifier le code)
        boolean confirmation = false;

        System.out.println(message + " (y/n) : ");
        String annuler = "";

        while(!annuler.equalsIgnoreCase("y") && !annuler.equalsIgnoreCase("n")){
            annuler = input.nextLine();
        }

        if (annuler.equalsIgnoreCase("y")) confirmation = true;

        return confirmation;
    }
}
