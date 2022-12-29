package Carte;

import Partie.Partie;

/**
 * <b>CarteSimple est la classe representant une carte simple</b>
 * <p>Une carte simple est caractérisé par :
 * <ul>
 * <li>Une valeur</li>
 * <li>Une couleur</li>
 * </ul>
 */
public class CarteSimple implements Carte {
    /**
     * Valeur de la carte
     * @see CarteSimple#getValeur()
     * @see CarteSimple#setValeur(int)
     */
    private String valeur;

    /**
     * Couleur de la carte
     * @see CarteSimple#getCouleur()
     * @see CarteSimple#setCouleur(String)
     */
    private String couleur;
    
    /**
     * Creer la carte 0 Rouge
     */
    public CarteSimple(){
        this.valeur="0";
        this.couleur="Rouge";
    }

    /**
     * Constructeur par copie de la classe CarteSimple
     * @param c
     */
    public CarteSimple(CarteSimple c){
        this.couleur=c.couleur;
        this.valeur=c.valeur;
    }

    /**
     * Constructeur de la classe CarteSimple
     * @param nom
     * @param couleur
     */
    public CarteSimple(String nom, String couleur) {
        setCouleur(couleur);
        setValeur(nom);
    }

    /**
     * Getter de la valeur de la carte
     * @return valeur
     */
    public String getValeur() {
        return valeur;
    }

    /**
     * Setter de la valeur de la carte
     * @param nom
     */
    public void setValeur(String nom) {
        this.valeur = nom;
    }

    /**
     * Getter de la couleur de la carte
     * @return couleur
     */
    public String getCouleur() {
        return couleur;
    }

    /**
     * Setter de la couleur de la carte
     * @param couleur
     */
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    /**
     * Methode qui fait effet de la carte,
     * ici on ne fait rien car c'est une carte simple
     * @param p
     * @see Carte#effetCarte(Partie)
     */
    public void effetCarte(Partie p){
    }

    @Override
    /**
     * Methode qui permet de mettre une carte en String
     * @return la carte en forme de text
     */
    public String toString() {
        return "CarteSimple [couleur=" + couleur + ", valeur=" + valeur + "]";
    }

    @Override
    /**
     * Methode qui permet de comparer 2 cartes
     * @param obj
     * @return true si les cartes sont egales, false sinon
     */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CarteSimple other = (CarteSimple) obj;
        if (couleur == null) {
            if (other.couleur != null)
                return false;
        } else if (!couleur.equals(other.couleur))
            return false;
        if (!valeur.equals(other.valeur))
            return false;
        return true;
    }
    
}