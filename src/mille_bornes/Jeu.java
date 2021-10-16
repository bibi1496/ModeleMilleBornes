package mille_bornes;

import mille_bornes.Sauvegarde.Sauvegarde;
import mille_bornes.cartes.Carte;

import java.io.InvalidObjectException;
import java.io.ObjectInputValidation;
import java.io.Serializable;
import java.util.Random;
import java.util.*;

public class Jeu implements Serializable, ObjectInputValidation {
    private static final long serialVersionUID = 1L;
    private List<Joueur> joueurs = new ArrayList<>();
    private TasDeCartes defausse;
    private Joueur joueurActif = null;
    private Joueur prochainJoueur;
    private TasDeCartes sabot;

    @Override
    public void validateObject() throws InvalidObjectException {
        if (this.defausse == null || this.sabot == null || joueurs == null) {
            throw new InvalidObjectException("Sauvegarde corrompus");
        }
    }

    public Jeu() {
        sabot = new TasDeCartes(false);
    }

    public Jeu(Joueur... joueurs) {
        sabot = new TasDeCartes(false);
        this.joueurs.addAll(Arrays.asList(joueurs));
    }

    public void ajouteJoueurs(Joueur... joueurs) throws IllegalStateException {
        if (joueurActif == null) {
            this.joueurs.addAll(Arrays.asList(joueurs));
        } else {
            throw new IllegalStateException(CouleurTextes.ROUGE + "La partie est déjà commencé on ne peut plus ajouter de joueurs." + CouleurTextes.NORMAL);
        }
    }

    public void prepareJeu() {
        Collections.shuffle(joueurs, new Random()); // Melange l'ordre des joueurs

        sabot.creeLesCartes();
        sabot.melangeCartes();
        defausse = new TasDeCartes(false);

        Joueur prochainJoueur = null;
        for (int y = 0; y < 6; y++) {
            for (Joueur joueur : joueurs) {
                try {
                    joueur.prendCarte(pioche());
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }

                prochainJoueur = joueur;
            }
        }

        for (Joueur joueur : joueurs) {
            joueur.setProchainJoueur(prochainJoueur);
            prochainJoueur = joueur;
        }
    }

    public void defausse(Carte carte) {
        defausse.pose(carte);
    }

    public void activeProchainJoueurEtTireCarte() {
        setProchainJoueur(prochainJoueur);
        try {
            getJoueurActif().prendCarte(pioche());
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean estPartieFinie() {
        boolean resultBoolean = false;

        if (sabot.estVide()) {
            resultBoolean = true;
        }

        for (Joueur joueur : joueurs) {
            if (joueur.getKm() >= 1000) {
                resultBoolean = true;
            }
        }

        return resultBoolean;
    }

    public void setProchainJoueur(Joueur ProchainJoueurActif) {
        if (joueurActif == null) {
            joueurActif = this.joueurs.get(0);
        } else {
            joueurActif = ProchainJoueurActif;
        }
        this.prochainJoueur = getJoueurActif().getProchainJoueur();
    }

    public Joueur getJoueurActif() {
        return joueurActif;
    }

    public List<Joueur> getGagnant() {
        List<Joueur> resultJoueurs = new ArrayList<>();
        Joueur meilleurJoueur = null;

        for (Joueur joueur : joueurs) {
            if (meilleurJoueur == null) {
                meilleurJoueur = joueur;
                resultJoueurs.add(joueur);
            }

            if (joueur.getKm() >= 1000) {
                resultJoueurs.clear();
                resultJoueurs.add(joueur);
                return resultJoueurs;
            } else if (joueur.getKm() > meilleurJoueur.getKm()) {
                resultJoueurs.clear();
                meilleurJoueur = joueur;
                resultJoueurs.add(joueur);
            } else if (joueur.getKm() == meilleurJoueur.getKm() || !joueur.equals(meilleurJoueur)) {
                meilleurJoueur = joueur;
                resultJoueurs.add(joueur);
            }
        }
        return resultJoueurs;
    }

    public boolean joue() {
        boolean resultBoolean = false;

        int nCarte = 0;
        while (!resultBoolean) {
            try {
                nCarte = getJoueurActif().choisitCarte();
                if(nCarte == -100){
                    Sauvegarde nouvelleSauvegarde = new Sauvegarde();
                    if (nouvelleSauvegarde.sauvegarde(this)){
                        System.out.println("Sauvegarde effectuée.");
                        System.exit(0);
                        return false;
                    }
                    else{
                        System.out.println("Sauvegarde impossible");
                    }
                }
                if (nCarte != 100) {
                    getJoueurActif().joueCarte(this, nCarte);
                    resultBoolean = true;
                }
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
        return resultBoolean;
    }

    public int getNbCartesSabot() {
        return this.sabot.getNbCartes();
    }

    public Carte regardeDefausse() {
        return defausse.regarde();
    }

    public Carte pioche() {
        return sabot.prend();
    }

    @Override
    public String toString() {
        String resultString = "";
        String forString = "";
        if (joueurs.isEmpty()) {
            resultString += " pas de joeurs. ";
            resultString += System.getProperty("line.separator");
        } else {
            for (Joueur joueur : joueurs) {
                forString += CouleurTextes.CYAN + joueur.nom + CouleurTextes.NORMAL + " :    ";
                forString += CouleurTextes.BLEU + joueur.getKm() + " km, ";
                if (joueur.getLimiteVitesse())
                    forString += CouleurTextes.ROUGE + "(" + CouleurTextes.BLANC + "50" + CouleurTextes.ROUGE + "), ";
                forString += CouleurTextes.JAUNE + "Bottes: " + joueur.getBottes().toString() + ", ";
                if (joueur.getBataille() != null) {
                    forString += CouleurTextes.GRIS + "Bataille: ";

                    switch (joueur.getBataille().categorie) {
                        case BORNE:
                            forString += CouleurTextes.BLEU;
                            break;
                        case PARADE:
                            forString += CouleurTextes.VERT;
                            break;
                        case BOTTE:
                            forString += CouleurTextes.JAUNE;
                            break;
                        case ATTAQUE:
                            forString += CouleurTextes.ROUGE;
                            break;
                    }// mettre les couleurs des cartes

                    forString += joueur.getBataille().toString() + "." + CouleurTextes.NORMAL;
                } else {
                    forString += CouleurTextes.GRIS + "Bataille: " + CouleurTextes.ROUGE + "Pas de carte" + ".";
                }

                forString += System.getProperty("line.separator");
                forString += CouleurTextes.NORMAL;
            }
        }
        resultString += forString;

        resultString += CouleurTextes.GRIS + "Pioche : " + getNbCartesSabot() + " cartes,    Défausse :";

        if (!defausse.estVide()) {
            switch (regardeDefausse().categorie) {
                case BORNE:
                    resultString += CouleurTextes.BLEU;
                    break;
                case PARADE:
                    resultString += CouleurTextes.VERT;
                    break;
                case BOTTE:
                    resultString += CouleurTextes.JAUNE;
                    break;
                case ATTAQUE:
                    resultString += CouleurTextes.ROUGE;
                    break;
            }// mettre les couleurs des cartes

            resultString += regardeDefausse().toString() +CouleurTextes.NORMAL;
        } else {
            resultString += "La defausse est vide !" + CouleurTextes.NORMAL;
        }

        resultString += System.getProperty("line.separator");
        return resultString;
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }
}
