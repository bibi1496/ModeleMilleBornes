package mille_bornes;

import mille_bornes.Bot.BotFacile;
import mille_bornes.Bot.BotDifficile;
import mille_bornes.Sauvegarde.Sauvegarde;
import mille_bornes.cartes.Carte;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static boolean estSave = false;

    // Main
    public static void main(String[] args) {
        Jeu jeu = new Jeu();
        Jeu lasauvegarde;
        List<Joueur> joueurs = new ArrayList<>();

        // Charger sauvegardes

        if ((lasauvegarde = chargerUneSauvegarde()) == null){
            // Préparation
            choixDesJoueurs(jeu,joueurs);
            jeu.prepareJeu();
        }else {
            jeu = lasauvegarde;
            estSave = true;
        }

        // La partie
        jouerLaPartie(jeu);

        // Fin de la partie
        afficheLesGagants(jeu);

        input.close();
    }

    public static Jeu chargerUneSauvegarde(){
        Jeu jeu = null;
        String chager = "";

        if (Sauvegarde.estCree()){
            System.out.println("Voulez vous charger la dernière sauvegarde ? (y/n) : ");

            while(!chager.equalsIgnoreCase("y") && !chager.equalsIgnoreCase("n")){
                chager = input.nextLine();
            }

            if (chager.equalsIgnoreCase("y")) jeu = Sauvegarde.LireSauvegarde();

            if (jeu == null) {
                Sauvegarde.detruireFichier();
                System.out.println("Sauvegarde corrompue suppression et creation d'un nouveau jeu...");
            }

        }
        return jeu;
    }

    // Choix des joueurs avant que la partie ne commence
    public static void choixDesJoueurs(Jeu jeu, List<Joueur> joueurs) {
        boolean isJoueursSet = false;
        String inputString;

        while (!isJoueursSet) {
            System.out.println("Les joueurs : ");
            afficheLesjoueurs(joueurs);
            if (joueurs.size() >= 2) {
                System.out.println("Pour commencer à jouer tapper " + CouleurTextes.JAUNE + "'start'" + CouleurTextes.NORMAL + " sinon tapper sur la touche " + CouleurTextes.JAUNE + "Entrée " + CouleurTextes.NORMAL + " pour un nouveau joueur : ");
                inputString = input.nextLine();

                if (inputString.equalsIgnoreCase("start")) {
                    isJoueursSet = true;
                }
            }

            if (!isJoueursSet) {
                System.out.println("Entrez le nom du joueur n°" + (joueurs.size()+1) + " ou entrez " + CouleurTextes.JAUNE + "'bot'" + CouleurTextes.NORMAL + " pour un nouveau bot : ");
                inputString = input.nextLine();
                if (inputString.equalsIgnoreCase("bot")) {
                    creerBot(joueurs, jeu);
                }else if (inputString.contains(" ")) {
                    System.out.println(CouleurTextes.ROUGE + "Votre pseudo ne doit pas contenir d'espace(s) !" + CouleurTextes.NORMAL);
                }else if (inputString.length() > 15) {
                    System.out.println(CouleurTextes.ROUGE + "Votre pseudo doit contenir maximums 15 caractères !" + CouleurTextes.NORMAL);
                }else if (inputString.isEmpty()) {
                    System.out.println(CouleurTextes.ROUGE + "Votre pseudo ne peut pas être vide !" + CouleurTextes.NORMAL);
                }else if (pseudoPasLibre(inputString,joueurs)){
                    System.out.println(CouleurTextes.ROUGE + "Un joueur possède déjà ce pseudo !" + CouleurTextes.NORMAL);
                }else {
                    joueurs.add(new Joueur(inputString));
                }
            }

            if (joueurs.size() > 3) {
                System.out.println(CouleurTextes.JAUNE + "Le nombre de joueurs maximum a été atteint, commencement de la partie." + CouleurTextes.NORMAL);
                isJoueursSet = true;
            }
        }
        for (Joueur joueur : joueurs) jeu.ajouteJoueurs(joueur);

    }
    public static void creerBot(List<Joueur> joueurs, Jeu jeu) {
        System.out.println("Choisissez votre difficulté " + CouleurTextes.JAUNE + "'facile', 'difficile'" + CouleurTextes.NORMAL + " :");
        String inputString = "";
        while (!inputString.equalsIgnoreCase("difficile") && !inputString.equalsIgnoreCase("facile")) {
            inputString = input.nextLine();
            if (inputString.equalsIgnoreCase("facile")) {

                // Génération du pseudo du bot
                int numeroDuBot = 1;
                while (pseudoPasLibre("BotFacile" + numeroDuBot,joueurs)){
                    numeroDuBot++;
                }

                // Ajout du bot
                joueurs.add(new BotFacile("BotFacile" + numeroDuBot,jeu));
            }else if (inputString.equalsIgnoreCase("difficile")) {

                // Génération du pseudo du bot
                int numeroDuBot = 1;
                while (pseudoPasLibre("BotDifficile" + numeroDuBot,joueurs)){
                    numeroDuBot++;
                }

                // Ajout du bot
                joueurs.add(new BotDifficile("BotDifficile" + numeroDuBot,jeu));
            }
        }
    }
    public static boolean pseudoPasLibre(String inputString, List<Joueur> joueurs) {
        for (Joueur joueur : joueurs){
            if (joueur.nom.equalsIgnoreCase(inputString)){
                return true;
            }
        }
        return false;
    }

    // Affiche les joueurs present dans la partie.
    public static void afficheLesjoueurs(List<Joueur> joueurs) {
        if (!joueurs.isEmpty()){
            for(Joueur joueur : joueurs) System.out.print(CouleurTextes.CYAN + "    " + joueur.nom);
            System.out.println();
        }else {
            System.out.println(CouleurTextes.ROUGE + "     Il n'y a pas encore de joueurs !");
        }
        System.out.println(CouleurTextes.NORMAL);
    }

    // Boucle de jeu
    public static void jouerLaPartie(Jeu jeu){
        while (!jeu.estPartieFinie()) {
            System.out.println();
            System.out.println("----------------------------------------------------------------");
            System.out.println("Etat du jeu :");
            System.out.println(jeu.toString());

            if (estSave){
                estSave = false;
            }else{
                jeu.activeProchainJoueurEtTireCarte();
            }
            if (jeu.getJoueurActif() != null) {
                System.out.println("Au joueur " + CouleurTextes.CYAN + jeu.getJoueurActif().nom + CouleurTextes.NORMAL + " de jouer !");
                System.out.println();
            }

            afficherCartesJoueur(jeu);

            jeu.joue();
        }
    }

    // Affiche les cartes de la main du joueur.
    public static void afficherCartesJoueur(Jeu jeu) {
        assert (jeu.getJoueurActif().getMain() != null);

        System.out.println("Votre main:");

        StringBuilder cartes = new StringBuilder();
        for (Carte carte : jeu.getJoueurActif().getMain()){
            switch (carte.categorie){
                case BORNE:
                    cartes.append(CouleurTextes.BLEU);
                    break;
                case PARADE:
                    cartes.append(CouleurTextes.VERT);
                    break;
                case BOTTE:
                    cartes.append(CouleurTextes.JAUNE);
                    break;
                case ATTAQUE:
                    cartes.append(CouleurTextes.ROUGE);
                    break;
            }
            cartes.append(carte.toString()).append(", ");
        }
        System.out.println(cartes);
        System.out.println(CouleurTextes.NORMAL);
    }


    // Affiche les gagnants quand la partie se termine
    public static void afficheLesGagants(Jeu jeu) {
        System.out.print(CouleurTextes.ROSE + "Bravo à " + CouleurTextes.JAUNE);

        for (Joueur gagnant : jeu.getGagnant()){
            System.out.print(gagnant.nom + ", ");
        }
        System.out.println(CouleurTextes.ROSE + "qui gagne(nt) la partie !" + CouleurTextes.NORMAL);

    }
}