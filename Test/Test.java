
import static org.junit.Assert.*;
import mille_bornes.*;
import mille_bornes.Bot.BotFacile;

import java.util.List;

public class Test {

    @org.junit.Test
    public void TestAll(){
        Joueur bibi = new Joueur("Bibi");
        Joueur gaetan = new Joueur("Gaetan");

        assertNotSame(gaetan,bibi);
        assertNotNull(gaetan);
        assertNotNull(bibi);
        gaetan.setProchainJoueur(bibi);
        assertEquals(bibi,gaetan.getProchainJoueur());
        assertEquals(gaetan.getMain().size(),0);
    }

    @org.junit.Test
    public void TestListJoueursJeu(){
        Joueur bibi = new Joueur("Bibi");
        Joueur gaetan = new Joueur("Gaetan");

        Jeu jeu = new Jeu(bibi,gaetan);

        List<Joueur> joueurs = jeu.getJoueurs();

        assertFalse(joueurs.isEmpty());
        assertEquals(joueurs.size(), 2);
        assertTrue(joueurs.contains(bibi));
        assertTrue(joueurs.contains(gaetan));
    }

    @org.junit.Test
    public void TestJoueur(){
        Jeu jeu;
        jeu = new Jeu();
        jeu.ajouteJoueurs(new BotFacile("botFacile1",jeu),new BotFacile("botFacile2",jeu));
        jeu.prepareJeu();
        assertNotNull(jeu.getJoueurs().get(0).getProchainJoueur());
        assertNotNull(jeu.getJoueurs().get(0).getMain());
        assertEquals(0, jeu.getJoueurs().get(0).getKm());
        assertFalse(jeu.getJoueurs().get(0).getLimiteVitesse());
        assertNull(jeu.getJoueurs().get(0).getBataille());
        assertNotNull(jeu.getJoueurs().get(0).getBottes());
        jeu.activeProchainJoueurEtTireCarte();
        jeu.joue();
        assertNotNull(jeu.getJoueurs().get(0).getBataille());
    }
}












