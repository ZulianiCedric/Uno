package Carte;

import Partie.Partie;

/**
 * <b>CartePasserTour est la classe representant une carte PasserTour</b>
 * <p>Une carte PasserTour est caractérisé par :
 * <ul>
 * <li>Une couleur</li>
 * </ul>
 */
public class CartePasserTour implements Carte {
    /**
     * Couleur de la carte
     * @see CartePasserTour#getCouleur()
     * @see CartePasserTour#setCouleur(String)
     */
    private String couleur;

    /**
     * Constructeur de la classe CartePasserTour
     * @param couleur
     */
    public CartePasserTour(String couleur){
        this.couleur =couleur;
    }

    /**
     * Getter de la couleur de la carte
     * @return couleur
     */
    public String getCouleur() {return couleur;}

    /**
     * Setter de la couleur de la carte
     * @param couleur
     */
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    /**
     * Methode qui fait effet de la carte,
     * ici on passe au joueur suivant
     * @param partie
     */
    public void effetCarte(Partie p){
        p.getJoueurSuivant().setAJoue(true);
        p.setPasser(true);
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
      CartePasserTour other = (CartePasserTour) obj;
      if (couleur == null){
          if (other.couleur != null)
              return false;
      }else if (!couleur.equals(other.couleur))
          return false;
      return true;
    }

    @Override
     /**
     * Methode qui permet de mettre une carte en String
     * @return la carte en forme de text
     */
    public java.lang.String toString() {
        return "CartePasserTour{" +
                "couleur='" + couleur + '\'' +
                '}';
    }
}
