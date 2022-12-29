package Joueur;

import Carte.*;
import Exceptions.*;
import Partie.Partie;

import java.util.ArrayList;

/**
 * <b>Joueur est la classe representant un joueur du UNO</b>
 * <p>Un joueur est caractérisé par :
 * <ul>
 * <li>Un nom de joueur</li>
 * <li>Si le joueur a dit UNO</li>
 * <li>Une main du joueur</li>
 * <li>Un booleen qui indique si le joueur a deja jouee</li>
 * </ul>
 * 
 * 
 * @author Zuliani, Henissart
 * @version 1.0
 */
public class Joueur {
    /**
     * Nom du joueur
     * @see Joueur#getNom()
     * @see Joueur#setNom(String)
     */
    private String nom;

    /**
     * Savoir si le joueur a dit UNO
     * @see Joueur#getUno()
     * @see Joueur#setUno(boolean)
     */
    private boolean uno;

    /**
     * Liste des cartes du joueur
     * @see Joueur#getMain()
     * @see Joueur#getNombreCarteMain()
     */
    private ArrayList<Carte> main =  new ArrayList<>();

    /**
     * Savoir si le joueur a deja joue
     */
    private boolean AJoue;

    /**
     * Constructeur de la classe Joueur
     * <p>
     * A la construction d'un joueur, il est initialisé avec un nom et une main vide et il n'a pas encore dit UNO ou joue
     * @param nom Nom du joueur
     * @see Joueur#nom
     */
    public Joueur(String nom){
        setNom(nom);    
        setUno(false);
        setAJoue(false);
    }
    
    /**
     * Setter du nom du joueur
     * @param nom Nom du joueur
     * @throws IllegalArgumentException Si le nom est null ou vide
     */
    private void setNom(String nom) {
        if (nom==null || nom.trim().equals(""))
            throw new IllegalArgumentException("Le nom d'un joueur ne peut pas être vide");
        this.nom = nom;
    }

    /**
     * Getter du nom du joueur
     * @return Nom du joueur
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * Getter pour savoir si le joueur a dit UNO
     * @return true si le joueur a dit UNO, false sinon
     */
    public boolean getUno(){
        return this.uno;
    }

    /**
     * Setter pour dire que le joueur a dit UNO
     * @param uno 
     */
    public void setUno(boolean uno){
        this.uno = uno;
    }

    /**
     * Getter pour savoir si le joueur a deja joue
     * @return true si le joueur a deja joue, false sinon
     */
    public boolean getAJoue(){
        return this.AJoue;
    }

    /**
     * Setter pour dire que le joueur a joue
     * @param AJoue
     */
    public void setAJoue(boolean AJoue){
        this.AJoue = AJoue;
    }

    /**
     * Getter pour le nombre de carte que le joueur a dans sa main
     * @return Nombre de carte dans la main du joueur
     */
    public int getNombreCarteMain(){
        return this.main.size();
    }
    
    /**
     * Getter pour les cartes contenues dans la main du joueur
     * @return La main du joueur
     */
    public ArrayList<Carte> getMain(){
    	return this.main;
    }
    
    /**
     * Verifie si la carte passee en parametre est dans la main du joueur
     * @param c
     * @return true si la carte est dans la main du joueur, false sinon
     * @throws MauvaisNumeroException Si le tas est vide 
     */
    public boolean PresenceCarteDansMain(Carte c) throws Exception{
    	boolean present=false;
    	int i=0;
    	while(!present&& i<getNombreCarteMain()) {
            if(getNombreCarteMain() == 0)
                throw new MauvaisNombreCarteException("Tas vide");
            Carte CarteI = getMain().get(i);
            present = Partie.getInstance().getValider().traiter(c, CarteI);
    		i++;
    	}
    	return present;
    }

    /**
     * Pose la carte passee en parametre sur le tas de jeu
     * @param c
     * @throws IllegalArgumentException Si la carte est null
     * @throws CoupIllegaux Si le joueur a deja joue
     * @throws MauvaiseCarteException Si le joueur essaye de mettre autre chose qu'un plus 2, si il ya deja un plus 2
     * @throws CoupIllegaux Si la carte n'est valide regle parlant
     * @throws CoupIllegaux Si ce n'est son tour
     */
    public void poserCarte(Carte c) throws Exception{
        if (c==null)
            throw new IllegalArgumentException("La carte ne peut pas etre null");
        if(getAJoue())
            throw new CoupIllegaux(getNom()+"a deja joue donc punition");

        if(getNom().equals(Partie.getInstance().getJoueurActuel())){
            if(Partie.getInstance().getEncaisser()){
                if(!(c instanceof CartePlus2))
                    throw new MauvaiseCarteException("La Carte n'est pas un Plus 2 ou tu dois encaisser");
            }

            if(Partie.getInstance().PoseValide(c)) {
                c.effetCarte(Partie.getInstance());
                getMain().remove(c);
                Partie.getInstance().posezTas(c);
                setAJoue(true);
            }
            else
                throw new CoupIllegaux("Cette carte n'est pas valide");
        }
        else
            throw new CoupIllegaux("Ce n'est pas le tour de "+getNom());
    }

