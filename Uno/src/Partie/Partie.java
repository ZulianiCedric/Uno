package Partie;

import Carte.Carte;
import Fichier.Fichier;
import Joueur.Joueur;
import Parser.Parser;
import Parser.ParserCartePasser;
import Parser.ParserCartePlus2;
import Parser.ParserCarteSimple;
import PoseValidation.*;

import java.util.ArrayList;

/**
 * <b>Partie est la classe representant une partie du UNO</b>
 * <p>Une partie est caractérisé par :
 * <ul>
 * <li>Une liste de joueurs</li>
 * <li>Un joueur courant</li>
 * <li>Une liste de cartes, qui est la pioche</li>
 * <li>Une liste de cartes, qui est le tas</li>
 * <li>Un boolean qui indique si le joueur passe son tour</li>
 * <li>Un boolean qui indique si le joueur doit encaisser le cumul de +2</li>
 * <li>Un entier qui indique le cumul de +2</li> 
 */
public class Partie {

    private static Partie instance=null; // Singleton
    private Valide valider;

    // Ici enfaite on a crée un singleton car une partie est unique et qu'on ne veut pas créer plusieurs fois la même partie, pour utiliser on utilise la méthode getInstance()
    public static Partie getInstance(){ // Création de l'unique instance de la classe Partie si elle n'existe pas encore et renvoie cette instance
        if(instance==null){
            instance = new Partie();
        }
        return instance;
    }

    public static Partie FinPartie(){
        for (Joueur joueur: getInstance().getTabJoueurs()) {
            joueur.setAJoue(false);
        }
        instance = null;
        return instance;
    }

    /**
     * Liste des joueurs
     * @see Partie#getTabJoueurs()
     * @see Partie#setTabJoueurs(ArrayList)
     * @see Partie#ajouterJoueur(Joueur)
     */
    private ArrayList<Joueur> tabJoueurs;

    /**
     * Joueur actuel
     * @see Partie#getJoueurActuel()
     * @see Partie#setJoueurActuel(Joueur)
     */
    private String joueurActuel;

    /**
     * Liste des cartes, qui est la pioche
     * @see Partie#getPioche()
     * @see Partie#enleverPioche()
     */
    private ArrayList<Carte> pioche;

    /**
     * Liste des cartes, qui est le tas
     * @see Partie#getTas()
     * @see Partie#getNombreTas()
     */
    private ArrayList<Carte> tas;

    /**
     * Savoir si le joueur passe son tour
     * @see Partie#getPasser()
     * @see Partie#setPasser(boolean)
     */
    private boolean Passer;

    /**
     * Savoir si le joueur doit encaisser le cumul de +2
     * @see Partie#getEncaisser()
     * @see Partie#setEncaisser(boolean)
     */
    private boolean Encaisser;

    /**
     * Cumul de +2
     * @see Partie#getCumul()
     * @see Partie#setCumul(int)
     */
    private int Cumul;

    /**
     * Constructeur de la classe Partie
     * <p>Cette classe est privée car elle n'est pas instanciable
     * <p>Elle est donc utilisable uniquement par la méthode getInstance()
     * <p>A la construction de la partie, on initialise les attributs :
     * une liste de joueurs, un joueur actuel, une pioche, un tas, le passer,l'encaisser
     * et le cumul de +2
     */
    private Partie(){
        this.tabJoueurs = new ArrayList<Joueur>();
        this.joueurActuel = null;
        this.pioche = new ArrayList<Carte>();
        this.tas = new ArrayList<Carte>();
        this.Passer=false;
        this.Encaisser=false;
        this.Cumul=0;
        setValider();
    }

    /**
     * Getter qui permet de récupérer le nombre de joueurs
     * @return nombre de joueurs
     */
    public int getNbJoueurs() {
        return tabJoueurs.size();
    }

    /**
     * Getter qui permet de récupérer la liste des joueurs
     * @return la liste des joueurs
     */
    public ArrayList<Joueur> getTabJoueurs() {
        return tabJoueurs;
    }

    /**
     * Setter pour dire les joueurs
     * @param tabJoueurs
     */
    public void setTabJoueurs(ArrayList<Joueur> tabJoueurs) {
        this.tabJoueurs = tabJoueurs;
    }

    /**
     * Getter qui permet de récupérer le joueur actuel
     * @return le joueur actuel
     */
    public String getJoueurActuel() { 
        return joueurActuel;
    }

    /**
     * Setter pour dire le joueur actuel
     * @param joueurActuel
     */
    public void setJoueurActuel(String joueurActuel) {
        this.joueurActuel = joueurActuel;
    }

    /**
     * Getter qui permet de récupérer la liste des cartes de la pioche
     * @return la liste des cartes de la pioche
     * @see Partie#pioche
     */
    public ArrayList<Carte> getPioche() {
        return pioche;
    }

    /**
     * Getter pour dire la liste des cartes du tas
     * @return la liste des cartes du tas
     */
    public ArrayList<Carte> getTas() {
        return tas;
    }
    
    /**
     * Getter pour dire le nombre de carte du tas
     * @return le nombre de carte dans le tas
     */
    public int getNombreTas() {
    	return tas.size();
    }

