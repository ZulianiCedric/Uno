package PoseValidation;

import Carte.Carte;
import Carte.CarteSimple;

/**
 * <b>SimpleSurSimple est la classe representant une pose valide</b>
 */
public class SimpleSurSimple extends Valide{


    public SimpleSurSimple(Valide suivant) {
        super(suivant);
    }

    @Override
    public boolean Test(Carte carte, Carte carteTas) {
        CarteSimple carte1 = (CarteSimple) carte;
        CarteSimple carte2 = (CarteSimple) carteTas;
        return carte1.getCouleur().equals(carte2.getCouleur()) || carte1.getValeur().equals(carte2.getValeur());

    }

    @Override
    public boolean saitTester(Carte carte, Carte carteTas) {
        if(carte instanceof CarteSimple && carteTas instanceof CarteSimple)
        {
            return true;
        }
        return false;
    }
}