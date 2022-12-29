package PoseValidation;

import Carte.Carte;
import Carte.CartePlus2;
import Carte.CarteSimple;

/**
 * <b>Plus2SurSimple est la classe representant une pose valide</b>
 */
public class Plus2SurSimple extends Valide{


    public Plus2SurSimple(Valide suivant) {
        super(suivant);
    }

    @Override
    public boolean Test(Carte carte, Carte carteTas) throws Exception {
        CarteSimple carte1 = (CarteSimple) carteTas;
        CartePlus2 carte2 = (CartePlus2) carte;
        return carte1.getCouleur().equals(carte2.getCouleur());
    }

    @Override
    public boolean saitTester(Carte carte, Carte carteTas) {
        if(carte instanceof CartePlus2 && carteTas instanceof CarteSimple)
        {
            return true;
        }
        return false;
    }
}