
/*

    Ce code permet de tester un échantillons des parties possible entre les bots
    en faisant 1000 parties pour chaque situation et verifier que le programme ne
    plante pas.

*/


import mille_bornes.Bot.BotDifficile;
import mille_bornes.Bot.BotFacile;
import mille_bornes.Jeu;
import mille_bornes.Main;

public class TestMain {
    public static void main(String[] args) {
        Jeu jeu;

        System.out.println("Facile / Facile");
        // Facile / Facile
        for (int i = 0; i < 200; i++){
            jeu = new Jeu();
            jeu.ajouteJoueurs(new BotFacile("botFacile1.1",jeu),new BotFacile("botFacile1.2",jeu));
            jeu.prepareJeu();
            Main.jouerLaPartie(jeu);
            Main.afficheLesGagants(jeu);
        }

        System.out.println("Difficile / Difficile");
        // Difficile / Difficile
        for (int i = 0; i < 200; i++){
            jeu = new Jeu();
            jeu.ajouteJoueurs(new BotDifficile("botDifficile2.1",jeu),new BotDifficile("botDifficile2.2",jeu));
            jeu.prepareJeu();
            Main.jouerLaPartie(jeu);
            Main.afficheLesGagants(jeu);
        }

        System.out.println("Facile / Difficile");
        // Facile / Difficile
        for (int i = 0; i < 200; i++){
            jeu = new Jeu();
            jeu.ajouteJoueurs(new BotFacile("botFacile3.1",jeu),new BotDifficile("botDifficile3.1",jeu));
            jeu.prepareJeu();
            Main.jouerLaPartie(jeu);
            Main.afficheLesGagants(jeu);
        }
    }
}