    /**
     * Méthode qui ajoute un joueur à la liste des joueurs
     * @param j
     */
    public void ajouterJoueur(Joueur j){
        this.tabJoueurs.add(j);
    }

    /**
     * Methode qui permet de poser une carte dans le tas
     * @param c
     */
    public void posezTas(Carte c){
        this.tas.add(c);
    }

    /**
     * Methode qui permet d'enlever une carte de la pioche
     * @param c
     */
    public void enleverPioche(Carte c){
        this.pioche.remove(c);
    }
    
    /**
     * Getter qui permet de recuperer le joueur suivant
     * @return le joueur suivant
     */
    public Joueur getJoueurSuivant(){
    	Joueur j = null;
        int i=0;
    	for(Joueur joueur : tabJoueurs){
    		if(joueur.getNom().equals(getJoueurActuel())){
                i = tabJoueurs.indexOf(joueur);
    		}
    	}
        if (i == tabJoueurs.size()-1){
            j = tabJoueurs.get(0);
        }
        else{
            j = tabJoueurs.get(i+1);
        }
    	return j;
    }

    /**
     * Getter qui permet de verifier si correspond au la carte du tas
     * @param c
     * @return true si correspond, false sinon
     */
    public boolean getCarteTas(Carte c) {
        if(getTas().get(getNombreTas()-1).equals(c))
            return true;
        else
            return false;

    }

    /**
     * Getter qui permet de verifier si correspond au la carte de la pioche
     * @param c
     * @return true si correspond, false sinon
     */
    public boolean getCartePioche(Carte c) {
        if(getPioche().get(0).equals(c))
            return true;
        else
            return false;

    }

    /**
     * Getter qui dit si le joueur a passer
     * @return true si le joueur a passer, false sinon
     */
    public boolean getPasser() {
        return this.Passer;
    }

    /**
     * Setter pour dire si le joueur a passer
     * @param b
     */
    public void setPasser(boolean b){
        this.Passer=b;
    }

    /**
     * Getter qui dit si le joueur a encaisser
     * @return true si le joueur a encaisser, false sinon
     */
    public boolean getEncaisser(){ return this.Encaisser;}

    /**
     * Setter pour dire si le joueur a encaisser
     * @param b
     */
    public void setEncaisser(boolean b) {this.Encaisser=b;}

    /**
     * Getter qui dit quelle cumul il y a
     * @return le cumul
     */
    public int getCumul(){ return this.Cumul; }

    /**
     * Setter pour dire quelle cumul il y a
     * @param cumul
     */
    public void setCumul(int cumul) {Cumul = cumul;}

    /**
     * Methode qui permet de verifier si la carte a poser est valide
     * @param c
     * @return true si la carte est valide, false sinon
     * @throws IllegalArgumentException si le tas est vide
     */
    public boolean PoseValide(Carte c) throws Exception {
        if(getNombreTas() == 0)
            throw new IllegalArgumentException("Tas vide");
        Carte CarteTas = getTas().get(getNombreTas()-1);
        return getValider().traiter(c, CarteTas);
    }

    /**
     * Getter qui dit si c'est valide de poser la carte
     * @return true si c'est valide, false sinon
     * @see Partie#PoseValide(Carte)
     */
    public Valide getValider() {
        return valider;
    }

    /**
     * Setter qui verifie dit c'est valide de poser la carte par rapport à
     * chaque type de carte sur un autre type de carte
     */
    private void setValider(){
        this.valider = null;
        this.valider = new SimpleSurSimple(valider);
        this.valider = new SimpleSurPasser(valider);
        this.valider = new SimpleSurPlus2(valider);
        this.valider = new PasserSurPasser(valider);
        this.valider = new PasserSurSimple(valider);
        this.valider = new PasserSurPlus2(valider);
        this.valider = new PasserSurPlus2(valider);
        this.valider = new Plus2SurSimple(valider);
        this.valider = new Plus2SurPasser(valider);
        this.valider = new Plus2SurPlus2(valider);
    }

    /**
     * Methode qui permet d'initialiser la pioche de la partie grace au parser, et au fichier
     * @param File
     */
    private void initialiserPioche(String File){
        Parser premierParser=null;
        premierParser=new ParserCarteSimple(premierParser);
        premierParser=new ParserCartePlus2(premierParser);
        premierParser=new ParserCartePasser(premierParser);

        Fichier.lire(File,premierParser);
    }

    /**
     * Methode qui permet d'initialiser la partie à partir d'un fichier, et du nombre de carte qui doit etre donner a chaque joueur
     * @param nb
     * @param File
     * @throws Exception
     */
    public void initialiserPartie(int nb, String File) throws Exception{//
        initialiserPioche(File);
        for (int i = 0; i < nb; i++) {
            Partie.getInstance().tabJoueurs.get(i).setUno(false);
            for (int j = 0; j < Partie.getInstance().tabJoueurs.size(); j++) {
                Partie.getInstance().tabJoueurs.get(j).getMain().add(Partie.getInstance().getPioche().get(0));
                Partie.getInstance().getPioche().remove(0);
            }
        }
        Partie.getInstance().tas.add(Partie.getInstance().pioche.get(0));
        Partie.getInstance().pioche.remove(0);
        Partie.getInstance().joueurActuel=Partie.getInstance().tabJoueurs.get(0).getNom();
    }
}