    /**
     * Ajoute la carte du haut de la pioche a la main du joueur
     * @throws JeuxUnoException Si la pioche est vide
     * @throws CoupIllegaux Si le joueur a deja joue
     * @throws CoupIllegaux Si le joueur dois encaisser ou mettre un plus 2
     * 
     * @see Partie#getPioche()
     * @see Joueur#Encaisser()
     */
    public void piocher() throws Exception{
        if(Partie.getInstance().getPioche().isEmpty())
            throw new JeuxUnoException("La pioche est vide");
        if(getAJoue())
            throw new CoupIllegaux(getNom()+" a deja joue");
        if(Partie.getInstance().getEncaisser())
            throw new CoupIllegaux("Tu dois encaisser ou jouer une carte Plus 2");
        else{
            this.main.add(Partie.getInstance().getPioche().get(0));
            if(getUno())
                setUno(false);
            Partie.getInstance().getPioche().remove(0);
            setAJoue(true);
        }  
    }

    /**
     * Fait la fin du tour du joueur
     * 
     * @throws UnoException Si le joueur a oublie de dire UNO
     */
    public void FinTour() throws Exception{
        if(getNom().equals(Partie.getInstance().getJoueurActuel()) && getAJoue()){
            if(!getUno() && getMain().size()==1)
                throw new UnoException("Uno oublie pour "+ getNom());
            setAJoue(false);
            Joueur JSuivant=Partie.getInstance().getJoueurSuivant();
            Partie.getInstance().setJoueurActuel(Partie.getInstance().getJoueurSuivant().getNom());
            if(Partie.getInstance().getPasser()){
                Partie.getInstance().setPasser(false);
                JSuivant.FinTour();
            }
        }
        else throw new CoupIllegaux(getNom()+" n'a pas joue");
    }
    
    /**
     * Permet de dire UNO
     * @throws UnoException Si le joueur dit Uno au mauvais moment
     */
    public void Uno() throws UnoException{
        if(getNom().equals(Partie.getInstance().getJoueurActuel()) && getMain().size()==1)
            setUno(true);
        else throw new UnoException("Erreur dans l'utilisation de l'UNO");
    }
    
    /**
     * Permet de punir le joueur
     * @throws JeuxUnoException Si la pioche est vide
     */
    public void punition() throws Exception{
        if(Partie.getInstance().getPioche().isEmpty())
            throw new JeuxUnoException("La pioche est vide");
        else{
            if(getNom().equals(Partie.getInstance().getJoueurActuel())){
                if(!getUno() && getMain().size()==1){
                    this.main.add(Partie.getInstance().getTas().get(Partie.getInstance().getNombreTas()-1));
                    Partie.getInstance().getTas().remove(Partie.getInstance().getNombreTas()-1);
                }
                setAJoue(false);
                Partie.getInstance().setJoueurActuel(Partie.getInstance().getJoueurSuivant().getNom());
            }
            for(int i =0 ; i<2 ;i++) {
                this.main.add(Partie.getInstance().getPioche().get(0));
                Partie.getInstance().getPioche().remove(0);
            }

        }  
    }

    /**
     * Permet de faire piocher le joueur du cumul de +2
     * @throws JeuxUnoException Si la pioche est vide
     */
    public void Encaisser() throws Exception{
        if(Partie.getInstance().getPioche().isEmpty())
            throw new JeuxUnoException("La pioche est vide");
        else {
            for (int i = 0; i < Partie.getInstance().getCumul(); i++) {
                this.main.add(Partie.getInstance().getPioche().get(0));
                Partie.getInstance().getPioche().remove(0);
            }
            setAJoue(true);
            setUno(false);
            Partie.getInstance().setEncaisser(false);
            Partie.getInstance().setCumul(0);
            FinTour();
        }
    }
}