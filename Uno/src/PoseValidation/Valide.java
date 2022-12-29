package PoseValidation;

import Carte.Carte;

/**
 * <b>Valide est la classe representant une pose valide</b>
 * <p>Une pose valide est caractérisé par :
 * <ul>
 * <li>Une carte</li>
 * </ul>
 */
public abstract class Valide{

    /**
     * Carte de la pose valide
     * @see Valide#getSuivant()
     * @see Valide#aUnSuivant()
     * @see Valide#Valide(Valide)
     * @see Valide#traiter(Carte, Carte)
     */
    private Valide suivant = null;

    /**
     * Constructeur de la classe Valide
     * @param suivant
     */
    public Valide(Valide suivant) {
        this.suivant = suivant;
    }

    /**
     * Methode qui permet de traiter une carte qu'on pose et la carte du haut du tas
     * @param carte
     * @param carteTas
     * @return true si la pose est valide, false sinon
     */
    public boolean traiter(Carte carte, Carte carteTas) throws Exception {
        if(saitTester(carte, carteTas))
        {
            return Test(carte, carteTas);
        }
        else if(aUnSuivant())
        {
            return getSuivant().traiter(carte, carteTas);
        }
            return false;
    }

    /**
     * Methode qui permet de savoir si la carte suivante est valide
     * @return true si la carte suivante est null, false sinon
     */
    private boolean aUnSuivant()
    {
        return suivant != null;
    }

    /**
     * Getter de la carte suivante
     * @return suivant
     */
    public Valide getSuivant() {
        return suivant;
    }

    /**
     * Methode abstraite
     */
    public abstract boolean Test(Carte carte, Carte carteTas) throws Exception;
    
    /**
     * Methode abstraite
     */
    public abstract boolean saitTester(Carte carte, Carte CarteTas);
}
