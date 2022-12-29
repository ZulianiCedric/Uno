package Carte;

import Partie.Partie;

/**
 * <b>CartePlus2 est la classe representant une carte Plus2</b>
 * <p>Une carte Plus2 est caractérisé par :
 * <ul>
 * <li>Une couleur</li>
 * </ul>
 */
public class CartePlus2 implements Carte {
    /**
     * Couleur de la carte
     * @see CartePlus2#getCouleur()
     * @see CartePlus2#setCouleur(String)
     */
    private String couleur;

    /**
     * Constructeur de la classe CartePlus2
     * @param couleur
     */
    public CartePlus2(String couleur) {
        this.couleur = couleur;
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
     * ici on ajoute 2 cartes à piocher au cumul et si il n'y a plus de +2 le joueur suivant prend le cumul
     * @param partie
     */
    public void effetCarte(Partie p){
        p.setCumul(p.getCumul()+2);
        p.setEncaisser(true);
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
        CartePlus2 other = (CartePlus2) obj;
        if (couleur == null) {
            if (other.couleur != null)
                return false;
        } else if (!couleur.equals(other.couleur))
            return false;
        return true;
    }

    @Override
     /**
     * Methode qui permet de mettre une carte en String
     * @return la carte en forme de text
     */
    public String toString() {
        return "CartePlus2 [couleur=" + couleur + "]";
    }
}
